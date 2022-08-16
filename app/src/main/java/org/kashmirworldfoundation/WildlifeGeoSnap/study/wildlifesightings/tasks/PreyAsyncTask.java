package org.kashmirworldfoundation.WildlifeGeoSnap.study.wildlifesightings.tasks;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.WildlifeSighting;
import org.kashmirworldfoundation.WildlifeGeoSnap.study.wildlifesightings.WildlifeSightingFragment;

import java.util.ArrayList;

public class PreyAsyncTask extends AsyncTask<String, Void, String> {
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;
    private FirebaseAuth FireAuth;

    private ArrayList<WildlifeSighting> aprey = new ArrayList<>();
    private UserData mem;
    private String Org;
    private static final String TAG = "StationAsyncTask";
    private int count;
    private int size;

    private WildlifeSightingFragment wildlifeSightingFragment;

    public PreyAsyncTask(WildlifeSightingFragment li){
        wildlifeSightingFragment =li;}

    protected void update(){
        wildlifeSightingFragment.updatePreyList(aprey);
        wildlifeSightingFragment.updateList();
    }


    @Override
    protected String doInBackground(String... strings) {

        // Add data from Firebase on the the Arrays
        try {
            FireAuth = FirebaseAuth.getInstance();
            firebaseFirestore = FirebaseFirestore.getInstance();
            collectionReference = firebaseFirestore.collection("WildlifeSighting");
        }
        catch (Exception e){
            //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

/**
        mem= UserData.getInstance();
        collectionReference.whereEqualTo("org",mem.getOrg()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    size = task.getResult().size();
                    for (DocumentSnapshot objectDocumentSnapshot: task.getResult()){
                        WildlifeSighting stat = objectDocumentSnapshot.toObject(WildlifeSighting.class);
                        aprey.add(stat);
                        count++;
                        if(count==size){
                            update();
                        }
                    }
                }
            }
        });
 **/
        return null;
    }
}
