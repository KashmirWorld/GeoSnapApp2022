package org.kashmirworldfoundation.WildlifeGeoSnap.auth;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

public class TOSAgreementHandler {
    public interface AgreementSuccess { void onAgree(String email, String password); }

    /**
     *  This method sends the user the Terms of Service and executes a lambda function once its positive
     * @param email
     * @param password
     * @param listener
     * @param activity
     */
    public static void sendAgreementThen(String email, String password, final AgreementSuccess listener, Activity activity){

        String userEmail = email.trim();
        String userPassword = password.trim();

        Utils util = Utils.getInstance();

        if (util.getAgreement(activity)){
            hasNotAgreed(userEmail, userPassword, listener, activity);
        }else {
            listener.onAgree(userEmail, userPassword);
        }
    }

    /**
     *  If they haven't agreed, send them the ui and wait for the to agree
     * @param email
     * @param password
     * @param listener
     * @param activity
     */
    private static void hasNotAgreed(String email, String password, final AgreementSuccess listener, Activity activity){

        Utils util = Utils.getInstance();

        AlertDialog.Builder alertDialog = createUI(email, password, listener, activity);
        // if they disagree inform that they won't be able to login
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> Toast.makeText(activity, "Agreement needed to login", Toast.LENGTH_LONG).show());

        // if they agree, save the fact that they agreed and try logging them in
        alertDialog.setPositiveButton("Agree", (dialog, which) -> {
            util.setAgreement(activity);
            listener.onAgree(email, password);
        });

        // show the alert dialog to the user
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    /**
     *  Create the basic TOS agreement UI and send it to the user
     * @param email
     * @param password
     * @param listener
     * @param activity
     * @return
     */
    private static AlertDialog.Builder createUI(String email, String password, final AgreementSuccess listener, Activity activity){
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.disclaimer_layout, null);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("Terms of Service");
        alertDialog.setView(view);
        return alertDialog;
    }



}
