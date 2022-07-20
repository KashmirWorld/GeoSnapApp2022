package org.kashmirworldfoundation.WildlifeGeoSnap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// this activity creates an account for the user
public class SignUpActivity extends AppCompatActivity {

    // maps the EditTexts to variables
    EditText myName = findViewById(R.id.enter_name);
    EditText myEmail = findViewById(R.id.enter_email);
    EditText myPassword = findViewById(R.id.enter_password);
    EditText myConfPassword = findViewById(R.id.enter_confirm_password);

    boolean noIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    // checks that:
    // name field isn't blank
    // email field contains an "@"
    // password is more than 8 characters, with at least 1 uppercase, 1 number, 1 symbol
    // password confirmation matches

    // if an issue is found in a particular text field, it will return an error

    private void onClickSignUp(View view) {
        noIssues = true;

        if (myName.getText().toString().isEmpty()) {
            myName.setError("Enter a valid full name.");
            noIssues = false;
        }

        if (!myEmail.getText().toString()
                .matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")) {
            myEmail.setError("Enter a valid email address.");
            noIssues = false;
        }

        if (!isStrong(myPassword.getText().toString())) {
            myPassword.setError("The password must contain " +
                    "uppercase characters, " +
                    "lowercase characters, " +
                    "digits, " +
                    "and special characters. " +
                    "It must be at least length 8.");
            noIssues = false;
        }

        if (!myPassword.getText().equals(myConfPassword.getText())) {
            noIssues = false;
        }

        // if there are no issues, it sends the user to the next activity
        if (noIssues) {
            Intent verifyEmail = new Intent(getApplicationContext(), VerifyEmailActivity.class);
            startActivity(verifyEmail);
        }
    }

    private boolean isStrong(String input) {
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



