package org.kashmirworldfoundation.WildlifeGeoSnap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.FirebaseHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.permissions.PermissionManager;
import org.kashmirworldfoundation.WildlifeGeoSnap.study.StudyFragment;
import org.kashmirworldfoundation.WildlifeGeoSnap.maps.MapFragment;
import org.kashmirworldfoundation.WildlifeGeoSnap.profile.ProfileFragment;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.LoginActivity;

import com.google.android.gms.location.LocationServices;

public class MainActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    private StudyFragment studyFragment;
    private FusedLocationProviderClient mFusedLocationClient;
    public static final int LOCATION_REQUEST = 111;
    private static final String TAG = "MainActivity";
    public Double latitudeD;
    private Double longitudeD;
    private Double altitudeD;

    private String latitude;
    private String longitude;
    private String altitude;

    private String Uid;

    private static final String ELEVATION_API_KEY="AIzaSyBh-rFSAH9QqPtUrLXcT5Z0c2ZQiJUWkTc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        studyFragment = new StudyFragment();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

        initNavBar(savedInstanceState);

        initThreadingPolicy();

        PermissionManager.getInstance().requestPermissions(this);
    }

    public void initViews(){
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    private void initThreadingPolicy(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void initNavBar(Bundle savedInstanceState){
        navigationView.setNavigationItemSelectedListener(this);
        updateNavHeader();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_study:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StudyFragment()).commit();
                break;
            case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void updateNavHeader(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.NameHeader);
        ImageView navSerPhoto = headerView.findViewById(R.id.ProfilePicHeader);

        UserData userData = UserData.getInstance();
        navUserName.setText(userData.getFullname());
        FirebaseHandler.loadImageIntoView(userData.getProfile(), navSerPhoto, this);

        navSerPhoto.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ProfileFragment profile = new ProfileFragment();
            fragmentTransaction.replace(R.id.fragment_container, profile);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            navigationView.getMenu().findItem(R.id.nav_home).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_study).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_map).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setChecked(false);
            drawer.closeDrawer(GravityCompat.START);
        });
        navUserName.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ProfileFragment profile = new ProfileFragment();
            fragmentTransaction.replace(R.id.fragment_container, profile);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            navigationView.getMenu().findItem(R.id.nav_home).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_study).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_map).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_about).setChecked(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setChecked(false);
            drawer.closeDrawer(GravityCompat.START);
        });
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST) {
            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Location Permission not Granted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
