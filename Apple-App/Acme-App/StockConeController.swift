//
//  StockConeController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/28/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit


class StockConeController: UIViewController, UITextFieldDelegate, UITableViewDelegate, UITableViewDataSource {

    
    var segue_titles = []
    var segue_images = []
    var segue_desc = []
    var segue_numberOfRowsInSection: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return segue_numberOfRowsInSection
    }
    
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("coneStockCell") as! StockConeCell
        
        
        cell.coneStockImage.image = segue_images[indexPath.row] as? UIImage
        cell.coneStockHeader.text = segue_titles[indexPath.row] as? String
        cell.coneStockDesc.text = segue_desc[indexPath.row] as? String

        
        return cell
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "Tap to Show Details"
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let message = "Split and Non-Split" +
            "\n Color : White & Grey" +
            "\n Material : TPO & PVC" +
            "\n Available in 060 mil. " +
            "\n\n Acme Cone works with all major manufacturerers and brands."
            
            
        let alert: UIAlertView = UIAlertView(title: segue_titles[indexPath.row] as? String, message: message , delegate: nil, cancelButtonTitle: "OK");
        let loadingIndicator: UIActivityIndicatorView = UIActivityIndicatorView(frame: CGRectMake(50, 10, 37, 37)) as UIActivityIndicatorView
            
        loadingIndicator.center = self.view.center;
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.Gray
        loadingIndicator.startAnimating();
            
        alert.show()
            
        // Delay the dismissal by 5 seconds
        let delay = 5.0 * Double(NSEC_PER_SEC)
        let time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
        dispatch_after(time, dispatch_get_main_queue(), {
            alert.dismissWithClickedButtonIndex(-1, animated: true)
        })
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
