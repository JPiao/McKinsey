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
    

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
         // for hitting return
        textfield1.delegate = self
        textfield2.delegate = self
        textfield3.delegate = self
        
         // for tapping outside keyboard
        self.view.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(ViewController.dismissKeyboard)))
        
    }
    
    // for tapping outside keyboard
    func dismissKeyboard() {
        textfield1.resignFirstResponder()
        textfield2.resignFirstResponder()
        textfield3.resignFirstResponder()
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


}

