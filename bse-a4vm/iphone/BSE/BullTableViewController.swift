//
//  BullTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class BullTableViewController: UITableViewController {

    
    var bullArray = [PFObject]();
    var bullGlobal : PFObject!;
    var bulls = [PFObject]();
    var group : PFObject!;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
                // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        if(self.bullArray.count == 0){
            return 1;
        } else {
            return self.bullArray.count + 1;
        }
        //change to the view of multiple views
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        if (indexPath.row == 0){
            let cell = tableView.dequeueReusableCellWithIdentifier("newBullCell", forIndexPath: indexPath);
            return cell;
        }
        
        
        let cell : BullTableViewCell = tableView.dequeueReusableCellWithIdentifier("existingBullCell", forIndexPath: indexPath) as! BullTableViewCell;
        
        if (self.bullArray.count != 0 && indexPath.row > 0){
            
            let bullIDLabel = self.bullArray[indexPath.row - 1]["bullID"] as! String;
            cell.bullID.text = bullIDLabel;
        }
        
        return cell;

    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        if(indexPath.row == 0) {
            getBullID();
        }
        
    }
    
    func getBullID(){
        
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Enter Bull ID", message: "Please Enter a Bull ID", preferredStyle: .Alert)
        
        //2. Add the text field. You can configure it however you need.
        alert.addTextFieldWithConfigurationHandler({ (textField) -> Void in
            textField.text = "";
        })
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            
            //TODO: Clean this up
            let textField = alert.textFields![0] as UITextField;
            let bullIDString = textField.text;
            self.getBullType(bullIDString!);
        }));
        
        alert.addAction(UIAlertAction(title: "Cancel", style: .Default, handler: {
            (action) -> Void in
            //Do nothing
        }));
        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
    }
    
    func SameBullID (bullID: String,bullType: String)
    {
    let alert = UIAlertController(title: "Same ID Alert!", message: "Are you sure you want to continue?", preferredStyle: .Alert)
        
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
    
            self.createBull(bullID,bulltype: bullType);
        }));
        alert.addAction(UIAlertAction(title: "Cancel", style: .Default, handler: {
            (action) -> Void in
            //Do nothing
        }));
        self.presentViewController(alert, animated: true, completion: nil)
    }
    
    func getBullType(bullid :String){
        
        //1. Create the alert controller.
        let alert = UIAlertController(title: "Select Bull Type", message: "Please select a Bull Type", preferredStyle: .Alert)
        
        
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "Tag", style: .Default, handler: { (action) -> Void in
            var temp = 0;
            
            
            //Create a new bull and navigate to the next view
            let query = PFQuery(className: "Bull");
            query.fromLocalDatastore();
            query.whereKey("group", equalTo: self.group);
            query.orderByAscending("bullID");
            query.findObjectsInBackgroundWithBlock { (bullArray: [PFObject]?, error: NSError?) -> Void in
                
                if let bullArray = bullArray as [PFObject]!
                {
                    self.bulls = bullArray as [PFObject];
                    
                    for b in self.bulls
                    {
                        if(b["bullID"] != nil && b["bullTag"] != nil)
                        {
                            let temp1 = b["bullTag"] as! String;
                            if( temp1 == bullid )
                            {
                                self.SameBullID(bullid, bullType: "Tag");
                                temp=1;
                            }
                            
                        }
                        
                    }
                    if(temp==0)
                    {
                        self.createBull(bullid,bulltype: "Tag");
                    }
                }
            }
            
            //TODO: Clean this up
            
        }));
        alert.addAction(UIAlertAction(title: "Tattoo", style: .Default, handler: { (action) -> Void in
            var temp = 0;
            //Create a new bull and navigate to the next view
            let query = PFQuery(className: "Bull");
            query.fromLocalDatastore();
            query.whereKey("group", equalTo: self.group);
            query.orderByAscending("bullID");
            query.findObjectsInBackgroundWithBlock { (bullArray: [PFObject]?, error: NSError?) -> Void in
                
                if let bullArray = bullArray as [PFObject]!
                {
                    self.bulls = bullArray as [PFObject];
                    
                    for b in self.bulls
                    {
                        if(b["bullID"] != nil && b["bullTattoo"] != nil)
                        {
                            let temp1 = b["bullTattoo"] as! String;
                            if( temp1 == bullid)
                            {
                                self.SameBullID(bullid, bullType: "Tattoo");
                                temp=1;
                            }
                            
                        }
                        
                    }
                    if(temp==0)
                    {
                        self.createBull(bullid,bulltype: "Tattoo");
                    }
                }
            }
            
            //TODO: Clean this up
            
        }));

        alert.addAction(UIAlertAction(title: "Brand", style: .Default, handler: { (action) -> Void in
            var temp = 0;
            
            //Create a new bull and navigate to the next view
            let query = PFQuery(className: "Bull");
            query.fromLocalDatastore();
            query.whereKey("group", equalTo: self.group);
            query.orderByAscending("bullID");
            query.findObjectsInBackgroundWithBlock { (bullArray: [PFObject]?, error: NSError?) -> Void in
                
                if let bullArray = bullArray as [PFObject]!
                {
                    self.bulls = bullArray as [PFObject];
                    
                    for b in self.bulls
                    {
                        if(b["bullID"] != nil && b["bullBrand"] != nil)
                        {
                            let temp1 = b["bullBrand"] as! String;
                            if( temp1 == bullid )
                            {
                                self.SameBullID(bullid, bullType: "Brand");
                                temp=1;
                            }
                            
                        }
                        
                    }
                    if(temp==0)
                    {
                        self.createBull(bullid,bulltype: "Brand");
                    }
                }
            }
            
            //TODO: Clean this up
            
        }));
        alert.addAction(UIAlertAction(title: "Other", style: .Default, handler: { (action) -> Void in
            var temp = 0;
            
            //Create a new bull and navigate to the next view
            let query = PFQuery(className: "Bull");
            query.fromLocalDatastore();
            query.whereKey("group", equalTo: self.group);
            query.orderByAscending("bullID");
            query.findObjectsInBackgroundWithBlock { (bullArray: [PFObject]?, error: NSError?) -> Void in
                
                if let bullArray = bullArray as [PFObject]!
                {
                    self.bulls = bullArray as [PFObject];
                    
                    for b in self.bulls
                    {
                        if(b["bullID"] != nil && b["bullOther"] != nil)
                        {
                            let temp1 = b["bullOther"] as! String;
                            if( temp1 == bullid )
                            {
                                self.SameBullID(bullid, bullType: "Other");
                                temp=1;
                            }
                            
                        }
                        
                    }
                    if(temp==0)
                    {
                        self.createBull(bullid,bulltype: "Other");
                    }
                }
            }
            
            //TODO: Clean this up
            
        }));
        alert.addAction(UIAlertAction(title: "RFID", style: .Default, handler: { (action) -> Void in
            var temp = 0;
            
            //Create a new bull and navigate to the next view
            let query = PFQuery(className: "Bull");
            query.fromLocalDatastore();
            query.whereKey("group", equalTo: self.group);
            query.orderByAscending("bullID");
            query.findObjectsInBackgroundWithBlock { (bullArray: [PFObject]?, error: NSError?) -> Void in
                
                if let bullArray = bullArray as [PFObject]!
                {
                    self.bulls = bullArray as [PFObject];
                    
                    for b in self.bulls
                    {
                        if(b["bullID"] != nil && b["bullRFID"] != nil)
                        {
                            let temp1 = b["bullRFID"] as! String;
                            if( temp1 == bullid )
                            {
                                self.SameBullID(bullid, bullType: "RFID");
                                temp=1;
                            }
                            
                        }
                        
                    }
                    if(temp==0)
                    {
                        self.createBull(bullid,bulltype: "RFID");
                    }
                }
            }
            
            //TODO: Clean this up
            
        }));

        
        alert.addAction(UIAlertAction(title: "Cancel", style: .Default, handler: {
            (action) -> Void in
            //Do nothing
        }));
        
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
    }

    func createBull(bullID: String, bulltype : String){
        
        //TODO: Write a check here if the bull exists
        //TODO: Check empty string
        
        let bull = PFObject(className:"Bull")
        bull["bullID"] = bullID;
        bull["group"] = self.group;
        bull["datePerformed"] = NSDate();
        if(bulltype == "Tag")
        {
            bull["bullTag"] = bullID;
        }
        if(bulltype == "Tattoo")
        {
            bull["bullTattoo"] = bullID;
        }
        if(bulltype == "RFID")
        {
            bull["bullRFID"] = bullID;
        }
        if(bulltype == "Brand")
        {
            bull["bullBrand"] = bullID;
        }
        if(bulltype == "Other")
        {
            bull["bullOther"] = bullID;
        }
        
        bull.pinInBackgroundWithBlock({(success: Bool, error: NSError?) -> Void in
            
            self.bullGlobal = bull;
            self.performSegueWithIdentifier("viewBullTVManual", sender: nil);

            
        });

    }
    
    
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        let query = PFQuery(className:"Bull");
        
        query.fromLocalDatastore();
        query.orderByAscending("bullID");
        query.whereKey("group", equalTo: self.group);

        
        
        query.findObjectsInBackgroundWithBlock { (bulls: [PFObject]?, error: NSError?) -> Void in
            
            if let bulls = bulls as [PFObject]!
            {
                self.bullArray = bulls as [PFObject];
                self.tableView.reloadData();
                
            }
            else
            {
                print("Didn't find anything?")
            }
            
        };
    }
    

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

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if(segue.identifier == "viewBullTableView"){
            let singleBullVC = segue.destinationViewController as! SingleBullTableViewController;
            
            singleBullVC.bull = self.bullArray[ self.tableView!.indexPathForSelectedRow!.row - 1];
            singleBullVC.group = self.group;
        }
        else if (segue.identifier == "viewBullTVManual"){
            let singleBullVC = segue.destinationViewController as! SingleBullTableViewController;
            
            singleBullVC.bull = self.bullGlobal;
            singleBullVC.group = self.group;
        }
    }


}
