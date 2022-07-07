package org.kashmirworldfoundation.WildlifeGeoSnap.misc.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class PermissionManager {

    public static final int LOCATION_REQUEST = 111;

    private static PermissionManager instance;

    public static PermissionManager getInstance(){
        if(instance == null){
            instance = new PermissionManager();
        }
        return instance;
    }

    public void requestPermissions(Activity activity){
        ArrayList<String> permissions = new ArrayList<>();
        if(!hasLocationPermission(activity)){
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!permissions.isEmpty()){
            ActivityCompat.requestPermissions(activity, permissions.toArray(new String[permissions.size()]), LOCATION_REQUEST);
        }
    }

    public boolean hasLocationPermission(Context context){
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
