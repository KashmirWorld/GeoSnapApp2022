package org.kashmirworldfoundation.WildlifeGeoSnap.misc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class Activity extends AppCompatActivity {

    /**
     *  This method should be called within the on create method
     */
    protected abstract void initViews();

}