package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ProjectData {

    public String name;
    public String adminId;
    public String joinCode;
    public ArrayList<String> members;

    // Project (REGISTER ORG HANDLER)
    public ProjectData(String name, String adminId, String joinCode, ArrayList<String> members) {
        this.name = name;
        this.adminId = adminId;
        this.joinCode = joinCode;
        this.members = members;
    }

    public ProjectData() {
    }
}
