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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        source.calculateEverything()
        
        let differenceInReturnString = String(abs(source.differenceInReturnToOwnership))
        let differenceInReturn = source.differenceInReturnToOwnership
        let saleWeightTxA = String(source.saleWeightTxA)
        let saleWeightTxB = String(source.saleWeightTxB)
        let grossIncomeTxA = String(source.grossIncomeTxA)
        let grossIncomeTxB = String(source.grossIncomeTxB)
        let returnToOwnershipTxA = String(source.returnToOwnershipAndManagementTxA)
        let returnToOwndershipTxB = String(source.returnToOwnershipAndManagementTxB)
        let costOfTreamtmentTxA = String(source.costOfTreatmentPerHeadTxA)
        let costOfTreamtmentTxB = String(source.costOfTreatmentPerHeadTxB)
        
        
        var drugComparisonString = ""
        if differenceInReturn.isSignMinus {
            drugComparisonString = "Drug 1 is better\n"
        } else {
            drugComparisonString = "Drug 2 is better\n"
        }
        let detailsString = "Difference in return to Ownership: " + differenceInReturnString + "\n" +
                            "Sale weight for Tx A: " + saleWeightTxA + "\n" +
                            "Sale weight for Tx B: " + saleWeightTxB + "\n" +
                            "Gross income Tx A: " + grossIncomeTxA + "\n" +
                            "Gross income Tx A: " + grossIncomeTxB + "\n" +
                            "Return to ownership and mgt w/ Tx A: " + returnToOwnershipTxA + "\n" +
                            "Return to ownership and mgt w/ Tx A: " + returnToOwndershipTxB + "\n" +
                            "Cost of treatment with Tx A: " + costOfTreamtmentTxA + "\n" +
                            "Cost of treatment with Tx A: " + costOfTreamtmentTxB
        
        let resultString = drugComparisonString + "\n" + detailsString
        
        let boldFont = UIFont(name: "Helvetica-Bold", size: 16)
        let attributedString = NSMutableAttributedString(string:detailsString)
        let range = (resultString as NSString).rangeOfString(differenceInReturnString)
        let boldedRange = (resultString as NSString).rangeOfString(drugComparisonString)
        attributedString.addAttributes([kCTFontAttributeName as String:boldFont!], range: boldedRange)
        if differenceInReturn.isSignMinus {
            attributedString.addAttribute(NSForegroundColorAttributeName, value: UIColor.redColor() , range: range)
        }
        resultsTextView.attributedText = attributedString
        
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
