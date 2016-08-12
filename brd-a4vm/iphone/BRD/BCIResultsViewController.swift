//
//  BCIResultsViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/14/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

enum Drugs {
    case Drug1
    case Drug2
    case None
}

class BCIResultsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    let source = BCIDataSource.sharedInstance
    @IBOutlet weak var resultsTextView: UITextView!
    let doubleFormat = ".2"
    
    
    let labelsDictionary = ["drugname":"",
                       "tfp":"Tx Failure Percent",
                       "cfr":"Case Fatality Rate",
                       "cot":"Cost of Treatment",
                       "chronic":"Chronic Percentage"]
    var drug1Dictionary: [String:String] = [:]
    var drug2Dictionary: [String:String] = [:]
    var keys: [String] = ["drugname","tfp","cfr","cot","chronic"]

    @IBOutlet weak var differentialTextView: UITextView!
    @IBOutlet weak var drug1TableView: UITableView!
    @IBOutlet weak var drug2TableView: UITableView!
    @IBOutlet weak var labelsTableView: UITableView!
    let drug1CellIdentifier = "drug1"
    let drug2CellIdentifier = "drug2"
    let labelsCellIdentifier = "labels"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        prepDictionary()
        
        // Use the formulas provided
        source.calculateEverything(test: false)
        
        let differenceInReturnString = abs(source.differenceInReturnToOwnership).format(doubleFormat) // Remove (-) sign for results string
        let differenceInReturn = source.differenceInReturnToOwnership // Save for the sign check below
        
        var bestDrugName = ""
        var otherDrugName = ""
        var resultString = ""
        
        // If the number is negative, drug 1 is better
        if differenceInReturn.isSignMinus {
            bestDrugName = source.drug1Name
            otherDrugName = source.drug2Name
        } else {
            bestDrugName = source.drug2Name
            otherDrugName = source.drug1Name
        }
        
        // The text displayed at the results screen
        resultString = "\(bestDrugName) shows $\(differenceInReturnString)/head advantage over \(otherDrugName) using the information provided. This calculator does not provide a recommendation for antimicrobial selection. Please consult your veterinarian to make the most appropriate decision for your operation."
        resultsTextView.text = resultString
                
    }

    override func viewDidDisappear(animated: Bool) {
        // Reset every calculation upon exit
        source.reset()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func resetButtonAction(sender: AnyObject) {
        // Reset previously saved values
        let appDomain = NSBundle.mainBundle().bundleIdentifier!
        NSUserDefaults.standardUserDefaults().removePersistentDomainForName(appDomain)

        
        self.navigationController?.popToRootViewControllerAnimated(true)
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return keys.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        var cell:UITableViewCell?
        let key = keys[indexPath.row]
        if (tableView == self.labelsTableView) {
            cell = self.labelsTableView.dequeueReusableCellWithIdentifier(labelsCellIdentifier)
            cell?.textLabel?.text = labelsDictionary[key]
        } else if (tableView == self.drug1TableView) {
            cell = self.drug1TableView.dequeueReusableCellWithIdentifier(drug1CellIdentifier)
            cell?.textLabel?.text = drug1Dictionary[key]
            if (indexPath.row > 0 && betterDrugAtIndex(key) == Drugs.Drug1) {
                cell?.backgroundColor = .greenColor()
            }
        } else {
            cell = self.drug2TableView.dequeueReusableCellWithIdentifier(drug2CellIdentifier)
            cell?.textLabel?.text = drug2Dictionary[key]
            if (indexPath.row > 0 && betterDrugAtIndex(key) == Drugs.Drug2) {
                cell?.backgroundColor = .greenColor()
            }
        }
        cell?.selectionStyle = .None
        
        return cell ?? UITableViewCell()
    }
    
    func betterDrugAtIndex(key: String) -> Drugs {
        let drug1 = drug1Dictionary[key]
        let drug2 = drug2Dictionary[key]

        if (key == "drugname" || drug1 == nil || drug2 == nil) {
            return Drugs.None
        } else if (Double(drug1!) < Double(drug2!)) {
            return Drugs.Drug1
        }
        return Drugs.Drug2
    }
    
    func prepDictionary() {
        drug1Dictionary = ["drugname":source.drug1Name,
                      "tfp":String(source.tfpa.format(doubleFormat)),
                      "cfr":String(source.cfra.format(doubleFormat)),
                      "cot":String(source.cta1.format(doubleFormat)),
                      "chronic":String(source.cpa.format(doubleFormat))]
        drug2Dictionary = ["drugname":source.drug2Name,
                      "tfp":String(source.tfpb.format(doubleFormat)),
                      "cfr":String(source.cfrb.format(doubleFormat)),
                      "cot":String(source.ctb1.format(doubleFormat)),
                      "chronic":String(source.cpb.format(doubleFormat))]
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
