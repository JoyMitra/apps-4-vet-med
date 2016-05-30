//
//  MorphologyTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class MorphologyTableViewController: UITableViewController {
    
    var bull : PFObject!;
    var collections = [PFObject]();
    var group: PFObject!;
    var globalCollection : PFObject!;
    
    @IBOutlet var individualMotility: UITextField!
    @IBOutlet var motilityPercent: UITextField!
    @IBOutlet var motilityCategory: UISegmentedControl!
    
    

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
        if(self.collections.count == 0){
            return 1;
        } else {
            return self.collections.count + 1;
        }
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        if (indexPath.row == 0){
            let cell = tableView.dequeueReusableCellWithIdentifier("newCollection", forIndexPath: indexPath);
            return cell;
        }
        
       
        
        
        let cell : CollectionTableViewCell = tableView.dequeueReusableCellWithIdentifier("existingCollection", forIndexPath: indexPath) as! CollectionTableViewCell;
        
        if (self.collections.count != 0 && indexPath.row > 0){
            
            let totalCount = self.collections[indexPath.row - 1]["totalCount"] as! Int;
            
            let date = self.collections[indexPath.row - 1]["createdAt1"] as! NSDate;
            
            let dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = "yyyy-MM-dd HH:mm" //format style. Browse online to get a format that fits your needs.
            let dateString = dateFormatter.stringFromDate(date);
            
            cell.collectionCount.text = "Total Count: " + String(totalCount);
            cell.collectionDate.text = "Collected On: " + dateString;
        }
        
        return cell;
    }
    
    
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        let query = PFQuery(className: "Collection");
        
        query.fromLocalDatastore();
        query.orderByAscending("createdAt1");
        query.whereKey("bull", equalTo: self.bull);
        
        query.findObjectsInBackgroundWithBlock { (collectionsArray: [PFObject]?, error: NSError?) -> Void in
            
            if let collectionsArray = collectionsArray as [PFObject]!
            {
                self.collections = collectionsArray as [PFObject];
                self.tableView.reloadData();
                
            }
            else
            {
                print("Didn't find anything?")
            }
            
        };

        
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        if(indexPath.row == 0){
            createNewCollection();
        }
        
    }
    
    
    func createNewCollection(){
        
        let newCollection = PFObject(className: "Collection");
        newCollection["bull"] = self.bull;
        newCollection["totalCount"] = 0;
        
        newCollection.pinInBackgroundWithBlock({(success: Bool, error: NSError?) -> Void in
            
            self.globalCollection = newCollection;
            
            self.performSegueWithIdentifier("showCounterNew", sender: nil);
            
        });
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
        
        if(segue.identifier == "showCounterNew"){
            
            //new bull
            
            let semenCounter = segue.destinationViewController as! SemenCounterViewController;
            
            semenCounter.collection = self.globalCollection;
            semenCounter.group = self.group;
        }
        
        else if (segue.identifier == "showCounter"){
            
            //existing bull
            let semenCounter = segue.destinationViewController as! SemenCounterViewController;
            
            semenCounter.group = self.group;
            semenCounter.collection = self.collections[ self.tableView!.indexPathForSelectedRow!.row - 1];
            
            
            
        }
    }
    

}
