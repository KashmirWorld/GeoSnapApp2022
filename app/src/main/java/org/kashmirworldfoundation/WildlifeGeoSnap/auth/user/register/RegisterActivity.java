package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.TOSAgreementHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.org.RegisterOrgActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

public class RegisterActivity extends Activity {

    EditText mFullName, mJobTitle, mOrganization, mEmail, mPassword, mReEnter, mPhonenumber;
    Button mRegisterBtn;
    TextView mLoginBtn, mRegisterOrgB;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

    }

    @Override
    protected void initViews() {
        mFullName = findViewById(R.id.fullName);
        mJobTitle = findViewById(R.id.jobTitle);
        mEmail = findViewById(R.id.email);
        mPhonenumber = findViewById(R.id.phone);
        mPassword = findViewById(R.id.password);
        mReEnter = findViewById(R.id.reEnter);
        mRegisterBtn = findViewById(R.id.registrationBtn);
        mLoginBtn = findViewById(R.id.createText);
        mRegisterOrgB = findViewById(R.id.RegisterOrgB);
        progressBar = findViewById(R.id.progressBar2);
    }

    public void onClickRegisterOrg(View v) {
        startActivity(new Intent(getApplicationContext(), RegisterOrgActivity.class));
    }

    public void onClickLogin(View v) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void onClickRegister(View v) {
        Intent i = getIntent();
        if (!RegisterHandler.validateRegisterData(mFullName, mJobTitle, mEmail, mPhonenumber, mPassword, mReEnter)) {
            return;
        }

        final String organization = i.getStringExtra("OrgName");
        final String country = i.getStringExtra("Country");

        Utils util = Utils.getInstance();

        TOSAgreementHandler.sendAgreementThen("Agreement needed to register", () -> {
            register(organization, country);
        }, this);
    }

    private void register(String organization, String country) {
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String name = mFullName.getText().toString().trim();
        final String number = mPhonenumber.getText().toString().trim();
        final String job = mJobTitle.getText().toString().trim();
        RegisterHandler.register(name, job, email, number, password, organization, country, false, this);
    }
}


