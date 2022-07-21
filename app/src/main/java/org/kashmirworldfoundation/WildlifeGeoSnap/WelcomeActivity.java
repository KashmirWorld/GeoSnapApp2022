package org.kashmirworldfoundation.WildlifeGeoSnap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register.RegisterActivity;


// this activity directs the user to either the sign up or login activities
// it's the first thing the user sees when opening the app

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    // sends the user to the sign up activity
    // NOTE: it is accessed in the login class
    public void onClickSignUp(View view) {
        Intent signUp = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(signUp);
    }

    // sends the user to the login activity
    // NOTE: it is accessed in the sign up class
    public void onClickLogin(View view) {
        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(login);
    }
}
