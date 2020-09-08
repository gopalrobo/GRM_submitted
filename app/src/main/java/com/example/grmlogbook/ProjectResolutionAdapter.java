package com.example.grmlogbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.GlideApp;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectResolutionAdapter extends RecyclerView.Adapter<ProjectResolutionAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Projectbean> projectbeans;
    private OnResolution onResolution;
    private OnItemClick onItemClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CardView itemsCard, itemsAdd, itemsReportCard;
        private final ImageView itemsImage, itemsReportImage;
        private final TextView geodetails, description, geodetailsReport, descriptionReport,resolution;
        Button resolutionEdit;

        public MyViewHolder(View view) {
            super((view));
            itemsAdd = (CardView) view.findViewById(R.id.itemsAdd);
            itemsCard = (CardView) view.findViewById(R.id.itemsCard);
            itemsImage = (ImageView) view.findViewById(R.id.itemsImage);
            geodetails = (TextView) view.findViewById(R.id.geodetails);
            description = (TextView) view.findViewById(R.id.description);
            itemsReportCard = (CardView) view.findViewById(R.id.itemsReportCard);
            itemsReportImage = (ImageView) view.findViewById(R.id.itemsReportImage);
            resolutionEdit = view.findViewById(R.id.resolutionEdit);
            resolution = view.findViewById(R.id.resolution);
            geodetailsReport = (TextView) view.findViewById(R.id.geodetailsReport);
            descriptionReport = (TextView) view.findViewById(R.id.descriptionReport);

        }
    }

    public ProjectResolutionAdapter(Context mainActivityUser, ArrayList<Projectbean> projectbeans,
                                    OnResolution onResolution,OnItemClick onItemClick) {
        this.mainActivityUser = mainActivityUser;
        this.projectbeans = projectbeans;
        this.onResolution = onResolution;
        this.onItemClick=onItemClick;
    }

    public void notifyData(ArrayList<Projectbean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.projectbeans = myList;
        notifyDataSetChanged();
    }

    public ProjectResolutionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projectdetail_row_resolution, parent, false);

        return new ProjectResolutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Projectbean projectbean = projectbeans.get(position);
        holder.itemsCard.setVisibility(View.VISIBLE);
        holder.itemsAdd.setVisibility(View.GONE);
        holder.geodetails.setText(projectbean.geotag);
        try {
            holder.geodetails.setText(AppConfig.df.format(Double.parseDouble(
                    projectbean.geotag.split(",")[0])) + "," + AppConfig.df.format(Double.parseDouble(
                    projectbean.geotag.split(",")[1])));
        } catch (Exception e) {

        }
        try {
            holder.geodetailsReport.setText(AppConfig.df.format(Double.parseDouble(
                    projectbean.geotagReport.split(",")[0])) + "," + AppConfig.df.format(Double.parseDouble(
                    projectbean.geotagReport.split(",")[1])));
        } catch (Exception e) {

        }
        if (projectbean.detail.length() > 15) {
            holder.description.setText(projectbean.detail.substring(0, 14) + "...");
        } else {
            holder.description.setText(projectbean.detail);
        }
        if (projectbean.resolution != null) {
            holder.resolution.setText(projectbean.resolution);
        } else {
            holder.resolution.setText("");
        }

        if (projectbean.detailReport != null) {
            if (projectbean.detailReport.length() > 15) {
                holder.descriptionReport.setText(projectbean.detailReport.substring(0, 14) + "...");
            } else {
                holder.descriptionReport.setText(projectbean.detailReport);
            }
        } else {
            holder.descriptionReport.setText("");
        }

        try {
            GlideApp.with(mainActivityUser).load(projectbean.getAttachment())
                    .into(holder.itemsImage);
        } catch (Exception e) {
            Toast.makeText(mainActivityUser, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        if (projectbean.attachmentReport != null && projectbean.attachmentReport.length() > 0) {
            holder.itemsAdd.setVisibility(View.GONE);
            holder.itemsReportCard.setVisibility(View.VISIBLE);
            try {
                GlideApp.with(mainActivityUser).load(projectbean.getAttachmentReport())
                        .into(holder.itemsReportImage);
            } catch (Exception e) {
                Toast.makeText(mainActivityUser, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else {
            holder.itemsAdd.setVisibility(View.VISIBLE);
            holder.itemsReportCard.setVisibility(View.GONE);
        }

        holder.resolutionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResolution.onEditClick(position);
            }
        });



        holder.itemsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemProjectReportDetailClick(position);
            }
        });

        holder.itemsReportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemProjectReportDetailClick(position);
            }
        });


        holder.itemsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemProjectDetailClick(position);
            }
        });


    }

    public int getItemCount() {
        return projectbeans.size();
    }

}


