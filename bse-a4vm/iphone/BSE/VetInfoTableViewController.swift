//
//  VetInfoTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class VetInfoTableViewController: UITableViewController , UIPickerViewDataSource, UIPickerViewDelegate{
    
    
    var user : PFUser!;
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
    @IBOutlet var firstName: UITextField!
    @IBOutlet var lastName: UITextField!
    @IBOutlet var clinicName: UITextField!
    @IBOutlet var address1: UITextField!
    @IBOutlet var address2: UITextField!
    @IBOutlet var city: UITextField!
    @IBOutlet var state: UITextField!
    @IBOutlet var zip: UITextField!
    @IBOutlet var email: UITextField!
    @IBOutlet var phone: UITextField!
    var picker = UIPickerView()
    

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
    override func viewDidLoad() {
        super.viewDidLoad()

        user = PFUser.currentUser();
        firstName.text = user["firstName"] as? String;
        lastName.text = user["lastName"] as? String;
        clinicName.text = user["clinicName"] as? String;
        address1.text = user["address1"] as? String;
        address2.text = user["address2"] as? String;
        city.text = user["city"] as? String;
        state.text = user["state"] as? String;
        zip.text = user["zip"] as? String;
        email.text = user["email"] as? String;
        phone.text = user["phone"] as? String;
        picker.delegate=self
        picker.dataSource=self
        state.inputView=picker
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillDisappear(animated: Bool) {
        if(firstName.text != nil){
            user["firstName"] = firstName.text;
        }
        
        if(lastName.text != nil){
            user["lastName"] = lastName.text;
        }
        
        if(clinicName.text != nil){
            user["clinicName"] = clinicName.text;
        }
        
        if(address1.text != nil){
            user["address1"] = address1.text;
        }
        
        if(address2.text != nil){
            user["address2"] = address2.text;
        }
        
        if(city.text != nil){
            user["city"] = city.text;
        }
        
        if(state.text != nil){
            user["state"] = state.text;
        }
        
        if(zip.text != nil){
            user["zip"] = zip.text;
        }
        
        if(email.text != nil){
            user["email"] = email.text;
        }
        
        if(phone.text != nil){
            user["phone"] = phone.text;
        }
        
        user.pinInBackground();
        
    }

    // MARK: - Table view data source

    
    /*
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)

        // Configure the cell...

        return cell
    }
    */

    /*
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
