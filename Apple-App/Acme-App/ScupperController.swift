//
//  ScupperController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/26/16.
//  Copyright © 2016 Acme. All rights reserved.
//

import UIKit

class ScupperController: UIViewController, UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource {
    
    
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var typeSegmented: UISegmentedControl!
    @IBOutlet weak var quantityTextField: UITextField!
    @IBOutlet weak var quantityLabel: UILabel!
    @IBOutlet weak var err_quantity: UILabel!
    @IBOutlet weak var err_quantity_int: UILabel!
    @IBOutlet weak var depthTextField: UITextField!
    @IBOutlet weak var depthFracPicker: UIPickerView!
    @IBOutlet weak var depthLabel: UILabel!
    @IBOutlet weak var err_depth: UILabel!
    @IBOutlet weak var err_depth_int: UILabel!
    @IBOutlet weak var lengthTextField: UITextField!
    @IBOutlet weak var lengthFracPicker: UIPickerView!
    @IBOutlet weak var lengthLabel: UILabel!
    @IBOutlet weak var err_length: UILabel!
    @IBOutlet weak var err_length_int: UILabel!
    @IBOutlet weak var widthTextField: UITextField!
    @IBOutlet weak var widthFracPicker: UIPickerView!
    @IBOutlet weak var widthLabel: UILabel!
    @IBOutlet weak var err_width: UILabel!
    @IBOutlet weak var err_width_int: UILabel!
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
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
