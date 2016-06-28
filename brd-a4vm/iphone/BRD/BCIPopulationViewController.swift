//
//  BCIPopulationViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIPopulationViewController: UIViewController,UITextFieldDelegate {

    @IBOutlet var morbidityField: BCITextField!
    @IBOutlet var costOfGainField: BCITextField!
    @IBOutlet var priceReceivedPerSaleField: BCITextField!
    @IBOutlet var arrivalWeightField: BCITextField!
    @IBOutlet var daysOnFeedField: BCITextField!
    @IBOutlet var adgField: BCITextField!
    var morbidity: String!
    var cog: String!
    var price: String!
    var weight: String!
    var dof: String!
    var adg: String!
    let source = BCIDataSource.sharedInstance
    let kPopulationKey = "populationParameters"
    let drug1Segue = "goToDrug1"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.loadLocalValues()
        self.morbidityField.delegate = self
        self.costOfGainField.delegate = self
        self.priceReceivedPerSaleField.delegate = self
        self.arrivalWeightField.delegate = self
        self.daysOnFeedField.delegate = self
        self.adgField.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    @IBAction func nextStepAction() {
        morbidity = morbidityField.text!
        cog = costOfGainField.text!
        price = priceReceivedPerSaleField.text!
        weight = arrivalWeightField.text!
        dof = daysOnFeedField.text!
        adg = adgField.text!
        
    
        textFieldDidEndEditing(morbidityField)
        textFieldDidEndEditing(costOfGainField)
        textFieldDidEndEditing(arrivalWeightField)
        textFieldDidEndEditing(daysOnFeedField)
        textFieldDidEndEditing(priceReceivedPerSaleField)
        textFieldDidEndEditing(adgField)

        
        if morbidity.isBlank || cog.isBlank || price.isBlank || weight.isBlank || cog.isBlank || dof.isBlank || adg.isBlank ||
            !morbidityField.isSatisfied || !costOfGainField.isSatisfied || !arrivalWeightField.isSatisfied ||
            !daysOnFeedField.isSatisfied || !priceReceivedPerSaleField.isSatisfied || !adgField.isSatisfied {
            let alertView = UIAlertController(title: "Error", message: "emptyFields".localized , preferredStyle: .Alert)
            alertView.addAction(UIAlertAction(title: "Okay", style: .Default, handler: nil))
            self.presentViewController(alertView, animated: true, completion: nil)
        } else {
            returnValues()
            self.performSegueWithIdentifier(drug1Segue, sender: self)
        }
    }
    
    func loadLocalValues() {
        let userDataStore = NSUserDefaults.standardUserDefaults()
        let populationDictionary = userDataStore.objectForKey(kPopulationKey)
        if let dict = populationDictionary as? [String:String!] {
            morbidityField.text = dict["m"]!
            costOfGainField.text = dict["cog"]!
            priceReceivedPerSaleField.text = dict["sp"]!
            arrivalWeightField.text = dict["pw"]!
            daysOnFeedField.text = dict["days"]!
            adgField.text = dict["ahc"]!
        }
        
    }
    
    func returnValues() {
        source.m = Double(morbidity)
        source.cog = Double(cog)
        source.sp = Double(price)
        source.pw = Double(weight)
        source.days = Double(dof)
        source.ahc = Double(adg)
        let populationDictionary = ["m": morbidity,
                            "cog": cog,
                            "sp": price,
                            "pw": weight,
                            "days": dof,
                            "ahc": adg]
        source.populationDictionary = populationDictionary
        let userDataStore = NSUserDefaults.standardUserDefaults()
        userDataStore.setObject(populationDictionary, forKey: kPopulationKey)
    }
    
    // Focus on the field
    func textFieldDidBeginEditing(textField: UITextField) {
        let myTextField = textField as! BCITextField
        highlightSelectedTextField(myTextField)
    }
    
    // Check when editing ends and update accordingly
    func textFieldDidEndEditing(textField: UITextField) {
        let myTextField = textField as! BCITextField
        let whitespace = NSCharacterSet.whitespaceCharacterSet()
        if(textField.text!.stringByTrimmingCharactersInSet(whitespace) == ""){
            errorHighlightTextField(myTextField)
        }
        else{
            removeErrorHighlightTextField(myTextField)
        }
        
        switch(textField) {
        case morbidityField:
            let input = Double(morbidityField.text!)
            if (input >= 0 && input <= 100) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case costOfGainField:
            let input = Double(costOfGainField.text!)
            if (input >= 0.1 && input <= 10) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case arrivalWeightField:
            let input = Int(arrivalWeightField.text!)
            if (input >= 100 && input <= 1200) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case daysOnFeedField:
            let input = Int(daysOnFeedField.text!)
            if (input >= 1 && input <= 400) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case priceReceivedPerSaleField:
            let input = Double(priceReceivedPerSaleField.text!)
            if (input >= 0.1 && input <= 40) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case adgField:
            let input = Double(adgField.text!)
            if (input >= 0.1 && input <= 5) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        default: break
        }
    }

    // Textfield focus - show gray border
    func highlightSelectedTextField(textfield: BCITextField){
        textfield.layer.borderColor = UIColor.grayColor().CGColor
        textfield.layer.borderWidth = 1
        textfield.layer.cornerRadius = 5
    }
    
    // Text Field is empty - show red border
    func errorHighlightTextField(textField: BCITextField){
        textField.layer.borderColor = UIColor.redColor().CGColor
        textField.layer.borderWidth = 1
        textField.layer.cornerRadius = 5
        textField.isSatisfied = false
    }
    
    // Text Field is NOT empty - show gray border with 0 border width
    // Reset
    func removeErrorHighlightTextField(textField: BCITextField){
        textField.layer.borderColor = UIColor.grayColor().CGColor
        textField.layer.borderWidth = 0
        textField.layer.cornerRadius = 5
        textField.isSatisfied = true
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
