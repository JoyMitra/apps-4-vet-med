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
        source.calculateEverything()
        
        let differenceInReturnString = abs(source.differenceInReturnToOwnership).format(doubleFormat)
        let differenceInReturn = source.differenceInReturnToOwnership
        let saleWeightTxA = String(source.saleWeightTxA)
        let saleWeightTxB = String(source.saleWeightTxB)
        let grossIncomeTxA = String(source.grossIncomeTxA)
        let grossIncomeTxB = String(source.grossIncomeTxB)
        let returnToOwnershipTxA = String(source.returnToOwnershipAndManagementTxA)
        let returnToOwndershipTxB = String(source.returnToOwnershipAndManagementTxB)
        let costOfTreamtmentTxA = String(source.costOfTreatmentPerHeadTxA)
        let costOfTreamtmentTxB = String(source.costOfTreatmentPerHeadTxB)
        
        var bestDrugName = ""
        var resultString = ""
        
        if differenceInReturn.isSignMinus {
            bestDrugName = source.drug1Name
        } else {
            bestDrugName = source.drug2Name
        }
        resultString = "Treatment " + bestDrugName + " shows $" + differenceInReturnString + "/head advantage"
        resultsTextView.text = resultString
        
    }

    override func viewDidDisappear(animated: Bool) {
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
