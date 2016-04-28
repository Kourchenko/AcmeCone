//
//  PIpeController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/26/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class PipeController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {
    
    var segmentedArray = ["Split", "Non-Split"]
    
    var segue_quantity = ""
    var segue_type = ""
    var segue_height = ""
    var segue_heightFrac = ""
    var segue_diameter = ""
    var segue_diameterFrac = ""
    var segue_flange = ""
    var segue_flangeFrac = ""
    var segue_color = ""
    var segue_material = ""
    var segue_optional = ""

    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var typeSegment: UISegmentedControl!
    @IBOutlet weak var quantityTextField: UITextField!
    @IBOutlet weak var quantityLabel: UILabel!
    @IBOutlet weak var err_quantity: UILabel!
    @IBOutlet weak var err_quantity_int: UILabel!
    @IBOutlet weak var heightTextField: UITextField!
    @IBOutlet weak var heightFracPicker: UIPickerView!
    @IBOutlet weak var heightLabel: UILabel!
    @IBOutlet weak var err_height: UILabel!
    @IBOutlet weak var err_height_int: UILabel!
    @IBOutlet weak var diameterTextField: UITextField!
    @IBOutlet weak var diameterFracPicker: UIPickerView!
    @IBOutlet weak var diameterLabel: UILabel!
    @IBOutlet weak var err_diameter: UILabel!
    @IBOutlet weak var err_diameter_int: UILabel!
    @IBOutlet weak var flangeTextField: UITextField!
    @IBOutlet weak var flangeFracPicker: UIPickerView!
    @IBOutlet weak var flangeLabel: UILabel!
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
        diameterTextField.delegate = self
        flangeTextField.delegate = self
        colorTextField.delegate = self
        materialTextField.delegate = self
        _optionalTextField.delegate = self
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(ConeController.keyboardWillShow(_:)), name:UIKeyboardWillShowNotification, object: nil)
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(ConeController.keyboardWillHide(_:)), name:UIKeyboardWillHideNotification, object: nil)
        
        err_quantity_int.hidden = true
        err_height_int.hidden = true
        err_diameter_int.hidden = true
        err_flange_int.hidden = true
        
        err_quantity.hidden = true
        err_height.hidden = true
        err_diameter.hidden = true
        err_flange.hidden = true
        err_color.hidden = true
        err_material.hidden = true
        
        if (segue_quantity == "" ) {
            quantityLabel.hidden = true
            heightLabel.hidden = true
            diameterLabel.hidden = true
            flangeLabel.hidden = true
            colorLabel.hidden = true
            materialLabel.hidden = true
        } else if (segue_quantity != ""){
            quantityLabel.hidden = false
            heightLabel.hidden = false
            diameterLabel.hidden = false
            flangeLabel.hidden = false
            colorLabel.hidden = false
            materialLabel.hidden = false
            
            typeSegment.selectedSegmentIndex = segmentedArray.indexOf(segue_type)!
            quantityTextField.text = segue_type
            
            heightTextField.text = segue_height
            heightFracPicker.selectRow(fractions.indexOf(segue_heightFrac)!, inComponent: 0, animated: false)
            
            diameterTextField.text = segue_diameter
            diameterFracPicker.selectRow(fractions.indexOf(segue_diameterFrac)!, inComponent: 0, animated: false)
            
            flangeTextField.text = segue_flange
            flangeFracPicker.selectRow(fractions.indexOf(segue_flange)!, inComponent: 0, animated: false)
            
            colorTextField.text = segue_color
            materialTextField.text = segue_material
            _optionalTextField.text = segue_optional
        }
    }
    
    override func viewWillDisappear(animated: Bool) {
        super.viewWillDisappear(animated)
        
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
        let diameter = diameterTextField.text!
        let flange = flangeTextField.text!
        let color = colorTextField.text!
        let material = materialTextField.text!
        
        if ((!quantity.isEmpty)
            && (!height.isEmpty)
            && (!diameter.isEmpty)
            && (!flange.isEmpty)
            && (!color.isEmpty)
            && (!material.isEmpty)) {
            
            let heightFrac = String(fractions[heightFracPicker.selectedRowInComponent(0)])
            let diameterFrac = String(fractions[diameterFracPicker.selectedRowInComponent(0)])
            let flangeFrac = String(fractions[flangeFracPicker.selectedRowInComponent(0)])
            let _optional = _optionalTextField.text!

            
            if (_optional.isEmpty) {
                if ((Int(quantity) == nil)
                    || (Int(height) == nil)
                    || (Int(diameter) == nil)
                    || (Int(flange) == nil)) {
                    
                    if (Int(quantity) == nil) {
                        err_quantity_int.hidden = false
                    } else if (Int(height) == nil) {
                        err_height_int.hidden = false
                    } else if (Int(diameter) == nil) {
                        err_diameter_int.hidden = false
                    } else if (Int(flange) == nil) {
                        err_flange_int.hidden = false
                    }
                    
                } else {
                
                    let id = String(height) + String(diameter) + String(material)
                    let pipe = Pipe(quantity: Int(quantity)!,
                                type: String(self.typeSegment.titleForSegmentAtIndex(self.typeSegment.selectedSegmentIndex)!),
                                height: Int(height)!,
                                heightFrac: heightFrac,
                                diameter: Int(diameter)!,
                                diameterFrac: diameterFrac,
                                flange: Int(flange)!,
                                flangeFrac: flangeFrac,
                                color: color,
                                material: material,
                                _optional: "",
                                id: id)
                
                    let results = PIPES.filter {$0.id == id}
                    if (results.isEmpty) {
                        PIPES.append(pipe)
                        self.dismissViewControllerAnimated(true, completion: nil)
                
                    } else {
                        let alert: UIAlertView = UIAlertView(title: "", message: "These Cone Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
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
                    || (Int(height) == nil)
                    || (Int(diameter) == nil)
                    || (Int(flange) == nil)) {
                    
                    if (Int(quantity) == nil) {
                        err_quantity_int.hidden = false
                    } else if (Int(height) == nil) {
                        err_height_int.hidden = false
                    } else if (Int(diameter) == nil) {
                        err_diameter_int.hidden = false
                    } else if (Int(flange) == nil) {
                        err_flange_int.hidden = false
                    }
                    
                } else {
                    let id = String(height) + String(diameter) + String(material)
                    let pipe = Pipe(quantity: Int(quantity)!,
                                    type: String(self.typeSegment.titleForSegmentAtIndex(self.typeSegment.selectedSegmentIndex)!),
                                    height: Int(height)!,
                                    heightFrac: heightFrac,
                                    diameter: Int(diameter)!,
                                    diameterFrac: diameterFrac,
                                    flange: Int(flange)!,
                                    flangeFrac: flangeFrac,
                                    color: color,
                                    material: material,
                                    _optional: "",
                                    id: id)
                    
                    let results = PIPES.filter {$0.id == id}
                    if (results.isEmpty) {
                        PIPES.append(pipe)
                        self.dismissViewControllerAnimated(true, completion: nil)
                        
                    } else {
                        let alert: UIAlertView = UIAlertView(title: "", message: "These Pipe Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
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
                || (diameter.isEmpty)
                || (flange.isEmpty)
                || (color.isEmpty)
                || (material.isEmpty)) {
                
                if (quantity.isEmpty) {
                    err_quantity.hidden = false
                } else if (height.isEmpty) {
                    err_height.hidden = false
                } else if (diameter.isEmpty) {
                    err_diameter.hidden = false
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
    @IBAction func diameterFieldChanged(sender: AnyObject) {
        if (!diameterTextField.hasText()) {
            diameterLabel.hidden = true
            err_diameter.hidden = true
            err_diameter_int.hidden = true
            // Dispose of no quantity error
        } else if (diameterTextField.hasText()) {
            diameterLabel.hidden = false
            err_diameter.hidden = true
            if (Int(diameterTextField.text!) != nil) {
                err_diameter_int.hidden = true
            } else if (Int(diameterTextField.text!) == nil) {
                err_diameter_int.hidden = false
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
            quantityTextField.resignFirstResponder()
            heightTextField.becomeFirstResponder()
        } else if (textField == heightTextField) {
            heightTextField.resignFirstResponder()
            diameterTextField.becomeFirstResponder()
        } else if (textField == diameterTextField) {
            diameterTextField.resignFirstResponder()
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
