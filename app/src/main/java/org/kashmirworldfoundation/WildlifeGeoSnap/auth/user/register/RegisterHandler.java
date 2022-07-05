package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Member;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Org;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterHandler {

    public static void register(String name, String job, String email, String number, String password, String organization, String country, Boolean admin, Activity activity) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                onSuccessRegister(name, job, email, number, organization, country, admin, activity);
            }
        });
    }

    private static void onSuccessRegister(String name, String job, String email, String number, String organization, String country, Boolean admin, Activity activity) {
        Org.loadOrgIntoInstance(organization, country, (path) -> {
            final Member member = new Member(email, name, job, number, admin, path, "profile/kwflogo.jpg");
            Toast.makeText(activity,"User Created", Toast.LENGTH_SHORT).show();
            Member.setInstance(member);
            member.save(null);
            Study.loadStudies(() -> {
                activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
            });
        });
    }

    public static boolean validateRegisterData(EditText nameView, EditText jobView, EditText emailView, EditText numberView, EditText password1View, EditText password2View) {
        final String name = nameView.getText().toString().trim();
        final String job = jobView.getText().toString().trim();
        final String email = emailView.getText().toString().trim();
        final String number = numberView.getText().toString().trim();
        final String password1 = password1View.getText().toString().trim();
        final String password2 = password2View.getText().toString().trim();

        // Check to see if any of the fields are not properly made
        if (name.isEmpty()) {
            nameView.setError("Full name is required.");
            return false;
        }
        if (job.isEmpty()) {
            jobView.setError("Job title is required.");
            return false;
        }
        if (email.isEmpty()) {
            emailView.setError("Email is required.");
            return false;
        }
        if (number.isEmpty()) {
            numberView.setError("Phone number is required.");
            return false;
        }
        if (password1.isEmpty()) {
            password1View.setError("Password is required.");
            return false;
        }
        if (password2.isEmpty()) {
            password2View.setError("Re-enter your password");
            return false;
        }
        if (!validateEmail(email)) {
            emailView.setError("Invalid email.");
            return false;
        }
        if (!validatePassword(password1, password1View)) {
            return false;
        }
        if (!password1.equals(password2)) {
            password2View.setError("Password does not match");
            return false;
        }

        return true;
    }

    private static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean validatePassword(String password, EditText passwordView) {
        if (password.length() < 6) {
            passwordView.setError("Password must be at least 6 characters.");
            return false;
        }
        return true;
    }

}
