package org.kashmirworldfoundation.WildlifeGeoSnap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import org.kashmirworldfoundation.WildlifeGeoSnap.MapCreateList;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;

public class HomeFragment extends Fragment {
    private View HomeFragment;
    private Button buttonProfile;
    private Button buttonStudy;
    private Button buttonMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeFragment=inflater.inflate(R.layout.fragment_home, container, false);
        buttonProfile=HomeFragment.findViewById(R.id.buttonProfile);
        buttonStudy=HomeFragment.findViewById(R.id.buttonStudy);
        buttonMap=HomeFragment.findViewById(R.id.buttonMap);

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationView navigationView = (NavigationView) HomeFragment.findViewById(R.id.nav_view);
//
//                HomeFragment.onNavigationItemSelected(navigationView.getMenu().getItem(R.id.nav_profile));
//                navigationView.setCheckedItem(R.id.nav_profile);
//                navigationView.getMenu().getItem(1).setChecked(true);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProfileFragment profile = new ProfileFragment();
                fragmentTransaction.replace(R.id.fragment_container, profile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        buttonStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationView navigationView = (NavigationView) HomeFragment.findViewById(R.id.nav_view);
//                navigationView.setCheckedItem(R.id.nav_add);
//                navigationView.getMenu().getItem(3).setChecked(true);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AddFragment study = new AddFragment();
                fragmentTransaction.replace(R.id.fragment_container, study);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationView navigationView = (NavigationView) HomeFragment.findViewById(R.id.nav_view);
//                navigationView.setCheckedItem(R.id.nav_map);
//                navigationView.getMenu().getItem(5).setChecked(true);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MapFragment map = new MapFragment();
                fragmentTransaction.replace(R.id.fragment_container, map);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return HomeFragment;
    }

}