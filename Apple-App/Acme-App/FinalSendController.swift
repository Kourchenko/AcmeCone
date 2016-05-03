//
//  FinalSendController.swift
//  Acme-App
//
//  Created by Diego Kourchenko on 4/30/16.
//  Copyright © 2016 Acme. All rights reserved.
//
import Foundation
import UIKit
import MessageUI

class FinalSendController: UIViewController, UITextFieldDelegate, MFMailComposeViewControllerDelegate {
    
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var phoneTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var companyTextField: UITextField!
    @IBOutlet weak var manufacturerTextField: UITextField!
    @IBOutlet weak var datePicker: UIDatePicker!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(StockController.keyboardWillShow(_:)), name: UIKeyboardWillShowNotification, object: nil)
        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(StockController.keyboardWillHide(_:)), name: UIKeyboardWillHideNotification, object: nil)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func shouldAutorotate() -> Bool {
        return false
    }
    
    override func supportedInterfaceOrientations() -> UIInterfaceOrientationMask {
        return UIInterfaceOrientationMask.Portrait
    }
    
    @IBAction func sendEmail(sender: AnyObject) {
        let mailComposeView = configuredMailComposeViewController()
        if MFMailComposeViewController.canSendMail() {
            if ((!STOCK.isEmpty)
                || (!CONES.isEmpty)
                || (!CORNERS.isEmpty)
                || (!PIPES.isEmpty)
                || (!DROPS.isEmpty)
                || (!SCUPPERS.isEmpty)
                || (!PANS.isEmpty)
                || (!TUBES.isEmpty)
                || (!CURBS.isEmpty)
                || (!SLEEPERS.isEmpty)) {
                self.presentViewController(mailComposeView, animated: true, completion: nil)
            } else {
                self.showSendMailEmptyCartErrorAlert()
            }
        } else {
            self.showSendMailErrorAlert()
            
        }
    }
    
    func configuredMailComposeViewController() -> MFMailComposeViewController {
        let mailComposerVC = MFMailComposeViewController()
        mailComposerVC.mailComposeDelegate = self
        
        let _recipients = emailTextField.text!
        let _subject = companyTextField.text! + ": Order Request"
        let _body = sendEmailHelper()
        
        mailComposerVC.setToRecipients(["acmecone.acme@gmail.com", _recipients])
        mailComposerVC.setSubject(_subject)
        mailComposerVC.setMessageBody(_body, isHTML: false)
        
        return mailComposerVC
        
    }
    
    func showSendMailEmptyCartErrorAlert() {
        let sendMailErrorAlert = UIAlertView(title: "Empty Cart!", message: "Nothing Ordered...", delegate: self, cancelButtonTitle: "OK")
        sendMailErrorAlert.show()
    }
    
    
    func showSendMailErrorAlert() {
        let sendMailErrorAlert = UIAlertView(title: "No Network Connection...", message: "Don\'t worry! We saved your Order!", delegate: self, cancelButtonTitle: "OK")
        sendMailErrorAlert.show()
    }
    
    func mailComposeController(controller: MFMailComposeViewController, didFinishWithResult result: MFMailComposeResult, error: NSError?) {
        
        var err_msg: NSString
        switch (result) {
        case MFMailComposeResultCancelled:
            err_msg = "Sending Mail is Cancelled!"
            break;
            
        case MFMailComposeResultSaved:
            err_msg = "Sending Mail is Saved!"
            break;
        case MFMailComposeResultSent:
            err_msg = "Message Sent Successfully!"
            break;
        case MFMailComposeResultFailed:
            err_msg = "Message Failed!"
            break;
        default:
            err_msg = "Mail Not Sent!"
            break;
            
        }
        let alertView: UIAlertView = UIAlertView(title: err_msg as String, message: "", delegate: self, cancelButtonTitle: "OK")
        alertView.show()
        controller.dismissViewControllerAnimated(true, completion: nil)

    }
    
    
    // GATHER ORDER INTO ONE MESSAGE
    func sendEmailHelper() -> String {
        var finalString: String = ""
        
        let name: String = nameTextField.text!
        let phone: String = phoneTextField.text!
        let email: String = emailTextField.text!
        let company: String = companyTextField.text!
        let manufacturer: String = manufacturerTextField.text!
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "MM/dd/yyyy"
        let date = dateFormatter.stringFromDate(datePicker.date)


        finalString += "ORDER NEEDED: " + String(date)
        // GET ORDER
        // && USER_INFO
        if ( (!name.isEmpty)
            && (!phone.isEmpty)
            && (!email.isEmpty)
            && (!company.isEmpty)
            && (!manufacturer.isEmpty) ){
            
            finalString += "\n\n"
            
    /*
    * STOCK
    */
            if (!STOCK.isEmpty) {
                finalString += "STOCK: \n"
                for stock: String in STOCK {
                    finalString += stock + "\n"
            
                }
                finalString += "\n\n"
        }
        
    /*
    * CONES
    */
            if (!CONES.isEmpty) {
                finalString += "Custom Cones: \n"
                for cone: Cone in CONES {
                    finalString += String(cone.quantity) + ": "
                        + String(cone.type) + " "
                        + String(cone.color) + " "
                        + String(cone.material) + "\n"
                        + String(cone.topDiameter) + " " + String(cone.topFrac) + " T \n"
                        + String(cone.botDiameter) + " " + String(cone.botFrac) + " B \n"
                        + String(cone.height) + " " +   String(cone.heightFrac) + " H \n"
                        + String(cone.flange) + " " + String(cone.flangeFrac) + " F \n"
                        + "NOTES: " + String(cone._optional)
                }
            
                finalString += "\n\n"
            }
            
    /*
    * CORNERS
    */
            if (!CORNERS.isEmpty) {
                finalString += "Custom Corners: \n"
                for corner: Corner in CORNERS {
                    finalString += String(corner.quantity) + ": "
                        + String(corner.type) + " "
                        + String(corner.color) + " "
                        + String(corner.material) + "\n"
                        + String(corner.depth) + " "
                        + String(corner.depthFrac) + " T \n"
                        + String(corner.height) + " "
                        + String(corner.heightFrac) + " H \n"
                        + String(corner.flange) + " "
                        + String(corner.flangeFrac) + " F \n"
                        + "NOTES: " + String(corner._optional)
                }
                finalString += "\n\n"
            }
            
    /*
    * PIPES
    */
            if (!PIPES.isEmpty) {
                finalString += "Custom Pipe Wraps: \n"
                for pipe: Pipe in PIPES {
                    finalString += String(pipe.quantity) + ": "
                        + String(pipe.type) + " "
                        + String(pipe.color) + " "
                        + String(pipe.material) + "\n"
                        + String(pipe.height) + " "
                        + String(pipe.heightFrac) + " H \n"
                        + String(pipe.diameter) + " "
                        + String(pipe.diameterFrac) + " D \n"
                        + String(pipe.flange) + " "
                        + String(pipe.flangeFrac) + " F \n"
                        + "NOTES: " + String(pipe._optional)
                }
                finalString += "\n\n"
            }
            
            
    /*
    * DROPS
    */
            if (!DROPS.isEmpty) {
                finalString += "Custom Drop Scuppers: \n"
                for drop: Drop in DROPS {
                    finalString += String(drop.quantity) + ": "
                        + String(drop.color) + " "
                        + String(drop.material) + "\n"
                        + String(drop.depth) + " "
                        + String(drop.depthFrac) + " Depth \n"
                        + String(drop.diameter) + " "
                        + String(drop.diameterFrac) + " D \n"
                        + String(drop.flange) + " "
                        + String(drop.flangeFrac) + " F \n"
                        + "NOTES: " + String(drop._optional)
                }
                finalString += "\n\n"
            }
 
    /*
    * SCUPPERS
    */
            if (!SCUPPERS.isEmpty) {
                finalString += "Custom Scuppers: \n"
                for scupper: Scupper in SCUPPERS {
                    finalString += String(scupper.quantity) + ": "
                        + String(scupper.type) + " "
                        + String(scupper.color) + " "
                        + String(scupper.material) + "\n"
                        + String(scupper.depth) + " "
                        + String(scupper.depthFrac) + " Depth \n"
                        + String(scupper.length) + " "
                        + String(scupper.lengthFrac) + " L \n"
                        + String(scupper.width) + " "
                        + String(scupper.widthFrac) + " W \n"
                        + String(scupper.flange) + " "
                        + String(scupper.flangeFrac) + " F \n"
                        + "NOTES: " + String(scupper._optional)
                }
                finalString += "\n\n"
            }
            
    /*
    * PITCH PANS
    */
            if (!PANS.isEmpty) {
                finalString += "Custom Pitch Pans: \n"
                for pan: Pan in PANS {
                    finalString += String(pan.quantity) + ": "
                        + String(pan.roundType) + " "
                        + String(pan.splitType) + " "
                        + String(pan.color) + " "
                        + String(pan.material) + "\n"
                        + String(pan.height) + " "
                        + String(pan.heightFrac) + " H \n"
                        + String(pan.diameter) + " "
                        + String(pan.diameterFrac) + "D \n"
                        + String(pan.width) + " "
                        + String(pan.widthFrac) + " W \n"
                        + String(pan.length) + " "
                        + String(pan.lengthFrac) + " L \n"
                        + "NOTES: " + String(pan._optional)
                }
                finalString += "\n\n"
            }
            
            
    /*
    * TUBE WRAPS
    */
            if (!TUBES.isEmpty) {
                finalString += "Custom Tube Wraps: \n"
                for tube: Tube in TUBES {
                    finalString += String(tube.quantity) + ": "
                        + String(tube.type) + " "
                        + String(tube.color) + " "
                        + String(tube.material) + "\n"
                        + String(tube.length) + " "
                        + String(tube.lengthFrac) + " L \n"
                        + String(tube.width) + " "
                        + String(tube.widthFrac) + " W \n"
                        + String(tube.height) + " "
                        + String(tube.heightFrac) + " H \n"
                        + String(tube.flange) + " "
                        + String(tube.flangeFrac) + " F \n"
                        + "NOTES: " + String(tube._optional)
                }
                finalString += "\n\n"
            }
            
    /*
    * CURB WRAPS
    */
            if (!CURBS.isEmpty) {
                finalString += "Custom Curb Wraps: \n"
                for curb: Curb in CURBS {
                    finalString += String(curb.quantity) + " "
                        + String(curb.type) + " "
                        + String(curb.color) + " "
                        + String(curb.material) + "\n"
                        + String(curb.length) + " "
                        + String(curb.lengthFrac) + " L \n"
                        + String(curb.width) + " "
                        + String(curb.widthFrac) + " W \n"
                        + String(curb.flange) + " "
                        + String(curb.flangeFrac) + " F \n"
                        + "NOTES: " + String(curb._optional)
                }
                
                finalString += "\n\n"
            }
            
    /*
    * SLEEPER BOXES
    */
            if (!SLEEPERS.isEmpty) {
                finalString += "Custom Sleeper Boxes: \n"
                for sleeper: Sleeper in SLEEPERS {
                    finalString += String(sleeper.quantity) + ": "
                        + String(sleeper.length) + " "
                        + String(sleeper.lengthFrac) + " L \n"
                        + String(sleeper.width) + " "
                        + String(sleeper.widthFrac) + " W \n"
                        + String(sleeper.height) + " "
                        + String(sleeper.heightFrac) + "H \n"
                        + String(sleeper.flange) + " "
                        + String(sleeper.flangeFrac) + " F \n"
                        + "NOTES: " + (sleeper._optional)
                }
                finalString += "\n\n"
            }
        
        finalString += "\n\n\n\nCONTACT: \n"
            + String(name) + "\n"
            + String(email) + "\n"
            + String(phone) + "\n"
            + String(company) + "\n"
        }
        
        return finalString

    }
    
    
    func keyboardWillShow(sender: NSNotification) {
        let info = sender.userInfo!
        let keyboardSize = (info[UIKeyboardFrameEndUserInfoKey] as! NSValue).CGRectValue().height
        
        self.scrollView.contentInset.bottom = keyboardSize
    }
    
    
    func keyboardWillHide(sender: NSNotification) {
        self.scrollView.contentInset.bottom = 0
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        
        if (textField == nameTextField) {
            phoneTextField.becomeFirstResponder()
        } else if (textField == phoneTextField) {
            emailTextField.becomeFirstResponder()
        } else if (textField == emailTextField) {
            companyTextField.becomeFirstResponder()
        } else if (textField == companyTextField) {
            manufacturerTextField.becomeFirstResponder()
        } else if (textField == manufacturerTextField) {
            manufacturerTextField.resignFirstResponder()
        }
        
        return false
    }
    // -- ScrollView
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
}
