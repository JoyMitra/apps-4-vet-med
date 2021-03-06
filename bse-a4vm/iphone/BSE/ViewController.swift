//
//  ViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

extension String {
    var isPhoneNumber: Bool {
        do {
            let detector = try NSDataDetector(types: NSTextCheckingType.PhoneNumber.rawValue)
            let matches = detector.matchesInString(self, options: [], range: NSMakeRange(0, self.characters.count))
            if let res = matches.first {
                return res.resultType == .PhoneNumber && res.range.location == 0 && res.range.length == self.characters.count
            } else {
                return false
            }
        } catch {
            return false
        }
    }
}

class ViewController: UIViewController {
   
var ranchersArray = [PFObject]();
    @IBAction func Delete30(sender: AnyObject) {
        let alert = UIAlertController(title: "Delete operation", message: "This feature is disabled till next update. ", preferredStyle: .Alert)
        
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            /*
            let query = PFQuery(className:"RanchInfo");
            
            query.fromLocalDatastore();
            query.orderByAscending("updatedAt");
            
            query.findObjectsInBackgroundWithBlock { (ranchers: [PFObject]?, error: NSError?) -> Void in
                
                if let ranchers = ranchers as [PFObject]!
                {
                    self.ranchersArray = ranchers as [PFObject];
                                   }
                else
                {
                    print("Didn't find anything?")
                }
                
            };
            for var i = 0; i < self.ranchersArray.count; i++
            {
                let today = self.ranchersArray[i]["madeAt"] as! NSDate!
                let tomorrow = NSCalendar.currentCalendar().dateByAddingUnit(
                    .Month,
                    value: 1,
                    toDate: today,
                    options: NSCalendarOptions(rawValue: 0))
                let current = NSDate();
                
                let compareResult = tomorrow!.compare(current)
                if compareResult == NSComparisonResult.OrderedAscending {
                    
                    self.ranchersArray[i].unpinInBackgroundWithBlock({(success: Bool, error: NSError?) -> Void in
                        
                    });
                    
                }
                
            }*/
                        
        }))
        alert.addAction(UIAlertAction(title: "Cancel", style: .Default, handler: { (action) -> Void in
        }))
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
        
    }
    var currentUser = PFUser.currentUser()
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        currentUser = PFUser.currentUser();
        
        
        if currentUser != nil {
            if(isFirstTime())
            {
                //Show dialog to input name and email
                getEmail();
            }
            
        } else {
            PFAnonymousUtils.logInWithBlock {
                (user: PFUser?, error: NSError?) -> Void in
                if error != nil || user == nil {
                    print("Anonymous login failed.")
                } else {
                    print("Anonymous user logged in.")
                    self.getEmail()
                    //show dialog to input name and email
                }
            }
        
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    //Check to see if this is the users first time.
    func  isFirstTime() -> Bool {
        
        let currentUser = PFUser.currentUser();
        let firstTime = currentUser?["firstTime"] as? Bool;
        
        if(firstTime == nil || firstTime == true){
            return true;
        }
        else
        {
            return false;
        }
    }
    func isValidEmail(testStr:String) -> Bool {
        
        
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        
        let emailTest = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        
        let result = emailTest.evaluateWithObject(testStr)
        
        return result
        
    }
    
    //Pops up a file dialog that asks the user for their email, then asks the user to input their name by calling getName()
   public func getEmail(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter Email", message: "This appears to be your first time using the app. Please set you email.", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder="name@example.com"
            //textField.becomeFirstResponder()
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(!self.isValidEmail(alert.textFields![0].text!))
            {
                alert.title = "Incorrect Email"
                //alert = UIAlertController(title: "Incorrect Email", message: "Please set the Email", preferredStyle: .Alert)
            self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["firstTime"] = false;
            PFUser.currentUser()?["email"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
           
            self.getName();
            
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
            alert.textFields![0].becomeFirstResponder()
    }
    
    
    //gets and sets the name of the user.
    func getName(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter your Name", message: "Please set your First Name.", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder = "Your First Name"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(alert.textFields![0].text == "")
            {
                alert.title = "Incorrect First Name"
            self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["firstName"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.lastName();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
        
        
        }))
        
        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
    }

    func clinicName(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter Clinic Name", message: "Please set the clinic Name", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder = "Your Clinic Name"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(alert.textFields![0].text == "")
            {
                alert.title = "Incorrect Clinic Name"
                self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["clinicName"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.add1();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
    
}
    func lastName(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter your Name", message: "Please set the Last Name", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder = "Your Last Name"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(alert.textFields![0].text == "")
            {
                alert.title = "Incorrect Last Name "
                self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["lastName"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.clinicName();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
    }
    func add1(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter your Address1", message: "Please set the Address1", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder = "Your Address1"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(alert.textFields![0].text == "")
            {
                alert.title = "Incorrect Address1"
                self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["address1"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.add2();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
    }
    func add2(){
        
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Enter your Address2", message: "Please set the Address2", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder = " Your Address2"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["address2"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.city();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
    }
    func city(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter your City", message: "Please set the City", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder = "Your City"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(alert.textFields![0].text == "")
            {
                alert.title = "Incorrect City "
                
                self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["city"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.state();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
    }
    func state(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter your State", message: "Please set the State", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.placeholder = "Your State"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(alert.textFields![0].text == "")
            {
                alert.title = "Incorrect State"
                self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["state"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.phone();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
    }
    func validate(value: String) -> Bool {
        let PHONE_REGEX =  "[(]?\\d{3}[-.)]?\\d{3}[-.]?\\d{4}"
        let phoneTest = NSPredicate(format: "SELF MATCHES %@", PHONE_REGEX)
        let result =  phoneTest.evaluateWithObject(value)
        return result
    }
       func phone(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter your Phone", message: "Please set the Phone Number", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.keyboardType = UIKeyboardType.NumberPad
            textField.placeholder = "Your Phone Number"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(!(alert.textFields![0].text! ).isPhoneNumber)
                
            {
                alert.title = "Incorrect Phone number"
                self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["phone"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            self.zip();
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
    }

    func zip(){
        
        //1. Create the alert controller.
        var alert = UIAlertController(title: "Enter your Zip Code", message: "Please set the Zip Code", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.keyboardType = UIKeyboardType.PhonePad
            
            textField.placeholder = "Your Zip Code"
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            if(alert.textFields![0].text == "" || alert.textFields![0].text?.characters.count != 5)
            {
                alert.title = "Incorrect Zip"
                
                self.presentViewController(alert, animated: true, completion: nil)
            }
            let textField = alert.textFields![0] as UITextField;
            print("Text field: \(textField.text)");
            PFUser.currentUser()?["zip"] = textField.text;
            do {
                try PFUser.currentUser()?.pinInBackground();
            } catch {
                print("Error")
            }
            
        }))
        alert.addAction(UIAlertAction(title: "Not now", style: .Default, handler: { (action) -> Void in
            
            
        }))
        

        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
    }


}
