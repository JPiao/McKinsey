//
//  JSONParser.swift
//  MckinseyHack
//
//  Created by Jordan Lee on 2016-10-22.
//  Copyright © 2016 Jordan Lee. All rights reserved.
//

import Foundation
import Alamofire


class JSONParser {

    init() {
        print ("Init")
    }
    func downloadCoordinates() {
        let url = "https://api.data.gov.sg/v1/transport/taxi-availability"
        let APIKey = "oDhGWY2jYTfUFrhDwAHHp2OjopQxJ6GZ"
//     Alamofire.request(.GET, url, paheaders: APIKEY).responseJSON { response in
//            
//        }
        
        let headers = ["api-key": APIKey]
        Alamofire.request(url, parameters: nil, headers: headers).responseJSON { response in
            //print(response)
            let result = response.result
            print ("0")
            if let dict = result.value as? Dictionary<String, AnyObject> {
                print ("1")
                //print (dict)
                
                if let features = dict["features"] as? [Dictionary<String, AnyObject>] {
                    print ("3")
                    if let geometry = features[0] as? Dictionary<String, AnyObject> {
                        print ("4")
                        if let coordinates = geometry["geometry"] as? Dictionary<String, AnyObject> {
                            print ("HERE")
                            if let coordinateArray = coordinates["coordinates"] as? Array<Array<Double>> {
                                print ("HERE2")
                                for coordinate in coordinateArray {
                                    print (coordinate)
                                }
                            }
                            //print (coordinates["coordinates"])
                        }
                
                    }
                        
                }
                
            }
        }
        
    }
}
