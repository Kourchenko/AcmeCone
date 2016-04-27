//
//  CornersController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/25/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class CornerController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {

    
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
        
        quantityLabel.hidden = true
        heightLabel.hidden = true
        depthLabel.hidden = true
        flangeLabel.hidden = true
        colorLabel.hidden = true
        materialLabel.hidden = true
        
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
                                        _optional: "")
                    
                    CORNERS.append(corner)
                    self.dismissViewControllerAnimated(true, completion: nil)
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
                                        _optional: optional)
                    
                    CORNERS.append(corner)
                    self.dismissViewControllerAnimated(true, completion: nil)
                }
            }
        } else {
            if (quantityTextField.text == "") {
                err_quantity.hidden = false
            } else if (heightTextField.text == "") {
                err_height.hidden = false
            } else if (depthTextField.text == "") {
                err_depth.hidden = false
            } else if (flangeTextField.text == "") {
                err_flange.hidden = false
            } else if (colorTextField.text == "") {
                err_color.hidden = false
            } else if (materialTextField.text == "") {
                err_material.hidden = false
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
        if (quantityTextField.text == "") {
            quantityLabel.hidden = true
            if (Int(quantityTextField.text!) != nil) {
                err_quantity_int.hidden = true
            } else if (Int(quantityTextField.text!) == nil) {
                err_quantity_int.hidden = false
            }
        } else {
            if (quantityTextField.text != "") {
                quantityLabel.hidden = false
                if (Int(quantityTextField.text!) != nil) {
                    err_quantity_int.hidden = true
                } else if (Int(quantityTextField.text!) == nil) {
                    err_quantity_int.hidden = false
                }
            }
        }
    }
    
    @IBAction func heightFieldChanged(sender: AnyObject) {
        if (!heightTextField.hasText()) {
            heightLabel.hidden = true
            if (Int(heightTextField.text!) != nil) {
                err_height_int.hidden = true
            } else if (Int(heightTextField.text!) == nil) {
                err_height_int.hidden = false
            }
        } else {
            if (heightTextField.hasText()) {
                heightLabel.hidden = false
                if (Int(heightTextField.text!) != nil) {
                    err_height_int.hidden = true
                } else if (Int(heightTextField.text!) == nil) {
                    err_height_int.hidden = false
                }
            }
        }
    }
    
    @IBAction func depthFieldChanged(sender: AnyObject) {
        if (!depthTextField.hasText()) {
            depthLabel.hidden = true
            if (Int(depthTextField.text!) != nil) {
                err_depth_int.hidden = true
            } else if (Int(depthTextField.text!) == nil) {
                err_depth_int.hidden = false
            }
        } else {
            if (depthTextField.hasText()) {
                depthLabel.hidden = false
                if (Int(depthTextField.text!) != nil) {
                    err_depth_int.hidden = true
                } else if (Int(depthTextField.text!) == nil) {
                    err_depth_int.hidden = false
                }
            }
        }
    }
    
    @IBAction func flangeFieldChanged(sender: AnyObject) {
        if (!flangeTextField.hasText()) {
            flangeLabel.hidden = true
            if (Int(flangeTextField.text!) != nil) {
                err_flange_int.hidden = true
            } else if (Int(flangeTextField.text!) == nil) {
                err_flange_int.hidden = false
            }
        } else {
            if (flangeTextField.hasText()) {
                flangeLabel.hidden = false
                if (Int(flangeTextField.text!) != nil) {
                    err_flange_int.hidden = true
                } else if (Int(flangeTextField.text!) == nil) {
                    err_flange_int.hidden = false
                }
            }
        }
    }
    
    
    @IBAction func colorFieldChanged(sender: AnyObject) {
        if (!colorTextField.hasText()) {
            colorLabel.hidden = true
            err_color.hidden = false
            
        } else {
            if (colorTextField.hasText()) {
                colorLabel.hidden = false
                err_color.hidden = true
            }
        }
    }
    
    @IBAction func materialFieldChanged(sender: AnyObject) {
        if (materialTextField.text == "") {
            materialLabel.hidden = true
            err_material.hidden = false
        } else {
            if (materialTextField.text != "") {
                materialLabel.hidden = false
                err_material.hidden = true
            }
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
            quantityTextField.resignFirstResponder()
            heightTextField.becomeFirstResponder()
        } else if (textField == heightTextField) {
            heightTextField.resignFirstResponder()
            depthTextField.becomeFirstResponder()
        } else if (textField == depthTextField) {
            depthTextField.resignFirstResponder()
            flangeTextField.becomeFirstResponder()
        } else if (textField == flangeTextField) {
            flangeTextField.resignFirstResponder()
            colorTextField.becomeFirstResponder()
        } else if (textField == colorTextField) {
            colorTextField.resignFirstResponder()
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
