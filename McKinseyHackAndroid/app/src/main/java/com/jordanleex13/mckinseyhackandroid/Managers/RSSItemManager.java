package com.jordanleex13.mckinseyhackandroid.Managers;

import com.jordanleex13.mckinseyhackandroid.Models.RSSItem;

import java.util.ArrayList;

/**
 * Created by Jordan on 2016-10-23.
 */

public class RSSItemManager {

    private static ArrayList<RSSItem> rssItems = new ArrayList<>();

    private RSSItemManager() {

    }

    public static ArrayList<RSSItem> getList() {
        return rssItems;
    }
    public static void addRSS(RSSItem r) {
        rssItems.add(r);
    }
    public static RSSItem getRSS(int index) {
        return rssItems.get(index);
    }
    public static void removeRSS(RSSItem r) {
        rssItems.remove(r);
    }
    public static void clearList() {
        rssItems.clear();
    }

    public static void print() {
        for (RSSItem r : rssItems) {
            System.out.println(r.getTitle());
        }
    }
}
