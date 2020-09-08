package com.example.grmlogbook.ComplaintLetter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.grmlogbook.ActivityMediaOnline;
import com.example.grmlogbook.Projectbean;
import com.example.grmlogbook.R;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.GlideApp;
import com.example.grmlogbook.maps.MediaLocationActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.grmlogbook.app.CompliantPdfConfig.decodeSampledBitmapFromResource;


public class MasterAllSurveyMediaAdapter extends RecyclerView.Adapter<MasterAllSurveyMediaAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Projectbean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final ImageView itemsImage;
        private final TextView geodetails, description;
        private LinearLayout itemsLocation;

        public MyViewHolder(View view) {
            super((view));
            itemsImage = (ImageView) view.findViewById(R.id.itemsImage);
            geodetails = (TextView) view.findViewById(R.id.geodetails);
            description = (TextView) view.findViewById(R.id.description);
            itemsLocation = (LinearLayout) view.findViewById(R.id.itemsLocation);

        }
    }


    public MasterAllSurveyMediaAdapter(Context mainActivityUser, ArrayList<Projectbean> moviesList) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;

    }

    public void notifyData(ArrayList<Projectbean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.survey_list_media_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Projectbean complaintLetterbean = moviesList.get(position);

        holder.geodetails.setText(complaintLetterbean.geotag);
        try {
            holder.geodetails.setText(AppConfig.df.format(Double.parseDouble(
                    complaintLetterbean.geotag.split(",")[0])) + "," + AppConfig.df.format(Double.parseDouble(
                    complaintLetterbean.geotag.split(",")[1])));
        } catch (Exception e) {

        }
        if (complaintLetterbean.detail.length() > 15) {
            holder.description.setText(complaintLetterbean.detail.substring(0, 14) + "...");
        } else {
            holder.description.setText(complaintLetterbean.detail);
        }
        try {
            new Thread(new Runnable() {
                public void run() {
                    try {
                      //Bitmap image = decodeSampledBitmapFromResource(new URL(complaintLetterbean.attachment), 100, 100);
                        holder.itemsImage.post(new Runnable() {
                            public void run()
                            {
                                Picasso.with(mainActivityUser)
                                        .load(complaintLetterbean.attachment)
                                        .fit()
                                        .into(holder.itemsImage);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            Toast.makeText(mainActivityUser, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

        holder.itemsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent(mainActivityUser, ActivityMediaOnline.class);
                localIntent.putExtra("filePath", complaintLetterbean.getAttachment());
                localIntent.putExtra("isImage", true);
                if (complaintLetterbean.getAttachment().toLowerCase().contains("mp4")) {
                    localIntent.putExtra("isImage", false);
                }
                mainActivityUser.startActivity(localIntent);
            }
        });
        holder.itemsLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(mainActivityUser, MediaLocationActivity.class);
                    intent.putExtra("data", complaintLetterbean);
                    mainActivityUser.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(mainActivityUser, "Invalid Geotag", Toast.LENGTH_SHORT).show();
                }
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
