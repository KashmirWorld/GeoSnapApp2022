package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.kashmirworldfoundation.WildlifeGeoSnap.GlideApp;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.AuthHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.TOSAgreementHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.org.RegisterOrgActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register.RegisterOrgAdminActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register.RegisterSelectOrganizationActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.SharedPreferenceUtil;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    public static final String PREFERENCE_NAME = "MY_PREFS_KEY";

    // UI Views
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mRegisterBtn,mRegisterOrgBtn, mForgetBtn;
    ImageView Background;
    ConstraintLayout layout;
    ArrayList<String> studies;

    //User Inputted Values
    Boolean rememberLogin = false;
    String userEmail, userPassword;
    private SharedPreferenceUtil loginPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initBackground();
        populateLoginInfo();
    }

    @Override
    protected void initViews() {
        layout = findViewById(R.id.LoginLayout);
        mForgetBtn = findViewById(R.id.LoginForget);
        mEmail      = findViewById(R.id.email);
        mPassword   = findViewById(R.id.password);
        mLoginBtn   = findViewById(R.id.LoginBtn);
        mRegisterBtn = findViewById(R.id.Register0);
        mRegisterOrgBtn = findViewById(R.id.RegisterOrgBtn);
        Background =findViewById(R.id.BackgroundLogin);
    }

    /**
     *  This method is supposed to change the background of the Login menu to look like the one within firebases database (does not work)
     *  TODO: Fix it so the background changes
     */
    private void initBackground() {
        StorageReference ref = FirebaseStorage.getInstance().getReference("assets/Start.png");
        GlideApp.with(this)
                .load(ref)
                .into(Background);
    }

    // THESE ARE THE ONCLICK LISTENERS FOR THE UI

    public void onClickForget(View v){
        startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
    }

    public void onClickRegisterUser(View v){
        startActivity(new Intent(getApplicationContext(), RegisterSelectOrganizationActivity.class));
    }

    public void onClickRegisterOrg(View v){
        if (getAdmin()){
            startActivity(new Intent(getApplicationContext(), RegisterOrgAdminActivity.class));
        }
        startActivity(new Intent(getApplicationContext(), RegisterOrgActivity.class));
    }

    public void onClickLogin(View v){
        Utils util = Utils.getInstance();

        userEmail = mEmail.getText().toString();
        userPassword = mPassword.getText().toString();

        if (!AuthHandler.validateLoginInfo(userEmail, userPassword, this)){
            return;
        }

        TOSAgreementHandler.sendAgreementThen(userEmail, userPassword, (email, password) -> {
            LoginHandler.login(email, password, rememberLogin, loginPreferences, this);
        }, this);
    }

    public void onClickRememberLogin(View v) {
        rememberLogin = !rememberLogin;
    }

    /**
     *  This method reads the saved password from the system and sets it within the UI as well as the saved data
     */
    private void populateLoginInfo() {
        loginPreferences = new SharedPreferenceUtil(PREFERENCE_NAME, this);
        userEmail = loginPreferences.getValue("email");
        userPassword = loginPreferences.getValue("password");
        mEmail.setText(userEmail);
        mPassword.setText(userPassword);
    }

    /**
     *  This method will probably be removed later when a user's admin status is held within a Member struct and not saved on the device
     * @return boolean
     */
    private boolean getAdmin(){
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("Admin", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("Admin",false);
    }
}
