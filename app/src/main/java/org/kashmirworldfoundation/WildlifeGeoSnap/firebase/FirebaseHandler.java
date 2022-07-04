package org.kashmirworldfoundation.WildlifeGeoSnap.firebase;

import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.kashmirworldfoundation.WildlifeGeoSnap.GlideApp;

public class FirebaseHandler {

    /**
     * Loads an image from the firebase database into a view
     * @param location
     * @param image
     * @param fragment
     */
    public static void loadImageIntoView(String location, ImageView image, Fragment fragment){
        StorageReference ref = FirebaseStorage.getInstance().getReference(location);
        GlideApp.with(fragment)
                .load(ref)
                .into(image);
    }
}
