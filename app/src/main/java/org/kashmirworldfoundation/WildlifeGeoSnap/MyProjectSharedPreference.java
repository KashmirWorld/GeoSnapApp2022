package org.kashmirworldfoundation.WildlifeGeoSnap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class MyProjectSharedPreference {

    private static final String TAG = "MyProjectSharedPreference";
    private SharedPreferences prefs;

    public MyProjectSharedPreference(Activity activity) {
        super();
        prefs = activity.getSharedPreferences("MY_PREFS_KEY", Context.MODE_PRIVATE);
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