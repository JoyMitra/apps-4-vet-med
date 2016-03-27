//
//  MeasurementsTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class MeasurementsTableViewController: UITableViewController {
    
    
    var bull : PFObject!;
    
    @IBOutlet var scrotalCircumference: UITextField!
    @IBOutlet var bodyCondition: UITextField!
    @IBOutlet var pelvicX: UITextField!
    @IBOutlet var pelvicY: UITextField!
    @IBOutlet var frameScore: UITextField!
    @IBOutlet var hipHeight: UITextField!
    @IBOutlet var otherMeasurement: UITextField!
    @IBOutlet var otherMeasurementUnits: UITextField!
    

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        scrotalCircumference.text = bull["scrotalCircumference"] as? String;
        bodyCondition.text = bull["bodyCondition"] as? String;
        pelvicX.text = bull["pelvicXMeasurement"] as? String;
        pelvicY.text = bull["pelvicYMeasurement"] as? String;
        frameScore.text = bull["frameScore"] as? String;
        hipHeight.text = bull["hipHeight"] as? String;
        otherMeasurement.text = bull["otherMeasurementPhysical"] as? String;
        otherMeasurementUnits.text = bull["otherMeasurementUnitsPhysical"] as? String;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    
    
    override func viewWillDisappear(animated: Bool) {
       
    }
    
    @IBAction func Save(sender: AnyObject) {
        
        if(scrotalCircumference.text != nil){
            bull["scrotalCircumference"] = scrotalCircumference.text;
        }
        
        if(bodyCondition.text != nil) {
            bull["bodyCondition"] = bodyCondition.text;
        }
        
        if(pelvicX.text != nil){
            bull["pelvicXMeasurement"] = pelvicX.text;
        }
        
        if(pelvicY.text != nil){
            bull["pelvicYMeasurement"] = pelvicY.text;
        }
        
        if(frameScore.text != nil){
            bull["frameScore"] = frameScore.text;
        }
        
        if(hipHeight.text != nil){
            bull["hipHeight"] = hipHeight.text;
        }
        
        if(otherMeasurement.text != nil){
            bull["otherMeasurementPhysical"] = otherMeasurement.text;
        }
        
        if(otherMeasurementUnits.text != nil){
            bull["otherMeasurementUnitsPhysical"] = otherMeasurementUnits.text;
        }
        
        bull.pinInBackground();
        if let nav = self.navigationController {
        nav.popViewControllerAnimated(true)
        }
    }
    
    @IBAction func Frame(sender: AnyObject) {
        if(frameScore.text != "")
        {
            let temp  = Float(frameScore.text!)
            var temp1:Int = Int (temp!)
            var temp2 : Float = Float (temp!)
            temp1=temp1*2
            temp2=temp2*2
            if((temp>30 || temp<8) || ( Float (temp1) != temp2  && Float(temp1) + 1 != temp2))
            {
                let alert = UIAlertController(title: "WARNING! Frame Score", message: " This field can not be more than 20 and less than 1 (only increments of .5), do you wish to continue anyway or change it?", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.frameScore.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    let textField = alert.textFields![0] as UITextField;
                    print("Text field: \(textField.text)");
                    self.frameScore.text=textField.text
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

    }
    @IBAction func PelvicY(sender: AnyObject) {
        if(pelvicY.text != "")
        {
            let temp  = Float(pelvicY.text!)
            var temp1:Int = Int (temp!)
            var temp2 : Float = Float (temp!)
            temp1=temp1*2
            temp2=temp2*2
            if((temp>30 || temp<8) || ( Float (temp1) != temp2  && Float(temp1) + 1 != temp2))
            {
                let alert = UIAlertController(title: "WARNING! Pelvic Y Measure", message: " This field can not be more than 30 and less than 8 (only increments of .5), do you wish to continue anyway or change it?", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.pelvicY.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    let textField = alert.textFields![0] as UITextField;
                    print("Text field: \(textField.text)");
                    self.pelvicY.text=textField.text
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

    }
    @IBAction func Pelvicx(sender: AnyObject) {
        if(pelvicX.text != "")
        {
            let temp  = Float(pelvicX.text!)
            var temp1:Int = Int (temp!)
            var temp2 : Float = Float (temp!)
            temp1=temp1*2
            temp2=temp2*2
            if((temp>30 || temp<8) || ( Float (temp1) != temp2  && Float(temp1) + 1 != temp2))
            {
                let alert = UIAlertController(title: "WARNING! Pelvic X Measure", message: " This field can not be more than 30 and less than 8 (only increments of .5), do you wish to continue anyway or change it?", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.pelvicX.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    let textField = alert.textFields![0] as UITextField;
                    print("Text field: \(textField.text)");
                    self.pelvicX.text=textField.text
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

    }
    @IBAction func Bodycheck(sender: AnyObject) {
        if(bodyCondition.text != "")
        {
            let temp  = Float(bodyCondition.text!)
            var temp1:Int = Int (temp!)
            var temp2 : Float = Float (temp!)
            temp1=temp1*2
            temp2=temp2*2
            if(temp>9 || ( Float (temp1) != temp2  && Float(temp1) + 1 != temp2))
            {
                let alert = UIAlertController(title: "WARNING! Body Circumference ", message: " This field can not be more than 9 (only increments of .5), do you wish to continue anyway or change it?", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.bodyCondition.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    let textField = alert.textFields![0] as UITextField;
                    print("Text field: \(textField.text)");
                    self.bodyCondition.text=textField.text
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

    }
    
    @IBAction func scrotalcheck(sender: AnyObject) {
      
        if(scrotalCircumference.text != "")
        {
            let temp  = Float(scrotalCircumference.text!)
            var temp1:Int = Int (temp!)
            var temp2 : Float = Float (temp!)
            temp1=temp1*2
            temp2=temp2*2
            if(temp>60 || ( Float (temp1) != temp2  && Float(temp1) + 1 != temp2))
            {
                let alert = UIAlertController(title: "WARNING! Scrotal Circumference", message: " This field can not be more than 60cm (only increments of .5), do you wish to continue anyway or change it?", preferredStyle: .Alert)
                
                //2. Add the text field. You can configure it however you need.
                alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
                    textField.text = self.scrotalCircumference.text
                })
                
                //3. Grab the value from the text field, and print it when the user clicks OK.
                alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
                    
                  //  let textField = alert.textFields![0] as UITextField;
                   // self.scrotalCircumference.text=textField.text;
                }))
                
                // 4. Present the alert.
                self.presentViewController(alert, animated: true, completion: nil)
                
                
            }
            
            
        }

    }
    

    
    
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
