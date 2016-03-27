//
//  MotilityInfoViewController.swift
//  BSE
//
//  Created by Chris Piggott on 11/29/15.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class MotilityInfoViewController: UIViewController {

    var bull : PFObject!;
    
    @IBOutlet var individualMotility: UITextField!
    @IBOutlet var motilityPercent: UITextField!
    @IBOutlet var grossMotility: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        if(bull["individualMotility"] != nil){
            individualMotility.text = bull["individualMotility"] as? String;
        }
        
        if(bull["motilityPercent"] != nil){
            motilityPercent.text = bull["motilityPercent"] as? String;
        }
        
        if(bull["grossType"] != nil){
            let grossType = bull["grossType"] as? String;
            
            if (grossType == "Poor"){
                grossMotility.selectedSegmentIndex = 0;
            }
            else if(grossType == "Fair"){
                grossMotility.selectedSegmentIndex = 1;
            }
            else if (grossType == "Good"){
                grossMotility.selectedSegmentIndex = 2;
            }
            else if (grossMotility == "Very Good"){
                grossMotility.selectedSegmentIndex = 3;
            }
        }
        

    }
    @IBAction func Save(sender: AnyObject) {
        if(individualMotility.text != nil){
            bull["individualMotility"] = individualMotility.text;
        }
        
        if(motilityPercent.text != nil){
            bull["motilityPercent"] = motilityPercent.text;
        }
        
        bull.pinInBackground();
        if let nav = navigationController{
        nav.popViewControllerAnimated(true)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillDisappear(animated: Bool) {
        
        
    }
    
    @IBAction func gossChanged(sender: AnyObject) {
        switch grossMotility.selectedSegmentIndex
        {
        case 0:
            bull["grossType"] = "Poor";
        case 1:
            bull["grossType"] = "Fair";
        case 2:
            bull["grossType"] = "Good";
        case 3:
            bull["grossType"] = "Very Good";
        default:
            break;

        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
