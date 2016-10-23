//
//  ListingVC.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import UIKit

class ListingVC: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var TbView: UITableView!
    @IBOutlet weak var prevBtn: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        TbView.delegate = self
        TbView.dataSource = self
        

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }

    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return DataService.shared.jobList.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let post = DataService.shared.jobList[indexPath.row]
        
        if let cell = tableView.dequeueReusableCellWithIdentifier("listCell") as? ListCell {
            cell.configureCell(post)
            return cell
        } else {
            return ListCell()
        }
    }
    
    @IBAction func prevBtnPress(sender: AnyObject!) {
        dismissViewControllerAnimated(true, completion: nil)
    }

}
