package com.jordanleex13.mckinseyhackandroid.Models;

/**
 * Created by Jordan on 2016-10-22.
 */

public class RSSItem {
    private String title;
    private String desc;
    private String url;

    public RSSItem(String title, String desc, String url) {
        this.title = title;
        this.desc = desc;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }
    public String getDesc() {
        return desc;
    }
    public String getUrl() {
        return url;
    }
}
