//
//  StockCell.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/22/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class StockCell: UITableViewCell {

    @IBOutlet weak var stockLabel: UILabel!
    @IBOutlet weak var stockQuantity: UITextField!
    @IBOutlet weak var stockAdd: UIButton!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
