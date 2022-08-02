package org.kashmirworldfoundation.WildlifeGeoSnap.projects.overlays;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects.Project;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects.User;
import org.kashmirworldfoundation.WildlifeGeoSnap.projects.ProjectsActivity;

public class ProjectCreateOverlay extends BottomSheetDialog {

    private ProjectsActivity activity;

    private EditText nameView;
    private TextView cancelView, submitView;
    private Button createView;

    public ProjectCreateOverlay(ProjectsActivity activity) {
        super(activity, R.style.BottomSheetStyle);
        this.activity = activity;
        this.setContentView(R.layout.project_create_overlay);
        this.setCanceledOnTouchOutside(true);
        this.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        // initialize the views
        initViews();
        initListeners();
    }

    public void initViews() {
        nameView = findViewById(R.id.projects_create_enter_name);
        cancelView = findViewById(R.id.project_create_cancel);
        submitView = findViewById(R.id.project_create_submit_top);
        createView = findViewById(R.id.project_create_button);
    }

    public void initListeners() {
        submitView.setOnClickListener(text -> {
            onSubmit();
        });
        createView.setOnClickListener(text ->{
            onSubmit();
        });
        cancelView.setOnClickListener(text -> {
            this.cancel();
        });
    }

    // onSubmit handles the submition of the BottomSheetDialog for creating a project. Handles clicks
    // for both Submit and Create views.
    public void onSubmit(){
        String projectName = nameView.getText().toString().trim();
        if (projectName.isEmpty()){
            nameView.setError("Project name is required.");
            return;
        }
        Project.createProject(projectName, User.getInstance().getUuid(), project -> {
            activity.addProject(project);
            User.getInstance().addProject(project.getUuid());
            this.dismiss();
        }, () -> {
            Toast.makeText(activity, "Error creating project", Toast.LENGTH_SHORT).show();
            this.dismiss();
        });
    }
}
