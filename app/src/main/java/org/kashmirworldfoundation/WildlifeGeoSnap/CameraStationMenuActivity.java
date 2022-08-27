package org.kashmirworldfoundation.WildlifeGeoSnap;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.kashmirworldfoundation.WildlifeGeoSnap.study.station.rebait.Rebait;
import org.kashmirworldfoundation.WildlifeGeoSnap.study.station.rebait.RebaitAdapter;

import java.util.ArrayList;

public class CameraStationMenuActivity extends AppCompatActivity {

    // views
    private TextView setupDate, setupTime, endDate, endTime;
    private EditText cameraID, SDCardID, watershedID, longitude, latitude, lure, habitat, notes;
    private Switch operational, currentLocation;
    private CardView takenDownTag;
    private RecyclerView recyclerView;

    // adapters
    private RebaitAdapter adapter;
    private ArrayList<Rebait> rebaitArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_station_menu);

        // maps the views to variables
        setupDate = findViewById(R.id.setup_date);
        setupTime = findViewById(R.id.setup_time);
        endDate = findViewById(R.id.end_date);
        endTime = findViewById(R.id.end_time);

        cameraID = findViewById(R.id.CameraID);
        SDCardID = findViewById(R.id.SDCardID);
        watershedID = findViewById(R.id.WatershedID);
        longitude = findViewById(R.id.longitude_coord);
        latitude = findViewById(R.id.latitude_coord);
        lure = findViewById(R.id.lure);
        habitat = findViewById(R.id.habitat);
        notes = findViewById(R.id.notes);

        operational = findViewById(R.id.operational);
        currentLocation = findViewById(R.id.currentLocation);

        takenDownTag = findViewById(R.id.taken_down_tag);

        // set up recyclerView
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rebaitArrayList = new ArrayList<>();
        adapter = new RebaitAdapter(this, rebaitArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));

        createListData();

    }

    private void createListData() {

        // adding data into the recyclerView
        // example below: it's not perfect but it can be adjusted later
        Rebait rebait = new Rebait("NH-20", "23", "Jul 23");
    }

    public void onClickUpdate(View view) {

    }

    public void onClickRebait(View view) {

    }

    public void onClickTakedown(View view) {

    }

    public void onClickDelete(View view) {

    }
}