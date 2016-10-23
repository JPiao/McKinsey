package com.jordanleex13.mckinseyhackandroid.Models;

/**
 * Created by Jordan on 2016-10-22.
 */

public class Job {

    private String jobTitle, company, city, state, country, description, url;
    private float latitude, longitude;

    public Job(String jobTitle, String company, String city, String state,
               String country, String description, String url,
               float latitude, float longitude)
    {
        this.jobTitle = jobTitle;
        this.company = company;
        this.city = city;
        this.state = state;
        this.country = country;
        this.description = description;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float[] getCoordinates() {
        float[] coor = {latitude, longitude};

        return coor;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public String getCompany() {
        return company;
    }
    public String getCity() {
        return city;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
}
