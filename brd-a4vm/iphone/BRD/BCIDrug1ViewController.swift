//
//  BCIDrug1ViewController.swift
//  BRD
//
//  Created by Kevin Manase on 6/15/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIDrug1ViewController: UIViewController,UITextFieldDelegate {

    @IBOutlet var drugNameField: BCITextField!
    @IBOutlet var tpfField: BCITextField!
    @IBOutlet var cfrField: BCITextField!
    @IBOutlet var costOfTreamentField: BCITextField!
    @IBOutlet var chronicPercentageField: BCITextField!
    var drugName: String!
    var tpf: String!
    var cfr: String!
    var cot: String!
    var chronic: String!
    let source = BCIDataSource.sharedInstance
    let kDrug1Parameters = "drug1Parameters"
    let drug2Segue = "goToDrug2"
    let doubleFormat = ".2"
    let intFormat = "0"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.loadLocalValues()
        self.drugNameField.delegate = self
        self.tpfField.delegate = self
        self.cfrField.delegate = self
        self.costOfTreamentField.delegate = self
        self.chronicPercentageField.delegate = self
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
        
        textFieldDidEndEditing(drugNameField)
        textFieldDidEndEditing(tpfField)
        textFieldDidEndEditing(cfrField)
        textFieldDidEndEditing(costOfTreamentField)
        textFieldDidEndEditing(chronicPercentageField)
        
        if drugName.isBlank || tpf.isBlank || cfr.isBlank || cot.isBlank || chronic.isBlank ||
           !tpfField.isSatisfied || !cfrField.isSatisfied || !costOfTreamentField.isSatisfied ||
            !chronicPercentageField.isSatisfied {
            let alertView = UIAlertController(title: "Error", message: "emptyFields".localized, preferredStyle: .Alert)
            alertView.addAction(UIAlertAction(title: "Okay", style: .Default, handler: nil))
            self.presentViewController(alertView, animated: true, completion: nil)
        } else {
            returnValues()
            self.performSegueWithIdentifier(drug2Segue, sender: self)
        }
    }
    
    func loadLocalValues() {
        let userDataStore = NSUserDefaults.standardUserDefaults()
        let drug1Dictionary = userDataStore.objectForKey(kDrug1Parameters)
        if let dict = drug1Dictionary as? [String:String!] {
            drugNameField.text = dict["drug1Name"]!
            tpfField.text = Double(dict["tpfa"]!)?.format(doubleFormat)
            cfrField.text = Double(dict["cfra"]!)?.format(doubleFormat)
            costOfTreamentField.text = Double(dict["cta1"]!)?.format(doubleFormat)
            chronicPercentageField.text = Double(dict["cpa"]!)?.format(doubleFormat)
        }
        
    }
    
    func returnValues() {
        source.drug1Name = drugName
        source.tfpa = Double(tpf)
        source.cfra = Double(cfr)
        source.cta1 = Double(cot)
        source.cpa = Double(chronic)
        let drug1Dictionary = ["drug1Name": drugName,
                                    "tpfa": tpf,
                                    "cfra": cfr,
                                    "cta1": cot,
                                    "cpa": chronic]
        source.drug1Dictionary = drug1Dictionary
        let userDataStore = NSUserDefaults.standardUserDefaults()
        userDataStore.setObject(drug1Dictionary, forKey: kDrug1Parameters)
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
        case tpfField:
            let input = Double(tpfField.text!)
            if (input >= 0 && input <= 100) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case cfrField:
            let input = Double(cfrField.text!)
            if (input >= 0 && input <= 100) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case costOfTreamentField:
            let input = Double(costOfTreamentField.text!)
            if (input >= 0 && input <= 50) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        case chronicPercentageField:
            let input = Double(chronicPercentageField.text!)
            if (input >= 0 && input <= 100) {
                removeErrorHighlightTextField(myTextField)
            } else {
                errorHighlightTextField(myTextField)
            }
            break
        default: break
        }

    }
    
    
    func textField(textField: UITextField, shouldChangeCharactersInRange range: NSRange, replacementString string: String) -> Bool {
        
        if string.isEmpty {
            return true
        }
        
        if let input = textField.text {
            let numberFormatter = NSNumberFormatter()
            let range = input.rangeOfString(numberFormatter.decimalSeparator)
            if let r = range {
                let endIndex = input.startIndex.advancedBy(input.startIndex.distanceTo(r.endIndex))
                let decimals = input.substringFromIndex(endIndex)
                return decimals.characters.count < 2
            }
        }
        
        return true
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
}
