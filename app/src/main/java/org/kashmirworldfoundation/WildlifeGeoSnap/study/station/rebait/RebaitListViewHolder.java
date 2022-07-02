package org.kashmirworldfoundation.WildlifeGeoSnap.study.station.rebait;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;

public class RebaitListViewHolder extends RecyclerView.ViewHolder{

    TextView Name,Notes,Sd,Works,Bait,Date, Pics;


    public RebaitListViewHolder(@NonNull View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.rowEname);
        Notes=itemView.findViewById(R.id.rowComments);
        Sd=itemView.findViewById(R.id.rowSDnum);
        Works=itemView.findViewById(R.id.rowWorks);
        Bait=itemView.findViewById(R.id.rowBait);
        Date=itemView.findViewById(R.id.rowDateId);
        Pics=itemView.findViewById(R.id.rowNumPics);
    }
}
