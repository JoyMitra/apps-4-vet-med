//
//  BCIDrug1ViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIDrug1ViewController: UIViewController {

    @IBOutlet var drugNameField: UITextField!
    @IBOutlet var tpfField: UITextField!
    @IBOutlet var cfrField: UITextField!
    @IBOutlet var costOfTreamentField: UITextField!
    @IBOutlet var chronicPercentageField: UITextField!
    var drugName: String!
    var tpf: String!
    var cfr: String!
    var cot: String!
    var chronic: String!
    let source = BCIDataSource.sharedInstance
    let kDrug1Parameters = "drug1Parameters"
    let drug2Segue = "goToDrug2"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.loadLocalValues()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func nextStepAction() {
        drugName = drugNameField.text!
        tpf = tpfField.text!
        cfr = cfrField.text!
        cot = costOfTreamentField.text!
        chronic = chronicPercentageField.text!
        
        if drugName.isBlank || tpf.isBlank || cfr.isBlank || cot.isBlank || chronic.isBlank {
            let alertView = UIAlertController(title: "Error", message: "emptyFields".localized, preferredStyle: .Alert)
            alertView.addAction(UIAlertAction(title: "Okay", style: .Default, handler: nil))
            self.presentViewController(alertView, animated: true, completion: nil)
        } else {
            returnValues()
            self.performSegueWithIdentifier(drug2Segue, sender: self)
        }
    }
    
    func loadLocalValues() {
        let userDataStore = NSUserDefaults.standardUserDefaults()
        let drug1Dictionary = userDataStore.objectForKey(kDrug1Parameters)
        if let dict = drug1Dictionary as? [String:String!] {
            drugNameField.text = dict["drug1Name"]!
            tpfField.text = dict["tpfa"]!
            cfrField.text = dict["cfra"]!
            costOfTreamentField.text = dict["cta1"]!
            chronicPercentageField.text = dict["cpa"]!
        }
        
    }
    
    func returnValues() {
        source.drug1Name = drugName
        source.tfpa = Double(tpf)
        source.cfra = Double(cfr)
        source.cta1 = Double(cot)
        source.cpa = Double(chronic)
        let drug1Dictionary = ["drug1Name": drugName,
                                    "tpfa": tpf,
                                    "cfra": cfr,
                                    "cta1": cot,
                                    "cpa": chronic]
        source.drug1Dictionary = drug1Dictionary
        let userDataStore = NSUserDefaults.standardUserDefaults()
        userDataStore.setObject(drug1Dictionary, forKey: kDrug1Parameters)
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
