//
//  MapVC.swift
//  McKinseyHACKS
//
//  Created by Jason Piao on 2016-10-23.
//  Copyright © 2016 Jason Piao. All rights reserved.
//

import UIKit
import MapKit

class MapVC: UIViewController, MKMapViewDelegate {
    
    @IBOutlet weak var mapView: MKMapView!
    
    let locationManager = CLLocationManager()
    let radius: CLLocationDistance = 1000

    override func viewDidLoad() {
        super.viewDidLoad()
        
         mapView.delegate = self
        
    }
    
    override func viewDidAppear(animated: Bool) {
        authStatus()
    }
    
    func authStatus() {
        
        //if user authorized location services, show location on map, else, ask for authorization
        //have to got into info.plist and click add and add NSLocationWhenInUseUsageDescription and set the pop up message
        if CLLocationManager.authorizationStatus() == .AuthorizedWhenInUse {
            mapView.showsUserLocation = true
        } else {
            locationManager.requestWhenInUseAuthorization()
        }
        
    }

    func centreMapAroundLocation(location: CLLocation) {
        
        //takes in a location, and sets how big the region around the location is. "zooms in"
        let coordinateRegion = MKCoordinateRegionMakeWithDistance(location.coordinate, radius, radius)
        mapView.setRegion(coordinateRegion, animated: true)
    }
    
    func mapView(mapView: MKMapView, didUpdateUserLocation userLocation: MKUserLocation) {
        
        //once the user's location is updated, the above func will be called
        //PART OF THE MAPVIEW, CALLED AUTOMATICALLY
        if let loc = userLocation.location {
            centreMapAroundLocation(loc)
        }
    }
    
    @IBAction func backBtnPress(sender: AnyObject) {
        dismissViewControllerAnimated(true, completion: nil)
    }

}
