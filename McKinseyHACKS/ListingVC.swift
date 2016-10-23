//
//  ListingVC.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import UIKit

class ListingVC: UIViewController, UITableViewDelegate, UITableViewDataSource, UISearchBarDelegate {

    @IBOutlet weak var TbView: UITableView!
    @IBOutlet weak var prevBtn: UIButton!
    @IBOutlet weak var desc: UILabel!
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var searchBtn: UIButton!
    @IBOutlet weak var positionsLbl: UILabel!
    @IBOutlet weak var mapBtn: UIButton!
    
    var searchStr: String = ""
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        TbView.delegate = self
        TbView.dataSource = self
        searchBar.delegate = self
        searchBar.returnKeyType = UIReturnKeyType.Done
        searchBar.enablesReturnKeyAutomatically = false
        
        

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
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let url = DataService.shared.jobList[indexPath.row].url
        performSegueWithIdentifier("WebView", sender: url)
    }
    
    //connect segue from view controller and not table view cell or else you get nil when passing data
    //if connecting segue from cell, it doesn't call the code we wrote, it only triggers a segue
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "WebView" {
            if let svc = segue.destinationViewController as? WebVC {
                if let info = sender as? String {
                    print("asasdfasdfasdfsadf")
                    print(info)
                    svc.WebUrl = info
                    print(svc.WebUrl)
                }
            }
        }
    }
    
    @IBAction func prevBtnPress(sender: AnyObject!) {
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    @IBAction func searchBtnPress(sender: AnyObject) {
        searchBar.alpha = 0.8
        prevBtn.alpha = 0
        positionsLbl.alpha = 0
        searchBtn.alpha = 0
        mapBtn.alpha = 0
    }
    
    func searchBarSearchButtonClicked(searchBar: UISearchBar) {
        
        if searchBar.text != "" || searchBar.text != nil {
            let job = DLJobs()
            searchStr = searchBar.text!
            let str = searchStr
            DataService.shared.searchTerm = str
            job.reloadSearch {
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    self.TbView.reloadData() //throwing it back on the main thread
                })
            } //Need to double click for table view to update, fix later
            
//            
//            searchBar.alpha = 0
//            prevBtn.alpha = 1
//            positionsLbl.alpha = 1
//            searchBtn.alpha = 1
//            
//            view.endEditing(true)

            
           
        }
    
    }
    
    func searchBar(searchBar: UISearchBar, textDidChange searchText: String) {
        
        if searchBar.text == "" || searchBar.text == nil {
            searchBar.alpha = 0
            prevBtn.alpha = 1
            positionsLbl.alpha = 1
            searchBtn.alpha = 1
            mapBtn.alpha = 1
           
            view.endEditing(true)
        
        }
    }
    
    

}
