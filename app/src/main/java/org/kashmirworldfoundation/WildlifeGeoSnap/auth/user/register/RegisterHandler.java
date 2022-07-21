package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.AuthHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Org;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RegisterHandler {

    public static void register(String name, String email, String password, Activity activity) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                onSuccessRegister(name, email, activity);
            }
        });
    }

    private static void onSuccessRegister(String name, String email, Activity activity) {
        Org.loadOrgIntoInstance(organization, country, (path) -> {
            final UserData userData = new UserData(email, name, job, number, admin, path, "profile/kwflogo.jpg");
            Toast.makeText(activity, "User Created", Toast.LENGTH_SHORT).show();
            UserData.setInstance(userData);
            userData.save(null);
            Study.loadStudies(() -> {
                activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
            });
        });
    }

    public static boolean validateRegisterData(EditText nameView, EditText emailView, EditText password1View, EditText password2View) {
        final String name = nameView.getText().toString().trim();
        final String email = emailView.getText().toString().trim();
        final String password1 = password1View.getText().toString().trim();
        final String password2 = password2View.getText().toString().trim();

        // Check to see if any of the fields are not properly made
        if (name.isEmpty()) {
            nameView.setError("Full name is required.");
            return false;
        }
        if (email.isEmpty()) {
            emailView.setError("Email is required.");
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
        if (!AuthHandler.validateEmail(email)) {
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

    private static boolean validatePassword(String password, EditText passwordView) {
        if (!isStrong(password)) {
            passwordView.setError("The password must contain " +
                    "uppercase characters, " +
                    "lowercase characters, " +
                    "digits, " +
                    "and special characters. " +
                    "It must be at least length 8.");
            return false;
        }
        if (password.length() < 6) {
            passwordView.setError("Password must be at least 6 characters.");
            return false;
        }
        return true;
    }

    public static boolean isStrong(String input) {
        // Checking lower alphabet in string
        int n = input.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(
                Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));
        for (char i : input.toCharArray()) {
            if (Character.isLowerCase(i))
                hasLower = true;
            if (Character.isUpperCase(i))
                hasUpper = true;
            if (Character.isDigit(i))
                hasDigit = true;
            if (set.contains(i))
                specialChar = true;
        }

        return hasDigit && hasLower && hasUpper && specialChar && (n >= 8);
    }

}
