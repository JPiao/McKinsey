package com.jordanleex13.mckinseyhackandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jordanleex13.mckinseyhackandroid.Helpers.RSSHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    public static final String TAG = NewsFragment.class.getSimpleName();

    public NewsFragment() {
        // Required empty public constructor
    }


    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        Button b = (Button) v.findViewById(R.id.btt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RSSHelper rssHelper = new RSSHelper("http://rss.cnn.com/rss/edition_europe.rss");
                rssHelper.fetchXML();
            }
        });

        return v;
    }

}
