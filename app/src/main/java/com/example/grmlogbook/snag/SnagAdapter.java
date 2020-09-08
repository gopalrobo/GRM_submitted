package com.example.grmlogbook.snag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grmlogbook.ActivityMediaOnline;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.Mailbean;
import com.example.grmlogbook.Projectbean;
import com.example.grmlogbook.R;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.GlideApp;
import com.example.grmlogbook.maps.MediaLocationActivity;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class SnagAdapter extends RecyclerView.Adapter<SnagAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<ComplaintLetterbean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView snagdate,snagvillage,snaglocation,snagsummary;
        EditText snagResolution;
        CardView parentLinear,investImgCard;
        ImageView complaintImage,itemsImage;
        LinearLayout complaintLocation,itemsLocation;
        TextView complaintgeo,complaintDescription,geodetails,description;


        public MyViewHolder(View view){
            super((view));
            snagdate =(TextView) view.findViewById(R.id.snagdate);
            snagvillage =(TextView) view.findViewById(R.id.snagvillage);
            snaglocation =(TextView) view.findViewById(R.id.snaglocation);
            snagsummary =(TextView) view.findViewById(R.id.snagsummary);
            snagResolution =(EditText) view.findViewById(R.id.snagResolution);

            complaintImage=(ImageView)view.findViewById(R.id.complaintImage);
            itemsImage=(ImageView)view.findViewById(R.id.itemsImage);
            complaintLocation=(LinearLayout)view.findViewById(R.id.complaintLocation);
            itemsLocation=(LinearLayout)view.findViewById(R.id.itemsLocation);
            complaintgeo=(TextView)view.findViewById(R.id.complaintgeo);
            complaintDescription=(TextView)view.findViewById(R.id.complaintDescription);
            geodetails=(TextView)view.findViewById(R.id.geodetails);
            description=(TextView)view.findViewById(R.id.description);
            investImgCard=view.findViewById(R.id.investImgCard);


            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }

    public SnagAdapter(Context mainActivityUser, ArrayList<ComplaintLetterbean> moviesList) {

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
                .inflate(R.layout.snag_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ComplaintLetterbean complaintLetterbean = moviesList.get(position);
        holder.snagdate.setText(complaintLetterbean.date);

        holder.investImgCard.setVisibility(View.GONE);
        ArrayList<Mailbean> mailbeans=complaintLetterbean.mailbeans;
        StringBuilder stringBuilder=new StringBuilder();
        if(mailbeans!=null) {
            for (int k = 0; k < mailbeans.size(); k++) {
                if (mailbeans.get(k).village != null) {
                    stringBuilder.append(mailbeans.get(k).village).append(" / ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).salutation != null) {
                    stringBuilder.append(mailbeans.get(k).salutation).append(". ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).name != null) {
                    stringBuilder.append(mailbeans.get(k).name).append(" ");
                }else{
                    stringBuilder.append("").append("");
                }
                if (mailbeans.get(k).surname != null) {
                    stringBuilder.append(mailbeans.get(k).surname).append(", ");
                }


            }
        }
        holder.snagvillage.setText(stringBuilder.toString());
        if(complaintLetterbean.projectbeans!=null) {
        ArrayList<Projectbean> projectbeans=complaintLetterbean.projectbeans;
        StringBuilder stringBuilder1=new StringBuilder();
            for (int j = 0; j < projectbeans.size(); j++) {
                if (projectbeans.get(j).geotag != null) {
                    stringBuilder1.append(projectbeans.get(j).geotag).append(" , ");
                }else{
                    stringBuilder1.append("").append("");
                }
            }
        holder.snaglocation.setText(stringBuilder1.toString());

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
            holder.snagsummary.setText(stringBuilder2.toString());

            holder.complaintgeo.setText(projectbeans.get(0).geotag);
            try {
                holder.complaintgeo.setText(AppConfig.df.format(Double.parseDouble(
                        projectbeans.get(0).geotag.split(",")[0])) + "," + AppConfig.df.format(Double.parseDouble(
                        projectbeans.get(0).geotag.split(",")[1])));
            } catch (Exception e) {

            }
            if (projectbeans.get(0).detail.length() > 15) {
                holder.complaintDescription.setText(projectbeans.get(0).detail.substring(0, 14) + "...");
            } else {
                holder.complaintDescription.setText(projectbeans.get(0).detail);
            }
            try {
                GlideApp.with(mainActivityUser).load(projectbeans.get(0).getAttachment())
                        .into(holder.complaintImage);
            } catch (Exception e) {
                Toast.makeText(mainActivityUser, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            holder.complaintImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent localIntent = new Intent(mainActivityUser, ActivityMediaOnline.class);
                    localIntent.putExtra("filePath", projectbeans.get(0).getAttachment());
                    localIntent.putExtra("isImage", true);
                    if (projectbeans.get(0).getAttachment().toLowerCase().contains("mp4")) {
                        localIntent.putExtra("isImage", false);
                    }
                    mainActivityUser.startActivity(localIntent);
                }
            });
            holder.complaintLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(mainActivityUser, MediaLocationActivity.class);
                        intent.putExtra("data",complaintLetterbean);
                        mainActivityUser.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(mainActivityUser, "Invalid Geotag", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }


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
