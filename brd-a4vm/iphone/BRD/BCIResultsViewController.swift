//
//  BCIResultsViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/14/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIResultsViewController: UIViewController {
    
    let source = BCIDataSource.sharedInstance
    @IBOutlet weak var resultsTextView: UITextView!
    let doubleFormat = ".2"

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Use the formulas provided
        source.calculateEverything()
        
        let differenceInReturnString = abs(source.differenceInReturnToOwnership).format(doubleFormat)
        let differenceInReturn = source.differenceInReturnToOwnership
        
        var bestDrugName = ""
        var otherDrugName = ""
        var resultString = ""
        
        if differenceInReturn.isSignMinus {
            bestDrugName = source.drug1Name
            otherDrugName = source.drug2Name
        } else {
            bestDrugName = source.drug2Name
            otherDrugName = source.drug1Name
        }
        resultString = "Drug " + bestDrugName + " shows $" + differenceInReturnString + "/head advantage over Drug " + otherDrugName + " using the information provided."
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

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
