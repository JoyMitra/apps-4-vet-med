//
//  GroupsTableViewTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class GroupsTableViewTableViewController: UITableViewController {
    
    var ranchersArray = [PFObject]();

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        //self.tableView.reloadData() - this will redraw the table, call from parse callback
        
        //let ish : AppDelegate = UIApplication.sharedApplication().delegate as! AppDelegate;
        
        
      
    }

    @IBAction func Delete30(sender: AnyObject) {
        let alert = UIAlertController(title: "WARNING!", message: "This will permenently delete data prior to 30 days", preferredStyle: .Alert)
        
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
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
                    self.ranchersArray[i].unpinInBackground()
                    self.ranchersArray[i].deleteInBackground()
                    
                }
                
                                            }
            if let nav = self.navigationController{
                nav.popViewControllerAnimated(true)
            }
            
        }))
        alert.addAction(UIAlertAction(title: "Cancel", style: .Default, handler: { (action) -> Void in
        }))
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        

    }
    @IBAction func Delete(sender: AnyObject) {
        let alert = UIAlertController(title: "WARNING!", message: "This will permenently delete all of the data", preferredStyle: .Alert)
        
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
            for var i = 0; i < self.ranchersArray.count; i++
            {
                self.ranchersArray[i].unpinInBackground()
                self.ranchersArray[i].deleteInBackground()
            }
            if let nav = self.navigationController{
                nav.popViewControllerAnimated(true)
            }
            
        }))
        alert.addAction(UIAlertAction(title: "Cancel", style: .Default, handler: { (action) -> Void in
        }))
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        

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
        if(self.ranchersArray.count == 0){
            return 1;
        } else {
            return self.ranchersArray.count + 1;
        }
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
         //= tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)

        // Configure the cell...
        if (indexPath.row == 0) {
            let cell = tableView.dequeueReusableCellWithIdentifier("newGroupCell", forIndexPath: indexPath);
            return cell;
        }
        
        let cell : ExistingGroupTableViewCell = tableView.dequeueReusableCellWithIdentifier("existingGroupCell", forIndexPath: indexPath) as! ExistingGroupTableViewCell;

        
        if (self.ranchersArray.count != 0 && indexPath.row > 0){
            
            let ranchName = self.ranchersArray[indexPath.row - 1]["ranchName"] as! String;
            cell.existingGroupName.text = ranchName;
            
            let myDateFormatter = NSDateFormatter();
            
            myDateFormatter.dateFormat = "yyyy-MM-dd HH:mm";
            
            let date = self.ranchersArray[indexPath.row - 1]["madeAt"] as! NSDate!;
            
            let strDate = myDateFormatter.stringFromDate(date);
            
            cell.existingGroupDate.text = strDate;
            
        }
        
        
        

        return cell
    }
    
    //When a row is selected this is called.
    override func tableView(tableView: UITableView, didDeselectRowAtIndexPath indexPath: NSIndexPath) {
        let cell = self.tableView.cellForRowAtIndexPath(indexPath)
        let text = cell?.textLabel?.text
        if let text = text {
            print("did select and the text is text" + text)
        }
    }
    
    //Called on both load of forward and backward
    override func viewWillAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        let query = PFQuery(className:"RanchInfo");
        
        query.fromLocalDatastore();
        query.orderByAscending("updatedAt");
        
        query.findObjectsInBackgroundWithBlock { (ranchers: [PFObject]?, error: NSError?) -> Void in
            
            if let ranchers = ranchers as [PFObject]!
            {
                self.ranchersArray = ranchers as [PFObject];
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
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        if(segue.identifier == "editGroups"){
            let existingGroupVC = segue.destinationViewController as! ExistingGroupViewController;
            
            existingGroupVC.group = self.ranchersArray[ self.tableView!.indexPathForSelectedRow!.row - 1];
        }
    }


}
