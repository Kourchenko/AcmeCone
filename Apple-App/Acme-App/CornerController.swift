//
//  CornersController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/25/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class CornerController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {

    var segmentedArray = ["Inside", "Outside"]
    
    var segue_quantity = ""
    var segue_type = ""
    var segue_height = ""
    var segue_heightFrac = ""
    var segue_depth = ""
    var segue_depthFrac = ""
    var segue_flange = ""
    var segue_flangeFrac = ""
    var segue_color = ""
    var segue_material = ""
    var segue_optional = ""
    
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var typeSegmented: UISegmentedControl!
    @IBOutlet weak var quantityTextField: UITextField!
    @IBOutlet weak var quantityLabel: UILabel!
    @IBOutlet weak var err_quantity: UILabel!
    @IBOutlet weak var err_quantity_int: UILabel!
    @IBOutlet weak var heightTextField: UITextField!
    @IBOutlet weak var heightLabel: UILabel!
    @IBOutlet weak var heightFracPicker: UIPickerView!
    @IBOutlet weak var err_height: UILabel!
    @IBOutlet weak var err_height_int: UILabel!
    @IBOutlet weak var depthTextField: UITextField!
    @IBOutlet weak var depthLabel: UILabel!
    @IBOutlet weak var depthFracPicker: UIPickerView!
    @IBOutlet weak var err_depth: UILabel!
    @IBOutlet weak var err_depth_int: UILabel!
    @IBOutlet weak var flangeTextField: UITextField!
    @IBOutlet weak var flangeLabel: UILabel!
    @IBOutlet weak var flangeFracPicker: UIPickerView!
    @IBOutlet weak var err_flange: UILabel!
    @IBOutlet weak var err_flange_int: UILabel!
    @IBOutlet weak var colorTextField: UITextField!
    @IBOutlet weak var colorLabel: UILabel!
    @IBOutlet weak var err_color: UILabel!
    @IBOutlet weak var materialTextField: UITextField!
    @IBOutlet weak var materialLabel: UILabel!
    @IBOutlet weak var err_material: UILabel!
    @IBOutlet weak var _optionalTextField: UITextField!
            
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        
        quantityTextField.delegate = self
        heightTextField.delegate = self
        depthTextField.delegate = self
        flangeTextField.delegate = self
        colorTextField.delegate = self
        materialTextField.delegate = self
        _optionalTextField.delegate = self
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(ConeController.keyboardWillShow(_:)), name:UIKeyboardWillShowNotification, object: nil)
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(ConeController.keyboardWillHide(_:)), name:UIKeyboardWillHideNotification, object: nil)
        
        err_quantity_int.hidden = true
        err_height_int.hidden = true
        err_depth_int.hidden = true
        err_flange_int.hidden = true
        
        err_quantity.hidden = true
        err_height.hidden = true
        err_depth.hidden = true
        err_flange.hidden = true
        err_color.hidden = true
        err_material.hidden = true
        
        if (segue_quantity == "") {
            quantityLabel.hidden = true
            heightLabel.hidden = true
            depthLabel.hidden = true
            flangeLabel.hidden = true
            colorLabel.hidden = true
            materialLabel.hidden = true
        } else if (segue_quantity != ""){
            quantityLabel.hidden = false
            heightLabel.hidden = false
            depthLabel.hidden = false
            flangeLabel.hidden = false
            colorLabel.hidden = false
            materialLabel.hidden = false
            
            typeSegmented.selectedSegmentIndex = segmentedArray.indexOf(segue_type)!
            
            quantityTextField.text = segue_quantity
            
            heightTextField.text = segue_height
            heightFracPicker.selectRow(fractions.indexOf((segue_heightFrac))!, inComponent: 0, animated: false)
            
            depthTextField.text = segue_depth
            depthFracPicker.selectRow(fractions.indexOf(segue_depthFrac)!, inComponent: 0, animated: false)
            
            flangeTextField.text = segue_flange
            flangeFracPicker.selectRow(fractions.indexOf(segue_flangeFrac)!, inComponent: 0, animated: false)
            
            colorTextField.text = segue_color
            materialTextField.text = segue_material
            _optionalTextField.text = segue_optional
    
        }
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func cancel(sender: AnyObject) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
    
    @IBAction func add(sender: AnyObject) {
        let quantity = quantityTextField.text!
        let height = heightTextField.text!
        let depth = depthTextField.text!
        let flange = flangeTextField.text!
        let color = colorTextField.text!
        let material = materialTextField.text!
        
        if ((!quantity.isEmpty)
            && (!height.isEmpty)
            && (!depth.isEmpty)
            && (!flange.isEmpty)
            && (!color.isEmpty)
            && (!material.isEmpty)) {
            
            
            let heightFrac = String(fractions[heightFracPicker.selectedRowInComponent(0)])
            let depthFrac = String(fractions[depthFracPicker.selectedRowInComponent(0)])
            let flangeFrac = String(fractions[flangeFracPicker.selectedRowInComponent(0)])
            let optional = _optionalTextField.text!
            
            if (optional.isEmpty) {
                if ((Int(quantity) == nil)
                    || (Int(depth) == nil)
                    || (Int(height) == nil)
                    || (Int(flange) == nil)) {
                    
                    if (Int(quantity) == nil) {
                        err_quantity_int.hidden = false
                    } else if (Int(depth) == nil) {
                        err_depth_int.hidden = false
                    } else if (Int(height) == nil) {
                        err_height_int.hidden = false
                    } else if (Int(flange) == nil) {
                        err_flange_int.hidden = false
                    }
                } else {
                    let id: String = String(depth) + String(depthFrac) + String(height) + String(heightFrac) + String(flange) + String(flangeFrac)

                    let corner = Corner(quantity: Int(quantity)!,
                                        type: self.typeSegmented.titleForSegmentAtIndex(self.typeSegmented.selectedSegmentIndex)!,
                                        depth: Int(depth)!,
                                        depthFrac:  depthFrac,
                                        height: Int(height)!,
                                        heightFrac: heightFrac,
                                        flange: Int(flange)!,
                                        flangeFrac: flangeFrac,
                                        color: color,
                                        material: material,
                                        _optional: "",
                                        id: id)
                    
                    let results = CORNERS.filter {$0.id == id}
                    if (results.isEmpty) {
                        CORNERS.append(corner)
                        self.dismissViewControllerAnimated(true, completion: nil)
                    } else {
                        
                        let alert: UIAlertView = UIAlertView(title: "", message: "These Corner Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
                        let loadingIndicator: UIActivityIndicatorView = UIActivityIndicatorView(frame: CGRectMake(50, 10, 37, 37)) as UIActivityIndicatorView
                        
                        loadingIndicator.center = self.view.center;
                        loadingIndicator.hidesWhenStopped = true
                        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.Gray
                        loadingIndicator.startAnimating();
                        
                        alert.show()
                        
                        // Delay the dismissal by 3 seconds
                        let delay = 2.0 * Double(NSEC_PER_SEC)
                        let time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
                        dispatch_after(time, dispatch_get_main_queue(), {
                            alert.dismissWithClickedButtonIndex(-1, animated: true)
                        })
                    }
                }
                
            } else {
                if ((Int(quantity) == nil)
                    || (Int(depth) == nil)
                    || (Int(height) == nil)
                    || (Int(flange) == nil)) {
                    
                    if (Int(quantity) == nil) {
                        err_quantity_int.hidden = false
                    } else if (Int(depth) == nil) {
                        err_depth_int.hidden = false
                    } else if (Int(height) == nil) {
                        err_height_int.hidden = false
                    } else if (Int(flange) == nil) {
                        err_flange_int.hidden = false
                    }
                } else {
                    
                    let id: String = String(depth) + String(depthFrac) + String(height) + String(heightFrac) + String(flange) + String(flangeFrac) + String(color)
                    
                    let corner = Corner(quantity: Int(quantity)!,
                                        type: self.typeSegmented.titleForSegmentAtIndex(self.typeSegmented.selectedSegmentIndex)!,
                                        depth: Int(depth)!,
                                        depthFrac: depthFrac,
                                        height: Int(height)!,
                                        heightFrac: heightFrac,
                                        flange: Int(flange)!,
                                        flangeFrac: flangeFrac,
                                        color: color,
                                        material: material,
                                        _optional: optional,
                                        id: id)
                    
                    let results = CORNERS.filter {$0.id == id}
                    if (results.isEmpty) {
                        CORNERS.append(corner)
                        self.dismissViewControllerAnimated(true, completion: nil)
                    } else {
                        
                        let alert: UIAlertView = UIAlertView(title: "", message: "These Corner Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
                        let loadingIndicator: UIActivityIndicatorView = UIActivityIndicatorView(frame: CGRectMake(50, 10, 37, 37)) as UIActivityIndicatorView
                        
                        loadingIndicator.center = self.view.center;
                        loadingIndicator.hidesWhenStopped = true
                        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.Gray
                        loadingIndicator.startAnimating();
                        
                        alert.show()
                        
                        // Delay the dismissal by 2 seconds
                        let delay = 2.0 * Double(NSEC_PER_SEC)
                        let time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
                        dispatch_after(time, dispatch_get_main_queue(), {
                            alert.dismissWithClickedButtonIndex(-1, animated: true)
                        })
                    }
                }
            }
            
        } else {
            if ((quantity.isEmpty)
                || (height.isEmpty)
                || (depth.isEmpty)
                || (height.isEmpty)
                || (flange.isEmpty)
                || (color.isEmpty)
                || (material.isEmpty)) {
                
                if (quantity.isEmpty) {
                    err_quantity.hidden = false
                } else if (height.isEmpty) {
                    err_height.hidden = false
                } else if (depth.isEmpty) {
                    err_depth.hidden = false
                } else if (height.isEmpty) {
                    err_height.hidden = false
                } else if (flange.isEmpty) {
                    err_flange.hidden = false
                } else if (color.isEmpty) {
                    err_color.hidden = false
                } else if (material.isEmpty) {
                    err_material.hidden = false
                }
                
            }
        }
    }
    
    // -* fieldChange
    //
    // -> ScrollView moves TextField above Keyboard
    func keyboardWillShow(notification: NSNotification) {
        var userInfo = notification.userInfo!
        var keyboardFrame: CGRect = (userInfo[UIKeyboardFrameBeginUserInfoKey] as! NSValue).CGRectValue();
        
        keyboardFrame = self.view.convertRect(keyboardFrame, fromView: nil)
        var contentInset: UIEdgeInsets = self.scrollView.contentInset
        contentInset.bottom = keyboardFrame.size.height + 20
        self.scrollView.contentInset = contentInset
        
    }
    
    func keyboardWillHide(notification: NSNotification) {
        let contentInset: UIEdgeInsets = UIEdgeInsetsZero
        self.scrollView.contentInset = contentInset
    }
    // -* ScrollView
    
    
    
    // - fieldChange
    // -- Show TextField placeholder
    // -- Show Error if no content
    @IBAction func quantityFieldChanged(sender: AnyObject) {
        if (!quantityTextField.hasText()) {
            quantityLabel.hidden = true
            err_quantity.hidden = true
            err_quantity_int.hidden = true
            // Dispose of no quantity error
        } else if (quantityTextField.hasText()) {
            quantityLabel.hidden = false
            err_quantity.hidden = true
            if (Int(quantityTextField.text!) != nil) {
                err_quantity_int.hidden = true
            } else if (Int(quantityTextField.text!) == nil) {
                err_quantity_int.hidden = false
            }
        }
    }
    
    @IBAction func heightFieldChanged(sender: AnyObject) {
        if (!heightTextField.hasText()) {
            heightLabel.hidden = true
            err_height.hidden = true
            err_height_int.hidden = true
        } else if (heightTextField.hasText()) {
            heightLabel.hidden = false
            err_height.hidden = true
            if (Int(heightTextField.text!) != nil) {
                err_height_int.hidden = true
            } else if (Int(heightTextField.text!) == nil) {
                err_height_int.hidden = false
            }
        }
    }
    
    @IBAction func depthFieldChanged(sender: AnyObject) {
        if (!depthTextField.hasText()) {
            depthLabel.hidden = true
            err_depth.hidden = true
            err_depth_int.hidden = true
        } else if (depthTextField.hasText()) {
            depthLabel.hidden = false
            err_depth.hidden = true
            if (Int(depthTextField.text!) != nil) {
                err_depth_int.hidden = true
            } else if (Int(depthTextField.text!) == nil) {
                err_depth_int.hidden = false
            }
        }
    }
    
    @IBAction func flangeFieldChanged(sender: AnyObject) {
        if (!flangeTextField.hasText()) {
            flangeLabel.hidden = true
            err_flange.hidden = true
            err_flange_int.hidden = true
        } else if (flangeTextField.hasText()) {
            flangeLabel.hidden = false
            err_flange.hidden = true
            if (Int(flangeTextField.text!) != nil) {
                err_flange_int.hidden = true
            } else if (Int(flangeTextField.text!) == nil) {
                err_flange_int.hidden = false
            }
        }
    }
    
    @IBAction func colorFieldChanged(sender: AnyObject) {
        if (!colorTextField.hasText()) {
            colorLabel.hidden = true
            err_color.hidden = false
            
        } else if (colorTextField.hasText()) {
            colorLabel.hidden = false
            err_color.hidden = true
        }
    }
    
    @IBAction func materialFieldChanged(sender: AnyObject) {
        if (materialTextField.text == "") {
            materialLabel.hidden = true
            err_material.hidden = false
        } else if (materialTextField.text != "") {
            materialLabel.hidden = false
            err_material.hidden = true
        }
    }
    
    
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return fractions.count
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return fractions[row]
    }
    
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        self.view.endEditing(true)
    }
    
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        if (textField == quantityTextField) {
            heightTextField.becomeFirstResponder()
        } else if (textField == heightTextField) {
            depthTextField.becomeFirstResponder()
        } else if (textField == depthTextField) {
            flangeTextField.becomeFirstResponder()
        } else if (textField == flangeTextField) {
            colorTextField.becomeFirstResponder()
        } else if (textField == colorTextField) {
            materialTextField.becomeFirstResponder()
        } else if (textField == materialTextField) {
            materialTextField.resignFirstResponder()
        } else if (textField == _optionalTextField) {
            _optionalTextField.resignFirstResponder()
        }
        return false
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
