package org.kashmirworldfoundation.WildlifeGeoSnap.study.study;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.kashmirworldfoundation.WildlifeGeoSnap.GlideApp;
import org.kashmirworldfoundation.WildlifeGeoSnap.R;
import org.kashmirworldfoundation.WildlifeGeoSnap.firebase.types.Study;


import java.util.ArrayList;


public class StudyListFragmentAdapter extends RecyclerView.Adapter<StudyListFragmentViewholder> {
    StudyListFragment studyListFragment;


    ArrayList<Study> CstationList;
    Study Cstation;
    StudyListFragmentAdapter(ArrayList<Study> stationList, StudyListFragment studyListFragment){
        this.studyListFragment = studyListFragment;
        this.CstationList=stationList;

    }

    @NonNull
    @Override
    public StudyListFragmentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext())
                //pass the list_row_item back to itemView
                .inflate(R.layout.row_station,parent,false);

        //wait to open browser
        itemView.setOnClickListener(studyListFragment);
        //and pass the entry
        return new StudyListFragmentViewholder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull StudyListFragmentViewholder holder, int position) {
        Cstation=CstationList.get(position);

        holder.Study.setText(Cstation.getTitle());
        holder.dateId.setText(Cstation.getEnd().toDate().toString());
        holder.imgId.setImageResource(R.drawable.kwflogo);


    }

    private void fetchData(String location, ImageView image) {
        StorageReference ref = FirebaseStorage.getInstance().getReference(location);
        GlideApp.with(studyListFragment)
                .load(ref)
                .into(image);
    }

    @Override
    public int getItemCount() {
        return CstationList.size();
    }

}
