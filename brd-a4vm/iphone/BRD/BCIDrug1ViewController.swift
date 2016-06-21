//
//  BCIDrug1ViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIDrug1ViewController: UIViewController {

    @IBOutlet weak var drugNameField: UITextField!
    @IBOutlet weak var tpfField: UITextField!
    @IBOutlet weak var cfrField: UITextField!
    @IBOutlet weak var costOfTreamentField: UITextField!
    @IBOutlet weak var chronicPercentageField: UITextField!
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
            drugNameField.text = ""
            tpfField.text = ""
            cfrField.text = ""
            costOfTreamentField.text = ""
            chronicPercentageField.text = ""
        } else {
            retain = true
            drugNameField.text = "Some kind of drug"
            tpfField.text = "3%"
            cfrField.text = "15%"
            costOfTreamentField.text = "$150"
            chronicPercentageField.text = "22.1%"
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
