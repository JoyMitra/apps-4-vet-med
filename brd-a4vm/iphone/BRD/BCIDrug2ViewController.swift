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
    var drugName: String!
    var tpf: String!
    var cfr: String!
    var cot: String!
    var chronic: String!
    let source = BCIDataSource.sharedInstance
    let kDrug2Parameters = "kDrug2Parameters"
    let resultsSegue = "showResults"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.loadLocalValues()
        self.drugNameField.delegate = self
        self.tpfField.delegate = self
        self.cfrField.delegate = self
        self.costOfTreatmentField.delegate = self
        self.chronicPercentageField.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func compareButtonAction(sender: AnyObject) {
        drugName = drugNameField.text!
        tpf = tpfField.text!
        cfr = cfrField.text!
        cot = costOfTreatmentField.text!
        chronic = chronicPercentageField.text!
        
        if drugName.isBlank || tpf.isBlank || cfr.isBlank || cot.isBlank || chronic.isBlank {
            textFieldDidEndEditing(drugNameField)
            textFieldDidEndEditing(tpfField)
            textFieldDidEndEditing(cfrField)
            textFieldDidEndEditing(costOfTreatmentField)
            textFieldDidEndEditing(chronicPercentageField)
            let alertView = UIAlertController(title: "Error", message: "emptyFields".localized, preferredStyle: .Alert)
            alertView.addAction(UIAlertAction(title: "Okay", style: .Default, handler: nil))
            self.presentViewController(alertView, animated: true, completion: nil)
        } else {
            returnValues()
            self.performSegueWithIdentifier(resultsSegue, sender: self)
        }
    }
    
    func loadLocalValues() {
        let userDataStore = NSUserDefaults.standardUserDefaults()
        let drug2Dictionary = userDataStore.objectForKey(kDrug2Parameters)
        if let dict = drug2Dictionary as? [String:String!] {
            drugNameField.text = dict["drug2Name"]!
            tpfField.text = dict["tpfb"]!
            cfrField.text = dict["cfrb"]!
            costOfTreatmentField.text = dict["ctb1"]!
            chronicPercentageField.text = dict["cpb"]!
        }
        
    }
    
    func returnValues() {
        source.drug2Name = drugName
        source.tfpb = Double(tpf)
        source.cfrb = Double(cfr)
        source.ctb1 = Double(cot)
        source.cpb = Double(chronic)
        let drug2Dictionary = ["drug2Name": drugName,
                               "tpfb": tpf,
                               "cfrb": cfr,
                               "ctb1": cot,
                               "cpb": chronic]
        source.drug1Dictionary = drug2Dictionary
        let userDataStore = NSUserDefaults.standardUserDefaults()
        userDataStore.setObject(drug2Dictionary, forKey:kDrug2Parameters)
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

}
