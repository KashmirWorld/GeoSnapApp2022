package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Org {

    public interface onOrgLoad { void loadSuccess(String path);}

    private static Org instance;
    private String OrgEmail;
    private String OrgWebsite;
    private String OrgName;
    private String OrgPhone;
    private String OrgCountry;
    public Org(String orgEmail, String orgWebsite, String orgName, String orgPhone, String orgCountry) {
        OrgEmail = orgEmail;
        OrgWebsite = orgWebsite;
        OrgName = orgName;
        OrgPhone = orgPhone;
        OrgCountry = orgCountry;
        //OrgRegion = orgRegion;
    }

    public Org() {

    }

    public static Org getInstance() {
        return instance;
    }

    public static void setInstance(Org org) {
        instance = org;
    }

    public String getOrgEmail() {
        return OrgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        OrgEmail = orgEmail;
    }

    public String getOrgWebsite() {
        return OrgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        OrgWebsite = orgWebsite;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public String getOrgPhone() {
        return OrgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        OrgPhone = orgPhone;
    }

    public String getOrgCountry() {
        return OrgCountry;
    }

    public void setOrgCountry(String orgCountry) {
        OrgCountry = orgCountry;
    }

    public static void loadOrgIntoInstance(String org, String country, final onOrgLoad onSuccess) {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        fStore.collection("Organization").whereEqualTo("orgName", org).
                whereEqualTo("orgCountry", country).get().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        String orgPath = "";
                        for (QueryDocumentSnapshot documentSnapshot : task1.getResult()) {
                            orgPath = documentSnapshot.getReference().getPath();
                            instance = documentSnapshot.toObject(Org.class);
                        }
                        onSuccess.loadSuccess(orgPath);
                    }
                });
    }


    /*
    public String getOrgRegion() {
        return OrgRegion;
    }

    public void setOrgRegion(String orgRegion) {
        OrgRegion = orgRegion;
    }
    */
}
