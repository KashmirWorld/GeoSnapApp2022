package org.kashmirworldfoundation.WildlifeGeoSnap.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;

import java.io.IOException;
import java.io.InputStream;

public class LocationUtil {

    private static final String ELEVATION_API_KEY="AIzaSyBh-rFSAH9QqPtUrLXcT5Z0c2ZQiJUWkTc";

    public interface LocationInterface{ public void onLocationSuccess(double longitude, double latitude, double altitude);}

    /**
     * it should be checked that the user has the valid permissions to access the location before callign this method
     * @param fusedLocationProviderClient
     * @param onSuccess
     * @param onFail
     * @param activity
     */
    @SuppressLint("MissingPermission")
    public static void getLocation(FusedLocationProviderClient fusedLocationProviderClient, LocationInterface onSuccess, final Utils.LambdaInterface onFail ,Activity activity){
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(activity, location -> {
            if (location != null){
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                double altitude = getElevationFromGoogleMaps(longitude, latitude);
                onSuccess.onLocationSuccess(longitude, latitude, altitude);
            }else if (onFail!=null){
                onFail.run();
            }
        });
    }

    public static boolean checkPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, MainActivity.LOCATION_REQUEST);
            return false;
        }
        return true;
    }

    public static double getElevationFromGoogleMaps(double longitude, double latitude) {
        double result = Double.NaN;
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        String url = "https://maps.googleapis.com/maps/api/elevation/"
                + "xml?locations=" + String.valueOf(latitude)
                + "," + String.valueOf(longitude)
                + "&key="
                + ELEVATION_API_KEY;
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                int r = -1;
                StringBuffer respStr = new StringBuffer();
                while ((r = instream.read()) != -1)
                    respStr.append((char) r);
                String tagOpen = "<elevation>";
                String tagClose = "</elevation>";
                if (respStr.indexOf(tagOpen) != -1) {
                    int start = respStr.indexOf(tagOpen) + tagOpen.length();
                    int end = respStr.indexOf(tagClose);
                    String value = respStr.substring(start, end);
                    //result = (double)(Double.parseDouble(value)*3.2808399); // convert from meters to feet
                    result=(double)Double.parseDouble(value);
                }
                instream.close();
            }
        } catch (ClientProtocolException e) {}
        catch (IOException e) {}

        return result;
    }
}
