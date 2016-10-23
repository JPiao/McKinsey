//
//  ViewController.swift
//  MckinseyHack
//
//  Created by Jordan Lee on 2016-10-19.
//  Copyright © 2016 Jordan Lee. All rights reserved.
//

import UIKit
import MapKit

class ViewController: UIViewController, MKMapViewDelegate, CLLocationManagerDelegate {

    @IBOutlet weak var map: MKMapView!
    
    let locationManager = CLLocationManager()
    let radius: CLLocationDistance = 1000
    let parser = JSONParser()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.map.delegate = self
        //locationManager.allowsBackgroundLocationUpdates = true
        
        self.locationManager.requestWhenInUseAuthorization()
        self.locationManager.requestAlwaysAuthorization()
        if (CLLocationManager.locationServicesEnabled())
        {
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
            locationManager.requestAlwaysAuthorization()
            locationManager.startUpdatingLocation()
        }
        
        self.setRegion();
        
        parser.downloadCoordinates()
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        authStatus()
    }
    
    
    // Add NSLocationWhenInUseUsageDescription to info.plist for pop-up
    func authStatus() {
        if CLLocationManager.authorizationStatus() == .authorizedWhenInUse {
            map.showsUserLocation = true
        } else {
            locationManager.requestWhenInUseAuthorization()
        }
    }
    func setRegion() {
        var currLocation: CLLocation!
        
        if let currLocation = locationManager.location as CLLocation!{
            let location: CLLocationCoordinate2D = currLocation.coordinate
            let latDelta:CLLocationDegrees = 0.3
            let lonDelta:CLLocationDegrees = 0.3
            let span:MKCoordinateSpan = MKCoordinateSpanMake(latDelta, lonDelta)

            let region:MKCoordinateRegion = MKCoordinateRegionMake(location, span)
            
            print("\(location.latitude)" + " , " + "\(location.longitude)")

            
            map.setRegion(region, animated: true)
            map.showsUserLocation = true

        }
        
        print("TESTING")
                //let location:CLLocationCoordinate2D = CLLocationCoordinate2DMake(latitude, longitude)
        
    }
    
//    func centerMapAroundLocation(location: CLLocation) {
//        let coordinateRegion = MKCoordinateRegionMakeWithDistance(location.coordinate, radius, radius)
//        map.setRegion(coordinateRegion,animated: true)
//    }
//    
//    func mapView(_ mapView: MKMapView, didUpdate userLocation: MKUserLocation) {
//        if let loc = userLocation.location {
//            centerMapAroundLocation(location: loc)
//        }
//    }
    
    


}

