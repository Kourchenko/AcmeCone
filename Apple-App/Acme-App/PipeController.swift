//
//  PIpeController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/26/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class PipeController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var typeSegment: UISegmentedControl!
    @IBOutlet weak var quantityTextField: UITextField!
    @IBOutlet weak var heightTextField: UITextField!
    @IBOutlet weak var heightFracPicker: UIPickerView!
    @IBOutlet weak var diameterTextField: UITextField!
    @IBOutlet weak var diameterFracPicker: UIPickerView!
    @IBOutlet weak var flangeTextField: UITextField!
    @IBOutlet weak var flangeFracPicker: UIPickerView!
    @IBOutlet weak var colorTextField: UITextField!
    @IBOutlet weak var materialTextField: UITextField!
    @IBOutlet weak var _optionalTextField: UITextField!
    
    
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
        
        if (   (!quantity.isEmpty)
            || (!height.isEmpty)
            || (!diameter.isEmpty)
            || (!flange.isEmpty)
            || (!color.isEmpty)
            || (!material.isEmpty)) {
            
            let heightFrac = String(fractions[heightFracPicker.selectedRowInComponent(0)])
            let diameterFrac = String(fractions[diameterFracPicker.selectedRowInComponent(0)])
            let flangeFrac = String(fractions[flangeFracPicker.selectedRowInComponent(0)])
            let _optional = _optionalTextField.text!

            
            if (_optional.isEmpty) {
                
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
                                _optional: "")
                
                PIPES.append(pipe)
                self.dismissViewControllerAnimated(true, completion: nil)
                                
                
            } else {
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
                                _optional: _optional)
                
                PIPES.append(pipe)
                self.dismissViewControllerAnimated(true, completion: nil)
                
            }
        }
    }
    
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
        
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
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
        return true
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
