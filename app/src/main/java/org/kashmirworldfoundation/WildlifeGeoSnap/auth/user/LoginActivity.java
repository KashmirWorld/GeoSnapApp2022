package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.AuthHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register.RegisterActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.SharedPreferenceUtil;

public class LoginActivity extends Activity {

    private SharedPreferenceUtil sharedPreference;

    // maps the EditTexts to variables
    private EditText myEmail;
    private EditText myPassword;

    // variables for forgot password pop up window
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText email_address;
    private ImageButton back;
    private Button submit;

    FirebaseAuth fAuth;

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

        fAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void initViews() {
        myEmail = findViewById(R.id.enter_email);
        myPassword = findViewById(R.id.enter_password);
    }

    // sends the user to the forgot password activity
    public void onClickForgotPassword(View view) {
        forgotPasswordDialog();
    }

    // sends the user to the dashboard (if successful)
    public void onClickLoginUser(View view) {
        // takes the email and password that is entered and sends it to the login method
        login(myEmail.getText().toString().trim(), myPassword.getText().toString().trim());
    }

    private void login(String email, String password) {
        // Does all the required steps for logging in and interacts with the database
        LoginHandler.login(email, password, sharedPreference, this);
    }

    public void onClickSignUp(View view) {
        Intent signUp = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(signUp);
    }

    // forget password

    public void forgotPasswordDialog(){
        // creates the forgot password dialog
        dialogBuilder = new AlertDialog.Builder(this);
        final View forgotPasswordView =
                getLayoutInflater().inflate(R.layout.activity_forget_password, null);
        email_address = (EditText) forgotPasswordView.findViewById(R.id.EmailRecoveryInput);

        back = (ImageButton) forgotPasswordView.findViewById(R.id.ForgetPassBack);
        submit = (Button) forgotPasswordView.findViewById(R.id.RecoverySubmit);

        dialogBuilder.setView(forgotPasswordView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public void onClickSubmit(View view) {
        final String email = email_address.getText().toString().trim();
        if (!AuthHandler.validateEmail(email)) {
            email_address.setError("Invalid email.");
        } else {
            fAuth.sendPasswordResetEmail(email_address.getText().toString().trim());
        }
    }

    public void onClickBack(View view) {
        dialog.dismiss();
    }
}
