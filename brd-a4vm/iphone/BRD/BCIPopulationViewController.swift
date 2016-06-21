//
//  BCIPopulationViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIPopulationViewController: UIViewController {

    @IBOutlet weak var morbidityField: UITextField!
    @IBOutlet weak var costOfGainField: UITextField!
    @IBOutlet weak var priceReceivedPerSaleField: UITextField!
    @IBOutlet weak var arrivalWeightField: UITextField!
    @IBOutlet weak var daysOnFeedField: UITextField!
    @IBOutlet weak var adgField: UITextField!
    var retain: Bool! = false
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if (retain == true) {
            fillButtonAction(self)
        }
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func fillButtonAction(sender: AnyObject) {
        if (retain == true) {
            retain = false
            morbidityField.text = ""
            costOfGainField.text = ""
            priceReceivedPerSaleField.text = ""
            arrivalWeightField.text = ""
            daysOnFeedField.text = ""
            adgField.text = ""
        } else {
            retain = true
            morbidityField.text = "2%"
            costOfGainField.text = "$3.1"
            priceReceivedPerSaleField.text = "$2.5"
            arrivalWeightField.text = "200 lbs."
            daysOnFeedField.text = "5"
            adgField.text = "10?"
        }
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
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
