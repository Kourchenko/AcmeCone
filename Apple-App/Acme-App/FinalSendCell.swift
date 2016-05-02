//
//  FinalSendCell.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/30/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class FinalSendCell: UITableViewCell, UITextFieldDelegate {
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var label: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
