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
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
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
            self.performSegueWithIdentifier("goToDrug2", sender: self)
        }
    }
    
    func returnValues() {
        source.tfpa = Double(tpf)
        source.cfra = Double(cfr)
        source.cta1 = Double(cot)
        source.cpa = Double(chronic)
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
