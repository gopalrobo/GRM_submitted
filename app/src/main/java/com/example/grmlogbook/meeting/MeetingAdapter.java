package com.example.grmlogbook.meeting;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.Mailbean;
import com.example.grmlogbook.Projectbean;
import com.example.grmlogbook.R;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<ComplaintLetterbean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView complaintName,location,complaintNature;
        EditText resolution,grgDecision;
        CardView parentLinear;



        public MyViewHolder(View view){
            super((view));
            complaintName =(TextView) view.findViewById(R.id.complaintName);
            complaintNature =(TextView) view.findViewById(R.id.complaintNature);
            location =(TextView) view.findViewById(R.id.location);
            resolution =(EditText) view.findViewById(R.id.resolution);
            grgDecision =(EditText) view.findViewById(R.id.grgDecision);


            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }



    public MeetingAdapter(Context mainActivityUser, ArrayList<ComplaintLetterbean> moviesList) {

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
                .inflate(R.layout.protocol_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ComplaintLetterbean complaintLetterbean = moviesList.get(position);
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
            }
        }
        holder.complaintName.setText(stringBuilder.toString());

        if(mailbeans!=null) {
            for (int k = 0; k < mailbeans.size(); k++) {
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
        holder.location.setText(stringBuilder.toString());

        ArrayList<Projectbean> projectbeans=complaintLetterbean.projectbeans;
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
        holder.complaintNature.setText(stringBuilder2.toString());


//      holder.dpSign.setText(complaintLetterbean.dpSign);



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
