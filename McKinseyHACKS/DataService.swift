//
//  DataService.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-22.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import Foundation


//Singleton
class DataService {
    
    static let shared = DataService()
    
    //Always having the most recent search term and job list in memory
    var jobList = [NewJob]()
    
    var searchTerm: String!
    
}
