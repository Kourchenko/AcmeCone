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
    
    @IBAction func IMG_CONE(sender: AnyObject) {
        
    }
    @IBOutlet weak var IMG_BOX: UIImageView!
    @IBOutlet weak var IMG_CORNER: UIImageView!
    
    @IBOutlet weak var IMG_PIPE: UIImageView!
    @IBOutlet weak var IMG_METAL: UIImageView!
    @IBOutlet weak var IMG_DRAIN: UIImageView!
    
    @IBOutlet weak var IMG_SCUPPER: UIImageView!
    @IBOutlet weak var IMG_PITCHPAN: UIImageView!
    @IBOutlet weak var IMG_TUBE: UIImageView!
    
    @IBOutlet weak var IMG_LOGO: UIImageView!
    @IBOutlet weak var collectionView: UICollectionView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view, typically from a nib.
        UIDevice.currentDevice().setValue(UIInterfaceOrientation.Portrait.rawValue, forKey: "Orientation")
    }
    
    override func shouldAutorotate() -> Bool {
        return false
    }
    
    override func supportedInterfaceOrientations() -> UIInterfaceOrientationMask {
        return UIInterfaceOrientationMask.Portrait
    }

    override func preferredInterfaceOrientationForPresentation() -> UIInterfaceOrientation {
        return UIInterfaceOrientation.Portrait
    }
        
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if (segue.identifier == "coneSegue") {

            let vc = StockConeController()
            
            vc.segue_numberOfRowsInSection = 2
            vc.segue_titles = ["Short A-Cone", "A-Cone"]
            vc.segue_images = [UIImage(named: "SAS_W_T")!, UIImage(named:"AS_W_T")!]
            vc.segue_desc = ["Short", "A-Cone"]
            
            vc.performSegueWithIdentifier("coneSegue", sender: sender)
            
        }
    }
}

