package org.kashmirworldfoundation.WildlifeGeoSnap.auth.org;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;


public class RegisterOrgActivity extends Activity implements AdapterView.OnItemSelectedListener {
    EditText mEmail, mOrgname, mPhone, mOrgWebsite;
    Spinner spinner;
    Button mbRegister;
    TextView mbLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_org);
        initViews();
        fAuth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.Countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void initViews() {
        mOrgname = findViewById(R.id.OrgName);
        mPhone = findViewById(R.id.OrgPhone);
        mOrgWebsite = findViewById(R.id.orgwebsite);
        mEmail = findViewById(R.id.OrgEmail);
        spinner = findViewById(R.id.countries);
        mbLogin = findViewById(R.id.logindr);
        mbRegister = findViewById(R.id.RegisterB);
        // mRegion = findViewById(R.id.Region);
    }

    public void onClickLogin(View v) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void onClickRegister(View v) {
        if (!RegisterOrgHandler.validateRegisterData(mOrgname, mEmail, mOrgWebsite, mPhone, spinner, this)) {
            return;
        }
        String orgName = mOrgname.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String website = mOrgWebsite.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String country = spinner.getSelectedItem().toString().trim();

    /**    final Intent i = new Intent(getApplicationContext(), RegisterOrgAdminActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Orgname", orgName);
        bundle.putString("Country", country);
        i.putExtras(bundle);

        RegisterOrgHandler.register(orgName, email, website, phone, country, () -> {
            Toast.makeText(RegisterOrgActivity.this, "Organization Successfully Created", Toast.LENGTH_LONG).show();
            saveAdmin();
            startActivity(i);
        }, () -> {
            Toast.makeText(RegisterOrgActivity.this, "This organization has already been registered.", Toast.LENGTH_LONG).show();
            recreate();
        }, this);**/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void saveAdmin() {
        SharedPreferences sharedPreferences = RegisterOrgActivity.this.getSharedPreferences("Admin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        editor.putBoolean("Admin", true);
        editor.apply();
    }
}

