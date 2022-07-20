package org.kashmirworldfoundation.WildlifeGeoSnap.study.station;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;

public class StationListViewHolder extends RecyclerView.ViewHolder{
        TextView stationId;
        TextView dateId;
        ImageView imgId;
        public StationListViewHolder(View view) {
            super(view);
            stationId=view.findViewById(R.id.rowStationId2);
            dateId=view.findViewById(R.id.rowDateId2);
            imgId=view.findViewById(R.id.rowImgId2);
        }
    }

