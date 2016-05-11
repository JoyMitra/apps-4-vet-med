//
//  PhysicalExamTableViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class PhysicalExamTableViewController: UITableViewController {
    
    var bull : PFObject!;
    
    @IBOutlet var AllNormal: UISwitch!
    @IBOutlet var ScrotumNormal: UISwitch!
    @IBOutlet var PrepuceNormal: UISwitch!
    @IBOutlet var PenisNormal: UISwitch!
    @IBOutlet var EpidimidesNormal: UISwitch!
    @IBOutlet var ScrotalNormal: UISwitch!
    @IBOutlet var InguinalNormal: UISwitch!
    @IBOutlet var AccesoryNormal: UISwitch!
    @IBOutlet var TesticlesNormal: UISwitch!
    @IBOutlet var LegsNormal: UISwitch!
    @IBOutlet var eyeDescription: UITextField!
    @IBOutlet var EyeNormal: UISwitch!
    @IBOutlet var feetDescription: UITextField!
    @IBOutlet var FeetNormal: UISwitch!
    @IBOutlet var legsDescription: UITextField!
    @IBOutlet var testiclesDescription: UITextField!
    @IBOutlet var accesoryDescription: UITextField!
    @IBOutlet var inguinalDescription: UITextField!
    @IBOutlet var scrotalDescription: UITextField!
    @IBOutlet var epidydimidesDescription: UITextField!
    @IBOutlet var penisDescription: UITextField!
    @IBOutlet var prepuceDescription: UITextField!
    @IBOutlet var scrotumDescription: UITextField!
    
    @IBAction func AllNormal(sender: UISwitch) {
        if AllNormal.on {
        EyeNormal.setOn(true, animated: true)
        FeetNormal.setOn(true, animated: true)
            LegsNormal.setOn(true, animated: true)
            TesticlesNormal.setOn(true, animated: true)
InguinalNormal.setOn(true, animated: true)
            ScrotalNormal.setOn(true, animated: true)
            EpidimidesNormal.setOn(true, animated: true)
           PenisNormal.setOn(true, animated: true)
        PrepuceNormal.setOn(true, animated: true)
          ScrotumNormal.setOn(true, animated: true)
            AccesoryNormal.setOn(true, animated: true)

            
    }
        else
            {
                EyeNormal.setOn(false, animated: true)
                FeetNormal.setOn(false, animated: true)
                LegsNormal.setOn(false, animated: true)
                TesticlesNormal.setOn(false, animated: true)
                InguinalNormal.setOn(false, animated: true)
                ScrotalNormal.setOn(false, animated: true)
                EpidimidesNormal.setOn(false, animated: true)
                PenisNormal.setOn(false, animated: true)
                PrepuceNormal.setOn(false, animated: true)
                ScrotumNormal.setOn(false, animated: true)
                AccesoryNormal.setOn(false, animated: true)

        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        eyeDescription.text = bull["eyeDescription"] as? String;
        feetDescription.text = bull["feetDescription"] as? String;
        legsDescription.text = bull["legsDescription"] as? String;
        testiclesDescription.text =  bull["testiclesDescription"] as? String;
        accesoryDescription.text = bull["accesoryDescription"] as? String;
        inguinalDescription.text = bull["inguinalDescription"] as? String;
        scrotalDescription.text = bull["scrotalDescription"] as? String;
        epidydimidesDescription.text = bull["epidydimidesDescription"] as? String;
        penisDescription.text = bull["penisDescription"] as? String;
        prepuceDescription.text = bull["prepuceDescription"] as? String;
        scrotumDescription.text = bull["scrotumDescription"] as? String;
        if(bull["eyesNormal"] != nil)
        {
        if(bull["eyesNormal"] as! Bool == true)
        {
            EyeNormal.setOn(true, animated: true)
        }
        }
        if(bull["accesoryNormal"] != nil)
        {
            if(bull["accesoryNormal"] as! Bool == true)
            {
                AccesoryNormal.setOn(true, animated: true)
            }
        }

        if(bull["feetNormal"] != nil)
        {
        if(bull["feetNormal"] as! Bool == true)
        {
            FeetNormal.setOn(true, animated: true)
        }
        }
        if(bull["legsNormal"] != nil)
        {
        if(bull["legsNormal"] as! Bool == true)
        {
            LegsNormal.setOn(true, animated: true)
        }
        }
        if(bull["testiclesNormal"] != nil)
        {
        if(bull["testiclesNormal"] as! Bool == true)
        {
            TesticlesNormal.setOn(true, animated: true)
        }
        }
        if(bull["inguinalNormal"] != nil)
        {
        if(bull["inguinalNormal"] as! Bool == true)
        {
            InguinalNormal.setOn(true, animated: true)
        }
        }
        if(bull["scrotalNormal"] != nil)
        {
        if(bull["scrotalNormal"] as! Bool == true)
        {
            ScrotalNormal.setOn(true, animated: true)
        }
        }
        if(bull["epidydimidesNormal"] != nil)
        {
        if(bull["epidydimidesNormal"] as! Bool == true)
        {
            EpidimidesNormal.setOn(true, animated: true)
        }
        }
        if(bull["penisNormal"] != nil)
        {
        if(bull["penisNormal"] as! Bool == true)
        {
            PenisNormal.setOn(true, animated: true)
        }
        }
        if(bull["prepuceNormal"] != nil)
        {
        if(bull["prepuceNormal"] as! Bool == true)
        {
            PrepuceNormal.setOn(true, animated: true)
        }
        }
        if(bull["scrotumNormal"] != nil)
        {
        if(bull["scrotumNormal"] as! Bool == true)
        {
            ScrotumNormal.setOn(true, animated: true)
        }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBOutlet var Save: UIButton!
    @IBAction func Savebut(sender: AnyObject) {
        
        
        if(eyeDescription.text != nil){
            bull["eyeDescription"] = eyeDescription.text;
            
        }
        if EyeNormal.on {
            bull["eyesNormal"]=true
            
        }
        else {bull["eyesNormal"]=false}
        if FeetNormal.on {
            bull["feetNormal"]=true
            
        }
        else {bull["feetNormal"]=false}
        
        if(feetDescription.text != nil){
            bull["feetDescription"] = feetDescription.text;
            
        }
        
        if(legsDescription.text != nil){
            bull["legsDescription"] = legsDescription.text;
        }
        if LegsNormal.on {
            bull["legsNormal"]=true
            
        }
        else {bull["legsNormal"]=false}
        if(testiclesDescription.text != nil){
            bull["testiclesDescription"] = testiclesDescription.text;
            
        }
        if TesticlesNormal.on {
            bull["testiclesNormal"]=true
            
        }
        else {bull["testiclesNormal"]=false}
        if(accesoryDescription.text != nil){
            bull["accesoryDescription"] = accesoryDescription.text;
            
        }
        if AccesoryNormal.on {
            bull["accesoryNormal"]=true
            
        }
        else {bull["accesoryNormal"]=false}
        if(inguinalDescription.text != nil){
            bull["inguinalDescription"] = inguinalDescription.text;
            
        }
        if InguinalNormal.on {
            bull["inguinalNormal"]=true
            
        }
        else {bull["inguinalNormal"]=false}
        if(scrotalDescription.text != nil){
            bull["scrotalDescription"] = scrotalDescription.text;
            
        }
        if ScrotalNormal.on {
            bull["scrotalNormal"]=true
            
        }
        else {bull["scrotalNormal"]=false}
        if(epidydimidesDescription.text != nil){
            bull["epidydimidesDescription"] = epidydimidesDescription.text;
        }
        if EpidimidesNormal.on {
            bull["epidydimidesNormal"]=true
            
        }
        else {bull["epidydimidesNormal"]=false}
        
        if(penisDescription.text != nil){
            bull["penisDescription"] = penisDescription.text;
            
        }
        
        if PenisNormal.on {
            bull["penisNormal"]=true
            
        }
        else {bull["penisNormal"]=false}
        if(prepuceDescription.text != nil){
            bull["prepuceDescription"] = prepuceDescription.text;
            
        }
        if PrepuceNormal.on {
            bull["prepuceNormal"]=true
            
        }
        else {bull["prepuceNormal"]=false}
        
        if(scrotumDescription.text != nil){
            bull["scrotumDescription"] = scrotumDescription.text;
        }
        if ScrotumNormal.on {
            bull["scrotumNormal"]=true
            
        }
        else {bull["scrotumNormal"]=false}
        bull.pinInBackground();
        
        
        if let nav = navigationController{
        nav.popViewControllerAnimated(true)
        }
    }
    override func viewWillDisappear(animated: Bool) {
        bull.pinInBackground();
        
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
