package org.kashmirworldfoundation.WildlifeGeoSnap.projects;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.FirebaseHandler;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects.Project;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.objects.User;
import org.kashmirworldfoundation.WildlifeGeoSnap.misc.Activity;
import org.kashmirworldfoundation.WildlifeGeoSnap.projects.overlays.ProjectCreateOverlay;
import org.kashmirworldfoundation.WildlifeGeoSnap.projects.overlays.ProjectJoinOverlay;

import java.util.ArrayList;
import java.util.List;

public class ProjectsActivity extends Activity {

    private ArrayList<ProjectModel> projectModels = new ArrayList<>();
    private Projects_RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private TextView nameView;
    private ImageView profileView;
    private SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        initViews();
        loadViewData();
        initSearchView();
        initAdapterList();
    }

    @Override
    protected void initViews() {
        recyclerView = findViewById(R.id.project_list_recycler_view);
        nameView = findViewById(R.id.projects_name);
        profileView = findViewById(R.id.projects_profile_picture);
        searchView = findViewById(R.id.projects_search);
    }


    // initSearchViews initializes the search view for searching the listed Projects.
    private void initSearchView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });
    }

    // onClickCreate handles the clicking of the Create button, creating a BottomSheet and displaying it
    public void onClickCreate(View v){
        ProjectCreateOverlay overlay = new ProjectCreateOverlay(this);
        overlay.show();
    }


    public void onClickJoin(View v){
        ProjectJoinOverlay overlay = new ProjectJoinOverlay(this);
        overlay.show();
    }

    private void loadViewData(){
        searchView.clearFocus();
        FirebaseHandler.loadImageIntoView("profile/" + User.getInstance().getProfile(), profileView, this);
        nameView.setText(User.getInstance().getName());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initAdapterList(){
        adapter = new Projects_RecyclerViewAdapter(this, projectModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadProjects();
    }

    // loadProjects loads every project onto the Recycle View Adapater to be displayed to the user
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadProjects(){
        for (String projectUuid : User.getInstance().getProjects()){
            Project.loadProject(projectUuid, (project) -> {
                boolean alreadyExists = projectModels.stream().anyMatch(o -> project.getUuid().equals(o.getUuid()));
                if (alreadyExists){
                    return;
                }
                projectModels.add(ProjectModel.fromProject(project));
                adapter.notifyItemInserted(projectModels.size()-1);
            }, () -> {

            });
        }
    }

    // addProject adds a project to the adapter and updates the recycle view
    public void addProject(Project project){
        projectModels.add(0,ProjectModel.fromProject(project));
        adapter.notifyItemInserted(0);
    }

    // filterList filters the adapter from the SearchView text
    private void filterList(String text){
        ArrayList<ProjectModel> filterdList = new ArrayList<>();
        for(ProjectModel model: projectModels){
            if (model.getName().toLowerCase().contains(text.toLowerCase())){
                filterdList.add(model);
            }
        }
        if (filterdList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        adapter.setFilteredList(filterdList);
    }
}