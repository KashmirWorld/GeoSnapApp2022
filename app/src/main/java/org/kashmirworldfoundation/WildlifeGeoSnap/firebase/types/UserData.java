package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

import java.util.ArrayList;

public class UserData {

    public String name;
    public String email;
    public boolean disabled;
    public String profile;
    public ArrayList<String> projects;
    public ArrayList<UserPendingTransferRequest> pendingTransferRequests;

    public UserData(){

    }

    public UserData(String name, String email, boolean disabled, String profile, ArrayList<String> projects, ArrayList<UserPendingTransferRequest> pendingTransferRequests){
        this.name = name;
        this.email = email;
        this.disabled = disabled;
        this.profile = profile;
        this.projects = projects;
        this.pendingTransferRequests = pendingTransferRequests;
    }
}
