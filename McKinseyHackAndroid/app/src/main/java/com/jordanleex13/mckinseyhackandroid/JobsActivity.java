package com.jordanleex13.mckinseyhackandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class JobsActivity extends AppCompatActivity {

    public ViewPager mViewPager;
    public SectionsPagerAdapter mSectionsPagerAdapter;

    private static final String[] tabs = {"Job Listings / قوائم الوظائف", "Map / خريطة"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        Toolbar toolBar = (Toolbar) findViewById(R.id.activity_job_toolbar);

        if (toolBar != null) {
            //toolBar.setTitle("Jobs");
            toolBar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolBar);

        }
        Log.e("HERE", "LOADED");

//        JobsFragment newFragment = JobsFragment.newInstance();
//        FragmentHelper.swapFragments(getSupportFragmentManager(), R.id.activity_job_container,
//                newFragment, true, false, null, JobsFragment.TAG);

        // create an object of the custom adapter to display the fragments
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // set up the viewpager
        mViewPager = (ViewPager) findViewById(R.id.activity_jobs_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        // default constructor
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment newFragment = null;

            switch(position) {
                case 0:
                    newFragment = JobsFragment.newInstance();
                    break;
                case 1:
                    newFragment = JobsMapFragment.newInstance();
                    break;
            }
            return newFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];

        }
    }

}
