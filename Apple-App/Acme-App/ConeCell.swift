//
//  ConeCell.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/25/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class ConeCell: UITableViewCell, UITextFieldDelegate{

    @IBOutlet weak var coneTextField: UITextField!
    @IBOutlet weak var coneSwitch: UISwitch!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        
        coneTextField.delegate = self
        coneTextField.tag = 0
        
    }
    

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
