//
//  ExistingGroupViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chorpa.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class ExistingGroupViewController: UIViewController {
    
    var group: PFObject!;
    
    var user : PFUser!;
    var ranchersArray = [PFObject]();

    var bulls = [PFObject]();
    var lines = [String]();
    
    var header = "animal_age,animal_ageType,animal_brand,animal_breed,animal_dateOfBirth,animal_lotNumber,animal_rfid,animal_tag,animal_tattoo,animal_other,bodyPartInfo_bodyParts_Eye_description,bodyPartInfo_bodyParts_Eye_normal,bodyPartInfo_bodyParts_Feet_description,bodyPartInfo_bodyParts_Feet_normal,bodyPartInfo_bodyParts_Legs_description,bodyPartInfo_bodyParts_Legs_normal,bodyPartInfo_bodyParts_Testicle_description,bodyPartInfo_bodyParts_Testicle_normal,bodyPartInfo_bodyParts_Accessory_description,bodyPartInfo_bodyParts_Accessory_normal,bodyPartInfo_bodyParts_Inguinal_description,bodyPartInfo_bodyParts_Inguinal_normal,bodyPartInfo_bodyParts_Scrotal_description,bodyPartInfo_bodyParts_Scrotal_normal,bodyPartInfo_bodyParts_Epidydimides_description,bodyPartInfo_bodyParts_Epidydimides_normal,bodyPartInfo_bodyParts_Penis_description,bodyPartInfo_bodyParts_Penis_normal,bodyPartInfo_bodyParts_Prepuce_description,bodyPartInfo_bodyParts_Prepuce_normal,bodyPartInfo_bodyParts_Scrotum_description,bodyPartInfo_bodyParts_Scrotum_normal,collectionNumber,completed,datePerformed,generalInfo_classification,generalInfo_comments,matingInfo_comments,matingInfo_performance,matingInfo_performanceDescription,matingInfo_seasonsUsed,matingInfo_sirePastureType,morphologyInfo_comments,morphologyInfo_morphologyMeasurements_0_count,morphologyInfo_morphologyMeasurements_0_morphologyChoice_label,morphologyInfo_morphologyMeasurements_1_count,morphologyInfo_morphologyMeasurements_1_morphologyChoice_label,morphologyInfo_morphologyMeasurements_2_count,morphologyInfo_morphologyMeasurements_2_morphologyChoice_label,morphologyInfo_morphologyMeasurements_3_count,morphologyInfo_morphologyMeasurements_3_morphologyChoice_label,morphologyInfo_morphologyMeasurements_4_count,morphologyInfo_morphologyMeasurements_4_morphologyChoice_label,morphologyInfo_morphologyMeasurements_5_count,morphologyInfo_morphologyMeasurements_5_morphologyChoice_label,morphologyInfo_morphologyMeasurements_6_count,morphologyInfo_morphologyMeasurements_6_morphologyChoice_label,morphologyInfo_morphologyMeasurements_7_count,morphologyInfo_morphologyMeasurements_7_morphologyChoice_label,motilityInfo_comments,motilityInfo_grossMotilityCategory,motilityInfo_individualMotility,motilityInfo_motilityPercent,physicalInfo_bodyCondition,physicalInfo_comments,physicalInfo_frameScore,physicalInfo_hipHeight,physicalInfo_hipHeightUnits,physicalInfo_pelvicXMeasure,physicalInfo_pelvicYMeasure,physicalInfo_scrotalCircumference,rancher_address_address1,rancher_address_address2,rancher_address_city,rancher_address_email,rancher_address_phone,rancher_address_state,rancher_address_zip,rancher_firstName,rancher_lastName,rancher_ranchName,uuid,veterinarian_address_address1,veterinarian_address_address2,veterinarian_address_city,veterinarian_address_email,veterinarian_address_phone,veterinarian_address_state,veterinarian_address_zip,veterinarian_clinicName,veterinarian_firstName,veterinarian_lastName\n";
    
    var finalCSVLine = "";
    
    

    @IBAction func Delete(sender: AnyObject) {
        let alert = UIAlertController(title: "Delete Operation", message: "This feature is disabled till next update.", preferredStyle: .Alert)
        
        
        //3. Grab the value from the text field, and print it when the user clicks OK.
        alert.addAction(UIAlertAction(title: "OK", style: .Default, handler: { (action) -> Void in
           
            /*let query = PFQuery(className:"RanchInfo");
            
            query.fromLocalDatastore();
            query.orderByAscending("updatedAt");
            
            query.findObjectsInBackgroundWithBlock { (ranchers: [PFObject]?, error: NSError?) -> Void in
                if let ranchers = ranchers as [PFObject]!
                {
                    self.ranchersArray = ranchers as [PFObject];
                    
                }
                self.group.unpinInBackgroundWithBlock({(success: Bool, error: NSError?) -> Void in
                for var i = 0; i < self.ranchersArray.count; i++
                    {
                        if(self.group != self.ranchersArray[i])
                        {
                            self.ranchersArray[i].pinInBackgroundWithBlock({(success: Bool, error: NSError?) -> Void in
                                
                            });
                        }
                        
                        
                    }
                    

                });
                                          };

            
            
            if let nav = self.navigationController{
                nav.popViewControllerAnimated(true)
            }*/
            
        }))
        alert.addAction(UIAlertAction(title: "Cancel", style: .Default, handler: { (action) -> Void in
                   }))
        // 4. Present the alert.
        self.presentViewController(alert, animated: true, completion: nil)
        
        
        
        
        
    }
    @IBOutlet var ranchName: UILabel!
    
    @IBOutlet var dateCollected: UILabel!
    
    override func viewDidLoad() {
        
        
          super.viewDidLoad()
        ranchName.text = group["ranchName"] as? String;
        
        let myDateFormatter = NSDateFormatter();
        
        myDateFormatter.dateFormat = "yyyy-MM-dd HH:mm";
        
        let date = self.group["madeAt"] as! NSDate!;
        
        
        dateCollected.text = myDateFormatter.stringFromDate(date);
        
        user = PFUser.currentUser();
        

        // Do any additional setup after loading the view.generalInfo_comments,
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    @IBAction func exportData(sender: AnyObject) {
        
        //Export data here
        lines.append(header);
               let query = PFQuery(className: "Bull");
        query.fromLocalDatastore();
        query.whereKey("group", equalTo: self.group);
        query.orderByAscending("bullID");
        
        query.findObjectsInBackgroundWithBlock { (bullArray: [PFObject]?, error: NSError?) -> Void in
            
            if let bullArray = bullArray as [PFObject]!
            {
                self.bulls = bullArray as [PFObject];
                self.startExport();
                
                
                
            }
            else
            {
                print("Didn't find any bulls.")
            }
            
        };

        
        
        
        
    }
    
    func startExport(){
        var line = "";
        
        for bull in bulls {
            var age : String = "";
            if(bull["AgeMonth"] != nil)
            {
                if(bull["AgeMonth"] as! String != "")
                {age += bull["AgeMonth"] as! String + " Months ";}
                else
                {
                age += "0 Months ";
                }
            }
            if(bull["AgeYear"] != nil)
            {
                if(bull["AgeYear"] as! String != "")
                {age += bull["AgeYear"] as! String + " Years";}
                else
                {
                age += "0 Years";
                }
            }
            line += age + ",,";
            if (bull["bullBrand"] != nil){
                
                    line += bull["bullBrand"] as! String;
                    line += ",";
            }
                else {
                    line += ",";
                }
            
            
            
            if (bull["breed"] != nil){
                line += bull["breed"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            if(bull["dobDate"] != nil){
                let dob = bull["dobDate"] as! NSDate;
                let myDateFormatter = NSDateFormatter();
                
                myDateFormatter.dateFormat = "yyyy-MM-dd HH:mm";
                line += myDateFormatter.stringFromDate(dob);
                line += ",";
            }
            else {
                line += ",";
            }
            
            if (bull["lot"] != nil){
                line += bull["lot"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            if (bull["bullRFID"] != nil){
                
                    line += bull["bullRFID"] as! String;
                    line += ",";
                
               
            }
            else {
                line += ",";
            }
            
            if(bull["bullTag"] != nil){
                 line += bull["bullTag"] as! String;
                    line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(bull["bullTattoo"] != nil){
                    line += bull["bullTattoo"] as! String;
               line += ",";
            }
            else {
                line += ",";
            }
            if(bull["bullOther"] != nil){
                line += bull["bullOther"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }

            if(bull["eyeDescription"] != nil){
            if(bull["eyeDescription"] as! String != ""){
                line += bull["eyeDescription"] as! String;
                line += ",";
                
                
            }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["eyesNormal"] != nil)
            {
            if(bull["eyesNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
            line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"}
            if(bull["feetDescription"] != nil){
            if(bull["feetDescription"] as! String != ""){
                line += bull["feetDescription"] as! String;
                line += ",";
                
                           }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["feetNormal"] != nil)
            {
            if(bull["feetNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"}
            if(bull["legsDescription"] != nil){
            if(bull["legsDescription"] as! String != ""){
                line += bull["legsDescription"] as! String;
                line += ",";
                
            }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["legsNormal"] != nil)
            {
            if(bull["legsNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["testiclesDescription"] != nil){
            if(bull["testiclesDescription"] as! String != ""){
                line += bull["testiclesDescription"] as! String;
                line += ",";
              
                
            }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["testiclesNormal"] != nil)
            {
            if(bull["testiclesNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["accesoryDescription"] != nil){
            if(bull["accesoryDescription"] as! String != ""){
                line += bull["accesoryDescription"] as! String;
                line += ",";
                
            }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["accesoryNormal"] != nil)
            {
            if(bull["accesoryNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
                
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["inguinalDescription"] != nil){
            if(bull["inguinalDescription"] as! String != ""){
                line += bull["inguinalDescription"] as! String;
                line += ",";
                
            }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["inguinalNormal"] != nil)
            {
            if(bull["inguinalNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["scrotalDescription"] != nil){
            if(bull["scrotalDescription"] as! String != ""){
                line += bull["scrotalDescription"] as! String;
                line += ",";
               
                           }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["scrotalNormal"] != nil)
            {
            if(bull["scrotalNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["epidydimidesDescription"] != nil){
            if(bull["epidydimidesDescription"] as! String != ""){
                line += bull["epidydimidesDescription"] as! String;
                line += ",";
                                            }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["epidydimidesNormal"] != nil)
            {
            if(bull["epidydimidesNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["penisDescription"] != nil){
            if(bull["penisDescription"] as! String != ""){
                line += bull["penisDescription"] as! String;
                line += ",";
                                           }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["penisNormal"] != nil)
            {
            if(bull["penisNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["prepuceDescription"] != nil){
            if(bull["prepuceDescription"] as! String != ""){
                line += bull["prepuceDescription"] as! String;
                line += ",";
                                            }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["prepuceNormal"] != nil)
            {
            if(bull["prepuceNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            if(bull["scrotumDescription"] != nil){
            if(bull["scrotumDescription"] as! String != ""){
                line += bull["scrotumDescription"] as! String;
                line += ",";
                              }
            else{
                line += ",";
                }
            }
            else{
                line += ",";
            }
            if(bull["scrotumNormal"] != nil)
            {
            if(bull["scrotumNormal"] as! Bool == true)
            {
                line += "TRUE,"
            }
            else
            {
                line += "FALSE,"
            }
            }
            else
            {
                line += "FALSE,"
            }
            line += "1,,";//codyPartComments,Collection number,completed,
            
            if(bull["datePerformed"] != nil){
                let datePerormed = bull["datePerformed"] as! NSDate;
                let myDateFormatter = NSDateFormatter();
                
                myDateFormatter.dateFormat = "yyyy-MM-dd HH:mm";
                line += myDateFormatter.stringFromDate(datePerormed);
                line += ",";
            }
            else {
                line += ",";
            }
            
            if(bull["classification"] != nil){
                line += bull["classification"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            if(bull["classificationText"] != nil){
                line += bull["classificationText"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            
            
            if(bull["matingComments"] != nil) {
                line += bull["matingComments"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            if(bull["lastSeasonPerformance"] != nil){
                line += bull["lastSeasonPerformance"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            if(bull["matingDescription"] != nil){
                line += bull["matingDescription"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            if(bull["seasonsUsed"] != nil){
                line += bull["seasonsUsed"] as! String;
                line += ",,";//added for not pasture type
            }
            else {
                line += ",,";
            }
            
            if(bull["singleOrMultiSire"] != nil){
                line += bull["singleOrMultiSire"] as! String;
                line += ",";
            }
            else {
                line += ",";
            }
            
            //line += ",";//Morphology comments
            
            //Morphology Value
            //Morphology Fields
            
            let query = PFQuery(className: "Collection");
            query.fromLocalDatastore();
            query.whereKey("bull", equalTo: bull);
            query.orderByDescending("createdAt1");
            var collections = [PFObject]();
            do{
                try collections = query.findObjects();
                if(collections.count > 0){
                    
                    if(collections[0]["m1"] != nil){
                        let temp = collections[0]["m1"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title1"] != nil){
                        line += collections[0]["title1"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                    
                    if(collections[0]["m2"] != nil){
                        let temp = collections[0]["m2"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title2"] != nil){
                        line += collections[0]["title2"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                    
                    if(collections[0]["m3"] != nil){
                        let temp = collections[0]["m3"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title3"] != nil){
                        line += collections[0]["title3"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                    
                    if(collections[0]["m4"] != nil){
                        let temp = collections[0]["m4"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title4"] != nil){
                        line += collections[0]["title4"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                    
                    if(collections[0]["m5"] != nil){
                        let temp = collections[0]["m5"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title5"] != nil){
                        line += collections[0]["title5"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                    
                    if(collections[0]["m6"] != nil){
                        let temp = collections[0]["m6"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title6"] != nil){
                        line += collections[0]["title6"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                    
                    if(collections[0]["m7"] != nil){
                        let temp = collections[0]["m7"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title7"] != nil){
                        line += collections[0]["title7"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                    
                    if(collections[0]["m8"] != nil){
                        let temp = collections[0]["m8"] as! Int;
                        line += String(temp);
                        line += ",";
                        
                    }
                    else {
                        line += ",";
                    }
                    
                    if(collections[0]["title8"] != nil){
                        line += collections[0]["title8"] as! String;
                        line += ",";
                    }
                    else{
                        line += ",";
                    }
                }
                else {
                    line += ",,,,,,,,,,,,,,,,";
                }

            } catch {
                line += ",,,,,,,,,,,,,,,,";
            }
            
            line += ",";//Motility Info Comments
            
            
            if(bull["grossType"] != nil){
                line  += bull["grossType"] as! String;
                line += ",";

            }
            else {
                line += ",";
            }
            
            if(bull["individualMotility"] != nil){
                line  += bull["individualMotility"] as! String;
                line += ",";

            }
            else {
                line += ",";
            }
            
            if(bull["motilityPercent"] != nil){
                line  += bull["motilityPercent"] as! String;
                line += ",";

            }
            else {
                line += ",";
            }
            
            if(bull["bodyCondition"] != nil){
                line  += bull["bodyCondition"] as! String;
                line += ",,";

            }
            else {
                line += ",,";
            }
            
            if(bull["frameScore"] != nil){
                line  += bull["frameScore"] as! String;
                line += ",";

            }
            else {
                line += ",";
            }
            
            if(bull["hipHeight"] != nil){
                line  += bull["hipHeight"] as! String;
                line += ",cm,";
                
            }
            else {
                line += ",cm,";
            }
            
            if(bull["pelvicXMeasurement"] != nil){
                line  += bull["pelvicXMeasurement"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(bull["pelvicYMeasurement"] != nil){
                line  += bull["pelvicYMeasurement"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(bull["scrotalCircumference"] != nil){
                line  += bull["scrotalCircumference"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["address1"] != nil){
                line  += group["address1"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["address2"] != nil){
                line  += group["address2"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["city"] != nil){
                line  += group["city"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["email"] != nil){
                line  += group["email"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["phone"] != nil){
                line  += group["phone"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            
            if(group["state"] != nil){
                line  += group["state"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["zip"] != nil){
                line  += group["zip"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }

            if(group["firstName"] != nil){
                line  += group["firstName"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["firstName"] != nil){
                line  += group["firstName"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(group["ranchName"] != nil){
                line  += group["ranchName"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(bull["datePerformed"] != nil && bull["bullID"] != nil && user["firstName"] != nil){
                let date = bull["datePerformed"] as! NSDate;
                let myDateFor = NSDateFormatter();
                
                myDateFor.dateFormat = "yyyy-MM-dd HH:mm";
                line += myDateFor.stringFromDate(date);
                line += " ";
                line += bull["bullID"] as! String;
                line += " ";
                line += user["firstName"] as! String;
                line += ",";
            }
            else { line += "," ;}
            
            
            if(user["address1"] != nil){
                line  += user["address1"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["address2"] != nil){
                line  += user["address2"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["city"] != nil){
                line  += user["city"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["email"] != nil){
                line  += user["email"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["phone"] != nil){
                line  += user["phone"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["state"] != nil){
                line  += user["state"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["zip"] != nil){
                line  += user["zip"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["clinicName"] != nil){
                line  += user["clinicName"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["firstName"] != nil){
                line  += user["firstName"] as! String;
                line += ",";
                
            }
            else {
                line += ",";
            }
            
            if(user["lastName"] != nil){
                line  += user["lastName"] as! String;
                
                
            }
            else {
                
            }
            line += "\n";
            lines.append(line);
            line = "";
            
        }
        
        for singleLine in lines{
            finalCSVLine += singleLine;
        }
        
        //export CSV here
        saveAndExport(finalCSVLine);
        
        finalCSVLine = "";
        lines = [String]();
        
        
    }
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        if(segue.identifier == "viewBullsFromEdit"){
            let bullTableVC = segue.destinationViewController as! BullTableViewController;
            
            bullTableVC.group = self.group;
        }
        if(segue.identifier=="EditGroup"){
            let NewbullVC = segue.destinationViewController as! NewGroupViewController;
            
            NewbullVC.group = self.group;

        }
    }
    
    func saveAndExport(exportString: String) {
        let exportFilePath = NSTemporaryDirectory() + "export8.csv"
        let exportFileURL = NSURL(fileURLWithPath: exportFilePath)
        NSFileManager.defaultManager().createFileAtPath(exportFilePath, contents: NSData(), attributes: nil)
        var fileHandleError: NSError? = nil
        var fileHandle: NSFileHandle? = nil
        do {
            fileHandle = try NSFileHandle(forWritingToURL: exportFileURL)
        } catch {
            print("Error with fileHandle")
        }
        
        if fileHandle != nil {
            fileHandle!.seekToEndOfFile()
            let csvData = exportString.dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: false)
            fileHandle!.writeData(csvData!)
            
            fileHandle!.closeFile()
            
            let firstActivityItem = NSURL(fileURLWithPath: exportFilePath)
            let activityViewController : UIActivityViewController = UIActivityViewController(
                activityItems: [firstActivityItem], applicationActivities: nil)
            
            activityViewController.excludedActivityTypes = [
                UIActivityTypeAssignToContact,
                UIActivityTypeSaveToCameraRoll,
                UIActivityTypePostToFlickr,
                UIActivityTypePostToVimeo,
                UIActivityTypePostToTencentWeibo
            ]
            
            self.presentViewController(activityViewController, animated: true, completion: nil)
        }
    }
    

}
