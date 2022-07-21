package org.kashmirworldfoundation.WildlifeGeoSnap.study;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateStudyActivity extends AppCompatActivity {
    private EditText TitleInput;
    private EditText LocationInput;
    private EditText MissionInput;
    private TextView StartInput;
    private TextView EndInput;
    private Button Post;
    private TextView Back;
    private FirebaseFirestore db;
    private Button pickStartSateID;
    private Button studyDateEndLabel;
    String start = "";
    String end = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study);
        TitleInput =findViewById(R.id.StudyTitleInput);
        LocationInput=findViewById(R.id.StudyLocationInput);
        MissionInput=findViewById(R.id.StudyMissionInput);
        StartInput=findViewById(R.id.studyDateStart);
        EndInput=findViewById(R.id.studyDateEndLabel);
        Post = findViewById(R.id.StudyPostBtn);
        Back= findViewById(R.id.StudyBack);
        db= FirebaseFirestore.getInstance();
        pickStartSateID = findViewById(R.id.pickstartdateid);
        studyDateEndLabel = findViewById(R.id.pickenddateid);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
/**        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = TitleInput.getText().toString();
                String location = LocationInput.getText().toString();
                String mission = MissionInput.getText().toString();

                Date date1;
                Date date2;
                Timestamp ts = null;
                Timestamp ts2 =null;
                Utils utils= Utils.getInstance();
                UserData userData = UserData.getInstance();
                String org = userData.getOrg();

                try {
                    date1=new SimpleDateFormat("dd/MM/yyyy").parse(start);
                    date2= new SimpleDateFormat("dd/MM/yyyy").parse(end);
                    ts= new Timestamp(date1);
                    ts2 = new Timestamp(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Study study = new Study(title, org, mission, location, ts, ts2);
                if (study.getTitle().equals("")) {
                    Toast.makeText(CreateStudyActivity.this, "Please enter a title", Toast.LENGTH_LONG).show();
                    return;
                }
                if (study.getLocation().equals("")) {
                    Toast.makeText(CreateStudyActivity.this, "Please enter a location", Toast.LENGTH_LONG).show();
                    return;
                }
                if (study.getMission().equals("")) {
                    Toast.makeText(CreateStudyActivity.this, "Please enter a mission", Toast.LENGTH_LONG).show();
                    return;
                }
                if (ts == null || ts2 == null) {
                    Toast.makeText(CreateStudyActivity.this, "Please enter the dates of the study", Toast.LENGTH_LONG).show();
                    return;
                }
                if (ts.compareTo(ts2) > 0) {
                    Toast.makeText(CreateStudyActivity.this, "Please enter valid dates", Toast.LENGTH_LONG).show();
                    return;
                }
                db.collection("Study").add(study);
                Intent i= new Intent(getApplicationContext(), MainActivity.class);

                i.putExtra("Study",study);
                startActivity(i);
            }
        });**/

        pickStartSateID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerStart(v);
            }
        });

        studyDateEndLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerEnd(v);
            }
        });
    }



    public void datePickerStart(View v){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateTime = String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(day);
//                StartInput.setText(dateTime);
                start=String.valueOf(day)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
                StartInput.setText(start);

            }

        }, year, month, day).show();
    }

    public void datePickerEnd(View v){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
//                String dateTime = String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(day);
                end=String.valueOf(day)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
                EndInput.setText(end);
            }

        }, year, month, day).show();
    }




}
