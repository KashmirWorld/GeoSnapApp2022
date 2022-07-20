package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;

public class ForgetPasswordActivity extends Activity {
    TextView Email, Back;
    Button Submit;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        fAuth = FirebaseAuth.getInstance();
        initViews();
    }

    @Override
    protected void initViews() {
        Email = findViewById(R.id.EmailRecoveryInput);
        Back = findViewById(R.id.ForgetPassBack);
        Submit = findViewById(R.id.RecoverySubmit);
    }

    public void onClickSubmit(View v) {
        String email = Email.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(ForgetPasswordActivity.this, "Please enter an email", Toast.LENGTH_LONG).show();
            return;
        }
        fAuth.sendPasswordResetEmail(Email.getText().toString().trim());
        Toast.makeText(getApplicationContext(), "Email sent to" + email + " reset Password", Toast.LENGTH_LONG).show();
    }

    public void onClickBack(View v) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}