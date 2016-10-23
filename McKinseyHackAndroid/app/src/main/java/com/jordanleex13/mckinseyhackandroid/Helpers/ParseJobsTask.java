package com.jordanleex13.mckinseyhackandroid.Helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.jordanleex13.mckinseyhackandroid.Models.Job;
import com.jordanleex13.mckinseyhackandroid.Managers.JobManager;
import com.jordanleex13.mckinseyhackandroid.RVAdapterJobs;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Jordan on 2016-10-22.
 */

public class ParseJobsTask extends AsyncTask<String, Object, Object> {

    public static final String TAG = ParseJobsTask.class.getSimpleName();

    private RVAdapterJobs mAdapter;
    public ParseJobsTask(RVAdapterJobs rvAdapterJobs) {
        mAdapter = rvAdapterJobs;
    }

    @Override
    protected Object doInBackground(String... params) {
        JobManager.clearList();
        try {
            httpGet(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        mAdapter.updateArrayList(JobManager.getList());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Object o) {
        mAdapter.updateArrayList(JobManager.getList());
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Uses Indeed API to get JSON files online and parses them into the Job Data structure
     * @throws IOException
     * @throws JSONException
     */
    public void httpGet(String searchTerm) throws IOException, JSONException {
        String in = "http://api.indeed.com/ads/apisearch?" +
                "publisher=2863597559522400&format=json&q=" + searchTerm + "&l=london%2C+gb&sort=" +
                "&radius=&st=&jt=&start=&limit=50&fromage=&filter=&latlong=1&co=gb&chnl=" +
                "&userip=1.2.3.4&useragent=Mozilla/%2F4.0%28Firefox%29&v=2";

        JobManager.setSearchTerm(searchTerm);

        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet(in));


        StatusLine statusLine = response.getStatusLine();

        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);


            String responseString = out.toString();
            Log.d(TAG, responseString);



            JSONObject reader = new JSONObject(responseString);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = reader.optJSONArray("results");
            Log.e(TAG, "Num of jobs is " + String.valueOf(jsonArray.length()));
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String jobTitle = jsonObject.optString("jobtitle");
                String company = jsonObject.optString("company");
                String city = jsonObject.optString("city");
                String state = jsonObject.optString("state");
                String country = jsonObject.optString("country");
                String description = jsonObject.optString("snippet");
                String url = jsonObject.optString("url");
                float latitude = Float.parseFloat(jsonObject.optString("latitude"));
                float longitude = Float.parseFloat(jsonObject.optString("longitude"));


                Job curr = new Job(jobTitle, company, city, state, country, description, url, latitude, longitude);
                JobManager.addJob(curr);

                publishProgress();

            }



            out.close();
        } else{
            //Closes the connection.
            response.getEntity().getContent().close();

        }
    }
}
