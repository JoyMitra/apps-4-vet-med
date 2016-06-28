//
//  BCITextField.swift
//  BRD
//
//  Created by Kevin Manase on 6/28/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCITextField: UITextField, UITextFieldDelegate {
    var isSatisfied: Bool = false
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
        delegate = self
    }
    required override init(frame: CGRect) {
        super.init(frame: frame)
        delegate = self
    }
    
    func textFieldDidBeginEditing(textField: UITextField) {
        
    }
    func textFieldDidEndEditing(textField: UITextField) {
        
    }

}
