package org.kashmirworldfoundation.WildlifeGeoSnap.study.station.rebait;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.kashmirworldfoundation.WildlifeGeoSnap.R;

import java.util.ArrayList;

// the Adapter class has 2 classes
public class RebaitAdapter extends RecyclerView.Adapter<RebaitAdapter.RebaitHolder> {

    // 1- Rebait Adapter
    private Context context;
    private ArrayList<Rebait> rebaits;

    // constructor
    public RebaitAdapter(Context context, ArrayList<Rebait> rebaits) {
        this.context = context;
        this.rebaits = rebaits;
    }

    @NonNull
    @Override
    public RebaitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_rebait_recycler,
                parent,false);

        return new RebaitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RebaitAdapter.RebaitHolder holder, int position) {

        Rebait rebait = rebaits.get(position);
        holder.SetDetails(rebait);
    }

    @Override
    public int getItemCount() {
        return rebaits.size();
    }

    // 2 - Rebait Holder
    class RebaitHolder extends RecyclerView.ViewHolder {

        private TextView txtCameraID, txtTotalPhotos, txtDate;

        public RebaitHolder(@NonNull View itemView) {
            super(itemView);
            txtCameraID = itemView.findViewById(R.id.txtCameraID);
            txtTotalPhotos = itemView.findViewById(R.id.txtTotalPhotos);
            txtDate = itemView.findViewById(R.id.txtDate);

        }

        void SetDetails (Rebait rebait) {
            txtCameraID.setText(rebait.getsdID());
            txtTotalPhotos.setText(rebait.getTotalPics());
            txtDate.setText(rebait.getDate());
        }
    }


}
