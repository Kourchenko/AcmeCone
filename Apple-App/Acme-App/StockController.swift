//
//  StockView.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/22/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

var STOCK_DATA = [
    // RVOs
    "RVO White TPO", "RVO Grey TPO", "RVO White PVC", "RVO Grey PVC",
    
    
    // Breather Vents
    // 2-Way
    "[ 2 - WAY ] BV White TPO", "[ 2 - WAY ] BV Grey TPO",
    "[ 2 - WAY ] BV White PVC", "[ 2 - WAY ] BV Grey PVC",
    "Breather Vent White TPO (2-Way)", "Breather Vent Grey TPO (2-Way)",
    "Breather Vent White PVC (2-Way)", "Breather Vent Grey PVC (2-Way)",
    // 1-Way
    "[ 1 - WAY ] BV White TPO", "[ 1 - WAY ] BV Grey TPO",
    "[ 1 - WAY ] BV White PVC", "[ 1 - WAY ] BV Grey PVC",
    "Breather Vent White TPO (1-Way)", "Breather Vent Grey TPO (1-Way)",
    "Breather Vent White PVC (1-Way)", "Breather Vent Grey PVC (1-Way)",
    
    
    // Cones -- Split
    "[ SPLIT ] Short A Cone White TPO",
    "[ SPLIT ] Short A Cone Grey TPO",
    "[ SPLIT ] Short A Cone White PVC",
    "[ SPLIT ] Short A Cone Grey PVC",
    
    "[ SPLIT ] A Cone White TPO", "[ SPLIT ] A Cone Grey TPO",
    "[ SPLIT ] A Cone White PVC", "[ SPLIT ] A Cone Grey PVC",
    "[ SPLIT ] B Cone White TPO", "[ SPLIT ] B Cone Grey TPO",
    "[ SPLIT ] B Cone White PVC", "[ SPLIT ] B Cone Grey PVC",
    "[ SPLIT ] C Cone White TPO", "[ SPLIT ] C Cone Grey TPO",
    "[ SPLIT ] C Cone White PVC", "[ SPLIT ] C ConeGrey PVC",
    
    // Cones -- Non-Split
    "[ NON SPLIT ] Short A Cone White TPO",
    "[ NON SPLIT ] Short A Cone Grey TPO",
    "[ NON SPLIT ] Short A Cone White PVC",
    "[ NON SPLIT ] Short A Cone Grey PVC",
    
    "[ NON SPLIT ] A Cone White TPO", "[ NON SPLIT ] A Cone Grey TPO",
    "[ NON SPLIT ] A Cone White PVC", "[ NON SPLIT ] A Cone Grey PVC",
    "[ NON SPLIT ] B Cone White TPO", "[ NON SPLIT ] B Cone Grey TPO",
    "[ NON SPLIT ] B Cone White PVC", "[ NON SPLIT ] B Cone Grey PVC",
    "[ NON SPLIT ] C Cone White TPO", "[ NON SPLIT ] C Cone Grey TPO",
    "[ NON SPLIT ] C Cone White PVC", "[ NON SPLIT ] C Cone Grey PVC",
    
    // Corners
    "[ INSIDE ] Corner White TPO", "[ INSIDE ] Corner Grey TPO",
    "[ INSIDE ] Corner White PVC", "[ INSIDE ] Corner Grey PVC",
    
    "[ OUTSIDE ] Corner White TPO", "[ OUTSIDE ] Corner Grey TPO",
    "[ OUTSIDE ] Corner White PVC", "[ OUTSIDE ] Corner Grey PVC",
    
    // Drains
    "[ Standard ] 2\" Drop Scupper White TPO",
    "[ Standard ] 2\" Drop Scupper Grey TPO",
    "[ Standard ] 2\" Drop Scupper White PVC",
    "[ Standard ] 2\" Drop Scupper Grey PVC",
    
    "[ Standard ] 3\" Drop Scupper White TPO",
    "[ Standard ] 3\" Drop Scupper Grey TPO",
    "[ Standard ] 3\" Drop Scupper White PVC",
    "[ Standard ] 3\" Drop Scupper Grey PVC",
    
    "[ Metal ] 2\" Drop White TPO", "[ Metal ] 2\" Drop Grey TPO",
    "[ Metal ] 2\" Drop White PVC", "[ Metal ] 2\" Drop Grey PVC",
    
    "[ Metal ] Drop 3\" White TPO", "[ Metal ] 3\" Drop Grey TPO",
    "[ Metal ] Drop 3\" White PVC", "[ Metal ] 3\" Drop Grey PVC",
    
    "3\" Commercial Drain White TPO", "3\" Commercial Drain White PVC",
    "3\" Commercial Drain Grey TPO", "3\" Commercial Drain Grey PVC",
    
    // ThruWalls
    "4\"X4\" Standard ThruWall Scupper White TPO",
    "4\"X4\" Standard ThruWall Scupper Grey TPO",
    "4\"X4\" Standard ThruWall Scupper White PVC",
    "4\"X4\" Standard ThruWall Scupper Grey PVC",
    
    "6\"X6\" Standard ThruWall Scupper White TPO",
    "6\"X6\" Standard ThruWall Scupper Grey TPO",
    "6\"X6\" Standard ThruWall Scupper White PVC",
    "6\"X6\" Standard ThruWall Scupper Grey PVC",
    
    // Pitch Pans
    // Round
    "[ ROUND ] 6\" Pitch Pan White TPO", "[ ROUND ] 6\" Pitch Pan Grey TPO",
    "[ ROUND ] 6\" Pitch Pan White PVC", "[ ROUND ] 6\" Pitch Pan Grey PVC",
    
    "[ ROUND ] 9\" Pitch Pan White TPO", "[ ROUND ] 9\" Pitch Pan Grey TPO",
    "[ ROUND ] 9\" Pitch Pan White PVC", "[ ROUND ] 9\" Pitch Pan Grey PVC",
    
    "[ ROUND ] 12\" Pitch Pan White TPO", "[ ROUND ] 12\" Pitch Pan Grey TPO",
    "[ ROUND ] 12\" Pitch Pan White PVC", "[ ROUND ] 12\" Pitch Pan Grey PVC",
    
    // Square
    "[ SQUARE ] 6\"X6\" Pitch Pan White TPO",
    "[ SQUARE ] 6\"X6\" Pitch Pan Grey TPO",
    "[ SQUARE ] 6\"x6\" Pitch Pan White PVC",
    "[ SQUARE ] 6\"x6\" Pitch Pan Grey PVC",
    
    "[ SQUARE ] 9\"X9\" Pitch Pan White TPO",
    "[ SQUARE ] 9\"X9\" Pitch Pan Grey TPO",
    "[ SQUARE ] 9\"X9\" Pitch Pan White PVC",
    "[ SQUARE ] 9\"X9\" Pitch Pan Grey PVC",
    
    
    "[ SQUARE ] 12\"X12\" Pitch Pan White TPO",
    "[ SQUARE ] 12\"X12\" Pitch Pan Grey TPO",
    "[ SQUARE ] 12\"X12\" Pitch Pan White PVC",
    "[ SQUARE ] 12\"X12\" Pitch Pan Grey PVC"
]

var STOCK_RVO = [
    // RVOs
    "RVO White TPO", "RVO Grey TPO", "RVO White PVC", "RVO Grey PVC"
]

var STOCK_CONES = [
    // Cones -- Split
    "[ SPLIT ] Short A Cone White TPO",
    "[ SPLIT ] Short A Cone Grey TPO",
    "[ SPLIT ] Short A Cone White PVC",
    "[ SPLIT ] Short A Cone Grey PVC",
    
    "[ SPLIT ] A Cone White TPO", "[ SPLIT ] A Cone Grey TPO",
    "[ SPLIT ] A Cone White PVC", "[ SPLIT ] A Cone Grey PVC",
    "[ SPLIT ] B Cone White TPO", "[ SPLIT ] B Cone Grey TPO",
    "[ SPLIT ] B Cone White PVC", "[ SPLIT ] B Cone Grey PVC",
    "[ SPLIT ] C Cone White TPO", "[ SPLIT ] C Cone Grey TPO",
    "[ SPLIT ] C Cone White PVC", "[ SPLIT ] C ConeGrey PVC",
    
    // Cones -- Non-Split
    "[ NON SPLIT ] Short A Cone White TPO",
    "[ NON SPLIT ] Short A Cone Grey TPO",
    "[ NON SPLIT ] Short A Cone White PVC",
    "[ NON SPLIT ] Short A Cone Grey PVC",
    
    "[ NON SPLIT ] A Cone White TPO", "[ NON SPLIT ] A Cone Grey TPO",
    "[ NON SPLIT ] A Cone White PVC", "[ NON SPLIT ] A Cone Grey PVC",
    "[ NON SPLIT ] B Cone White TPO", "[ NON SPLIT ] B Cone Grey TPO",
    "[ NON SPLIT ] B Cone White PVC", "[ NON SPLIT ] B Cone Grey PVC",
    "[ NON SPLIT ] C Cone White TPO", "[ NON SPLIT ] C Cone Grey TPO",
    "[ NON SPLIT ] C Cone White PVC", "[ NON SPLIT ] C Cone Grey PVC"
]

var STOCK_BV1 = [
    // Breather Vents
    // 1-Way
    "[ 1 - WAY ] BV White TPO", "[ 1 - WAY ] BV Grey TPO",
    "[ 1 - WAY ] BV White PVC", "[ 1 - WAY ] BV Grey PVC",
    "Breather Vent White TPO (1-Way)", "Breather Vent Grey TPO (1-Way)",
    "Breather Vent White PVC (1-Way)", "Breather Vent Grey PVC (1-Way)"
]

var STOCK_BV2 = [
    // Breather Vents
    // 2-Way
    "[ 2 - WAY ] BV White TPO", "[ 2 - WAY ] BV Grey TPO",
    "[ 2 - WAY ] BV White PVC", "[ 2 - WAY ] BV Grey PVC",
    "Breather Vent White TPO (2-Way)", "Breather Vent Grey TPO (2-Way)",
    "Breather Vent White PVC (2-Way)", "Breather Vent Grey PVC (2-Way)"
]

var STOCK_CORNERS = [
    // Corners
    "[ INSIDE ] Corner White TPO", "[ INSIDE ] Corner Grey TPO",
    "[ INSIDE ] Corner White PVC", "[ INSIDE ] Corner Grey PVC",
    
    "[ OUTSIDE ] Corner White TPO", "[ OUTSIDE ] Corner Grey TPO",
    "[ OUTSIDE ] Corner White PVC", "[ OUTSIDE ] Corner Grey PVC"
]



var STOCK_DRAINS = [
    // Drains
    "[ Standard ] 2\" Drop Scupper White TPO",
    "[ Standard ] 2\" Drop Scupper Grey TPO",
    "[ Standard ] 2\" Drop Scupper White PVC",
    "[ Standard ] 2\" Drop Scupper Grey PVC",
    
    "[ Standard ] 3\" Drop Scupper White TPO",
    "[ Standard ] 3\" Drop Scupper Grey TPO",
    "[ Standard ] 3\" Drop Scupper White PVC",
    "[ Standard ] 3\" Drop Scupper Grey PVC",
    
    "[ Metal ] 2\" Drop White TPO", "[ Metal ] 2\" Drop Grey TPO",
    "[ Metal ] 2\" Drop White PVC", "[ Metal ] 2\" Drop Grey PVC",
    
    "[ Metal ] Drop 3\" White TPO", "[ Metal ] 3\" Drop Grey TPO",
    "[ Metal ] Drop 3\" White PVC", "[ Metal ] 3\" Drop Grey PVC",
    
]

var STOCK_COMM_DRAINS = [
    // Commercial Drains
    "3\" Commercial Drain White TPO", "3\" Commercial Drain White PVC",
    "3\" Commercial Drain Grey TPO", "3\" Commercial Drain Grey PVC"
]

var STOCK_THRUWALLS = [
    // ThruWalls
    "4\"X4\" Standard ThruWall Scupper White TPO",
    "4\"X4\" Standard ThruWall Scupper Grey TPO",
    "4\"X4\" Standard ThruWall Scupper White PVC",
    "4\"X4\" Standard ThruWall Scupper Grey PVC",
    
    "6\"X6\" Standard ThruWall Scupper White TPO",
    "6\"X6\" Standard ThruWall Scupper Grey TPO",
    "6\"X6\" Standard ThruWall Scupper White PVC",
    "6\"X6\" Standard ThruWall Scupper Grey PVC"
]


var STOCK_PANS = [

    // Pitch Pans
    // Round
    "[ ROUND ] 6\" Pitch Pan White TPO", "[ ROUND ] 6\" Pitch Pan Grey TPO",
    "[ ROUND ] 6\" Pitch Pan White PVC", "[ ROUND ] 6\" Pitch Pan Grey PVC",
    
    "[ ROUND ] 9\" Pitch Pan White TPO", "[ ROUND ] 9\" Pitch Pan Grey TPO",
    "[ ROUND ] 9\" Pitch Pan White PVC", "[ ROUND ] 9\" Pitch Pan Grey PVC",
    
    "[ ROUND ] 12\" Pitch Pan White TPO", "[ ROUND ] 12\" Pitch Pan Grey TPO",
    "[ ROUND ] 12\" Pitch Pan White PVC", "[ ROUND ] 12\" Pitch Pan Grey PVC",
    
    // Square
    "[ SQUARE ] 6\"X6\" Pitch Pan White TPO",
    "[ SQUARE ] 6\"X6\" Pitch Pan Grey TPO",
    "[ SQUARE ] 6\"x6\" Pitch Pan White PVC",
    "[ SQUARE ] 6\"x6\" Pitch Pan Grey PVC",
    
    "[ SQUARE ] 9\"X9\" Pitch Pan White TPO",
    "[ SQUARE ] 9\"X9\" Pitch Pan Grey TPO",
    "[ SQUARE ] 9\"X9\" Pitch Pan White PVC",
    "[ SQUARE ] 9\"X9\" Pitch Pan Grey PVC",
    
    
    "[ SQUARE ] 12\"X12\" Pitch Pan White TPO",
    "[ SQUARE ] 12\"X12\" Pitch Pan Grey TPO",
    "[ SQUARE ] 12\"X12\" Pitch Pan White PVC",
    "[ SQUARE ] 12\"X12\" Pitch Pan Grey PVC"
]



class StockController: UIViewController, UITableViewDataSource, UITableViewDelegate, UISearchBarDelegate, UITextFieldDelegate {
    
    @IBOutlet weak var autoCompleteSearch: UISearchBar!
    @IBOutlet weak var autoCompleteTable: UITableView!
    
    var searchActive: Bool = false

    
    var filtered:[String] = []
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        autoCompleteTable.delegate = self
        autoCompleteTable.dataSource = self
        autoCompleteSearch.delegate = self
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(StockController.keyboardWillShow(_:)), name: UIKeyboardWillShowNotification, object: nil)
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(StockController.keyboardWillHide(_:)), name: UIKeyboardWillHideNotification, object: nil)
        
        
    }
    
    override func shouldAutorotate() -> Bool {
        return false
    }
    
    override func supportedInterfaceOrientations() -> UIInterfaceOrientationMask {
        return UIInterfaceOrientationMask.Portrait
    }
    
    func keyboardWillShow(sender: NSNotification) {
        let info = sender.userInfo!
        let keyboardSize = (info[UIKeyboardFrameEndUserInfoKey] as! NSValue).CGRectValue().height
        
        autoCompleteTable.contentInset.bottom = keyboardSize
    }
    
    func keyboardWillHide(sender: NSNotification) {
        autoCompleteTable.contentInset.bottom = 0
    }
    
    // START **--------------- SEARCH BAR --------------- **
    func searchBarTextDidBeginEditing(searchBar: UISearchBar) {
        searchActive = true;
    }
    
    func saerchBarTextDidEndEditing(searchBar: UISearchBar) {
        searchActive = false
    }
    
    func searchBarCancelButtonClicked(searchBar: UISearchBar) {
        searchActive = false
    }
    
    func searchBarSearchButtonClicked(searchBar: UISearchBar) {
        searchActive = true
    }
    
    func searchBar(searchBar: UISearchBar, textDidChange searchText: String) {
        
        filtered = STOCK_DATA.filter({ (text) -> Bool in
            let tmp: NSString = text
            let range = tmp.rangeOfString(searchText, options: NSStringCompareOptions.CaseInsensitiveSearch)
            return range.location != NSNotFound
        })
        if(filtered.count == 0){
            searchActive = false;
        } else {
            searchActive = true;
        }
        self.autoCompleteTable.reloadData()
    }
    // END **--------------- SEARCH BAR ---------------**
    
    
    // START **--------------- TABLE VIEW ---------------**

    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        if (searchActive) {
            return 1
        } else {
            return 9
        }
        
    }
    
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if (searchActive) {
            if (filtered.count >= 1) {
                return "Showing " + String(filtered.count) + " Search Results"
            } else {
                return ""
            }
        } else {
            if section == 0 {
                return "RVO"
            } else if section == 1{
                return "Cones"
            } else if section == 2 {
                return "Breather Vent 1-Way"
            } else if section == 3 {
                return "Breather Vent 2-Way"
            } else if section == 4 {
                return "Corners"
            } else if section == 5 {
                return "Drop Scuppers"
            } else if section == 6 {
                return "Commercial Drains"
            } else if section == 7 {
                return "ThruWall Scuppers"
            } else if section == 8 {
                return "Pitch Pans"
            } else {
                return ""
            }
        }
    }
    
    
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (searchActive) {
            if (filtered.count >= 1) {
                return filtered.count
            } else {
                return STOCK_DATA.count
            }
        } else {
            if section == 0 {
                return STOCK_RVO.count
            } else if section == 1 {
                return STOCK_CONES.count
            } else if section == 2 {
                return STOCK_BV1.count
            } else if section == 3 {
                return STOCK_BV2.count
            } else if section == 4 {
                return STOCK_CORNERS.count
            } else if section == 5 {
                return STOCK_DRAINS.count
            } else if section == 6 {
                return STOCK_COMM_DRAINS.count
            } else if section == 7 {
                return STOCK_THRUWALLS.count
            } else if section == 8 {
                return STOCK_PANS.count
            } else {
                return STOCK_DATA.count
            }
        }
    
    }

    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell")! as! StockCell
        
        if (searchActive) {
            if (filtered.count >= 1) {
                //cell.configure(filtered[indexPath.row], text: "", placeholder: "Quantity")
                
                cell.stockLabel!.text = filtered[indexPath.row]
                
                // Arbitrary Tag for Filtered Results
                cell.stockAdd.tag = 10
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
            
            } else {
                cell.stockLabel!.text = STOCK_DATA[indexPath.row]
                
                // Arbitrary Tag for Filtered Results Not Matching
                cell.stockAdd.tag = 11
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
            }
            
        } else {
            if indexPath.section == 0 {
                
                cell.stockLabel!.text = STOCK_RVO[indexPath.row]
                cell.stockAdd.tag = 0
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            } else if indexPath.section == 1 {
                cell.stockLabel!.text = STOCK_CONES[indexPath.row]
                cell.stockAdd.tag = 1
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
                
            } else if indexPath.section == 2 {
                cell.stockLabel!.text = STOCK_BV1[indexPath.row]
                cell.stockAdd.tag = 2
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            } else if indexPath.section == 3 {
                cell.stockLabel!.text = STOCK_BV2[indexPath.row]
                cell.stockAdd.tag = 3
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            } else if indexPath.section == 4 {
                cell.stockLabel!.text = STOCK_CORNERS[indexPath.row]
                cell.stockAdd.tag = 4
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            } else if indexPath.section == 5 {
                cell.stockLabel!.text = STOCK_DRAINS[indexPath.row]
                cell.stockAdd.tag = 5
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            } else if indexPath.section == 6 {
                cell.stockLabel!.text = STOCK_COMM_DRAINS[indexPath.row]
                cell.stockAdd.tag = 6
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            } else if indexPath.section == 7 {
                cell.stockLabel!.text = STOCK_THRUWALLS[indexPath.row]
                cell.stockAdd.tag = 7
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            } else if indexPath.section == 8 {
                cell.stockLabel!.text = STOCK_PANS[indexPath.row]
                cell.stockAdd.tag = indexPath.row
                cell.stockAdd.addTarget(self, action: #selector(StockController.addSTOCK(_:)), forControlEvents: .TouchUpInside)
                    
            }
        }
        
        return cell
    }
    // END **--------------- TABLE VIEW ---------------**
    
    
    func addSTOCK(sender: UIButton!) {
        let buttonPosition = sender.convertPoint(CGPointZero, toView: self.autoCompleteTable)
        let indexPath = self.autoCompleteTable.indexPathForRowAtPoint(buttonPosition)
        if indexPath != nil {
            let selectedCell = self.autoCompleteTable.cellForRowAtIndexPath(indexPath!) as! StockCell
            let textLabel = selectedCell.stockLabel!.text!
            let textString = selectedCell.stockQuantity.text!
            if (!textString.isEmpty) {
                let text = textString + ": " + textLabel
                if (!STOCK.contains(text)) {
                    STOCK.append(text)
                    selectedCell.stockQuantity!.text = ""
                } else {
                    return
                }
            }
        }
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
