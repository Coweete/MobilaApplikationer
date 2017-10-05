package com.mah.ag0071.assigment2;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by User on 2017-10-03.
 */

public class LocList implements LocationListener {

    private LocationListener locationListener;
    private LocationManager locationManager;
    private double latitude,longitude;
    private Controller controller;

    public LocList(Controller controller){
        this.controller = controller;
    }


    @Override
    public void onLocationChanged(Location location) {
        //Called when the location has changed
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        Log.v("Location","Your location is long: " + longitude + ",Lat: " + latitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        //Called when the provider is disabled by the user
    }

    @Override
    public void onProviderEnabled(String s) {
        //Called when the provider is disabled by the user
    }


    @Override
    public void onProviderDisabled(String s) {
        //Called when the provider status changes
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
