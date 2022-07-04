package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Member;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.Objects;

public class LoginHandler {

    public static void login(String uemail, String upassword, boolean rememberLogin, SharedPreferenceUtil loginPreferences, Activity activity){
        // Initialize  firebase auth
        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        // Trim the email and password
        String email = uemail.trim();
        String password = upassword.trim();

        /**
         * Try siging in with firebase
         */
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                onSuccessSignIn(email, password, rememberLogin, loginPreferences, activity);
            }
            else{
                Toast.makeText(activity, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG ).show();
            }
        });
    }

    /**
     *  firebase successfully logged us in
     * @param email
     * @param password
     * @param rememberLogin
     * @param loginPreferences
     * @param activity
     */
    private static void onSuccessSignIn(String email, String password, boolean rememberLogin, SharedPreferenceUtil loginPreferences, Activity activity) {

        // Try to save the login data between sessions
        trySaveLoginData(email, password, rememberLogin, loginPreferences);

        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        fStore.document("Member/"+fAuth.getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Member member= task.getResult().toObject(Member.class);
                assert member != null;
                Member.setInstance(member);
                loadStudies(activity);
            }
        });
    }

    private static void loadStudies(Activity activity){
        Member member = Member.getInstance();
        // for some reason studies are held on by the user in store (will need to be fixed)
        ArrayList<String> studies = new ArrayList<String>();

        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        studies.add("Pick A Study");

        fStore.collection("Study").whereEqualTo("org",member.getOrg()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                    Study study = documentSnapshot.toObject(Study.class);
                    studies.add(study.getTitle());
                }
                if (studies.size()==1){
                    studies.set(0,"No Studies");
                }
            }else{
                studies.set(0, "No Studies");
            }
            saveStudies(studies, activity);
            activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
        });

    }

    /**
     * try saving the login data by the user
     * @param rememberLogin
     * @param loginPreferences
     */
    private static void trySaveLoginData(String email, String password, boolean rememberLogin, SharedPreferenceUtil loginPreferences){
        if (rememberLogin) {
            loginPreferences.save("email", email);
            loginPreferences.save("password", password);
        }
        else{
            loginPreferences.clearAll();
        }
    }


    /**
     *  Why are studies saved on the device?
     * @param studies
     */
    private static void saveStudies(ArrayList<String> studies, Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        Gson gson = new Gson();
        String json =gson.toJson(studies);
        editor.putString("studies",json);
        editor.apply();
    }

}
