package org.kashmirworldfoundation.WildlifeGeoSnap.maps;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import org.kashmirworldfoundation.WildlifeGeoSnap.maps.GoogleMapActivity;

public class MyLocListener implements LocationListener {

    private GoogleMapActivity googleMapActivity;
    private static final String TAG = "MyLocListener";

    public MyLocListener(GoogleMapActivity googleMapActivity) {
        this.googleMapActivity = googleMapActivity;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location);
        googleMapActivity.updateLocation(location);
    }
}