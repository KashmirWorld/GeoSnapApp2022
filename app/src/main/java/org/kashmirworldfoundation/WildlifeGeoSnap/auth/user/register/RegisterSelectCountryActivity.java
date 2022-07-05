package org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Org;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RegisterSelectCountryActivity extends Activity {

    private ArrayAdapter<String> dataAdapter;

    Spinner mCountry;
    Button mSubmit;
    TextView mBack,mRefresh;

    String Sorg;
    String Scountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_country);
        initViews();

        Intent intent = getIntent();
        Sorg =intent.getStringExtra("Orgname");

        loadCountries();

        mCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Scountry = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    protected void initViews() {
        mCountry=findViewById(R.id.Spinner_Country);
        mBack = findViewById(R.id.BackOrg);
        mRefresh = findViewById(R.id.RefreshSpin1);
        mSubmit = findViewById(R.id.Submit1);
    }

    public void onClickSubmit(View v){
        //Check if data is selected
        if (Scountry == null || Scountry.equals("Select Country")){
            Toast.makeText(RegisterSelectCountryActivity.this, "Need to select a country", Toast.LENGTH_SHORT).show();
            return;
        }
        //Add data to bundle
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        Bundle b = new Bundle();
        b.putString("OrgName", Sorg);
        b.putString("Country",Scountry);
        i.putExtras(b);
        startActivity(i);
    }

    public void onClickChooseOrg(View v){
        startActivity(new Intent(getApplicationContext(), RegisterSelectOrganizationActivity.class));
        finish();
    }

    public void onClickRefresh(View v){
        dataAdapter.clear();
        loadCountries();
    }

    public void loadCountries(){
        dataAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item, new ArrayList<String>());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountry.setAdapter(dataAdapter);
        FirebaseFirestore.getInstance().collection("Organization").whereEqualTo("orgName",Sorg).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    dataAdapter.add("Select Country");
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Org org = documentSnapshot.toObject(Org.class);
                        if(org != null){
                            dataAdapter.add(org.getOrgCountry());
                        }
                    }
                }
            }

        });
    }

}
