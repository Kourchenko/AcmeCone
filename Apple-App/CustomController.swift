//
//  CustomController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/24/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

var fractions = ["\"", "1/8", "1/4", "3/8", "1/2", "5/8", "3/4"]

class CustomController: UIViewController, UITableViewDelegate, UITableViewDataSource, UIPopoverPresentationControllerDelegate {

    @IBOutlet weak var customTable: UITableView!
    
    
    var CUSTOM = ["Cones", "Corners", "Pipe Wraps", "Drop Scuppers", "Scuppers", "Pitch Pans", "Tube Wraps", "Curbs", "Sleepers"]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "Custom Products"
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return CUSTOM.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("customCell")! 
        
        if (indexPath.row == 0) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Cones")
        } else if (indexPath.row == 1) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Corners")
        } else if (indexPath.row == 2) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Pipewraps")
        } else if (indexPath.row == 3) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Drops")
        } else if (indexPath.row == 4) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Scuppers")
        } else if (indexPath.row == 5) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Pitchpans")
        } else if (indexPath.row == 6) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Tubewraps")
        } else if (indexPath.row == 7) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Boxes")
        } else if (indexPath.row == 8) {
            cell.textLabel!.text = CUSTOM[indexPath.row]
            cell.imageView!.image = UIImage(named: "Boxes")
        }
        
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        if (indexPath.row == 0) {
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Cones")
            self.presentViewController(vc!, animated: true, completion: nil)
        } else if (indexPath.row == 1) {
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Corners")
            self.presentViewController(vc!, animated: true, completion: nil)
        } else if (indexPath.row == 2) {
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Pipe Wraps")
            self.presentViewController(vc!, animated: true, completion: nil)
        } else if (indexPath.row == 3) {
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Drop Scuppers")
            self.presentViewController(vc!, animated: true, completion: nil)
        } else if (indexPath.row == 4) {
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Thruwall Scuppers")
            self.presentViewController(vc!, animated: true, completion: nil)
        } else if (indexPath.row == 5) {
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Pitch Pans")
            self.presentViewController(vc!, animated: true, completion: nil)
        } else if (indexPath.row == 6) {
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Tube Wraps")
            self.presentViewController(vc!, animated: true, completion: nil)
        }
    }

    func adaptivePresentationStyleForPresentationController(controller: UIPresentationController) -> UIModalPresentationStyle {
        return .None
    }
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        
        
        
        
    }
    

}
