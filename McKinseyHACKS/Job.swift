//
//  Job.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import Foundation
import Alamofire

class Jobs {
    
    
    var company: String!
    var jobTitle: String!
    var location: String!
    
    init() {
        
    }
    
    func downloadJobs() {
        
        let url = NSURL(string: "http://api.indeed.com/ads/apisearch?publisher=2863597559522400&format=json&q=java&l=austin%2C%20tx&sort&radius&st&jt&start&limit&fromage&filter&latlong=1&co=us&chnl&userip=1.2.3.4&useragent=Mozilla%2F%2F4.0(Firefox)&v=2")!
       
        Alamofire.request(.GET, url).responseJSON { response in
            //print(response)
            let result = response.result
            print("HERE")
            
            if let result1 = result.value as? Dictionary<String, AnyObject> {
                //print (result1)
                print(2)
                
                if let results = result1["results"] as? [Dictionary<String, AnyObject>] {
                    print(3)
                    //print (results)
                    
                    if let company = results[0]["company"] as? String {
                        print (company)
                    }
                }
                
            }
        }
    }
}