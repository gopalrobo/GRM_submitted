package com.example.grmlogbook.logbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.Mailbean;
import com.example.grmlogbook.Projectbean;
import com.example.grmlogbook.R;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class LogbookAdapter extends RecyclerView.Adapter<LogbookAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<ComplaintLetterbean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView grievanceNumber,date,apdetail,type,location,summary,photo;
        CardView parentLinear;



        public MyViewHolder(View view){
            super((view));
            grievanceNumber =(TextView) view.findViewById(R.id.grievanceNumber);
            date =(TextView) view.findViewById(R.id.date);
            apdetail =(TextView) view.findViewById(R.id.apDetail);
            type =(TextView) view.findViewById(R.id.type);
            location =(TextView) view.findViewById(R.id.location);
            summary =(TextView) view.findViewById(R.id.summary);
            photo =(TextView) view.findViewById(R.id.photo);


            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }



    public LogbookAdapter(Context mainActivityUser, ArrayList<ComplaintLetterbean> moviesList) {

        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;

    }



    public void notifyData(ArrayList<ComplaintLetterbean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loglist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ComplaintLetterbean complaintLetterbean = moviesList.get(position);
        holder.grievanceNumber.setText(complaintLetterbean.projectname+"\n"+complaintLetterbean.date);

        ArrayList<Mailbean> mailbeans=complaintLetterbean.mailbeans;
        StringBuilder stringBuilder=new StringBuilder();
        if(mailbeans!=null) {
            for (int k = 0; k < mailbeans.size(); k++) {
                if (mailbeans.get(k).salutation != null) {
                    stringBuilder.append(mailbeans.get(k).salutation).append(". ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).name != null) {
                    stringBuilder.append(mailbeans.get(k).name).append(", ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).surname != null) {
                    stringBuilder.append(mailbeans.get(k).surname).append(", ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).doornumber != null) {
                    stringBuilder.append(mailbeans.get(k).doornumber).append(", ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).village != null) {
                    stringBuilder.append(mailbeans.get(k).village).append(", ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).commune != null) {
                    stringBuilder.append(mailbeans.get(k).commune).append(", ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).district != null) {
                    stringBuilder.append(mailbeans.get(k).district).append(", ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).Province != null) {
                    stringBuilder.append(mailbeans.get(k).Province).append(". ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).mobile != null) {
                    stringBuilder.append(mailbeans.get(k).mobile).append(" . ");
                }else{
                    stringBuilder.append("").append("");
                }
            }
        }
        holder.apdetail.setText(stringBuilder.toString());

        ArrayList<Projectbean> projectbeans=complaintLetterbean.projectbeans;
        StringBuilder stringBuilder1=new StringBuilder();
        if(projectbeans!=null) {
            for (int j = 0; j < projectbeans.size(); j++) {
                if (projectbeans.get(j).geotag != null) {
                    stringBuilder1.append(projectbeans.get(j).geotag).append(" , ");
                }else{
                    stringBuilder1.append("").append("");
                }
            }
        }
        holder.location.setText(stringBuilder1.toString());



        StringBuilder stringBuilder2=new StringBuilder();
        if(projectbeans!=null) {
            for (int j = 0; j < projectbeans.size(); j++) {
                if (projectbeans.get(j).detail != null) {
                    stringBuilder2.append(projectbeans.get(j).detail).append(" , ");
                }else{
                    stringBuilder2.append("").append("");
                }
            }
        }
        holder.summary.setText(stringBuilder2.toString());


//      holder.dpSign.setText(complaintLetterbean.dpSign);

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityUser, LogbookActivity.class);
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
