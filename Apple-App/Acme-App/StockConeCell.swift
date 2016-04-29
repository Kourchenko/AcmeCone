//
//  StockConeCell.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/29/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class StockConeCell: UITableViewCell {

    @IBOutlet weak var coneStockImage: UIImageView!
    @IBOutlet weak var coneStockHeader: UILabel!
    @IBOutlet weak var coneStockDesc: UILabel!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
