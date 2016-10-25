//
//  Job.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import Foundation
import Alamofire

class DLJobs {
    
    var company: String!
    var jobTitle: String!
    var snippet: String!
    var url: String!
    
    func downloadJobs(complete: DLComplete) {
        
        var tempArray = [NewJob]() //clearing old search results
        
        let tempUrl = URL + DataService.shared.searchTerm + URL2
        
        let NsUrl = NSURL(string: tempUrl)!
       
        Alamofire.request(.GET, NsUrl).responseJSON { response in
            
            let result = response.result
            print("HERE")
            
            if let result1 = result.value as? Dictionary<String, AnyObject> {
                print(2)
                
                if let results = result1["results"] as? [Dictionary<String, AnyObject>] {
                    
                    for i in 0 ..< results.count {
                        if let company = results[i]["company"] as? String {
                            print (company)
                            self.company = company
                        }
                        
                        if let job = results[i]["jobtitle"] as? String {
                            print(job)
                            self.jobTitle = job
                        }
                        
                        if let desc = results[i]["snippet"] as? String {
                            print(desc)
                            
                            //Removing the HTML tags in the description
                            let str1 = desc.stringByReplacingOccurrencesOfString("<b>", withString: "")
                            let str2 = str1.stringByReplacingOccurrencesOfString("</b>", withString: "")
                            self.snippet = str2
                        }
                        
                        if let link = results[i]["url"] as? String {
                            print(link)
                            self.url = link
                        }
                        
                        //creating a new job for every job returned from the web request
                        let newJob = NewJob(company: self.company, jobTitle: self.jobTitle, snippet: self.snippet, url: String(self.url))
                        
                        tempArray.append(newJob)
                        
                    }
                    
                    //after all the JSON is parsed and all the NewJob objects are created and appending into tempArray, make the data service array of jobs equal the tempArray containing all the jobs specified from the search term
                    self.updateJobs(tempArray)
                }
                
            }
            //Call closure after everything has been downloaded, parsed, and set
            complete()
        }
    }
    
    func updateJobs(arr: [NewJob]) {
        DataService.shared.jobList = arr
    }
    
    func reloadSearch(completed: DLComplete) {
        
        downloadJobs { 
            
        }
        //Call closure after all everything has been downloaded, parsed, and set
        completed()
    }

}