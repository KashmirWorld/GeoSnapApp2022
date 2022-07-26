package org.kashmirworldfoundation.WildlifeGeoSnap.auth.org;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import org.kashmirworldfoundation.WildlifeGeoSnap.auth.AuthHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.ProjectData;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

public class RegisterOrgHandler {

    public static void register(String name, String email, String website, String number, String country, final Utils.LambdaInterface success, final Utils.LambdaInterface fail, Activity activity) {
/**
        ProjectData projectData = new ProjectData(name, email, website, number, country);

        FirebaseFirestore.getInstance().collection("Organization").whereEqualTo("orgName", name).whereEqualTo("orgCountry", country).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().isEmpty()) {
                if (task.getResult().isEmpty()) {
                    createOrganization(projectData, success, fail);
                } else {
                    Toast.makeText(activity, "This organization has already been registered.", Toast.LENGTH_LONG).show();
                    activity.recreate();
                }
            }
        });**/
    }

    public static boolean validateRegisterData(EditText nameView, EditText emailView, EditText websiteView, EditText numberView, Spinner countryView, Activity activity) {
        String name = nameView.getText().toString().trim();
        String email = emailView.getText().toString().trim();
        String website = websiteView.getText().toString().trim();
        String number = numberView.getText().toString().trim();
        String country = countryView.getSelectedItem().toString().trim();
        if (name.isEmpty()) {
            nameView.setError("Organization Required!");
            return false;
        }
        if (email.isEmpty()) {
            emailView.setError("Email Required!");
            return false;
        }
        if (!AuthHandler.validateEmail(email)) {
            emailView.setError("Invalid Email.");
            return false;
        }
        if (website.isEmpty()) {
            websiteView.setError("Website Required!");
            return false;
        }
        if (number.isEmpty()) {
            numberView.setError("Phone Number Required!");
            return false;
        }
        if (country.equals("Country")) {
            Toast.makeText(activity, "Need to select a country", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
