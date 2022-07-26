package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.ProjectData;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

import java.util.ArrayList;

public class Project {

    private static final String COLLECTION_PROJECT = "Project";

    public interface onProjectLoadSuccess { void loadSuccess(Project project);}


    //TODO: Add storage folder as proejct UUID
    private String uuid;
    private ProjectData data;

    public Project(String uuid, ProjectData data){
        this.uuid = uuid;
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName(){
        return data.name;
    }

    public void setName(String name){
        data.name = name;
        save(null);
    }

    public String getAdminId(){
        return data.adminId;
    }

    public void setAdminId(String adminId){
        data.adminId = adminId;
        save(null);
    }

    public String getJoinCode(){
        return data.joinCode;
    }

    public void setJoinCode(String joinCode){
        data.joinCode = joinCode;
        save(null);
    }

    public ArrayList<String> getMembers(){
        return data.members;
    }

    public void addMember(String uuid){
        if (!data.members.contains(uuid)){
            data.members.add(uuid);
        }
    }

    public void removeMember(String uuid){
        data.members.remove(uuid);
    }

    public static void createProject(String name, String adminId, final onProjectLoadSuccess onSuccess, final Utils.LambdaInterface onFail) {
        //TODO: implement proper join codes
        ArrayList<String> members = new ArrayList<>();
        members.add(adminId);
        ProjectData projectData = new ProjectData(name, adminId, "Testcode", members);
        FirebaseFirestore.getInstance().collection(COLLECTION_PROJECT).add(projectData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Project pj = new Project(task.getResult().getId(), projectData);
                onSuccess.loadSuccess(pj);
            } else {
                onFail.run();
            }
        });
    }
    public static void loadProject(String projectUUID, final onProjectLoadSuccess onSuccess, final Utils.LambdaInterface onFail){
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        fStore.collection(COLLECTION_PROJECT).document(projectUUID).get().addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               ProjectData projectData = task.getResult().toObject(ProjectData.class);
               assert projectData != null;
               Project pj = new Project(projectUUID, projectData);
               onSuccess.loadSuccess(pj);
           }else{
               onFail.run();
           }
        });
    }

    public void save(Utils.LambdaInterface onSave) {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        fStore.collection(COLLECTION_PROJECT).document(getUuid()).set(data).addOnCompleteListener(task -> {
            if(task.isSuccessful() && onSave != null){
                onSave.run();
            }
        });
    }
}
