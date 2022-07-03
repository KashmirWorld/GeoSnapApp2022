package org.kashmirworldfoundation.WildlifeGeoSnap.auth;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Member;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

import java.util.ArrayList;

public class AuthHandler {

    /**
     *  This method validates the user's login information
     * @param email
     * @param password
     * @param activity
     * @return
     */
    public static boolean validateLoginInfo(String email, String password, Activity activity){
        email = email.trim();
        password = password.trim();

        // if the email or password is empty, send a message and return
        if (email == null || email.isEmpty() || password == null || password.isEmpty()){
            Toast.makeText(activity, "Please enter username and password", Toast.LENGTH_LONG ).show();
            return false;
        }

        return true;
    }

}
