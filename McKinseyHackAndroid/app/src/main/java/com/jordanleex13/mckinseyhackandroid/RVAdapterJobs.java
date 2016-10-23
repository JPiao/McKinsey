package com.jordanleex13.mckinseyhackandroid;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jordanleex13.mckinseyhackandroid.Models.Job;

import java.util.ArrayList;

/**
 * Created by Jordan on 2016-10-22.
 */

public class RVAdapterJobs  extends RecyclerView.Adapter<RVAdapterJobs.ViewHolder> {

    public static final String TAG = RVAdapterJobs.class.getSimpleName();

    // Arraylist of a user's category data
    private ArrayList<Job> mCurrUserData;
    private ViewHolder.OnJobClickedListener mListener;

    // Separate method to set the listener
    public void setOnJobClickedListener(ViewHolder.OnJobClickedListener listener) {
        mListener = listener;
    }

    // Constructor
    public RVAdapterJobs(ArrayList<Job> list) {
        mCurrUserData = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflate view for a single row (viewholder)
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_viewholder_job, parent, false);
        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);

        Job curr = mCurrUserData.get(position);

        // Fill the UI with user data
        String title = curr.getJobTitle();
        title = title.substring(0, 1).toUpperCase() + title.substring(1);  // Simply makes first letter capital
        String company = curr.getCompany();

        String desc = curr.getDescription();
        // remove all html tags
        desc = desc.replaceAll("\\<.*?>","");

        //String city = curr.getCity();

        viewholder.mJobTitle.setText(title);
        viewholder.mCompany.setText(company);
        viewholder.mDescription.setText(desc);
        //viewholder.mCity.setText(city);
    }

    @Override
    public int getItemCount() {
        return mCurrUserData.size();
    }

    /**
     * Method to update the arraylist. Note that notifyDataSetChanged() must be called to actually update
     * the recycler view
     *
     * @param newList the new arraylist for a different user
     */
    public void updateArrayList(ArrayList<Job> newList) {
        mCurrUserData = newList;
    }


    /**
     * This class is a viewholder for a CategoryData
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // UI elements
        public final View mView;
        public final TextView mJobTitle;
        public final TextView mCompany;
        public final TextView mDescription;
        //public final TextView mCity;
        private OnJobClickedListener mListener;



        public ViewHolder(View view, OnJobClickedListener listener) {
            super(view);
            mView = view;

            mListener = listener;
            mView.setOnClickListener(this);

            // set up UI elements
            mJobTitle = (TextView) view.findViewById(R.id.viewholder_job);
            mCompany = (TextView) view.findViewById(R.id.viewholder_company);
            mDescription = (TextView) view.findViewById(R.id.viewholder_snippet);
            //mCity = (TextView) view.findViewById(R.id.viewholder_city);

        }

        // interface to communicate when viewholder is clicked
        public interface OnJobClickedListener {
            void onJobClicked(View itemView);
        }

        @Override
        public void onClick(View v) {
            mListener.onJobClicked(mView);
        }
    }
}
