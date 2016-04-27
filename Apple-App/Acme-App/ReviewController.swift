//
//  ReviewController.swift
//  Acme-App
//
//  Created by diegok on 3/31/16.
//  Copyright © 2016 Acme. All rights reserved.
//


import UIKit

var STOCK = [String]()
var CONES = [Cone]()
var CORNERS = [Corner]()
var PIPES = [Pipe]()
var DROPS = [Drop]()
var SCUPPERS = [Scupper]()
var PANS = [Pan]()
var TUBES = [Tube]()
var CURBS = [Curb]()
var SLEEPERS = [Sleeper]()

class ReviewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UITextFieldDelegate {
    
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var companyField: UITextField!
    @IBOutlet weak var emailField: UITextField!
    @IBOutlet weak var manufacturerField: UITextField!
    
    @IBOutlet weak var currentOrderTable: UITableView!
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        if (textField == nameField) {
            companyField.becomeFirstResponder()
        } else if (textField == companyField) {
            emailField.becomeFirstResponder()
        } else if (textField == emailField) {
            manufacturerField.becomeFirstResponder()
        } else if (textField == manufacturerField) {
            manufacturerField.resignFirstResponder()
        } else {
            manufacturerField.resignFirstResponder()
        }
        
        return true
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 10
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == 0 {
            return STOCK.count
        } else if section == 1 {
            return CONES.count
        } else if section == 2 {
            return CORNERS.count
        } else if section == 3 {
            return PIPES.count
        } else if section == 4 {
            return DROPS.count
        } else if section == 5 {
            return SCUPPERS.count
        } else if section == 6 {
            return PANS.count
        } else if section == 7 {
            return TUBES.count
        } else if section == 8 {
            return CURBS.count
        } else if section == 9 {
            return SLEEPERS.count
        } else {
            return 1
        }
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier("currentOrderCell")
        
        if indexPath.section == 0 {
            cell?.textLabel!.text = STOCK[indexPath.row]
            
            //cell?.textLabel?.text = String(STOCK[indexPath.row].amount) + ": " + STOCK[indexPath.row].type
            
        } else if indexPath.section == 1 {
            cell!.textLabel?.text = String(CONES[indexPath.row].quantity) + ": "
                                    + CONES[indexPath.row].type + " "
                                    + CONES[indexPath.row].color + " "
                                    + CONES[indexPath.row].material + " "
                                    + "Cones"
            
        } else if indexPath.section == 2 {
            cell!.textLabel?.text = String(CORNERS[indexPath.row].quantity) + ": "
                                    + CORNERS[indexPath.row].type + " "
                                    + CORNERS[indexPath.row].color + " "
                                    + CORNERS[indexPath.row].material + " "
                                    + "Corners"
        } else if indexPath.section == 3 {
            cell!.textLabel?.text = PIPES[indexPath.row].type + " Pipes"
        }else if indexPath.section == 1 {
            cell!.textLabel?.text = DROPS[indexPath.row].color + " Cones"
        } else if indexPath.section == 2 {
            cell!.textLabel?.text = SCUPPERS[indexPath.row].color + " Corners"
        } else if indexPath.section == 3 {
            cell!.textLabel?.text = PANS[indexPath.row].color + " Pipes"
        }else if indexPath.section == 1 {
            cell!.textLabel?.text = TUBES[indexPath.row].color + " Cones"
        } else if indexPath.section == 2 {
            cell!.textLabel?.text = CURBS[indexPath.row].color + " Corners"
        } else if indexPath.section == 3 {
            cell!.textLabel?.text = SLEEPERS[indexPath.row].color + " Pipes"
        }
        
        return cell!
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if section == 0 {
            return "Stock"
        } else if section == 1 {
            return "Cones"
        }else if section == 2 {
            return "Corners"
        } else if section == 3 {
            return "Pipes"
        } else if section == 4 {
            return "Drops"
        } else if section == 5 {
            return "Scuppers"
        } else if section == 6 {
            return "Pans"
        } else if section == 7 {
            return "Tubes"
        } else if section == 8 {
            return "Curbs"
        } else if section == 9 {
            return "Sleepers"
        } else {
            return "Custom"
        }
    }
    
    func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        return true
    }
    
    func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        
    }
    
    func tableView(tableView: UITableView, editActionsForRowAtIndexPath indexPath: NSIndexPath) -> [UITableViewRowAction]? {
        
        let deleteAction = UITableViewRowAction(style: .Normal, title: "Delete") { (action: UITableViewRowAction!, indexPath: NSIndexPath!) -> Void in
            
            if (indexPath.section == 0) {
                STOCK.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 1) {
                CONES.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 2) {
                CORNERS.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 3) {
                PIPES.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 4) {
                DROPS.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 5) {
                SCUPPERS.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 6) {
                PANS.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 7) {
                TUBES.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 8) {
                CURBS.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            } else if (indexPath.section == 9) {
                SLEEPERS.removeAtIndex(indexPath.row)
                tableView.reloadData()
                
            }
            
        }
        
        
        deleteAction.backgroundColor = UIColor.redColor()
        return [deleteAction]
        
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        self.currentOrderTable.reloadData()
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        
        
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
    
    
    /*
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    // Get the new view controller using segue.destinationViewController.
    // Pass the selected object to the new view controller.
    }
    */
    
}
