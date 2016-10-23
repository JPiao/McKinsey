package com.jordanleex13.mckinseyhackandroid;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.jordanleex13.mckinseyhackandroid.Helpers.FillMapWithMarkersTask;
import com.jordanleex13.mckinseyhackandroid.Helpers.FragmentHelper;
import com.jordanleex13.mckinseyhackandroid.Models.SearchEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import static com.google.android.gms.wearable.DataMap.TAG;


public class JobsMapFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;
    public HashMap<Marker, String> markerHashMap;
    public JobsMapFragment() {
        // Required empty public constructor
    }

    public static JobsMapFragment newInstance() {
        JobsMapFragment fragment = new JobsMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        markerHashMap = new HashMap<>();

    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jobs_map, container, false);

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        FragmentHelper.swapFragments(getChildFragmentManager(), R.id.fragment_jobs_map_container,
                mapFragment, true, false, null, null);

        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.e(TAG, "FAILED");
        } else {
            //mMap.setMyLocationEnabled(true);

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.5074, -0.1500), 10.0f));

        mMap.clear();

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                String markerURL = markerHashMap.get(marker);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(markerURL));
                startActivity(browserIntent);



            }
        });
        new FillMapWithMarkersTask(mMap, markerHashMap).execute();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchEvent(SearchEvent event) {
        Log.e(TAG, "HERE");
        mMap.clear();
        markerHashMap.clear();
        new FillMapWithMarkersTask(mMap, markerHashMap).execute();
    }

}
