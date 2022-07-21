package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Org;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;

import java.util.ArrayList;

public class RegisterSelectOrganizationActivity extends Activity {

    private ArrayAdapter<String> dataAdapter;

    Spinner mOrg;
    Button mSubmit;
    TextView mRefresh,mBack;

    String Sorg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_organization);
        initViews();

        loadOrganizations();

        mOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sorg = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void initViews() {
        mOrg = findViewById(R.id.register_orgS3);
        mRefresh = findViewById(R.id.RefreshSpin0);
        mBack =findViewById(R.id.GoBackLogin);
        mSubmit = findViewById(R.id.submit0);
    }

    public void onClickBack(View v){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void loadOrganizations(){
        dataAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item, new ArrayList<String>());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mOrg.setAdapter(dataAdapter);
        dataAdapter.addAll("Select Org");
        FirebaseFirestore.getInstance().collection("Organization").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    Org org = documentSnapshot.toObject(Org.class);
                    if(org.getOrgName() != null){
                        dataAdapter.add(org.getOrgName());
                    }
                }
            }
        });
    }

    public void onClickSubmit(View v){
        //Check if the data is selected
        if (Sorg == null || Sorg.equals("Select Org")){
            Toast.makeText(RegisterSelectOrganizationActivity.this, "Need to select an organization", Toast.LENGTH_SHORT).show();
            return;
        }
        //Add your data to bundle
        Intent I = new Intent(getApplicationContext(), RegisterSelectCountryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Orgname", Sorg);
        I.putExtras(bundle);
        startActivity(I);
    }

    public void onClickRefresh(View v){
        dataAdapter.clear();
        loadOrganizations();
    }
}
