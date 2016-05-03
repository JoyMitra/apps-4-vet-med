//
//  EditMorphology.swift
//  BSE
//
//  Created by Shubh Chopra on 4/20/16.
//  Copyright © 2016 Null Development. All rights reserved.
//

import Foundation

import Parse

import UIKit

class EditMorphology: UIViewController {
    
    var collection : PFObject!;
var user : PFUser!;
    var button1Count :Double! ;
    var button2Count :Double! ;
    var button3Count :Double! ;
    var button4Count :Double! ;
    var button5Count :Double! ;
    var button6Count :Double! ;
    var button7Count :Double! ;
    var button8Count :Double! ;
    var button9Count :Double! ;
    var totalCount :Double!;
    var tf1 : UITextField!;
    var tf2 : UITextField!;
    var tf3 : UITextField!;
    var tf4 : UITextField!;
    var tf5 : UITextField!;
    var tf6 : UITextField!;
    var tf7 : UITextField!;
    var tf8 : UITextField!;
    var tf9 : UITextField!;
    var title1 : String = "";
    var title2 : String = "";
    var title3 : String = "";
    var title4 : String = "";
    var title5 : String = "";
    var title6 : String = "";
    var title7 : String = "";
    var title8 : String = "";
    var title9 : String = "";
    override func viewDidLoad() {
        super.viewDidLoad()
         user = PFUser.currentUser();
        let sizeRect = UIScreen.mainScreen().applicationFrame
        let width    = sizeRect.size.width
        let height   = sizeRect.size.height
        
        let buttonWidth = ((width - 30) / 2);
        let buttonHeight = ((height) / 7);
        
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
        
        if let _ = user["m1"]{
            title1 = user["m1"] as! String;
        }
        if let _ = user["m2"]{
            title2 = user["m2"] as! String;
        }
        if let _ = user["m2"]{
            title3 = user["m3"] as! String;
        }
        if let _ = user["m4"]{
            title4 = user["m4"] as! String;
        }
        if let _ = user["m5"]{
            title5 = user["m5"] as! String;
        }
        if let _ = user["m6"]{
            title6 = user["m6"] as! String;
        }
        if let _ = user["m7"]{
            title7 = user["m7"] as! String;
        }
        if let _ = user["m8"]{
            title8 = user["m8"] as! String;
        }
        if let _ = user["m9"]{
            title8 = user["m9"] as! String;
        }
        if(title1 != ""){
            title1 = user["m1"] as! String;
            tf1 = UITextField(frame: CGRectMake(10, 68, buttonWidth+buttonWidth+10, buttonHeight));
            tf1.keyboardType = UIKeyboardType.NumberPad;
            tf1.placeholder = title1 + ": " + String(button1Count);
            self.view.addSubview(tf1);
            
        }
        if(title2 != ""){
            title2 = user["m2"] as! String;
            tf2 = UITextField(frame: CGRectMake(10, buttonHeight + 78, buttonWidth, buttonHeight));
            tf2.keyboardType = UIKeyboardType.NumberPad;
            tf2.placeholder = title2 + ": " + String(button2Count);
            self.view.addSubview(tf2);
            
        }
        if(title3 != ""){
            title3 = user["m3"] as! String;
            tf3 = UITextField(frame: CGRectMake(10 + (width / 2), buttonHeight + 78, buttonWidth, buttonHeight));
            tf3.keyboardType = UIKeyboardType.NumberPad;
            tf3.placeholder = title3 + ": " + String(button3Count);
            self.view.addSubview(tf3);
            
        }
        if(title4 != ""){
            title4 = user["m4"] as! String;
            tf4 = UITextField(frame: CGRectMake(10, (buttonHeight * 2) + 88, buttonWidth, buttonHeight));
            tf4.keyboardType = UIKeyboardType.NumberPad;
            tf4.placeholder = title4 + ": " + String(button4Count);
            self.view.addSubview(tf4);
            
        }
        if(title5 != ""){
            title5 = user["m5"] as! String;
            tf5 = UITextField(frame: CGRectMake(10 + (width / 2), (buttonHeight * 2) + 88, buttonWidth, buttonHeight));
            tf5.keyboardType = UIKeyboardType.NumberPad;
            tf5.placeholder = title5 + ": " + String(button5Count);
            self.view.addSubview(tf5);
            
        }
        if(title6 != ""){
            title6 = user["m6"] as! String;
            tf6 = UITextField(frame: CGRectMake(10, (buttonHeight * 3) + 98, buttonWidth, buttonHeight));
            tf6.keyboardType = UIKeyboardType.NumberPad;
            tf6.placeholder = title6 + ": " + String(button6Count);
            self.view.addSubview(tf6);
            
        }
        if(title7 != ""){
            title7 = user["m7"] as! String;
            tf7 = UITextField(frame: CGRectMake(10 + (width / 2), (buttonHeight * 3) + 98, buttonWidth, buttonHeight));
            tf7.keyboardType = UIKeyboardType.NumberPad;
            tf7.placeholder = title7 + ": " + String(button7Count);
            self.view.addSubview(tf7);
            
        }
        if(title8 != ""){
            title8 = user["m8"] as! String;
            tf8 = UITextField(frame: CGRectMake(10, (buttonHeight * 4) + 108, buttonWidth, buttonHeight));
            tf8.keyboardType = UIKeyboardType.NumberPad;
            tf8.placeholder = title8 + ": " + String(button8Count);
            self.view.addSubview(tf8);
            
        }
        if(title9 != ""){
            title9 = user["m9"] as! String;
            tf9 = UITextField(frame: CGRectMake(10 + (width / 2), (buttonHeight * 4) + 108, buttonWidth, buttonHeight));
            tf9.keyboardType = UIKeyboardType.NumberPad;
            tf9.placeholder = title9 + ": " + String(button9Count);
            self.view.addSubview(tf9);
            
        }

    }
    override func viewWillDisappear(animated: Bool) {
        
        if (tf1.text != ""){
        collection["m1"] = Double(tf1.text!);
            totalCount = totalCount - button1Count + Double(tf1.text!)!;
        }
        if (tf2.text != ""){
        collection["m2"] =  Double(tf2.text!);
            totalCount = totalCount - button2Count + Double(tf2.text!)!;
        }
        if (tf3.text != ""){
        collection["m3"] =  Double(tf3.text!);
            totalCount = totalCount - button3Count + Double(tf3.text!)!;
        }
        if (tf4.text != ""){
        collection["m4"] =  Double(tf4.text!);
            totalCount = totalCount - button4Count + Double(tf4.text!)!;
        }
        if (tf5.text != ""){
        collection["m5"] =  Double(tf5.text!);
            totalCount = totalCount - button5Count + Double(tf5.text!)!;
        }
        if (tf6.text != ""){
        collection["m6"] =  Double(tf6.text!);
            totalCount = totalCount - button6Count + Double(tf6.text!)!;
        }
        if (tf7.text != ""){
        collection["m7"] =  Double(tf7.text!);
            totalCount = totalCount - button7Count + Double(tf7.text!)!;
        }
        if (tf8.text != ""){
        collection["m8"] =  Double(tf8.text!);
            totalCount = totalCount - button8Count + Double(tf8.text!)!;
        }
        if (tf9.text != ""){
            collection["m9"] =  Double(tf9.text!);
            totalCount = totalCount - button9Count + Double(tf9.text!)!;
        }
        collection["totalCount"] = totalCount;
        collection.pinInBackground();
        
        
        
    }

}







