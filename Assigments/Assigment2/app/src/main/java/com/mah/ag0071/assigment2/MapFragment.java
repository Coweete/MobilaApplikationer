package com.mah.ag0071.assigment2;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MainActivity activity;
    private Controller controller;
    private boolean test = false;
    private MapView mapView;
    private double latitude,longitude;
    private GoogleMap googleMap;
    private LatLng trelleborg = new LatLng(55.37514, 13.15691);


    public MapFragment() {
        // Required empty public constructor
    }

    public void updateMapMarkers(ArrayList<Member> members){
        for (int i = 0; i < members.size(); i++) {
            LatLng tempPos = new LatLng(Double.parseDouble(members.get(i).getLongitude()),
                    Double.parseDouble(members.get(i).getLatitude()));
            googleMap.addMarker(new MarkerOptions().position(tempPos));
        }
    }

    public void updateMapPosition(Member member){
        try{
            Log.v("Map","Before adding thins to the map");
            LatLng temp = new LatLng(Double.parseDouble(member.getLongitude()),
                    Double.parseDouble(member.getLatitude()));
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(temp).title(member.getName()));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(temp, 10);
            googleMap.animateCamera(cameraUpdate);
            Log.v("Map","End of adding and fixing pos to the map");
        }catch (NullPointerException e){
            Log.e("Map","Nullpointer i member?");
            e.printStackTrace();
        }
    }

    public void setLatLong(double latitude,double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_map, container, false);

        initComponents(view,savedInstanceState);
        return view;
    }

    private void initComponents(View view,Bundle savedInstanceState) {
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        MapsInitializer.initialize(this.getActivity());
    }


    public void setResorces(MainActivity activity, Controller controller) {
        this.activity = activity;
        this.controller = controller;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        ((MainActivity) getActivity()).getController().setMapMarkers(googleMap);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
