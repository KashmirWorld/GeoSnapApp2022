package org.kashmirworldfoundation.WildlifeGeoSnap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreference;

    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    // maps the EditTexts to variables
    EditText myEmail = findViewById(R.id.enter_email);
    EditText myPassword = findViewById(R.id.enter_password);

    // this activity logs the user into the app, so they can access the dashboard
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // GeoSnapPrefs is where data is stored from this app on OUR device
        // the email and password is automatically entered
        // IMPORTANT: this is a slight security issue, we can change how it works later
        sharedPreference = getSharedPreferences("GeoSnapPrefs", Context.MODE_PRIVATE);
        myEmail.setText(sharedPreference.getString("email", ""));
        myPassword.setText(sharedPreference.getString("password", ""));
    }

    // sends the user to the forgot password activity
    private void onClickForgotPassword(View view) {
        Intent forgotPassword = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
        startActivity(forgotPassword);
    }

    // sends the user to the dashboard (if successful)
    private void onClickLoginUser(View view) {
        // takes the email and password that is entered and sends it to the login method
        login(myEmail.getText().toString(), myPassword.getText().toString());
    }

    private void login(String email, String password) {

        // this signs into firebase??
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                // saves the user's email and password into shared preferences
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putString("email", myEmail.getText().toString());
                editor.putString("password", myPassword.getText().toString());
                editor.apply();

                // open dashboard
                Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(dashboard);

            } else {
                //tells user that login was unsuccessful
                Toast.makeText(this, "Error: " + task.getException(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
