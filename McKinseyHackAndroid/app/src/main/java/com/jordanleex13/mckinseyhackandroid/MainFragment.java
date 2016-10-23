package com.jordanleex13.mckinseyhackandroid;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jordanleex13.mckinseyhackandroid.Helpers.FragmentHelper;


public class MainFragment extends Fragment implements View.OnClickListener{


    public static final String TAG = MainFragment.class.getSimpleName();

    private TextView welcomeText;
    private boolean English = true;

    private CountDownTimer timer;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_main, container, false);

        welcomeText = (TextView) v.findViewById(R.id.fragment_main_textview_welcome);

        ImageButton educationButton = (ImageButton) v.findViewById(R.id.fragment_main_education_logo);
        educationButton.setBackgroundColor(getResources().getColor(R.color.white));
        educationButton.setOnClickListener(this);

        ImageButton jobsButton = (ImageButton) v.findViewById(R.id.fragment_main_jobs_logo);
        jobsButton.setBackgroundColor(getResources().getColor(R.color.white));
        jobsButton.setOnClickListener(this);

        ImageButton newsButton = (ImageButton) v.findViewById(R.id.fragment_main_news_logo);
        newsButton.setBackgroundColor(getResources().getColor(R.color.white));
        newsButton.setOnClickListener(this);

        ImageButton communityButton = (ImageButton) v.findViewById(R.id.fragment_main_community_logo);
        communityButton.setBackgroundColor(getResources().getColor(R.color.white));
        communityButton.setOnClickListener(this);

        ImageButton policeButton = (ImageButton) v.findViewById(R.id.fragment_main_police_logo);
        policeButton.setBackgroundColor(getResources().getColor(R.color.white));
        policeButton.setOnClickListener(this);

        ImageButton prAppButton = (ImageButton) v.findViewById(R.id.fragment_main_prapp_logo);
        prAppButton.setBackgroundColor(getResources().getColor(R.color.white));
        prAppButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentHelper.setUpActionBar(getActivity(), true, "Categories");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fragment_main_jobs_logo:
//                JobsFragment newFragment = JobsFragment.newInstance();
//                FragmentHelper.swapFragments(getActivity().getSupportFragmentManager(), R.id.activity_main_container,
//                        newFragment, false, true, null, JobsFragment.TAG);
                    Intent intent = new Intent(getContext(), JobsActivity.class);
                    startActivity(intent);
                break;

//            case R.id.fragment_main_news_logo:
//                NewsFragment newsFragment = NewsFragment.newInstance();
//                FragmentHelper.swapFragments(getActivity().getSupportFragmentManager(), R.id.activity_main_container,
//                        newsFragment, false, true, null, NewsFragment.TAG);


            default:
                Log.e(TAG, "Unknown click registered with ID : " + String.valueOf(v.getId()));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);

        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (English) {
                    welcomeText.setText("أهلا بك");
                    English = false;
                } else {
                    welcomeText.setText("Welcome");
                    English = true;
                }
                welcomeText.startAnimation(in);
                timer.cancel();
                timer.start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                welcomeText.startAnimation(out);
            }
        };
        timer.start();
    }
}
