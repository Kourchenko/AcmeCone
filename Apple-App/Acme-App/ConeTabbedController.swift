//
//  ConeTabbedController.swift
//  Acme Cone
//
//  Created by Diego Kourchenko on 6/1/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class ConeTabbedController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.setupSwipeGestureRecognizers(true)

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
