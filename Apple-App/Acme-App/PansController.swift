//
//  PansController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/29/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class PansController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {

    var segmentedRoundArray = ["Round", "Square"]
    var segmentedSplitArray = ["Split", "Non-Split"]
    
    var segue_quantity = ""
    var segue_round = ""
    var segue_split = ""
    var segue_diameter = ""
    var segue_diameterFrac = ""
    var segue_height = ""
    var segue_heightFrac = ""
    var segue_width = ""
    var segue_widthFrac = ""
    var segue_length = ""
    var segue_lengthFrac = ""
    var segue_flange = ""
    var segue_flangeFrac = ""
    var segue_color = ""
    var segue_material = ""
    var segue_optional = ""
    
    
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var roundSegmented: UISegmentedControl!
    @IBOutlet weak var splitSegmented: UISegmentedControl!
    @IBOutlet weak var quantityTextField: UITextField!
    @IBOutlet weak var quantityLabel: UILabel!
    @IBOutlet weak var err_quantity: UILabel!
    @IBOutlet weak var err_quantity_int: UILabel!
    @IBOutlet weak var diameterTextField: UITextField!
    @IBOutlet weak var diameterFracPicker: UIPickerView!
    @IBOutlet weak var diameterLabel: UILabel!
    @IBOutlet weak var err_diameter: UILabel!
    @IBOutlet weak var err_diameter_int: UILabel!
    @IBOutlet weak var heightTextField: UITextField!
    @IBOutlet weak var heightFracPicker: UIPickerView!
    @IBOutlet weak var heightLabel: UILabel!
    @IBOutlet weak var err_height: UILabel!
    @IBOutlet weak var err_height_int: UILabel!
    @IBOutlet weak var widthTextField: UITextField!
    @IBOutlet weak var widthFracPicker: UIPickerView!
    @IBOutlet weak var widthLabel: UILabel!
    @IBOutlet weak var err_width: UILabel!
    @IBOutlet weak var err_width_int: UILabel!
    @IBOutlet weak var lengthTextField: UITextField!
    @IBOutlet weak var lengthFracPicker: UIPickerView!
    @IBOutlet weak var lengthLabel: UILabel!
    @IBOutlet weak var err_length: UILabel!
    @IBOutlet weak var err_length_int: UILabel!
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
        diameterTextField.delegate = self
        heightTextField.delegate = self
        widthTextField.delegate = self
        lengthTextField.delegate = self
        flangeTextField.delegate = self
        colorTextField.delegate = self
        materialTextField.delegate = self
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(ConeController.keyboardWillShow(_:)), name:UIKeyboardWillShowNotification, object: nil)
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(ConeController.keyboardWillHide(_:)), name:UIKeyboardWillHideNotification, object: nil)
        
        err_quantity_int.hidden = true
        err_diameter_int.hidden = true
        err_height_int.hidden = true
        err_width_int.hidden = true
        err_length_int.hidden = true
        err_flange_int.hidden = true
        
        err_quantity.hidden = true
        err_diameter.hidden = true
        err_height.hidden = true
        err_width.hidden = true
        err_length.hidden = true
        err_flange.hidden = true
        err_color.hidden = true
        err_material.hidden = true
        
        // Adding New Product, Not receiving Segue INFO
        if (segue_quantity == "") {
            quantityLabel.hidden = true
            diameterLabel.hidden = true
            heightLabel.hidden = true
            widthLabel.hidden = true
            lengthLabel.hidden = true
            flangeLabel.hidden = true
            colorLabel.hidden = true
            materialLabel.hidden = true
            
            // Default UISegmentedControl ROUND
            widthTextField.hidden = true
            widthFracPicker.hidden = true
            widthLabel.hidden = true
            err_width.hidden = true
            err_width_int.hidden = true
            
            lengthTextField.hidden = true
            lengthFracPicker.hidden = true
            lengthLabel.hidden = true
            err_length.hidden = true
            err_length_int.hidden = true
            
            
        } else if (segue_quantity != "") {
            quantityLabel.hidden = false
            heightLabel.hidden = false
            flangeLabel.hidden = false
            colorLabel.hidden = false
            materialLabel.hidden = false
            
            if (segue_round == "Round") {
                widthTextField.hidden = true
                widthFracPicker.hidden = true
                widthLabel.hidden = true
                err_width.hidden = true
                err_width_int.hidden = true
                
                lengthTextField.hidden = true
                lengthFracPicker.hidden = true
                lengthLabel.hidden = true
                err_length.hidden = true
                err_length_int.hidden = true
                
                roundSegmented.selectedSegmentIndex = segmentedRoundArray.indexOf(segue_round)!
                splitSegmented.selectedSegmentIndex = segmentedSplitArray.indexOf(segue_split)!
                diameterTextField.text = segue_quantity
                diameterFracPicker.selectRow(fractions.indexOf(segue_diameterFrac)!, inComponent: 0, animated: false)
                diameterLabel.hidden = false
                heightTextField.text = segue_height
                heightFracPicker.selectRow(fractions.indexOf(segue_heightFrac)!, inComponent: 0, animated: false)
                flangeTextField.text = segue_flange
                flangeFracPicker.selectRow(fractions.indexOf(segue_flangeFrac)!, inComponent: 0, animated: false)
                colorTextField.text = segue_color
                materialTextField.text = segue_material
                _optionalTextField.text = segue_optional
                
                
            } else if (segue_round == "Square") {
                diameterTextField.hidden = true
                diameterFracPicker.hidden = true
                diameterLabel.hidden = true
                err_diameter.hidden = true
                err_diameter_int.hidden = true
                
                roundSegmented.selectedSegmentIndex = segmentedRoundArray.indexOf(segue_round)!
                splitSegmented.selectedSegmentIndex = segmentedSplitArray.indexOf(segue_split)!
                widthTextField.text = segue_width
                widthFracPicker.selectRow(fractions.indexOf(segue_widthFrac)!, inComponent: 0, animated: false)
                widthLabel.hidden = false
                lengthTextField.text = segue_length
                lengthFracPicker.selectRow(fractions.indexOf(segue_lengthFrac)!, inComponent: 0, animated: false)
                lengthLabel.hidden = false
                heightTextField.text = segue_height
                heightFracPicker.selectRow(fractions.indexOf(segue_heightFrac)!, inComponent: 0, animated: false)
                flangeTextField.text = segue_flange
                flangeFracPicker.selectRow(fractions.indexOf(segue_flangeFrac)!, inComponent: 0, animated: false)
                colorTextField.text = segue_color
                materialTextField.text = segue_material
                _optionalTextField.text = segue_optional
                
            }
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
        let roundType = self.roundSegmented.titleForSegmentAtIndex(self.roundSegmented.selectedSegmentIndex)!
        let splitType = self.splitSegmented.titleForSegmentAtIndex(self.splitSegmented.selectedSegmentIndex)!
        let diameter = diameterTextField.text!
        let height = heightTextField.text!
        let width = widthTextField.text!
        let length = lengthTextField.text!
        let flange = flangeTextField.text!
        let color = colorTextField.text!
        let material = materialTextField.text!
        
        
        if ((!quantity.isEmpty)
            && (!height.isEmpty)
            && (!flange.isEmpty)
            && (!color.isEmpty)
            && (!material.isEmpty)) {
            
            let diameterFrac = String(fractions[diameterFracPicker.selectedRowInComponent(0)])
            let heightFrac = String(fractions[heightFracPicker.selectedRowInComponent(0)])
            let widthFrac = String(fractions[widthFracPicker.selectedRowInComponent(0)])
            let lengthFrac = String(fractions[lengthFracPicker.selectedRowInComponent(0)])
            let flangeFrac = String(fractions[flangeFracPicker.selectedRowInComponent(0)])
            let _optional = _optionalTextField.text!

            
            // ADD ROUND Pitch Pan
            if ((!diameter.isEmpty)
                && (width.isEmpty && length.isEmpty)) {
            
            
                if (_optional.isEmpty) {
                    if ((Int(quantity) == nil)
                        || (Int(diameter) == nil)
                        || (Int(height) == nil)
                        || (Int(flange) == nil)) {
                    
                        if (Int(quantity) == nil) {
                            err_quantity_int.hidden = false
                        } else if (Int(diameter) == nil) {
                            err_diameter_int.hidden = false
                        } else if (Int(height) == nil) {
                            err_height_int.hidden = false
                        } else if (Int(flange) == nil) {
                            err_flange_int.hidden = false
                        }
                    } else {
                        let id: String = String(roundType) + String(splitType) + String(height) + String(heightFrac) + String(diameter) + String(diameterFrac) + String(width) + String(widthFrac) + String(length) + String(lengthFrac) + String(flange) + String(flangeFrac) + String(color)
                        
                        let pan = Pan(quantity: Int(quantity)!,
                                      roundType: String(roundType),
                                      splitType: String(splitType),
                                      height: Int(height)!,
                                      heightFrac: heightFrac,
                                      diameter: Int(diameter)!,
                                      diameterFrac: diameterFrac,
                                      width: 0,
                                      widthFrac: "",
                                      length: 0,
                                      lengthFrac: "",
                                      flange: Int(flange)!,
                                      flangeFrac: flangeFrac,
                                      color: color,
                                      material: material,
                                      _optional: "",
                                      id: id)
                        
                        let results = PANS.filter {$0.id == id}
                        if (results.isEmpty) {
                            PANS.append(pan)
                            self.dismissViewControllerAnimated(true, completion: nil)
                        } else {
                            let alert: UIAlertView = UIAlertView(title: "", message: "These Cone Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
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
                
                } else if (!_optional.isEmpty) {
                    if ((Int(quantity) == nil)
                        || (Int(diameter) == nil)
                        || (Int(height) == nil)
                        || (Int(flange) == nil)) {
                        
                        if (Int(quantity) == nil) {
                            err_quantity_int.hidden = false
                        } else if (Int(diameter) == nil) {
                            err_diameter_int.hidden = false
                        } else if (Int(height) == nil) {
                            err_height_int.hidden = false
                        } else if (Int(flange) == nil) {
                            err_flange_int.hidden = false
                        }
                    } else {
                        let id: String = String(roundType) + String(splitType) + String(height) + String(heightFrac) + String(diameter) + String(diameterFrac) + String(width) + String(widthFrac) + String(length) + String(lengthFrac) + String(flange) + String(flangeFrac) + String(color)
                        
                        let pan = Pan(quantity: Int(quantity)!,
                                      roundType: String(roundType),
                                      splitType: String(splitType),
                                      height: Int(height)!,
                                      heightFrac: heightFrac,
                                      diameter: Int(diameter)!,
                                      diameterFrac: diameterFrac,
                                      width: 0,
                                      widthFrac: "",
                                      length: 0,
                                      lengthFrac: "",
                                      flange: Int(flange)!,
                                      flangeFrac: flangeFrac,
                                      color: color,
                                      material: material,
                                      _optional: _optional,
                                      id: id)
                        
                        let results = PANS.filter {$0.id == id}
                        if (results.isEmpty) {
                            PANS.append(pan)
                            self.dismissViewControllerAnimated(true, completion: nil)
                        } else {
                            let alert: UIAlertView = UIAlertView(title: "", message: "These Cone Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
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
            
                // ADD SQUARE Pitch Pan
            } else if ((diameter.isEmpty)
                && (!width.isEmpty && !length.isEmpty)) {
                
                if (_optional.isEmpty) {
                    
                
                    if ((Int(quantity) == nil)
                        || (Int(width) == nil)
                        || (Int(length) == nil)
                        || (Int(height) == nil)
                        || (Int(flange) == nil)) {
                    
                        if (Int(quantity) == nil) {
                            err_quantity_int.hidden = false
                        } else if (Int(width) == nil) {
                            err_width_int.hidden = false
                        } else if (Int(length) == nil) {
                            err_length_int.hidden = false
                        } else if (Int(flange) == nil) {
                            err_flange_int.hidden = false
                        }
                    } else {
                    
                        let id = String(roundType) + String(height) + String(heightFrac)
                        
                        let pan = Pan(quantity: Int(quantity)!,
                                      roundType: String(roundType),
                                      splitType: String(splitType),
                                      height: Int(height)!,
                                      heightFrac: heightFrac,
                                      diameter: 0,
                                      diameterFrac: "",
                                      width: Int(width)!,
                                      widthFrac: widthFrac,
                                      length: Int(length)!,
                                      lengthFrac: lengthFrac,
                                      flange: Int(flange)!,
                                      flangeFrac: flangeFrac,
                                      color: color,
                                      material: material,
                                      _optional: "",
                                      id: id)
                        
                        let results = PANS.filter {$0.id == id}
                        if (results.isEmpty) {
                            PANS.append(pan)
                            self.dismissViewControllerAnimated(true, completion: nil)
                        } else {
                            let alert: UIAlertView = UIAlertView(title: "", message: "These Pitch Pan Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
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
                } else if (!_optional.isEmpty) {
                    if ((Int(quantity) == nil)
                        || (Int(width) == nil)
                        || (Int(length) == nil)
                        || (Int(height) == nil)
                        || (Int(flange) == nil)) {
                        
                        if (Int(quantity) == nil) {
                            err_quantity_int.hidden = false
                        } else if (Int(width) == nil) {
                            err_width_int.hidden = false
                        } else if (Int(length) == nil) {
                            err_length_int.hidden = false
                        } else if (Int(flange) == nil) {
                            err_flange_int.hidden = false
                        }
                    } else {
                        
                        let id = String(roundType) + String(height) + String(heightFrac)
                        
                        let pan = Pan(quantity: Int(quantity)!,
                                      roundType: String(roundType),
                                      splitType: String(splitType),
                                      height: Int(height)!,
                                      heightFrac: heightFrac,
                                      diameter: 0,
                                      diameterFrac: "",
                                      width: Int(width)!,
                                      widthFrac: widthFrac,
                                      length: Int(length)!,
                                      lengthFrac: lengthFrac,
                                      flange: Int(flange)!,
                                      flangeFrac: flangeFrac,
                                      color: color,
                                      material: material,
                                      _optional: _optional,
                                      id: id)
                        
                        let results = PANS.filter {$0.id == id}
                        if (results.isEmpty) {
                            PANS.append(pan)
                            self.dismissViewControllerAnimated(true, completion: nil)
                        } else {
                            let alert: UIAlertView = UIAlertView(title: "", message: "These Pitch Pan Measurements exist in your Cart!", delegate: nil, cancelButtonTitle: "OK");
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
            }
        } else {
            if ((quantity.isEmpty)
                || (height.isEmpty)
                || (flange.isEmpty)
                || (color.isEmpty)
                || (material.isEmpty)) {
                
                if (quantity.isEmpty) {
                    err_quantity.hidden = false
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
    
    // ScrollView moves TextField above Keyboard
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
    // -- ScrollView
    

    @IBAction func roundIndexChanged(sender: UISegmentedControl) {
        switch roundSegmented.selectedSegmentIndex {
        case 0: // ROUND
            lengthTextField.hidden = true
            lengthTextField.text = ""
            lengthFracPicker.hidden  = true
            lengthLabel.hidden = true
            err_length.hidden = true
            err_length_int.hidden = true
            
            widthTextField.hidden = true
            widthTextField.text = ""
            widthFracPicker.hidden = true
            widthLabel.hidden = true
            err_width.hidden = true
            err_width_int.hidden = true
            
            diameterTextField.hidden = false
            diameterFracPicker.hidden = false
            
            
            // Keep View as it is
            // "If it looks like a duck, 
            //    swims like a duck, 
            //    and quacks like a duck,
            //    then it probably is a duck"
            if ((diameterLabel.hidden == true)
                || (err_diameter.hidden == true)
                || (err_diameter_int.hidden == true)) {
                
                if (diameterLabel.hidden == true) {
                    diameterLabel.hidden = true
                } else if (err_diameter.hidden == true) {
                    err_diameter.hidden = true
                } else if (err_diameter_int.hidden == true) {
                    err_diameter_int.hidden = true
                }
            } else if ((diameterLabel.hidden == false)
                || (err_diameter.hidden == false)
                || (err_diameter_int.hidden == false)) {
                
                if (diameterLabel.hidden == false) {
                    diameterLabel.hidden = false
                } else if (err_diameter == false) {
                    err_diameter.hidden = false
                } else if (err_diameter_int.hidden == false) {
                    err_diameter_int.hidden = false
                }
                
            }
            
            
        case 1: // SQUARE
            diameterTextField.hidden = true
            diameterTextField.text = ""
            diameterFracPicker.hidden = true
            diameterLabel.hidden = true
            err_diameter.hidden = true
            err_diameter_int.hidden = true
            
            lengthTextField.hidden = false
            lengthFracPicker.hidden  = false

            widthTextField.hidden = false
            widthFracPicker.hidden = false

            
            // Keep View as it is
            // "If it looks like a duck,
            //    swims like a duck,
            //    and quacks like a duck,
            //    then it probably is a duck"
            if ((lengthLabel.hidden == true)
                || (err_length.hidden == true)
                || (err_length_int.hidden == true)) {
                
                if (lengthLabel.hidden == true) {
                    lengthLabel.hidden = true
                } else if (err_length.hidden == true) {
                    err_length.hidden = true
                } else if (err_length_int.hidden == true) {
                    err_length_int.hidden = true
                }
            } else if ((lengthLabel.hidden == false)
                || (err_length.hidden == false)
                || (err_length_int.hidden == false)) {
                
                if (lengthLabel.hidden == false) {
                    lengthLabel.hidden = false
                } else if (err_length == false) {
                    err_length.hidden = false
                } else if (err_length_int.hidden == false) {
                    err_length_int.hidden = false
                }
                
            }
            
            if ((widthLabel.hidden == true)
                || (err_width.hidden == true)
                || (err_width_int.hidden == true)) {
                
                if (widthLabel.hidden == true) {
                    widthLabel.hidden = true
                } else if (err_width.hidden == true) {
                    err_width.hidden = true
                } else if (err_width_int.hidden == true) {
                    err_width_int.hidden = true
                }
            } else if ((widthLabel.hidden == false)
                || (err_width.hidden == false)
                || (err_width_int.hidden == false)) {
                
                if (widthLabel.hidden == false) {
                    widthLabel.hidden = false
                } else if (err_width == false) {
                    err_width.hidden = false
                } else if (err_width_int.hidden == false) {
                    err_width_int.hidden = false
                }
                
            }
            
            
        default:
            return
        }
    }
    
    
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
    @IBAction func diameterFieldChanged(sender: AnyObject) {
        if (!diameterTextField.hasText()) {
            diameterLabel.hidden = true
            err_diameter.hidden = true
            err_diameter_int.hidden = true
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
    @IBAction func wdithFieldChanged(sender: AnyObject) {
        if (!widthTextField.hasText()) {
            widthLabel.hidden = true
            err_width.hidden = true
            err_width_int.hidden = true
        } else if (widthTextField.hasText()) {
            widthLabel.hidden = false
            err_width.hidden = true
            if (Int(widthTextField.text!) != nil) {
                err_width_int.hidden = true
            } else if (Int(widthTextField.text!) == nil) {
                err_width_int.hidden = false
            }
        }
    }
    @IBAction func lengthFieldChanged(sender: AnyObject) {
        if (!lengthTextField.hasText()) {
            lengthLabel.hidden = true
            err_length.hidden = true
            err_length_int.hidden = true
        } else if (lengthTextField.hasText()) {
            lengthLabel.hidden = false
            err_length.hidden = true
            if (Int(lengthTextField.text!) != nil) {
                err_length_int.hidden = true
            } else if (Int(lengthTextField.text!) == nil) {
                err_length_int.hidden = false
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
    
    
    // Resolve
    func textFieldShouldReturn(textField: UITextField) -> Bool {
            // Are we presenting from a segue?
            
            // NO
            // Default: ROUND
            if (segue_quantity == "") {
                if (diameterFracPicker.hidden == false) {
                    if (textField == quantityTextField) {
                        diameterTextField.becomeFirstResponder()
                    } else if (textField == diameterTextField) {
                        heightTextField.becomeFirstResponder()
                    } else if (textField == heightTextField) {
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
                    
                } else if (diameterFracPicker.hidden == true) {
                    if (textField == quantityTextField) {
                        heightTextField.becomeFirstResponder()
                    } else if (textField == heightTextField) {
                        widthTextField.becomeFirstResponder()
                    } else if (textField == widthTextField) {
                        lengthTextField.becomeFirstResponder()
                    } else if (textField == lengthTextField) {
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
                }
                
                
            } else if (segue_quantity != "") {
                if (diameterFracPicker.hidden == false) {
                    if (textField == quantityTextField) {
                        diameterTextField.becomeFirstResponder()
                    } else if (textField == diameterTextField) {
                        heightTextField.becomeFirstResponder()
                    } else if (textField == heightTextField) {
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
                    
                } else if (diameterFracPicker.hidden == true) {
                    if (textField == quantityTextField) {
                        heightTextField.becomeFirstResponder()
                    } else if (textField == heightTextField) {
                        widthTextField.becomeFirstResponder()
                    } else if (textField == widthTextField) {
                        lengthTextField.becomeFirstResponder()
                    } else if (textField == lengthTextField) {
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
                }
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
