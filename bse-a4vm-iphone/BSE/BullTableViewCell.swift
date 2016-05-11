//
//  BullTableViewCell.swift
//  BSE
//
//  Created by Chris Piggott on 11/12/15.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

class BullTableViewCell: UITableViewCell {

    @IBOutlet var bullID: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
