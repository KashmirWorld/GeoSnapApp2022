package org.kashmirworldfoundation.WildlifeGeoSnap.projects;

import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects.Project;

public class ProjectModel {

    public String uuid;
    public String name;
    public String membersCount;


    public ProjectModel(String uuid, String name, String membersCount) {
        this.uuid = uuid;
        this.name = name;
        this.membersCount = membersCount;
    }

    public static ProjectModel fromProject(Project project){
        return new ProjectModel(project.getUuid(), project.getName(), Integer.toString(project.getMembers().size()));
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getMembersCount() {
        return membersCount;
    }
}
