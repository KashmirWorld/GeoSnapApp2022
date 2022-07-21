package org.kashmirworldfoundation.WildlifeGeoSnap.study.station.tasks;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;

public class StationDeleteAsyncTask extends AsyncTask<String,Void,String> {
    String path,study;
    UserData userData;
    public StationDeleteAsyncTask(String pat, String stud, UserData mem){
        path=pat;
        study=stud;
        userData =mem;
    }
    @Override
    protected String doInBackground(String... strings) {
        try{
            FirebaseFirestore firestore=FirebaseFirestore.getInstance();
            firestore.document(path).delete();
            firestore.collection("CameraStation").whereEqualTo("org", userData.getOrg()).whereEqualTo("study",study).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    WriteBatch batch=firestore.batch();

                    for(DocumentSnapshot objectDocumentSnapshot: task.getResult()){
                        batch.delete(firestore.document(objectDocumentSnapshot.getReference().getPath()));
                    }
                    batch.commit();
                }
            });
        }
        catch (Error e){

        }
        return null;
    }
}
