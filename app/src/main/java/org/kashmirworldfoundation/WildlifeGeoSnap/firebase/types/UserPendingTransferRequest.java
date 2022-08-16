package org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types;

public class UserPendingTransferRequest {
    private String project;
    private String requester;

    public UserPendingTransferRequest(String project, String requester) {
        this.project = project;
        this.requester = requester;
    }

    public UserPendingTransferRequest(){

    }

    public String getProjectID() {
        return project;
    }

    public void setProjectID(String project) {
        this.project = project;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }
}
