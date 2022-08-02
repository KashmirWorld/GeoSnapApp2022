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

public class ProjectJoinOverlay extends BottomSheetDialog {

    private ProjectsActivity activity;

    private EditText codeView;
    private TextView cancelView, submitView;
    private Button joinView;

    public ProjectJoinOverlay(ProjectsActivity activity) {
        super(activity, R.style.BottomSheetStyle);
        this.activity = activity;
        this.setContentView(R.layout.project_join_overlay);
        this.setCanceledOnTouchOutside(true);
        this.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
        // initialize the views
        initViews();
        initListeners();
    }

    public void initViews() {
        codeView = findViewById(R.id.projects_join_enter_code);
        cancelView = findViewById(R.id.project_join_cancel);
        submitView = findViewById(R.id.project_join_submit_top);
        joinView = findViewById(R.id.project_join_button);
    }

    public void initListeners() {
        submitView.setOnClickListener(text -> {
            onJoin();
        });
        joinView.setOnClickListener(text ->{
            onJoin();
        });
        cancelView.setOnClickListener(text -> {
            this.cancel();
        });
    }

    // onJoin handles the submition of the BottomSheetDialog for creating a project. Handles clicks
    // for both Submit and Create views.
    public void onJoin(){
        String code = codeView.getText().toString().trim();
        if (code.isEmpty()){
            codeView.setError("Join code is required.");
            return;
        }
        //TODO: Implement join behaviour
        this.dismiss();
    }
}
