package com.jordanleex13.mckinseyhackandroid.Helpers;

import android.util.Log;

import com.jordanleex13.mckinseyhackandroid.Managers.RSSItemManager;
import com.jordanleex13.mckinseyhackandroid.Models.RSSItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jordan on 2016-10-22.
 */

/**
 * Currently unused. Implemented into news if ever completed
 */
public class RSSHelper {
    private static final String TAG = RSSHelper.class.getSimpleName();

    private String title = "title";
    private String link = "link";
    private String description = "description";
    private String urlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public RSSHelper(String url){
        this.urlString = url;
    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    Log.d(TAG, "HERE");
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }

                catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });
        thread.start();
    }

    private void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = null;

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, name);
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        Log.d(TAG, text);
                        break;

                    case XmlPullParser.END_TAG:

                        if(name.equals("title")){
                            title = text;
                            Log.e(TAG, text);
                        } else if(name.equals("description")){
                            description = text;
                            Log.e(TAG, text);

                        } else if(name.equals("link")){
                            link = text;
                            Log.e(TAG, text);

                            RSSItem temp = new RSSItem(title,description,link);
                            RSSItemManager.addRSS(temp);

                        }

                        else{
                            Log.e(TAG, name);
                        }

                        break;
                }

                event = myParser.next();
            }

            parsingComplete = false;
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        RSSItemManager.print();
    }

}
