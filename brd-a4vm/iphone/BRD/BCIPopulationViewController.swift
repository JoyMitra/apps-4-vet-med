//
//  BCIPopulationViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIPopulationViewController: UIViewController {

    @IBOutlet var morbidityField: UITextField!
    @IBOutlet var costOfGainField: UITextField!
    @IBOutlet var priceReceivedPerSaleField: UITextField!
    @IBOutlet var arrivalWeightField: UITextField!
    @IBOutlet var daysOnFeedField: UITextField!
    @IBOutlet var adgField: UITextField!
    var retain: Bool! = false
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    @IBAction func nextStepAction() {
        let morbidity: String = morbidityField.text!
        let cog: String = costOfGainField.text!
        let price: String = priceReceivedPerSaleField.text!
        let cot: String = arrivalWeightField.text!
        let dof: String = daysOnFeedField.text!
        let adg: String = adgField.text!
        
        if morbidity.isBlank || cog.isBlank || price.isBlank || cot.isBlank || cog.isBlank || dof.isBlank || adg.isBlank {
            let alertView = UIAlertController(title: "Error", message: "Please fill out all text field!", preferredStyle: .Alert)
            alertView.addAction(UIAlertAction(title: "Okay", style: .Default, handler: nil))
            self.presentViewController(alertView, animated: true, completion: nil)
        } else {
            self.performSegueWithIdentifier("goToDrug1", sender: self)
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
