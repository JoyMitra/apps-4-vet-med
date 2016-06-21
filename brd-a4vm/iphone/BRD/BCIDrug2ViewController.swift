//
//  BCIDrug2ViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIDrug2ViewController: UIViewController, UITextFieldDelegate {

    
    @IBOutlet var drugNameField: UITextField!
    @IBOutlet var tpfField: UITextField!
    @IBOutlet var cfrField: UITextField!
    @IBOutlet var costOfTreatmentField: UITextField!
    @IBOutlet var chronicPercentageField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func compareButtonAction(sender: AnyObject) {
        let drugName: String = drugNameField.text!
        let tpf: String = tpfField.text!
        let cfr: String = cfrField.text!
        let cot: String = costOfTreatmentField.text!
        let chronic: String = chronicPercentageField.text!
        
        if drugName.isBlank || tpf.isBlank || cfr.isBlank || cot.isBlank || chronic.isBlank {
            let alertView = UIAlertController(title: "Error", message: "emptyFields".localized, preferredStyle: .Alert)
            alertView.addAction(UIAlertAction(title: "Okay", style: .Default, handler: nil))
            self.presentViewController(alertView, animated: true, completion: nil)
        } else {
            self.performSegueWithIdentifier("showResults", sender: self)
        }
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

extension String {
    
    var isBlank: Bool {
        get {
            let trimmed = stringByTrimmingCharactersInSet(NSCharacterSet.whitespaceCharacterSet())
            return trimmed.isEmpty
        }
    }
    
}
