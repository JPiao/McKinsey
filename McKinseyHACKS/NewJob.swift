//
//  NewJob.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import Foundation

class NewJob {
    
    var comp: String!
    var jobTi: String!
    var snip: String!
    var url: String!
    
    init(company: String, jobTitle: String, snippet: String, url: String) {
        self.comp = company
        self.jobTi = jobTitle
        self.snip = snippet
        self.url = url
    }

}