//
//  CollectionTableViewCell.swift
//  BSE
//
//  Created by Chris Piggott on 11/28/15.
//  Copyright © 2015 Null Development. All rights reserved.
//

import UIKit

class CollectionTableViewCell: UITableViewCell {

    @IBOutlet var collectionCount: UILabel!
    @IBOutlet var collectionDate: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
