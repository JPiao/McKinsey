//
//  WebVC.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import UIKit
import WebKit

class WebVC: UIViewController {
    //Brings up website when table cell is clicked

    @IBOutlet weak var container: UIView!
    
    var webView: WKWebView!
    var WebUrl: String!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        webView = WKWebView()
        container.addSubview(webView) 
    }
    
    override func viewDidAppear(animated: Bool) {
        let frame = CGRectMake(0, 0, container.bounds.width, container.bounds.height)
        webView.frame = frame
        
        print(WebUrl)
        loadRequest(WebUrl)    
    }

    func loadRequest(URLStr: String) {
        let url = NSURL(string: URLStr)!
        let request = NSURLRequest(URL: url)
        
        webView.loadRequest(request)
    }
    
    @IBAction func backBtnPress(sender: AnyObject) {
        dismissViewControllerAnimated(true, completion: nil)
    } 
}
