package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.auth.user.register.RegisterOrgAdminActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.SharedPreferenceUtil;

public class Member {

    public interface memberSave { public void onMemberSave();}

    private static Member instance;

    private String Email;
    private String Fullname;
    private String Job;
    private String Phone;
    private Boolean Admin;
    private String Org;
    private String Profile;

    public Member(){

    }

    public Member(String email, String name, String job, String phone, Boolean admin, String org, String profile) {
        Email = email;
        Fullname = name;
        Job = job;
        Phone = phone;
        Admin = admin;
        Org = org;
        Profile = profile;
    }

    public static Member getInstance() {
        return instance;
    }

    /**
     * This method should only called within the LoginHandler when the variable is initally assigned
     *
     * @param member
     */
    public static void setInstance(Member member) {
        instance = member;
      //  instance.initSnapshotListener();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
        save(null);
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String name) {
        Fullname = name;
        save(null);
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
        save(null);
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
        save(null);
    }

    public void setAdmin(Boolean admin) {
        Admin = admin;
        save(null);
    }

    public Boolean isAdmin() {
        return Admin;
    }

    public String getOrg() {
        return Org;
    }

    public void setOrg(String org) {
        Org = org;
        save(null);
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String profile) {
        Profile = profile;
        save(null);
    }

    public void save(final memberSave onSave) {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        fStore.collection("Member").document(uid).set(this).addOnCompleteListener(task -> {
            if(task.isSuccessful() && onSave != null){
                onSave.onMemberSave();
            }
        });
    }
}
