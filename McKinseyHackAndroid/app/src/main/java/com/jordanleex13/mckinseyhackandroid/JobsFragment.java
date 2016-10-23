package com.jordanleex13.mckinseyhackandroid;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.jordanleex13.mckinseyhackandroid.Helpers.FragmentHelper;
import com.jordanleex13.mckinseyhackandroid.Helpers.ParseJobsTask;
import com.jordanleex13.mckinseyhackandroid.Managers.JobManager;
import com.jordanleex13.mckinseyhackandroid.Models.Job;
import com.jordanleex13.mckinseyhackandroid.Models.SearchEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobsFragment extends Fragment implements RVAdapterJobs.ViewHolder.OnJobClickedListener {

    public static final String TAG = JobsFragment.class.getSimpleName();

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;


    private RecyclerView mRecyclerView;

    // Adapters
    private RVAdapterJobs mRVAdapter;

    public JobsFragment() {
        // Required empty public constructor
    }


    public static JobsFragment newInstance() {
        JobsFragment fragment = new JobsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //callback = (OnSearchClickedListener) this;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jobs, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_jobs_recycler);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRVAdapter = new RVAdapterJobs(JobManager.getList());
        mRVAdapter.setOnJobClickedListener(this);

        mRecyclerView.setAdapter(mRVAdapter);

        return v;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.jobs_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.jobs_menu_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            //searchView.setBackgroundColor(getResources().getColor(R.color.white));
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    new ParseJobsTask(mRVAdapter).execute(query);

                    FragmentHelper.setUpActionBar(getContext(), true, query);
                    EventBus.getDefault().post(new SearchEvent());

                    hideKeyboard();
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.jobs_menu_search:
                //Toast.makeText(getContext(), "HI", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentHelper.setUpActionBar(getActivity(), true, JobManager.getSearchTerm());
    }

    @Override
    public void onJobClicked(View itemView) {
        Log.e(TAG, "HERE");
        int position = mRecyclerView.getChildLayoutPosition(itemView);
        Job temp = JobManager.getJob(position);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(temp.getUrl()));
        startActivity(browserIntent);

        //Toast.makeText(getContext(), temp.getJobTitle(), Toast.LENGTH_SHORT).show();
    }


    private void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
