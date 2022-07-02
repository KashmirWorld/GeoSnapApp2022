package org.kashmirworldfoundation.WildlifeGeoSnap.study.station.rebait;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;

import java.util.ArrayList;

public class RebaitListAdapter extends RecyclerView.Adapter<RebaitListViewHolder> {

    RebaitListActivity activity;
    //final public ListItemClickListener mOnClickListener;

    ArrayList<Rebait> CrebaitList;
    Context mContext;
    Rebait Crebait;
    RebaitListAdapter(ArrayList<Rebait> RebaitList, RebaitListActivity activity){
        this.activity=activity;
        this.CrebaitList=RebaitList;

    }

    @NonNull
    @Override
    public RebaitListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext())
                //pass the list_row_item back to itemView
                .inflate(R.layout.row_rebait,parent,false);
        Log.e("layout", "inflated");
        //wait to open browser

        //and pass the entry
        return new RebaitListViewHolder(itemView);

    }


    public void onBindViewHolder(@NonNull RebaitListViewHolder holder, int position) {
        Crebait=CrebaitList.get(position);

        holder.Name.setText(activity.AuthorS);
        holder.Date.setText(Crebait.getCurrentTime().toString());
        holder.Works.setText(Crebait.getCamWorks());
        holder.Bait.setText(Crebait.getLureType());
        holder.Sd.setText(Crebait.getSdNum());
        holder.Pics.setText(Crebait.getTotalPics());
        holder.Notes.setText("");

    }





    public int getItemCount() {
        return CrebaitList.size();
    }

}
