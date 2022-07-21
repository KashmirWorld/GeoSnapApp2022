package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.SharedPreferenceUtil;

public class LoginHandler {

    public static void login(String uemail, String upassword, SharedPreferenceUtil loginPreferences, Activity activity) {
        // Initialize  firebase auth
        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        // Trim the email and password
        String email = uemail.trim();
        String password = upassword.trim();

        /**
         * Try siging in with firebase
         */
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                onSuccessSignIn(email, password, loginPreferences, activity);
            } else {
                Toast.makeText(activity, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * firebase successfully logged us in
     *
     * @param email
     * @param password
     * @param rememberLogin
     * @param loginPreferences
     * @param activity
     */
    private static void onSuccessSignIn(String email, String password, SharedPreferenceUtil loginPreferences, Activity activity) {

        // Try to save the login data between sessions
        trySaveLoginData(email, password, loginPreferences);

        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        fStore.document("Member/" + fAuth.getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                UserData userData = task.getResult().toObject(UserData.class);
                assert userData != null;
                UserData.setInstance(userData);
                Study.loadStudies(() -> {
                    activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
                });
            }
        });
    }

    /**
     * try saving the login data by the user
     *
     * @param loginPreferences
     */
    private static void trySaveLoginData(String email, String password, SharedPreferenceUtil loginPreferences) {
        loginPreferences.save("email", email);
        loginPreferences.save("password", password);
    }
}
