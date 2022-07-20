package org.kashmirworldfoundation.WildlifeGeoSnap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import org.kashmirworldfoundation.WildlifeGeoSnap.maps.MapFragment;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Fragment;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.permissions.PermissionManager;
import org.kashmirworldfoundation.WildlifeGeoSnap.profile.ProfileFragment;
import org.kashmirworldfoundation.WildlifeGeoSnap.study.StudyFragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View HomeFragment;
    private Button buttonProfile;
    private Button buttonStudy;
    private Button buttonMap;
    NavigationView navigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeFragment = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();
        initListeners();
        return HomeFragment;
    }

    public void initViews() {
        buttonProfile = HomeFragment.findViewById(R.id.buttonProfile);
        buttonStudy = HomeFragment.findViewById(R.id.buttonStudy);
        buttonMap = HomeFragment.findViewById(R.id.buttonMap);
        navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
    }

    private void initListeners(){
        buttonProfile.setOnClickListener(this);
        buttonStudy.setOnClickListener(this);
        buttonMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonProfile:
                onClickProfile(v);
                return;
            case R.id.buttonStudy:
                onClickStudy(v);
                return;
            case R.id.buttonMap:
                onClickMap(v);
        }
    }

    public void onClickProfile(View view) {
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(false);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProfileFragment profile = new ProfileFragment();
        fragmentTransaction.replace(R.id.fragment_container, profile);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onClickStudy(View view) {
        navigationView.getMenu().findItem(R.id.nav_study).setChecked(true);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StudyFragment study = new StudyFragment();
        fragmentTransaction.replace(R.id.fragment_container, study);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onClickMap(View v) {
        if (!PermissionManager.getInstance().hasLocationPermission(getActivity())) {
            Toast.makeText(getContext(), "Location Permission needed!", Toast.LENGTH_SHORT).show();
            return;
        }
        navigationView.getMenu().findItem(R.id.nav_map).setChecked(true);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapFragment map = new MapFragment();
        fragmentTransaction.replace(R.id.fragment_container, map);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}