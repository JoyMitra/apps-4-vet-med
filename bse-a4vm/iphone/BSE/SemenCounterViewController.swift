//
//  SemenCounterViewController.swift
//  BSE
//
//  Created by Chris Piggott and Shubh Chopra.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

import Parse;
import AVFoundation;
class SemenCounterViewController: UIViewController {
    
    var button1Count = 0.0;
    var button2Count = 0.0;
    var button3Count = 0.0;
    var button4Count = 0.0;
    var button5Count = 0.0;
    var button6Count = 0.0;
    var button7Count = 0.0;
    var button8Count = 0.0;
    var button9Count = 0.0;
    var button1P = 0.0;
    var button2P = 0.0;
    var button3P = 0.0;
    var button4P = 0.0;
    var button5P = 0.0;
    var button6P = 0.0;
    var button7P = 0.0;
    var button8P = 0.0;
    var button9P = 0.0;
    
    var totalCount = 0.0;
    var sound = NSURL(fileURLWithPath: NSBundle.mainBundle().pathForResource("beep-03", ofType: "wav")!);
    var audioplayer = AVAudioPlayer()
    var sound2 = NSURL(fileURLWithPath: NSBundle.mainBundle().pathForResource("beep-01a", ofType: "mp3")!);
    var audioplayer2 = AVAudioPlayer()
    var title1 : String = "";
    var title2 : String = "";
    var title3 : String = "";
    var title4 : String = "";
    var title5 : String = "";
    var title6 : String = "";
    var title7 : String = "";
    var title9 : String = "";
    var title8 : String = "";
    var th : Int = 0;
    var temp=0;
    
    var bull : PFObject!;
    var collection : PFObject!;
    var group: PFObject!;
    var user : PFUser!;
    
    var button1 : UIButton!;
    var button2 : UIButton!;
    var button3 : UIButton!;
    var button4 : UIButton!;
    var button5 : UIButton!;
    var button6 : UIButton!;
    var button7 : UIButton!;
    var button8 : UIButton!;
    var button9 : UIButton!;
    var edit : UIButton!;
    var lableP: UIButton!;

  
    override func viewDidAppear(animated: Bool) {
        super.viewDidLoad()
        
        user = PFUser.currentUser();
        
        if let _ = collection["totalCount"] {
            totalCount = collection["totalCount"] as! Double;
            
        }
         if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
         }
        else
        { self.title = "Total Count: " + String(Int(totalCount))}
        
        if let _ = collection["m1"]{
            button1Count = collection["m1"] as! Double;
        }
        if let _ = collection["m2"]{
            button2Count = collection["m2"] as! Double;
        }
        if let _ = collection["m3"]{
            button3Count = collection["m3"] as! Double;
        }
        if let _ = collection["m4"]{
            button4Count = collection["m4"] as! Double;
        }
        if let _ = collection["m5"]{
            button5Count = collection["m5"] as! Double;
        }
        if let _ = collection["m6"]{
            button6Count = collection["m6"] as! Double;
        }
        if let _ = collection["m7"]{
            button7Count = collection["m7"] as! Double;
        }
        if let _ = collection["m8"]{
            button8Count = collection["m8"] as! Double;
        }
        if let _ = collection["m9"]{
            button9Count = collection["m9"] as! Double;
        }

        if(totalCount != 0)
        {
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
        }
        let sizeRect = UIScreen.mainScreen().applicationFrame
        let width    = sizeRect.size.width
        let height   = sizeRect.size.height
        
        let buttonWidth = ((width - 30) / 2);
        let buttonHeight = ((height) / 7);
        do{
         audioplayer = try AVAudioPlayer(contentsOfURL: sound)
        }
        catch{}
        do{
            audioplayer2 = try AVAudioPlayer(contentsOfURL: sound2)
        }
        catch{}
    
        
        if let _ = user["th1"] {
            let temp = user["th1"] as! String;
            th = Int(temp)!
        }
               if let _ = group["m1"]{
            title1 = group["m1"] as! String;
        }
        if let _ = group["m2"]{
            title2 = group["m2"] as! String;
        }
        if let _ = group["m2"]{
            title3 = group["m3"] as! String;
        }
        if let _ = group["m4"]{
            title4 = group["m4"] as! String;
        }
        if let _ = group["m5"]{
            title5 = group["m5"] as! String;
        }
        if let _ = group["m6"]{
            title6 = group["m6"] as! String;
        }
        if let _ = group["m7"]{
            title7 = group["m7"] as! String;
        }
        if let _ = group["m8"]{
            title8 = group["m8"] as! String;
        }
        if let _ = group["m9"]{
            title9 = group["m9"] as! String;
        }
        
        if(title1 != ""){
            title1 = group["m1"] as! String;
            button1 = UIButton(frame: CGRectMake(10, 68, buttonWidth+buttonWidth+10, buttonHeight));
            button1.backgroundColor = UIColor.blueColor();
            button1.setTitle(title1 + ": " + String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button1.addTarget(self, action: #selector(SemenCounterViewController.pressed1(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button1.tag = 20;
            self.view.addSubview(button1);
        }
        
        if(title2 != ""){
            title2 = group["m2"] as! String;
            button2 = UIButton(frame: CGRectMake(10, buttonHeight + 78, buttonWidth, buttonHeight));
            button2.backgroundColor = UIColor.blueColor();
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button2.addTarget(self, action: #selector(SemenCounterViewController.pressed2(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button2.tag = 21;
            self.view.addSubview(button2);
        }
        
        if(title3 != ""){
            title3 = group["m3"] as! String;
            button3 = UIButton(frame: CGRectMake(10 + (width / 2), buttonHeight + 78, buttonWidth, buttonHeight));
            button3.backgroundColor = UIColor.greenColor();
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button3.addTarget(self, action: #selector(SemenCounterViewController.pressed3(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button3.tag = 22;
            self.view.addSubview(button3);
        }

        if(title4 != ""){
            title4 = group["m4"] as! String;
            button4 = UIButton(frame: CGRectMake(10, (buttonHeight * 2) + 88, buttonWidth, buttonHeight));
            button4.backgroundColor = UIColor.redColor();
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button4.addTarget(self, action: #selector(SemenCounterViewController.pressed4(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button4.tag = 23;
            self.view.addSubview(button4);
        }
        
        if(title5 != ""){
            button5 = UIButton(frame: CGRectMake(10 + (width / 2), (buttonHeight * 2) + 88, buttonWidth, buttonHeight));
            button5.backgroundColor = UIColor.orangeColor();
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button5.addTarget(self, action: #selector(SemenCounterViewController.pressed5(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button5.tag = 24;
            self.view.addSubview(button5);
        }
        
        if(title6 != ""){
            button6 = UIButton(frame: CGRectMake(10, (buttonHeight * 3) + 98, buttonWidth, buttonHeight));
            button6.backgroundColor = UIColor.purpleColor();
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button6.addTarget(self, action: #selector(SemenCounterViewController.pressed6(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button6.tag = 25;
            self.view.addSubview(button6);
        }
        
        
        if(title7 != ""){
            button7 = UIButton(frame: CGRectMake(10 + (width / 2), (buttonHeight * 3) + 98, buttonWidth, buttonHeight));
            button7.backgroundColor = UIColor.cyanColor();
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button7.addTarget(self, action: #selector(SemenCounterViewController.pressed7(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button7.tag = 26;
            self.view.addSubview(button7);
        }
        
        
        if(title8 != ""){
            button8 = UIButton(frame: CGRectMake(10, (buttonHeight * 4) + 108, buttonWidth, buttonHeight));
            button8.backgroundColor = UIColor.grayColor();
            button8.setTitle(title7 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button8.addTarget(self, action: #selector(SemenCounterViewController.pressed8(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button8.tag = 27;
            self.view.addSubview(button8);
        }
        
        
        if(title9 != ""){
            button9 = UIButton(frame: CGRectMake(10 + (width / 2), (buttonHeight * 4) + 108, buttonWidth, buttonHeight));
            button9.backgroundColor = UIColor.blackColor();
            button9.setTitle(title8 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            button9.addTarget(self, action: #selector(SemenCounterViewController.pressed9(_:)), forControlEvents: UIControlEvents.TouchUpInside)
            button9.tag = 28;
            self.view.addSubview(button9);
        }
       
        
        
        edit = UIButton(frame: CGRectMake(10 + (width / 2), (buttonHeight * 5) + 118, buttonWidth, buttonHeight-10));
        edit.backgroundColor = UIColor.whiteColor();
        edit.setTitleColor(UIColor.blackColor(), forState: UIControlState.Normal)
        edit.setTitle( "Edit Fields", forState: UIControlState.Normal)
        edit.addTarget(self, action: #selector(SemenCounterViewController.pressededit(_:)), forControlEvents: UIControlEvents.TouchUpInside)
        self.view.addSubview(edit);

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillDisappear(animated: Bool) {
    
        collection["totalCount"] = totalCount;
        collection["m1"] = button1Count;
        collection["m2"] = button2Count;
        collection["m3"] = button3Count;
        collection["m4"] = button4Count;
        collection["m5"] = button5Count;
        collection["m6"] = button6Count;
        collection["m7"] = button7Count;
        collection["m8"] = button8Count;
        collection["m9"] = button9Count;
        collection["title1"] = title1;
        collection["title2"] = title2;
        collection["title3"] = title3;
        collection["title4"] = title4;
        collection["title5"] = title5;
        collection["title6"] = title6;
        collection["title7"] = title7;
        collection["title8"] = title8;
        
        collection["createdAt1"] = NSDate();
        
        collection.pinInBackground();
        

        
    }
    
    
    
    
    
    func pressed1(sender: UIButton!) {
        if(temp != 1 && temp != 0)
        {audioplayer2.play()}
        
        
        button1Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
                if(title1 != ""){
        button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
        button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
        button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
       
        if(title3 != ""){
        button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
             button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
        button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
        
        button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
        
        button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
        
        button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
        button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
        button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
        button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        
            temp=1;
       
        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)
            if ( temp1 < button1Count)
            {
                self.audioplayer.play();
            }
        }
    }
    
    func pressed2(sender: UIButton!) {
        if(temp != 2 && temp != 0)
        {audioplayer2.play()}
        
        button2Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
      
        if(title1 != ""){
            button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        
        if(title3 != ""){
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
            
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
            
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
            
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
            button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        temp=2;
        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)

            
            if ( temp1 < button2Count)
            {
                self.audioplayer.play();
            }
        }
    }
    
    func pressed3(sender: UIButton!) {
        if(temp != 3 && temp != 0)
        {audioplayer2.play()}
        
        button3Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
              if(title1 != ""){
            button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        
        if(title3 != ""){
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
            
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
            
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
            
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
            button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        
        temp=3;
        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)

            if ( temp1 < button3Count)
            {
                self.audioplayer.play();
            }
        }
    }
    
    func pressed4(sender: UIButton!) {
        if(temp != 4 && temp != 0)
        {audioplayer2.play()}
        
        button4Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
        
        if(title1 != ""){
            button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        
        if(title3 != ""){
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
            
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
            
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
            
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
            button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        temp=4;
        

   if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)
            if ( temp1 < button4Count)
            {
                self.audioplayer.play();
            }
        }
    }
    
    func pressed5(sender: UIButton!) {
        if(temp != 5 && temp != 0)
        {audioplayer2.play()}
    
        button5Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
        
        if(title1 != ""){
            button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        
        if(title3 != ""){
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
            
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
            
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
            
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
            button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        temp=5;

        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)
            if ( temp1 < button5Count)
            {
                self.audioplayer.play();
            }
        }
    }
    
    func pressed6(sender: UIButton!) {
        if(temp != 6 && temp != 0)
        {audioplayer2.play()}
        
        button6Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
        
        if(title1 != ""){
            button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        
        if(title3 != ""){
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
            
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
            
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
            
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
            button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        temp=6;

        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)
            if ( temp1 < button6Count)
            {
                self.audioplayer.play();
            }
        }
    }
    
    func pressed7(sender: UIButton!) {
        if(temp != 7 && temp != 0)
        {audioplayer2.play()}
        
        button7Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
        if(title1 != ""){
            button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        
        if(title3 != ""){
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
            
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
            
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
            
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
            button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        
       temp=7;
        
        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)
            if ( temp1 < button7Count)
            {
                self.audioplayer2.play();
            }
        }
    }
    
    func pressed8(sender: UIButton!) {
        if(temp != 8 && temp != 0)
        {audioplayer2.play()}
        
        button8Count += 1;
        totalCount += 1;
        button1P = button1Count/totalCount * 100;
        button2P = button2Count/totalCount * 100;
        button3P = button3Count/totalCount * 100;
        button4P = button4Count/totalCount * 100;
        button5P = button5Count/totalCount * 100;
        button6P = button6Count/totalCount * 100;
        button7P = button7Count/totalCount * 100;
        button8P = button8Count/totalCount * 100;
        button9P = button9Count/totalCount * 100;
        
        if(title1 != ""){
            button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
            button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title2 != ""){
            button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
            button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        
        if(title3 != ""){
            button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
            button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title4 != ""){
            button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
            button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title5 != ""){
            
            button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
            button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title6 != ""){
            
            button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
            button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title7 != ""){
            
            button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
            button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title8 != ""){
            
            button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
            button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(title9 != ""){
            
            button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
            button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
        }
        if(th != 0)
        {
            let TP = (totalCount / Double(th) ) * 100 ;
            self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
        }
        else
        {
            self.title = "Total Count: " + String(totalCount);
        }
        
 temp=8;
        
        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)
            if ( temp1 < button7Count)
            {
                self.audioplayer.play();
            }
        }
    }

        func pressed9(sender: UIButton!) {
            if(temp != 9 && temp != 0)
            {audioplayer2.play()}
            
            button9Count += 1;
            totalCount += 1;
            button1P = button1Count/totalCount * 100;
            button2P = button2Count/totalCount * 100;
            button3P = button3Count/totalCount * 100;
            button4P = button4Count/totalCount * 100;
            button5P = button5Count/totalCount * 100;
            button6P = button6Count/totalCount * 100;
            button7P = button7Count/totalCount * 100;
            button8P = button8Count/totalCount * 100;
            button9P = button9Count/totalCount * 100;
           
            if(title1 != ""){
                button1.setTitle(title1 + ": " +  String(Int(button1Count)) +  " :" + String(Int(button1P*100)/100) + "%", forState: UIControlState.Normal)
                button1.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            if(title2 != ""){
                button2.setTitle(title2 + ": " + String(Int(button2Count)) +  " :" + String(Int(button2P*100)/100) + "%", forState: UIControlState.Normal)
                button2.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            
            if(title3 != ""){
                button3.setTitle(title3 + ": " + String(Int(button3Count)) +  " :" + String(Int(button3P*100)/100) + "%", forState: UIControlState.Normal)
                button3.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            if(title4 != ""){
                button4.setTitle(title4 + ": " + String(Int(button4Count)) +  " :" + String(Int(button4P*100)/100) + "%", forState: UIControlState.Normal)
                button4.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            if(title5 != ""){
                
                button5.setTitle(title5 + ": " + String(Int(button5Count)) +  " :" + String(Int(button5P*100)/100) + "%", forState: UIControlState.Normal)
                button5.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            if(title6 != ""){
                
                button6.setTitle(title6 + ": " + String(Int(button6Count)) +  " :" + String(Int(button6P*100)/100) + "%", forState: UIControlState.Normal)
                button6.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            if(title7 != ""){
                
                button7.setTitle(title7 + ": " + String(Int(button7Count)) +  " :" + String(Int(button7P*100)/100) + "%", forState: UIControlState.Normal)
                button7.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            if(title8 != ""){
                
                button8.setTitle(title8 + ": " + String(Int(button8Count)) +  " :" + String(Int(button8P*100)/100) + "%", forState: UIControlState.Normal)
                button8.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }
            if(title9 != ""){
                
                button9.setTitle(title9 + ": " + String(Int(button9Count)) +  " :" + String(Int(button9P*100)/100) + "%", forState: UIControlState.Normal)
                button9.titleLabel!.lineBreakMode = NSLineBreakMode.ByWordWrapping;
            }

            if(th != 0)
            {
                let TP = (totalCount / Double(th) ) * 100 ;
                self.title = "Total Count: " + String(Int(totalCount)) + " :"+String(Int(TP*100)/100) + "%";
            }
            else
            {
                self.title = "Total Count: " + String(totalCount);
            }
            
       temp=9;
        if(user["th1"] != nil && user["th1"] as! String != "")
        {
            let temp2 = user["th1"] as! String;
            let temp1 = Double(temp2)
            if ( temp1 < button8Count)
            {
                self.audioplayer.play();
            }
        }
    }
    
func pressededit(sender: UIButton!) {
    
    performSegueWithIdentifier("EditSegue", sender: nil);
    
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
*/
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        
        let semenCounter = segue.destinationViewController as! EditMorphology;
        
        semenCounter.collection = self.collection;
        semenCounter.button1Count = self.button1Count;
        semenCounter.button2Count = self.button2Count;
        semenCounter.button3Count = self.button3Count;
        semenCounter.button4Count = self.button4Count;
        semenCounter.button5Count = self.button5Count;
        semenCounter.button6Count = self.button6Count;
        semenCounter.button7Count = self.button7Count;
        semenCounter.button8Count = self.button8Count;
        semenCounter.button9Count = self.button9Count;
        semenCounter.totalCount = self.totalCount;
        
    }


}
