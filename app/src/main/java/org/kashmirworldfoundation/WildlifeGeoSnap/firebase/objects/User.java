package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserData;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.UserPendingTransferRequest;
import org.kashmirworldfoundation.WildlifeGeoSnap.utils.Utils;

import java.util.ArrayList;

public class User {

    private static final String COLLECTION_USER = "User";

    private static User instance;

    private UserData data;
    private String uuid;

    public User (String uuid, UserData data){
        this.uuid = uuid;
        this.data = data;
    }

    public static User getInstance(){
        return instance;
    }

    public static void loadInstance(String uuid, UserData data){
        instance = new User(uuid, data);
    }

    public static void resetInstance(){
        if (User.getInstance() != null){
            User.getInstance().save(() -> {
                instance = null;
            });
        }
    }

    public String getUuid(){
        return uuid;
    }
    public UserData getUserData() {return data;}

    public String getName() {
        return data.name;
    }

    public void setName(String name) {
        data.name = name;
        save(null);
    }

    public String getEmail() {
        return data.email;
    }

    public void setEmail(String email) {
        data.email = email;
        save(null);
    }

    public boolean isDisabled() {
        return data.disabled;
    }

    public void setDisabled(boolean disabled) {
        data.disabled = disabled;
        save(null);
    }

    public String getProfile() {
        return "profiles/" + data.profile;
    }

    public void setProfile(String profile) {
        data.profile = profile;
        save(null);
    }

    public ArrayList<String> getProjects() {
        return data.projects;
    }

    public void addProject(String projectUUID) {
        //TODO: locate saving to its own file
        data.projects.add(projectUUID);
        save(null);
    }

    public void removeProject(String projectUUID){
        data.projects.remove(projectUUID);
        save(null);
    }

    public ArrayList<UserPendingTransferRequest> getPendingTransferRequests() {
        return data.pendingTransferRequests;
    }

    public void addPendingTransferRequest(UserPendingTransferRequest transferRequest){
        data.pendingTransferRequests.add(transferRequest);
        save(null);
    }

    public void removePendingTransferRequest(String projectUUID){
        for(int i = 0; i < data.pendingTransferRequests.size(); i++){
            UserPendingTransferRequest request = data.pendingTransferRequests.get(i);
            if (request.getProjectID().equals(projectUUID)){
                data.pendingTransferRequests.remove(i);
                save(null);
                return;
            }
        }

    }

    public void save(Utils.LambdaInterface onSave) {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        fStore.collection(COLLECTION_USER).document(getUuid()).set(data).addOnCompleteListener(task -> {
            if(task.isSuccessful() && onSave != null){
                onSave.run();
            }
        });
    }
}
