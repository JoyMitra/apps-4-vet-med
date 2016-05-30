//
//  PhysicalSingleViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse


class PhysicalSingleViewController: UIViewController {
    
    var bull : PFObject!;

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        if(segue.identifier == "viewMeasurements")
        {
            let bullMeasurementsSegue = segue.destinationViewController as! MeasurementsTableViewController;
            
            bullMeasurementsSegue.bull = self.bull;
        }
        else if (segue.identifier == "viewPhysicalExam"){
            //navigate to physical exam
            let bullPhysical = segue.destinationViewController as! PhysicalExamTableViewController;
            
            bullPhysical.bull = self.bull;
        }
        
        
        
        
    }


}
