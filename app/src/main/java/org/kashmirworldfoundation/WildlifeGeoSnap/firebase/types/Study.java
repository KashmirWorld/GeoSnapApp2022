package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.kashmirworldfoundation.WildlifeGeoSnap.MainActivity;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;

public class Study implements Parcelable {

    private static ArrayList<Study> studies;

    String title;
    String org;
    String mission;
    String  location;
    Timestamp start;
    Timestamp end;

    public Study(String title, String org, String mission, String location, Timestamp start, Timestamp end){
        this.title = title;
        this.org = org;
        this.mission = mission;
        this.location = location;
        this.start = start;
        this.end = end;
    }

    public Study(Parcel in) {
        title = in.readString();
        org = in.readString();
        mission = in.readString();
        location = in.readString();
        start=in.readParcelable(getClass().getClassLoader());
        end=in.readParcelable(getClass().getClassLoader());
    }
    public  Study(){
    }

    public static ArrayList<Study> getStudies(){
        return studies;
    }

    public static ArrayList<String> getStudiesAsString() {
        ArrayList<String> studyTitles = new ArrayList<String>();
        for(Study study: studies){
            studyTitles.add(study.getTitle());
        }
        return studyTitles;
    }

    public static ArrayList<String> getStudiesAsStringWithNull(){
        ArrayList<String> studiesList = getStudiesAsString();
        if(studiesList.size() == 0){
            studiesList.add(0, "No Studies");
        }else{
            studiesList.add(0, "Pick A Study");
        }
        return studiesList;
    }

    public static void resetStudies(){
        studies = new ArrayList<Study>();
    }

    public static void loadStudies(final Utils.LambdaInterface onLoad){
        studies = new ArrayList<Study>();
        Member member = Member.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        fStore.collection("Study").whereEqualTo("org",member.getOrg()).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                    Study study = documentSnapshot.toObject(Study.class);
                    studies.add(study);
                }
            }
            onLoad.run();
        });
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
        save(null);
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
        save(null);
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
        save(null);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        save(null);
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
        save(null);
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
        save(null);
    }

    // WE don't actually need to save the study!
    public void save(final Utils.LambdaInterface runner){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(org);
        dest.writeString(location);
        dest.writeString(mission);
        dest.writeParcelable(start,0);
        dest.writeParcelable(end,0);

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Study createFromParcel(Parcel in) {
            return new Study(in);
        }

        public Study[] newArray(int size) {
            return new Study[size];
        }
    };


}
