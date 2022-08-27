package org.kashmirworldfoundation.WildlifeGeoSnap.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferenceUtil {

    private SharedPreferences prefs;

    private HashMap<String, String> queue;

    public SharedPreferenceUtil(String prefName, Activity activity) {
        super();
        prefs = activity.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        queue = new HashMap<String, String>();
    }

    public void add(String key, String value) {
        queue.put(key, value);
    }

    public void write(){
        SharedPreferences.Editor editor = prefs.edit();

        for(Map.Entry<String, String> entry : queue.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            editor.putString(key, value);
            // do what you have to do here
            // In your case, another loop.
        }
        editor.apply();
    }

    public void save(String key, String text) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, text);
        editor.apply(); // commit T/F
    }

    public String getValue(String key) {
        String text = prefs.getString(key, "");
        return text;
    }

    public void clearAll() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply(); // commit T/F
    }

    public void removeValue(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply(); // commit T/F
    }
}