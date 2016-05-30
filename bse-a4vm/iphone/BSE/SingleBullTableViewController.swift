//
//  SingleBullTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class SingleBullTableViewController: UITableViewController {
    
    var bull: PFObject!;
    var group : PFObject!;
    
    @IBOutlet var Cell7: UITableViewCell!
    @IBOutlet var Cell6: UITableViewCell!
    @IBOutlet var Cell5: UITableViewCell!
    @IBOutlet var Cell4: UITableViewCell!
    @IBOutlet var Cell3: UITableViewCell!
    @IBOutlet var Cell2: UITableViewCell!
    @IBOutlet var Cell1: UITableViewCell!
    @IBOutlet weak var Button1: UIButton!
    @IBOutlet weak var Button2: UIButton!
    @IBOutlet weak var Button3: UIButton!
    @IBOutlet weak var Button4: UIButton!
    @IBOutlet weak var Button5: UIButton!
    @IBOutlet weak var Button6: UIButton!
    @IBOutlet weak var Button7: UIButton!
    

    var user : PFUser!;
    override func viewWillAppear(_animated: Bool) {
    viewDidLoad() // to reload selected cell
    }
    override func viewWillDisappear(animated: Bool) {
        }

    override func viewDidLoad() {
        super.viewDidLoad()
        user = PFUser.currentUser();
        self.title = "Bull ID: " + (bull["bullID"] as! String);
        bull["examDate"] = NSDate();
        bull.pinInBackground();
        
        
        let examDate = bull["examDate"] as! NSDate;
        
        var string1 = "x";
        var string2 = "x";
        if (bull["dobDate"] != nil)
        {
        let dob = bull["dobDate"] as! NSDate;
        let myDateFormatter = NSDateFormatter();
        myDateFormatter.dateFormat = "yyyy-MM-dd";
        
         string1 = myDateFormatter.stringFromDate(dob);
        string2 = myDateFormatter.stringFromDate(examDate);
        }
        if(bull["lot"] != nil && bull["breed"] != nil)
        {
        if (bull["lot"] as! String != "" || bull["breed"] as! String != "" || string1 != string2 )
        {
            let image = UIImage(named: "Blue");
            Button1.setImage(image, forState: .Normal);
            Cell1.contentView.layer.borderWidth
                = 1.0;
            Cell1.contentView.layer.borderColor=UIColor.blueColor().CGColor;
        }
            if(bull["AgeMonth"] != nil || bull["AgeYear"] != nil )
            {
                if (bull["AgeMonth"] as! String != "" || bull["AgeYear"] as! String != "" )
                {
                    let image = UIImage(named: "Blue");
                    Button1.setImage(image, forState: .Normal);
                    Cell1.contentView.layer.borderWidth
                        = 1.0;
                    Cell1.contentView.layer.borderColor=UIColor.blueColor().CGColor;
                }
            }
            if (bull["lot"] as! String != "" && bull["breed"] as! String != "")
        {
            if(bull["AgeMonth"]  != nil && bull["AgeYear"] != nil  )
            {
            if(bull["AgeMonth"] as! String != "" && bull["AgeYear"] as! String != "")
            {
            let image = UIImage(named: "Green");
            Button1.setImage(image, forState: .Normal);
            Cell1.contentView.layer.borderWidth
                = 1.0;
            Cell1.contentView.layer.borderColor=UIColor.greenColor().CGColor;
                }
                }
            }
        }
        if(bull["seasonsUsed"] != nil || bull["lastSeasonPerformance"] != nil || bull["matingDescription"] != nil || bull["singleOrMultiSire"] != nil)
        {
       if (bull["seasonsUsed"] as! String != "" || bull["lastSeasonPerformance"] != nil || bull["matingDescription"] as! String != "" || bull["singleOrMultiSire"] != nil)
       {
            let image = UIImage(named: "Blue");
            Button2.setImage(image, forState: .Normal);
        Cell2.contentView.layer.borderWidth
            = 1.0;
        Cell2.contentView.layer.borderColor=UIColor.blueColor().CGColor;
        
        }
        if (bull["seasonsUsed"] as! String != "" && bull["lastSeasonPerformance"] != nil && bull["matingDescription"] as! String != "" && bull["singleOrMultiSire"] != nil)
        {
            let image = UIImage(named: "Green");
            Button2.setImage(image, forState: .Normal);
            Cell2.contentView.layer.borderWidth
                = 1.0;
            Cell2.contentView.layer.borderColor=UIColor.greenColor().CGColor;
        }
        }
        if (bull["eyeDescription"] == nil && bull["feetDescription"] == nil  && bull["legsDescription"] == nil && bull["testiclesDescription"] == nil && bull["accesoryDescription"] == nil && bull["inguinalDescription"] == nil && bull["scrotalDescription"] == nil && bull["epidydimidesDescription"] == nil && bull["penisDescription"] == nil && bull["prepuceDescription"] == nil && bull["scrotumDescription"] == nil && bull["bodyCondition"] == nil && bull["frameScore"] == nil && bull["hipHeight"] == nil && bull["pelvicXMeasurement"] == nil && bull["pelvicYMeasurement"] == nil && bull["scrotalCircumference"] == nil )
        {
            bull["eyeDescription"] = "" ; bull["feetDescription"] = "" ; bull["legsDescription"] = "" ; bull["testiclesDescription"] = ""; bull["accesoryDescription"] = "" ; bull["inguinalDescription"] = ""; bull["scrotalDescription"] = "" ; bull["epidydimidesDescription"] = "" ; bull["penisDescription"] = ""; bull["prepuceDescription"] = "" ; bull["scrotumDescription"] = "" ; bull["bodyCondition"] = "" ; bull["frameScore"] = "" ; bull["hipHeight"] = "" ; bull["pelvicXMeasurement"] = ""; bull["pelvicYMeasurement"] = "" ; bull["scrotalCircumference"] = "";
        }
        if (bull["eyeDescription"] as! String != "" || bull["feetDescription"] as! String != "" || bull["legsDescription"] as! String != "" || bull["testiclesDescription"] as! String != "" || bull["accesoryDescription"] as! String != "" || bull["inguinalDescription"] as! String != "" || bull["scrotalDescription"] as! String != "" || bull["epidydimidesDescription"] as! String != "" || bull["penisDescription"] as! String != "" || bull["prepuceDescription"] as! String != "" || bull["scrotumDescription"] as! String != "" || bull["bodyCondition"] as! String != "" || bull["frameScore"] as! String != "" || bull["hipHeight"] as! String != "" || bull["pelvicXMeasurement"] as! String != "" || bull["pelvicYMeasurement"] as! String != "" || bull["scrotalCircumference"] as! String != "" )
        {
            let image = UIImage(named: "Blue");
            Button3.setImage(image, forState: .Normal);
            Cell3.contentView.layer.borderWidth
                = 1.0;
            Cell3.contentView.layer.borderColor=UIColor.blueColor().CGColor;
            
        }
        if (bull["eyeDescription"] as! String != "" && bull["feetDescription"] as! String != "" && bull["legsDescription"] as! String != "" && bull["testiclesDescription"] as! String != "" && bull["accesoryDescription"] as! String != "" && bull["inguinalDescription"] as! String != "" && bull["scrotalDescription"] as! String != "" && bull["epidydimidesDescription"] as! String != "" && bull["penisDescription"] as! String != "" && bull["prepuceDescription"] as! String != "" && bull["scrotumDescription"] as! String != "" && bull["bodyCondition"] as! String != "" && bull["frameScore"] as! String != "" && bull["hipHeight"] as! String != "" && bull["pelvicXMeasurement"] as! String != "" && bull["pelvicYMeasurement"] as! String != "" && bull["scrotalCircumference"] as! String != "" )
        {
            let image = UIImage(named: "Green");
            Button3.setImage(image, forState: .Normal);
            Cell3.contentView.layer.borderWidth
                = 1.0;
            Cell3.contentView.layer.borderColor=UIColor.greenColor().CGColor;
            
        }
        
        
        let query = PFQuery(className: "Collection");
        query.fromLocalDatastore();
        query.whereKey("bull", equalTo: bull);
        query.orderByDescending("createdAt1");
        var collections = [PFObject]();
        do{
            try collections = query.findObjects();
            if(collections.count != 0)
            {
        
            
                    let image = UIImage(named: "Blue");
                    Button4.setImage(image, forState: .Normal);
                Cell4.contentView.layer.borderWidth
                    = 1.0;
                Cell4.contentView.layer.borderColor=UIColor.blueColor().CGColor;
                if (collections[0]["m1"] as! Int > 0 || user["m1"] as? String == "" )
                {
                    if (collections[0]["m2"] as! Int > 0 || user["m2"] as? String == "" )
                    {
                    if (collections[0]["m3"] as! Int > 0 || user["m3"] as? String == "" )
                    {
                        if (collections[0]["m4"] as! Int > 0 || user["m5"] as? String == "" )
                        {
                        if (collections[0]["m5"] as! Int > 0 || user["m5"] as? String == "" )
                            {
                            if (collections[0]["m6"] as! Int > 0 || user["m6"] as? String == "" )
                            {
                                if (collections[0]["m7"] as! Int > 0 || user["m7"] as? String == "" )
                                
                                {
                                if (collections[0]["m8"] as! Int > 0 || user["m8"] as? String == "" )
                                {
                                    let image = UIImage(named: "Green");
                                    Button4.setImage(image, forState: .Normal);
                                    Cell4.contentView.layer.borderWidth
                                        = 1.0;
                                    Cell4.contentView.layer.borderColor=UIColor.greenColor().CGColor;
                                    }
                                    
                                }
                                }
                            }
                        }
                        }
                    }
                }
                
        }
        }
        catch{}
        
if( bull["grossType"] == nil && bull["individualMotility"] == nil && bull["motilityPercen"] == nil)
    {
        bull["grossType"] = ""; bull["individualMotility"] = "" ; bull["motilityPercen"] = "";
        
        }
if(bull["grossType"] as! String != "" || bull["individualMotility"] as! String != "" ||  bull["motilityPercen"] as! String != "" )
    {
        let image = UIImage(named: "Blue");
        Button5.setImage(image, forState: .Normal);
        Cell5.contentView.layer.borderWidth
            = 1.0;
        Cell5.contentView.layer.borderColor=UIColor.blueColor().CGColor;
    }
if(bull["grossType"] as! String != "" && bull["individualMotility"]as! String != "" &&  bull["motilityPercen"] as! String != "" )
    {
    let image = UIImage(named: "Green");
    Button5.setImage(image, forState: .Normal);
        Cell5.contentView.layer.borderWidth
            = 1.0;
        Cell5.contentView.layer.borderColor=UIColor.greenColor().CGColor;
    
    }
    if(bull["classification"] != nil)
    {
        if(bull["classification"] as! String != "")
        {
            let image = UIImage(named: "Green");
            Button6.setImage(image, forState: .Normal);
            Cell6.contentView.layer.borderWidth
                = 1.0;
            Cell6.contentView.layer.borderColor=UIColor.greenColor().CGColor;
        }
        }
    
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
        return 6
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

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
                
        
        if(segue.identifier == "viewBullInfoTableView"){
            let bullInfoVC = segue.destinationViewController as! BullInfoViewController;
        
            bullInfoVC.bull = self.bull;
            bullInfoVC.group = self.group;
        }

        else if (segue.identifier == "viewSexDrive") {
            let bullSexDrive = segue.destinationViewController as! BullMatingViewController;
            
            bullSexDrive.bull = self.bull;
        }
        else if (segue.identifier == "viewPhysicalParameters")
        {
            let bullPhysical = segue.destinationViewController as! PhysicalSingleViewController;
            
            bullPhysical.bull = self.bull;
        }
        
        else if (segue.identifier == "viewClassification") {
            let bullClassification = segue.destinationViewController as! ClassificationViewController;
            
            bullClassification.bull = self.bull;
        }
        
        else if (segue.identifier == "viewSemenQuality"){
            let semenQuality = segue.destinationViewController as! MorphologyTableViewController;
            semenQuality.group = self.group;
            semenQuality.bull = self.bull;
        }
        
        else if (segue.identifier == "showMotilityFields"){
            
            let motility = segue.destinationViewController as! MotilityInfoViewController;
            
            motility.bull = self.bull;
            
        }
    }
    

}
