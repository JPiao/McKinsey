//
//  ViewController.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate { // inherit for hitting return
    
    @IBOutlet weak var textfield1: UITextField!
    @IBOutlet weak var textfield2: UITextField!
    @IBOutlet weak var textfield3: UITextField!
    @IBOutlet weak var textfield4: UITextField!
    

    var Link2 = DLJobs()
    
    override func viewDidLoad() {
    
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
         // for hitting return
        textfield1.delegate = self
        textfield2.delegate = self
        textfield3.delegate = self
        textfield4.delegate = self
        
    
         // for tapping outside keyboard
        self.view.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(ViewController.dismissKeyboard)))
        

        
    }
    
    // for tapping outside keyboard
    func dismissKeyboard() {
        textfield1.resignFirstResponder()
        textfield2.resignFirstResponder()
        textfield3.resignFirstResponder()
        textfield4.resignFirstResponder()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    // for hitting return
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func contBtnClicked(sender: AnyObject) {
        
        if textfield4.text != "" || textfield4.text != nil {
            DataService.shared.searchTerm = textfield4.text!
            
        } else {
            DataService.shared.searchTerm = "java"
        }
        performSegueWithIdentifier("contBtn", sender: nil)
        
        Link2.downloadJobs { //need to call this after button press and not in viewdidload or appear since dataservice.shared.search term is nil when the view is loaded into memory and will result in error
            for i in 0 ..< DataService.shared.jobList.count {
                print(DataService.shared.jobList[i].comp)
            }
        }
    }

}
