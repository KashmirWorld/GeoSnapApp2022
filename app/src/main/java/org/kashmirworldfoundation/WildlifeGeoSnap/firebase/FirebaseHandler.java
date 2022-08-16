package org.kashmirworldfoundation.WildlifeGeoSnap.firebase;

import android.app.Activity;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.kashmirworldfoundation.WildlifeGeoSnap.GlideApp;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects.User;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Org;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;

public class FirebaseHandler {

    public static void resetSession(){
        User.resetInstance();
        Org.setInstance(null);
        Study.resetStudies();
    }

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

    /**
     * Loads an image from the firebase database into a view
     * @param location
     * @param image
     * @param fragment
     */
    public static void loadImageIntoView(String location, ImageView image, Activity fragment){
        StorageReference ref = FirebaseStorage.getInstance().getReference(location);
        GlideApp.with(fragment)
                .load(ref)
                .into(image);
    }
}
