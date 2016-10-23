package com.jordanleex13.mckinseyhackandroid.Helpers;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jordanleex13.mckinseyhackandroid.Managers.JobManager;
import com.jordanleex13.mckinseyhackandroid.Models.Job;

import java.util.HashMap;

/**
 * Created by Jordan on 2016-10-23.
 */

public class FillMapWithMarkersTask extends AsyncTask<Object, Object, Object> {

    private GoogleMap mMap;
    private HashMap<Marker, String> mHashMap;

    public FillMapWithMarkersTask(GoogleMap map, HashMap<Marker, String> hashMap) {
        mMap = map;
        mHashMap = hashMap;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        for (int i = 0; i < JobManager.getSize(); i++) {

            Job currJob = JobManager.getJob(i);
            float[] coor = currJob.getCoordinates();

            LatLng latlng = new LatLng(coor[0], coor[1]);
            String jobTitle = currJob.getJobTitle();
            String company = currJob.getCompany();
            String url = currJob.getUrl();

            publishProgress(latlng, jobTitle, company, url);

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);

        LatLng curr = (LatLng) values[0];
        String jobTitle = (String) values[1];
        String company = (String) values[2];
        String url = (String) values[3];


        Marker temp = mMap.addMarker(new MarkerOptions().position(curr).title(jobTitle).snippet(company));
        mHashMap.put(temp, url);

    }
}
