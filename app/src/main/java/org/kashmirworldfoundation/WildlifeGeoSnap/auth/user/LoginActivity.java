package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.SharedPreferenceUtil;

public class LoginActivity extends Activity {

    private SharedPreferenceUtil sharedPreference;

    // maps the EditTexts to variables
    private EditText myEmail;
    private EditText myPassword;

    // this activity logs the user into the app, so they can access the dashboard
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        // GeoSnapPrefs is where data is stored from this app on OUR device
        // the email and password is automatically entered
        // IMPORTANT: this is a slight security issue, we can change how it works later
        sharedPreference = new SharedPreferenceUtil(this);
        myEmail.setText(sharedPreference.getValue(  "email"));
        myPassword.setText(sharedPreference.getValue("password"));
    }

    @Override
    protected void initViews() {
        myEmail = findViewById(R.id.enter_email);
        myPassword = findViewById(R.id.projects_create_enter_name);
    }

    // sends the user to the forgot password activity
    public void onClickForgotPassword(View view) {
        Intent forgotPassword = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
        startActivity(forgotPassword);
    }

    // sends the user to the dashboard (if successful)
    public void onClickLoginUser(View view) {
        // takes the email and password that is entered and sends it to the login method
        login(myEmail.getText().toString().trim(), myPassword.getText().toString().trim());
    }

    private void login(String email, String password) {
        // Does all the required steps for loggin in and interacts with the database
        LoginHandler.login(email, password, sharedPreference, this);
    }

}
