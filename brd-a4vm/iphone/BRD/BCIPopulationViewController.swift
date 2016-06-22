//
//  BCIPopulationViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIPopulationViewController: UIViewController,UITextFieldDelegate {

    @IBOutlet var morbidityField: UITextField!
    @IBOutlet var costOfGainField: UITextField!
    @IBOutlet var priceReceivedPerSaleField: UITextField!
    @IBOutlet var arrivalWeightField: UITextField!
    @IBOutlet var daysOnFeedField: UITextField!
    @IBOutlet var adgField: UITextField!
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
        
        if morbidity.isBlank || cog.isBlank || price.isBlank || weight.isBlank || cog.isBlank || dof.isBlank || adg.isBlank {
            textFieldDidEndEditing(morbidityField)
            textFieldDidEndEditing(costOfGainField)
            textFieldDidEndEditing(arrivalWeightField)
            textFieldDidEndEditing(costOfGainField)
            textFieldDidEndEditing(adgField)
            textFieldDidEndEditing(daysOnFeedField)
            textFieldDidEndEditing(priceReceivedPerSaleField)
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
        highlightSelectedTextField(textField)
    }
    
    // Check when editing ends and update accordingly
    func textFieldDidEndEditing(textField: UITextField) {
        let whitespace = NSCharacterSet.whitespaceCharacterSet()
        if(textField.text!.stringByTrimmingCharactersInSet(whitespace) == ""){
            errorHighlightTextField(textField)
        }
        else{
            removeErrorHighlightTextField(textField)
        }
    }

    // Textfield focus - show gray border
    func highlightSelectedTextField(textfield: UITextField){
        textfield.layer.borderColor = UIColor.grayColor().CGColor
        textfield.layer.borderWidth = 1
        textfield.layer.cornerRadius = 5
    }
    
    // Text Field is empty - show red border
    func errorHighlightTextField(textField: UITextField){
        textField.layer.borderColor = UIColor.redColor().CGColor
        textField.layer.borderWidth = 1
        textField.layer.cornerRadius = 5
    }
    
    // Text Field is NOT empty - show gray border with 0 border width
    // Reset
    func removeErrorHighlightTextField(textField: UITextField){
        textField.layer.borderColor = UIColor.grayColor().CGColor
        textField.layer.borderWidth = 0
        textField.layer.cornerRadius = 5
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
