package org.kashmirworldfoundation.WildlifeGeoSnap.projects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;

import java.util.ArrayList;
import java.util.List;

public class Projects_RecyclerViewAdapter extends RecyclerView.Adapter<Projects_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProjectModel> projectModels;

    public Projects_RecyclerViewAdapter(Context context, ArrayList<ProjectModel> projectModels){
        this.context = context;
        this.projectModels = projectModels;
    }

    public void setFilteredList(ArrayList<ProjectModel> filteredList){
        this.projectModels = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Projects_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.projects_list_row, parent, false);

        return new Projects_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Projects_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.projectName.setText(projectModels.get(position).getName());
        holder.memberCount.setText(projectModels.get(position).getMembersCount());
    }

    @Override
    public int getItemCount() {
        return projectModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView projectName, memberCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.project_list_row_name);
            memberCount = itemView.findViewById(R.id.projects_list_row_member_count);
        }
    }
}
