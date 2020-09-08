package com.example.grmlogbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grmlogbook.app.AppConfig;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class ProjectMasterAdapter extends RecyclerView.Adapter<ProjectMasterAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Projectmasterbean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView projectNumber, country, implemntingAgency;
        TextView editbtn, deletebtn;
        ImageView qrcode;
        RecyclerView recycler_view_media;

        public MyViewHolder(View view) {
            super(view);
            projectNumber = (TextView) view.findViewById(R.id.projectNumber);
            country = (TextView) view.findViewById(R.id.country);
            implemntingAgency = (TextView) view.findViewById(R.id.implemntingAgency);
            qrcode = (ImageView) view.findViewById(R.id.qrcode);


            editbtn = (TextView) view.findViewById(R.id.editbtn);
            deletebtn = (TextView) view.findViewById(R.id.deletebtn);

        }
    }


    public ProjectMasterAdapter(Context mainActivityUser, ArrayList<Projectmasterbean> moviesList) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;

    }

    public void notifyData(ArrayList<Projectmasterbean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;

        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.survey_projectlist_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Projectmasterbean projectmasterbean = moviesList.get(position);

        if (projectmasterbean.name != null
                && projectmasterbean.name.length() > 0) {
            Bitmap QRBit = AppConfig.printQRCode(projectmasterbean.getProjectNumber() + "@" +
                    projectmasterbean.getName());
            holder.qrcode.setImageBitmap(QRBit);
        } else {
            Bitmap QRBit = AppConfig.printQRCode(projectmasterbean.getProjectNumber() + "@" +
                    "name");
            holder.qrcode.setImageBitmap(QRBit);
        }

        holder.projectNumber.setText(projectmasterbean.getProjectNumber());
        holder.country.setText(projectmasterbean.getCountry());
        holder.implemntingAgency.setText(projectmasterbean.getImplemntingAgency());

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityUser, ProjectMaster.class);
                intent.putExtra("object", moviesList.get(position));
                mainActivityUser.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return String.valueOf((double) tmp / factor);
    }

}
