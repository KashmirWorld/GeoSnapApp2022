package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.TOSAgreementHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;


public class RegisterOrgAdminActivity extends Activity {
    EditText mEmail, mPassword, mPassword2, mPhone, mJob, mName;
    Button mbRegisterA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__org__admin);
        initViews();
    }

    @Override
    protected void initViews() {
        mbRegisterA = findViewById(R.id.AdminRegisterB);
        mEmail = findViewById(R.id.AdminEmail);
        mPassword = findViewById(R.id.AdminPassword);
        mPassword2 = findViewById(R.id.AdminPassword2);
        mPhone = findViewById(R.id.AdminPhone);
        mJob = findViewById(R.id.AdminJob);
        mName = findViewById(R.id.AdminName);
    }

    public void onClickRegister(View v) {
        if (!RegisterHandler.validateRegisterData(mName, mJob, mEmail, mPhone, mPassword, mPassword2)) {
            return;
        }
        TOSAgreementHandler.sendAgreementThen("Agreement needed to register", () -> {
            registerAdmin();
        }, this);
    }

    private void registerAdmin() {
        final String name = mName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String job = mJob.getText().toString().trim();
        final String number = mPhone.getText().toString().trim();
        Intent intent = getIntent();
        final String orgname = intent.getStringExtra("Orgname");
        final String country = intent.getStringExtra("Country");
        RegisterHandler.register(name, job, email, number, password, orgname, country, true, this);
    }
}
