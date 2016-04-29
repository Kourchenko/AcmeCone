//
//  ViewController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 3/31/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit
private let reuseIdentifier = "cell"

class ViewController: UIViewController {
    @IBOutlet weak var IMG_CONE: UIImageView!
    @IBOutlet weak var IMG_BOX: UIImageView!
    @IBOutlet weak var IMG_CORNER: UIImageView!
    
    @IBOutlet weak var IMG_PIPE: UIImageView!
    @IBOutlet weak var IMG_METAL: UIImageView!
    @IBOutlet weak var IMG_DRAIN: UIImageView!
    
    @IBOutlet weak var IMG_SCUPPER: UIImageView!
    @IBOutlet weak var IMG_PITCHPAN: UIImageView!
    @IBOutlet weak var IMG_TUBE: UIImageView!
    
    @IBOutlet weak var IMG_LOGO: UIImageView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view, typically from a nib.
        
    }
    
    override func shouldAutorotate() -> Bool {
        return false
    }
    
    override func supportedInterfaceOrientations() -> UIInterfaceOrientationMask {
        return UIInterfaceOrientationMask.Portrait
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
}

