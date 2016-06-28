//
//  BCIStringExtension.swift
//  BRD
//
//  Created by Kevin Manase on 6/21/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

extension String {

    // Localize string
    var localized: String {
        return NSLocalizedString(self, tableName: nil, bundle: NSBundle.mainBundle(), value: "", comment: "")
    }
    
    // Check if string is blank
    var isBlank: Bool {
        get {
            let trimmed = stringByTrimmingCharactersInSet(NSCharacterSet.whitespaceCharacterSet())
            return trimmed.isEmpty
        }
    }
}