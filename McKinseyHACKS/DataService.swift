//
//  DataService.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import Foundation

class DataService {
    
    static let shared = DataService()
    
    var jobList = [NewJob]()
    
    var searchTerm: String!
    
    func addJob(job: NewJob) {
        jobList.append(job)
    }
}