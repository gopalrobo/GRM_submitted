package com.example.grmlogbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.ComplaintLetter.MasterAllSurveyMediaAdapter;
import com.example.grmlogbook.app.CompliantLetterPdfConfig;
import com.example.grmlogbook.timeline.BookingTimelineActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MasterAllSurveyAdapter extends RecyclerView.Adapter<MasterAllSurveyAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<ComplaintLetterbean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView date, adboffice, projectname, status;
        Button editImage, timeImage, printBtn, acknowledgeBtn;
        RecyclerView recycler_view_media;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            adboffice = (TextView) view.findViewById(R.id.adboffice);
            projectname = (TextView) view.findViewById(R.id.projectname);
            status = (TextView) view.findViewById(R.id.status);
            editImage = (Button) view.findViewById(R.id.editImage);
            timeImage = (Button) view.findViewById(R.id.timeImage);
            acknowledgeBtn = (Button) view.findViewById(R.id.receiptImage);
            printBtn = (Button) view.findViewById(R.id.printBtn);
            recycler_view_media = (RecyclerView) view.findViewById(R.id.recycler_view_media);


        }
    }


    public MasterAllSurveyAdapter(Context mainActivityUser, ArrayList<ComplaintLetterbean> moviesList) {
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
                .inflate(R.layout.survey_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ComplaintLetterbean complaintLetterbean = moviesList.get(position);

        holder.date.setText(complaintLetterbean.getDate());
        holder.adboffice.setText(complaintLetterbean.getAdboffice());
        holder.projectname.setText(complaintLetterbean.getProjectname());
        holder.status.setText(complaintLetterbean.getStatus());


        if (complaintLetterbean.projectbeans != null) {
            ArrayList<Projectbean> projectbeans = new ArrayList<>();
            for (int k = 0; k < complaintLetterbean.projectbeans.size(); k++) {
                if (complaintLetterbean.projectbeans.get(k).attachment
                        != null && complaintLetterbean.projectbeans.get(k).attachment.length() > 0) {
                    projectbeans.add(complaintLetterbean.projectbeans.get(k));
                }
            }


            MasterAllSurveyMediaAdapter surveyMediaAdapter = new
                    MasterAllSurveyMediaAdapter(mainActivityUser, projectbeans);
            final LinearLayoutManager addManager = new LinearLayoutManager(mainActivityUser, LinearLayoutManager.HORIZONTAL, false);
            holder.recycler_view_media.setLayoutManager(addManager);
            holder.recycler_view_media.setAdapter(surveyMediaAdapter);

        }


//        if (!"Submitted".equals(complaintLetterbean.getStatus()) && !"Feed back date".equals(complaintLetterbean.getStatus()) && !"Issues resolved or escalated".equals(complaintLetterbean.getStatus())) {
//            holder.editImage.setText("View");
//            holder.editImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    CompliantPdfConfig.printFunction(mainActivityUser, complaintLetterbean);
//                }
//            });
//        } else if (complaintLetterbean.getStatus().equals("Feed back date") || complaintLetterbean.getStatus().equals("Issues resolved or escalated")) {
//            holder.editImage.setText("Feedback");
//        } else {
//            holder.editImage.setText("Edit");
//            holder.editImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(mainActivityUser, CompliantLetterCopy.class);
//                    intent.putExtra("object", moviesList.get(position));
//                    mainActivityUser.startActivity(intent);
//                }
//            });
//        }


        holder.timeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityUser, BookingTimelineActivity.class);
                intent.putExtra("id", moviesList.get(position).getUniId());
                mainActivityUser.startActivity(intent);
            }
        });

        holder.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityUser, CompliantLetterCopy.class);
                intent.putExtra("object", moviesList.get(position));
                mainActivityUser.startActivity(intent);
            }
        });

        holder.acknowledgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moviesList.get(position).getStatus().equals("Submitted")) {
                    Intent intent = new Intent(mainActivityUser, CompliantLetterCopy.class);
                    intent.putExtra("object", moviesList.get(position));
                    mainActivityUser.startActivity(intent);
                } else if (moviesList.get(position).getStatus().equals("Acknowledged")) {
                    Intent intent = new Intent(mainActivityUser, AssigningInvestigator.class);
                    intent.putExtra("object", moviesList.get(position));
                    mainActivityUser.startActivity(intent);
                } else if (moviesList.get(position).getStatus().equals("Investigator Assigned")) {
                    Intent intent = new Intent(mainActivityUser, InvestigateReport.class);
                    intent.putExtra("object", moviesList.get(position));
                    mainActivityUser.startActivity(intent);
                } else if (moviesList.get(position).getStatus().equals("Investigate Report Submitted")) {
                    Intent intent = new Intent(mainActivityUser, MeetingInvitation.class);
                    intent.putExtra("object", moviesList.get(position));
                    mainActivityUser.startActivity(intent);
                } else if (moviesList.get(position).getStatus().equals("Meeting Invitation Sent")) {
                    Intent intent = new Intent(mainActivityUser, ResolutionMeeting.class);
                    intent.putExtra("object", moviesList.get(position));
                    mainActivityUser.startActivity(intent);
                } else {
                    Toast.makeText(mainActivityUser, "Compliant resolved or Escalated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompliantLetterPdfConfig.printFunction(mainActivityUser, complaintLetterbean);
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
