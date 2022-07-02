package org.kashmirworldfoundation.WildlifeGeoSnap.study.study;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;

public class StudyListFragmentViewholder extends RecyclerView.ViewHolder {

    TextView Study;
    TextView dateId;
    ImageView imgId;

    public StudyListFragmentViewholder(View view) {
        super(view);
        Study=view.findViewById(R.id.rowStationId2);
        dateId=view.findViewById(R.id.rowDateId2);
        imgId=view.findViewById(R.id.rowImgId2);

    }
}
