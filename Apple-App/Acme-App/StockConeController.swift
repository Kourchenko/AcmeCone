//
//  StockConeController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/28/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class StockConeController: UIViewController, UITextFieldDelegate, UITableViewDelegate, UITableViewDataSource {

    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 5
    }
    
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("coneStockCell") as! StockConeCell
        
        if (indexPath.row == 0) {
            cell.coneStockImage.image = UIImage(named: "SAS_W_T")
            cell.coneStockHeader.text = "Short A-Cone"
            cell.coneStockDesc.text = ".25” Top Diameter, 4” Bottom Diameter, 5” Height, 5” Flange"
        } else if (indexPath.row == 1) {
            cell.coneStockImage.image = UIImage(named: "AS_W_T")
            cell.coneStockHeader.text = "A-Cone"
            cell.coneStockDesc.text = "1” Top Diameter, 5” Bottom Diameter, 10” Height, 5” Flange"
        } else if (indexPath.row == 2) {
            cell.coneStockImage.image = UIImage(named: "BS_W_T")
            cell.coneStockHeader.text = "B-Cone"
            cell.coneStockDesc.text = "2” Top Diameter, 6” Bottom Diameter, 12” Height, 6” Flange"
        } else if (indexPath.row == 3){
            cell.coneStockImage.image = UIImage(named: "CS_W_T")
            cell.coneStockHeader.text = "C-Cone"
            cell.coneStockDesc.text = "4” Top Diameter, 7” Bottom Diameter, 12” Height, 6” Flange"
        } else if (indexPath.row == 4) {
            cell.coneStockImage.image = UIImage(named: "DS_W_T")
            cell.coneStockHeader.text = "D-Cone"
            cell.coneStockDesc.text = "7” Top Diameter, 12” Bottom Diameter, 12” Height, 6” Flange"
        }
        
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
            
            
            let alert: UIAlertView = UIAlertView(title: "Short A-Cone", message: message , delegate: nil, cancelButtonTitle: "OK");
            let loadingIndicator: UIActivityIndicatorView = UIActivityIndicatorView(frame: CGRectMake(50, 10, 37, 37)) as UIActivityIndicatorView
            
            loadingIndicator.center = self.view.center;
            loadingIndicator.hidesWhenStopped = true
            loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.Gray
            loadingIndicator.startAnimating();
            
            alert.show()
            
            // Delay the dismissal by 2 seconds
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
