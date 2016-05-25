//
//  ClassificationViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse

class ClassificationViewController: UIViewController {
    
    var bull : PFObject!;
    
    @IBOutlet var classificationSegment: UISegmentedControl!
    @IBOutlet var commentsText: UITextField!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        commentsText.text = bull["classificationText"] as? String;
        
        if(bull["classification"] != nil){
            let bullClassification = bull["classification"] as! String;
            
            if( bullClassification == "Satisfactory"){
                classificationSegment.selectedSegmentIndex = 0;
            }
            else if (bullClassification == "Deferred"){
                classificationSegment.selectedSegmentIndex = 1;
            }
            else if (bullClassification == "Questionable"){
                classificationSegment.selectedSegmentIndex = 2;
            }
            else if (bullClassification == "Unsatisfactory"){
                classificationSegment.selectedSegmentIndex = 3;
            }
        }

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillDisappear(animated: Bool) {
        bull.pinInBackground();
         }
    
    
    @IBAction func save(sender: AnyObject) {
        if(commentsText.text != nil){
            bull["classificationText"] = commentsText.text;
        }
        
        bull.pinInBackground();
        if let nav = navigationController
        {
            nav.popViewControllerAnimated(true)
        }
    }
    
    @IBAction func segmentSelected(sender: UISegmentedControl) {
        
        switch classificationSegment.selectedSegmentIndex
        {
        case 0 :
            bull["classification"] = "Satisfactory";
        case 1 :
            bull["classification"] = "Deferred";
        case 2 :
            bull["classification"] = "Questionable";
        case 3 :
            bull["classification"] = "Unsatisfactory";
        default :
            break;
        }
        
        bull.pinInBackground();
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
