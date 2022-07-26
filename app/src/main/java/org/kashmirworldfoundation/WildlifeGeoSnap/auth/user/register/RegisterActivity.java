package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

// this activity creates an account for the user
public class RegisterActivity extends Activity {

    // maps the EditTexts to variables
    private EditText myName;
    private EditText myEmail;
    private EditText myPassword;
    private EditText myConfPassword;

    boolean noIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
    }

    @Override
    protected void initViews() {
        myName = findViewById(R.id.enter_name);
        myEmail = findViewById(R.id.enter_email);
        myPassword = findViewById(R.id.projects_create_enter_name);
        myConfPassword = findViewById(R.id.enter_confirm_password);
    }

    public void onClickRegister(View v) {
        //Intent i = getIntent();
        if (!RegisterHandler.validateRegisterData(myName, myEmail, myPassword, myConfPassword)) {
            return;
        }
        Utils util = Utils.getInstance();
        register();
        /**
        TOSAgreementHandler.sendAgreementThen("Agreement needed to register", () -> {
            register();
        }, this);**/
    }

    private void register() {
        final String email = myEmail.getText().toString().trim();
        final String password = myPassword.getText().toString().trim();
        final String name = myName.getText().toString().trim();
        RegisterHandler.register(name, email, password,this);

        //TODO: Add veryify email system, for now though we can skip past this
        // if there are no issues, it sends the user to the next activity
        /**if (noIssues) {
         Intent verifyEmail = new Intent(getApplicationContext(), VerifyEmailActivity.class);
         startActivity(verifyEmail);
         }**/
    }

}



