//
//  BullInfoViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse



class BullInfoViewController: UIViewController {
    

    @IBAction func DOB(sender: UIDatePicker) {
        bull["dobDate"] = sender.date;
        let flags: NSCalendarUnit = [.Day , .Month , .Year]
        let date1 = NSDate()
        let components = NSCalendar.currentCalendar().components(flags, fromDate: date1)
        let components1 = NSCalendar.currentCalendar().components(flags, fromDate: sender.date)
        let year = Int(components.year)
        let month = Int(components.month)
        let year1 = Int(components1.year)
        let month1 = Int(components1.month)
        var M : Int = month-month1;
        var Y : Int
        if(M >= 0)
        {
            Y  = year-year1;
        }
        else { Y = year-year1-1;
            M = M * (-1);
        }
        var age : String = String(M);
        bull["AgeMonth"] = age;
        Month.text = age;
        age  = String(Y);
        bull ["AgeYear"] = age;
        Years.text = age;
        
        Datelabel.text = (bull["AgeYear"] as? String)! + " Years" + (bull["AgeMonth"] as? String)! + " Months";
        
        bull.pinInBackground();
    }
    
    var bull : PFObject!;
    var group : PFObject!;
    var bulls = [PFObject]();
    @IBOutlet var TagID: UITextField!
    @IBOutlet var Month: UITextField!
    @IBOutlet var Years: UITextField!
    @IBOutlet var Datelabel: UILabel!
    @IBOutlet var Age: UISegmentedControl!
    @IBAction func AgeSelected(sender: AnyObject) {
        if(Age.selectedSegmentIndex==0)
        {
            Month.userInteractionEnabled = true;
            Years.userInteractionEnabled = true;
            
            DatePicker.userInteractionEnabled = false;
        }
        if(Age.selectedSegmentIndex==1)
        {
            Month.userInteractionEnabled = false;
            Years.userInteractionEnabled = false;
            DatePicker.userInteractionEnabled = true;

            
        }
        
    }
    
    @IBOutlet var OtherID: UITextField!
    @IBOutlet var Lot: UITextField!
         
    @IBOutlet var Comment: UITextField!
    @IBOutlet var Breed: UITextField!
    @IBOutlet var DatePicker: UIDatePicker!
    @IBOutlet var TattooID: UITextField!
    @IBOutlet var BrandID: UITextField!
    @IBOutlet var RFIDID: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        Month.keyboardType = UIKeyboardType.NumberPad;
        Years.keyboardType = UIKeyboardType.NumberPad;
        self.title = "Bull Info";
        if(bull["AgeMonth"] != nil)
        {
            Month.text = bull ["AgeMonth"] as? String
        }
        if(bull["AgeYear"] != nil)
        {
            Years.text = bull ["AgeYear"] as? String
        }
        if(bull["bullTag"] != nil)
        {
        TagID.text = bull["bullTag"] as? String;
        }
        if(bull["bullOther"] != nil)
        {
            OtherID.text = bull["bullOther"] as? String;
        }
        if(bull["bullTattoo"] != nil)
        {
            TattooID.text = bull["bullTattoo"] as? String;
        }
        if(bull["bullBrand"] != nil)
        {
            BrandID.text = bull["bullBrand"] as? String;
        }
        if(bull["bullRFID"] != nil)
        {
            RFIDID.text = bull["bullRFID"] as? String;
        }
            Lot.text = bull["lot"] as? String;
        Breed.text = bull["breed"] as? String;
        Comment.text = bull["infoComments"] as? String;
        
        if (bull["dobDate"] != nil){
        DatePicker.date = bull["dobDate"] as! NSDate;
        }
        
        let query = PFQuery(className: "Bull");
        query.fromLocalDatastore();
        query.whereKey("group", equalTo: group);
        query.orderByAscending("bullID");
        
        query.findObjectsInBackgroundWithBlock { (bullArray: [PFObject]?, error: NSError?) -> Void in
        
        if let bullArray = bullArray as [PFObject]!
        {
        self.bulls = bullArray as [PFObject];
        
        for b in self.bulls
        {
        if(b["idType"] != nil)
        {
        self.bull["idType"] = b["idType"] as! String;
        }
        
        }
        }
        
        if (self.bull["idType"] != nil) {
        let bullIdString = self.bull["idType"] as! String;
        
        if (bullIdString == "Tag"){
        //         self.tagTypePicker.selectedSegmentIndex = 0;
        }
        else if (bullIdString == "Tattoo") {
        //          self.tagTypePicker.selectedSegmentIndex = 1;
        }
        else if (bullIdString == "RFID") {
        //        self.tagTypePicker.selectedSegmentIndex = 2;
        }
        else if (bullIdString == "Brand"){
        //           self.tagTypePicker.selectedSegmentIndex = 3;
        }
        }
        
        
        // Do any additional setup after loading the view.
        }
        }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    //Called before navigating back. This will save all necessary data.

    override func viewWillDisappear(animated: Bool) {
        bull.pinInBackground();
        }

    @IBAction func Save(sender: AnyObject) {
        
        
        if(Lot.text != nil){
            bull["lot"] = Lot.text!;
        }
        if(RFIDID.text != nil)
        {
            bull["bullRFID"] = RFIDID.text ;
        }
        if(TagID.text != nil)
        {
            bull["bullTag"] = TagID.text ;
        }
        if(TattooID.text != nil)
        {
            bull["bullTattoo"] = TattooID.text ;
        }
        if(BrandID.text != nil)
        {
            bull["bullBrand"] = BrandID.text ;
        }
        if(OtherID.text != nil)
        {
            bull["bullOther"] = OtherID.text ;
        }
        if(Month.text != nil && Month.text != "")
        {
            bull["AgeMonth"] = Month.text;
        }
        if(Years.text != nil && Years.text != "")
        {
            bull["AgeYear"] = Years.text;
        }
        if(Breed.text != nil){
            bull["breed"] = Breed.text;
        }
        
        if(Comment.text != nil){
            bull["infoComments"] = Comment.text;
        }
        
        if (bull["AgeMonth"] != nil)
        {
        
        if(Int((bull["AgeMonth"] as? String)!)!>18)
        {
            let alert = UIAlertController(title: "WARNING! Bull Age", message: " This field can not be more than 18, do you wish to continue anyway or change it?", preferredStyle: .Alert)
            
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.text = self.Month.text
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            self.Month.text=textField.text
        }))
        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        }
        }
        bull.pinInBackground();
        if let nav = self.navigationController{
        nav.popViewControllerAnimated(true)
        }
        

    }
       func datePickerChanged(sender: UIDatePicker) {
        
     }
    
    

    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    // Get the new view controller using segue.destinationViewController.
    // Pass the selected object to the new view controller.
    }


}
