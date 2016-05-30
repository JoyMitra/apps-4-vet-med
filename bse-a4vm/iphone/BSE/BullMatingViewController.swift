//
//  BullMatingViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class BullMatingViewController: UIViewController , UIPickerViewDataSource, UIPickerViewDelegate{

    var bull : PFObject!;
    var data = ["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"]
    
    
    @IBOutlet var seasonUsedField: UITextField!
    @IBOutlet var descriptionField: UITextField!
    @IBOutlet var otherComments: UITextField!
    @IBOutlet var singleMuti: UISegmentedControl!
    @IBOutlet var lastYearPerformance: UISegmentedControl!
    var picker = UIPickerView()
    
    
    internal func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int
    {return 1}
    
    // returns the # of rows in each component..
    
    internal func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int
    {return data.count}
    internal func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        seasonUsedField.text=data[row]
    }
    internal func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return data[row]
    }

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "Mating Info";
        picker.delegate=self
        picker.dataSource=self
        seasonUsedField.inputView=picker
        seasonUsedField.text = bull["seasonsUsed"] as? String;
        //lastSeasonField.text = bull["lastSeasonPerformance"] as? String;
        descriptionField.text = bull["matingDescription"] as? String;
        otherComments.text = bull["matingComments"] as? String;
        
        if(bull["singleOrMultiSire"] != nil){
            
            let singleMultiString = bull["singleOrMultiSire"] as! String;
            
            if(singleMultiString == "Single-Sire"){
                singleMuti.selectedSegmentIndex = 0;
            }
            else if (singleMultiString == "Multi-Sire"){
                singleMuti.selectedSegmentIndex = 1;
            }
            else if (singleMultiString == "NotUsed")
            {
                singleMuti.selectedSegmentIndex = 2;
            }
        }
        
        if(bull["lastSeasonPerformance"] != nil){
            let matingPerformance = bull["lastSeasonPerformance"] as! String;
            if(matingPerformance == "Good"){
                lastYearPerformance.selectedSegmentIndex = 0;
            }
            else if(matingPerformance == "Poor"){
                lastYearPerformance.selectedSegmentIndex = 1;
            }
            else if(matingPerformance == "Unknown"){
                lastYearPerformance.selectedSegmentIndex = 2;
            }
            else if(matingPerformance == "Other"){
                lastYearPerformance.selectedSegmentIndex = 3;
            }
            
        }
        

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    override func viewWillDisappear(animated: Bool) {
        bull.pinInBackground();
           }
    
    @IBAction func Save(sender: AnyObject) {
        
        if(seasonUsedField.text != nil){
            bull["seasonsUsed"] = seasonUsedField.text;
        }
        
        
        if(descriptionField.text != nil){
            bull["matingDescription"] = descriptionField.text;
        }
        
        if(otherComments.text != nil){
            bull["matingComments"] = otherComments.text;
        }
        
        if let nav = self.navigationController{
        nav.popViewControllerAnimated(true)
        }
        
        //Set all variables
        
        bull.pinInBackground();

    }
    @IBAction func singleMultiIndexCHanged(sender: AnyObject) {
        switch singleMuti.selectedSegmentIndex
        {
        case 0:
            bull["singleOrMultiSire"] = "Single-Sire";
        case 1:
            bull["singleOrMultiSire"] = "Multi-Sire";
        case 2:
            bull["singleOrMultiSire"] = "NotUsed";
        default:
            break;
        }

       
        
    }

    @IBAction func performanceChanged(sender: AnyObject) {
        switch lastYearPerformance.selectedSegmentIndex
        {
        case 0:
            bull["lastSeasonPerformance"] = "Good";
        case 1:
            bull["lastSeasonPerformance"] = "Poor";
        case 2:
            bull["lastSeasonPerformance"] = "Unknown";
        case 3:
            bull["lastSeasonPerformance"] = "Other";
        default:
            break;
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
