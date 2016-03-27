//
//  MorphologyIFieldsTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class MorphologyIFieldsTableViewController: UITableViewController, UITextFieldDelegate {

    var user : PFUser!;
    
    @IBOutlet var m1: UITextField!
    @IBOutlet var m2: UITextField!
    @IBOutlet var m3: UITextField!
    @IBOutlet var m4: UITextField!
    @IBOutlet var m5: UITextField!
    @IBOutlet var m6: UITextField!
    @IBOutlet var m7: UITextField!
    @IBOutlet var m8: UITextField!
    @IBOutlet var TH1: UITextField!
    
    @IBOutlet var TH8: UITextField!
    @IBOutlet var TH7: UITextField!
    @IBOutlet var TH6: UITextField!
    @IBOutlet var TH5: UITextField!
    @IBOutlet var TH4: UITextField!
    @IBOutlet var TH2: UITextField!
    
    
    @IBOutlet var TH3: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        user = PFUser.currentUser();
                m1.text = user["m1"] as? String;
        m1.text="Normal"

        m2.text = user["m2"] as? String;
        m3.text = user["m3"] as? String;
        m4.text = user["m4"] as? String;
        m5.text = user["m5"] as? String;
        m6.text = user["m6"] as? String;
        m7.text = user["m7"] as? String;
        m8.text = user["m8"] as? String;
        TH1.delegate=self
        TH1.keyboardType = UIKeyboardType.NumberPad;
        TH1.text = user["th1"] as? String;
        TH2.delegate=self
        TH2.keyboardType = UIKeyboardType.NumberPad;
        TH2.text = user["th2"] as? String;
        TH3.delegate=self
        TH3.keyboardType = UIKeyboardType.NumberPad;
        TH3.text = user["th3"] as? String;
        TH4.delegate=self
        TH4.keyboardType = UIKeyboardType.NumberPad;
        TH4.text = user["th4"] as? String;
        TH5.delegate=self
        TH5.keyboardType = UIKeyboardType.NumberPad;
        TH5.text = user["th5"] as? String;
        TH6.delegate=self
        TH6.keyboardType = UIKeyboardType.NumberPad;
        TH6.text = user["th6"] as? String;
        TH7.delegate=self
        TH7.keyboardType = UIKeyboardType.NumberPad;
        TH7.text = user["th7"] as? String;
        TH8.delegate=self
        TH8.keyboardType = UIKeyboardType.NumberPad;
        TH8.text = user["th8"] as? String;
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
        if(m1.text != nil){
            user["m1"] = m1.text;
        }
        
        if(m2.text != nil){
            user["m2"] = m2.text;
        }
        
        if(m3.text != nil){
            user["m3"] = m3.text;
        }
        
        if(m4.text != nil){
            user["m4"] = m4.text;
        }
        
        if(m5.text != nil){
            user["m5"] = m5.text;
        }
        
        if(m6.text != nil){
            user["m6"] = m6.text;
        }
        
        if(m7.text != nil){
            user["m7"] = m7.text;
        }
        
        if(m1.text != nil){
            user["m8"] = m8.text;
        }
        if(TH1.text != nil){
            user["th1"] = TH1.text
        }
        if(TH2.text != nil){
            user["th2"] = TH2.text
        }
        if(TH3.text != nil){
            user["th3"] = TH3.text
        }
        if(TH4.text != nil){
            user["th4"] = TH4.text
        }
        if(TH5.text != nil){
            user["th5"] = TH5.text
        }
        if(TH6.text != nil){
            user["th6"] = TH6.text
        }
        if(TH7.text != nil){
            user["th7"] = TH7.text
        }
        if(TH8.text != nil){
            user["th8"] = TH8.text
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
