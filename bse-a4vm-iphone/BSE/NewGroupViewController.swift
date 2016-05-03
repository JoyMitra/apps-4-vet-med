//
//  NewGroupViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class NewGroupViewController: UIViewController, UITextFieldDelegate ,UIPickerViewDataSource, UIPickerViewDelegate{
    var data = [ "Alaska",
        "Alabama",
        "Arkansas",
        "American Samoa",
        "Arizona",
        "California",
        "Colorado",
        "Connecticut",
        "District of Columbia",
        "Delaware",
        "Florida",
        "Georgia",
        "Guam",
        "Hawaii",
        "Iowa",
        "Idaho",
        "Illinois",
        "Indiana",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Massachusetts",
        "Maryland",
        "Maine",
        "Michigan",
        "Minnesota",
        "Missouri",
        "Mississippi",
        "Montana",
        "North Carolina",
        "North Dakota",
        "Nebraska",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "Nevada",
        "New York",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "Pennsylvania",
        "Puerto Rico",
        "Rhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Virginia",
        "Virgin Islands",
        "Vermont",
        "Washington",
        "Wisconsin",
        "West Virginia",
        "Wyoming"]
    @IBOutlet var ranchName: UITextField!
    @IBOutlet var firstName: UITextField!
    @IBOutlet var lastName: UITextField!
    @IBOutlet var address1: UITextField!
    @IBOutlet var address2: UITextField!
    @IBOutlet var city: UITextField!
    @IBOutlet var state: UITextField!
    @IBOutlet var zip: UITextField!
    @IBOutlet var phone: UITextField!
    var picker = UIPickerView()
    
    
    @IBOutlet weak var ScrollView: UIScrollView!
    internal func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int
    {return 1}
    
    // returns the # of rows in each component..
    
    internal func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int
    {return data.count}
    internal func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        state.text=data[row]
    }
    internal func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return data[row]
    }

    
    var group: PFObject!;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        ranchName.delegate = self;
        firstName.delegate = self;
        picker.delegate=self
        picker.dataSource=self
        state.inputView=picker
        ScrollView.contentSize.height=1000
        //I need to override the back button in the next view and then do this so that they don't save stuff on accident!
        //self.navigationController?.popToViewController(self.navigationController!.viewControllers[1], animated: true);
       if (group["ranchName"] != nil && group["ranchName"] as! String != "")
       {
        ranchName.text = group["ranchName"] as? String;
        }
        if (group["firstName"] != nil && group["firstName"] as! String != "")
        {
        firstName.text = group["firstName"] as? String;
        }
        if (group["email"] != nil && group["email"] as! String != "")
        {
            lastName.text = group["email"] as? String;
        }
        if (group["address1"] != nil && group["address1"] as! String != "")
        {
            address1.text = group["address1"] as? String;
        }
        if (group["address2"] != nil && group["address2"] as! String != "")
        {
            address2.text = group["address2"] as? String;
        }
        if (group["city"] != nil && group["city"] as! String != "")
        {
            city.text = group["city"] as? String;
        }
        if (group["firstName"] != nil && group["firstName"] as! String != "")
        {
            
            firstName.text = group["firstName"] as? String;
        }
        if (group["state"] != nil && group["state"] as! String != "")
        {
            state.text = group["state"] as? String;
        }
        if (group["zip"] != nil && group["zip"] as! String != "")
        {
            zip.text = group["zip"] as? String;
        }
        if (group["phone"] != nil && group["phone"] as! String != "")
        {
            phone.text = group["phone"] as? String;
        }
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func phone(sender: AnyObject) {
        if(phone.text != "")
        {
            
            if(phone.text?.characters.count != 10)
            {
                let alert = UIAlertController(title: "Incorrect phone", message: " The Phone number address you have input is wrong", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.phone.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    let textField = alert.textFields![0] as UITextField;
                    print("Text field: \(textField.text)");
                    self.phone.text=textField.text
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

    }
    @IBAction func zip(sender: AnyObject) {
        if(zip.text != "")
        {
        
            if(zip.text?.characters.count != 5)
            {
                let alert = UIAlertController(title: "Incorrect zip", message: " The zip address you have input is wrong", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.zip.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    let textField = alert.textFields![0] as UITextField;
                    print("Text field: \(textField.text)");
                    self.zip.text=textField.text
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

        
    }
    func isValidEmail(testStr:String) -> Bool {
        
        
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        
        let emailTest = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        
        let result = emailTest.evaluateWithObject(testStr)
        
        return result
        
    }

    @IBAction func Email(sender: AnyObject) {
        if(lastName.text != "")
        {
           // let searchCharacter: Character = "@"
            if(!isValidEmail(lastName.text!))
            {
                let alert = UIAlertController(title: "Incorrect email", message: " The Email address you have input is wrong", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.lastName.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    let textField = alert.textFields![0] as UITextField;
                    print("Text field: \(textField.text)");
                    self.lastName.text=textField.text
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

    }
    
    
    @IBAction func saveClicked(sender: AnyObject) {
        if(ranchName.text != "" && firstName.text != "" && lastName.text != "" && address1.text != "" && city.text != "" && state.text != "" && zip.text != "" && phone.text != "" && lastName.text!.lowercaseString.characters.contains("@") && zip.text?.characters.count == 5 && phone.text?.characters.count == 10)
        {
        let ranchInfo = PFObject(className:"RanchInfo")
        ranchInfo["ranchName"] = ranchName.text;
        ranchInfo["firstName"] = firstName.text;
        ranchInfo["email"] = lastName.text;
        ranchInfo["address1"] = address1.text;
        ranchInfo["address2"] = address2.text;
        ranchInfo["city"] = city.text;
        ranchInfo["state"] = state.text;
        ranchInfo["zip"] = zip.text;
        ranchInfo["phone"] = phone.text;
        ranchInfo["madeAt"] = NSDate();
        
        ranchInfo.pinInBackgroundWithBlock({(success: Bool, error: NSError?) -> Void in
            
            self.group = ranchInfo;
            self.performSegueWithIdentifier("newGroupSegue", sender: nil);
            
            
        });
        
      //  ranchInfo.pinInBackground();
        print("Saved Ranch Name")
        }
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        
        if( textField == ranchName){
            firstName.becomeFirstResponder();
        }
        
        return true;
    }

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if(segue.identifier == "newGroupSegue"){
            let bullTableVC = segue.destinationViewController as! BullTableViewController;
            
            bullTableVC.group = self.group;
        }
        
        
        
    }
    
    override func viewWillDisappear(animated: Bool) {
        
        super.viewWillDisappear(animated)
        
    
        //SAve object this way
    }

}
