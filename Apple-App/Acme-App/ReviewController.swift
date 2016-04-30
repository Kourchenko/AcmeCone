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
    @IBOutlet weak var currentOrderTable: UITableView!
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        self.view.endEditing(true)
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
        
        let cell = tableView.dequeueReusableCellWithIdentifier("currentOrderCell")!
        
        if indexPath.section == 0 {
            cell.textLabel!.text = STOCK[indexPath.row]
            
            //cell?.textLabel?.text = String(STOCK[indexPath.row].amount) + ": " + STOCK[indexPath.row].type
            
        } else if indexPath.section == 1 {
            cell.textLabel?.text = String(CONES[indexPath.row].quantity) + ": "
                                    + CONES[indexPath.row].type + " "
                                    + CONES[indexPath.row].color + " "
                                    + CONES[indexPath.row].material + " "
                                    + "Cones"
            
        } else if indexPath.section == 2 {
            cell.textLabel?.text = String(CORNERS[indexPath.row].quantity) + ": "
                                    + CORNERS[indexPath.row].type + " "
                                    + CORNERS[indexPath.row].color + " "
                                    + CORNERS[indexPath.row].material + " "
                                    + "Corners"
        } else if indexPath.section == 3 {
            cell.textLabel?.text = String(PIPES[indexPath.row].quantity) + ":"
                                    + PIPES[indexPath.row].type + " "
                                    + PIPES[indexPath.row].color + " "
                                    + PIPES[indexPath.row].material +  " "
                                    + "Pipes"
        } else if indexPath.section == 4 {
            cell.textLabel?.text = String(DROPS[indexPath.row].quantity) + ": "
                                    + DROPS[indexPath.row].color + " "
                                    + DROPS[indexPath.row].material + " "
                                    + "Drops"
        } else if indexPath.section == 5 {
            cell.textLabel?.text = String(SCUPPERS[indexPath.row].quantity) + ": "
                                    + SCUPPERS[indexPath.row].type + " "
                                    + SCUPPERS[indexPath.row].color + " "
                                    + SCUPPERS[indexPath.row].material + " "
                                    + "Scuppers"
        } else if indexPath.section == 6 {
            cell.textLabel?.text = String(PANS[indexPath.row].quantity) + ": "
                                    + PANS[indexPath.row].roundType + " "
                                    + PANS[indexPath.row].splitType + " "
                                    + PANS[indexPath.row].color + " "
                                    + PANS[indexPath.row].material + " "
                                    + "Pitch Pans"
        }else if indexPath.section == 7 {
            cell.textLabel?.text = String(TUBES[indexPath.row].quantity) + ": "
                                    + TUBES[indexPath.row].type + " "
                                    + TUBES[indexPath.row].color + " "
                                    + TUBES[indexPath.row].material + " "
                                    + "Tube Wraps"
        } else if indexPath.section == 8 {
            cell.textLabel?.text = String(CURBS[indexPath.row].quantity) + ": "
                                    + CURBS[indexPath.row].type + " "
                                    + CURBS[indexPath.row].color + " "
                                    + CURBS[indexPath.row].material + " "
                                    + "Curbs"
        } else if indexPath.section == 9 {
            cell.textLabel?.text = String(SLEEPERS[indexPath.row].quantity) + ": "
                                    + SLEEPERS[indexPath.row].color + " "
                                    + SLEEPERS[indexPath.row].material + " "
                                    + "Sleepers"
        }
        
        return cell
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
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        if (indexPath.section == 1) {
            
            let vc = storyboard?.instantiateViewControllerWithIdentifier("Cones") as! ConeController
        
            let quantity = CONES[indexPath.row].quantity
            let coneSegmented = CONES[indexPath.row].type
            let height = CONES[indexPath.row].height
            let heightFrac = CONES[indexPath.row].heightFrac
            let top = CONES[indexPath.row].topDiameter
            let topFrac = CONES[indexPath.row].topFrac
            let bot = CONES[indexPath.row].botDiameter
            let botFrac = CONES[indexPath.row].botFrac
            let flange = CONES[indexPath.row].flange
            let flangeFrac = CONES[indexPath.row].flangeFrac
            let color = CONES[indexPath.row].color
            let material = CONES[indexPath.row].material
            let optional = CONES[indexPath.row]._optional

            vc.segue_type = coneSegmented
            vc.segue_quantity = String(quantity)
            vc.segue_height = String(height)
            vc.segue_heightFrac = String(heightFrac)
            vc.segue_top = String(top)
            vc.segue_topFrac = String(topFrac)
            vc.segue_bot = String(bot)
            vc.segue_botFrac = String(botFrac)
            vc.segue_flange = String(flange)
            vc.segue_flangeFrac = String(flangeFrac)
            vc.segue_color = String(color)
            vc.segue_material = String(material)
            vc.segue_optional = String(optional)
        
            self.presentViewController(vc, animated: true, completion: nil)
        }
        
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
