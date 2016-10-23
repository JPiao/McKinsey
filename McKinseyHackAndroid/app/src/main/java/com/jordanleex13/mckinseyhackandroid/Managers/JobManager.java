package com.jordanleex13.mckinseyhackandroid.Managers;

import com.jordanleex13.mckinseyhackandroid.Models.Job;

import java.util.ArrayList;

/**
 * Created by Jordan on 2016-10-22.
 */

public class JobManager {

    private static ArrayList<Job> jobArrayList = new ArrayList<>();
    private static String searchTerm;


    private JobManager() {

    }

    public static ArrayList<Job> getList() {
        return jobArrayList;
    }
    public static int getSize() {
        return jobArrayList.size();
    }
    public static void addJob(Job j) {
        jobArrayList.add(j);
    }
    public static Job getJob(int index) {
        return jobArrayList.get(index);
    }
    public static void removeJob(Job j) {
        jobArrayList.remove(j);
    }
    public static void clearList() {
        jobArrayList.clear();
    }




    public static String getSearchTerm() {
        return searchTerm;
    }
    public static void setSearchTerm(String s) {
        searchTerm = s;
    }
}
