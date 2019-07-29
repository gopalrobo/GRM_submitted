package com.example.arthi.dmssmartapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;



public class MasterAllSurveyAdapter extends RecyclerView.Adapter<MasterAllSurveyAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Mainbean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView surveyId,
        questionNo,
        province;
        ImageView editImage, printImage;
        LinearLayout parentLinear;

        public MyViewHolder(View view) {
            super(view);
            surveyId= (TextView) view.findViewById(R.id.surveyId);
            questionNo= (TextView) view.findViewById(R.id.questionNo);
            province= (TextView) view.findViewById(R.id.province);

            editImage = (ImageView) view.findViewById(R.id.editImage);
            printImage = (ImageView) view.findViewById(R.id.printImage);



            parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);
        }
    }


    public MasterAllSurveyAdapter(Context mainActivityUser, ArrayList<Mainbean> moviesList) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;

    }

    public void notifyData(ArrayList<Mainbean> myList) {
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
        final Mainbean bean = moviesList.get(position);
        if(bean.getQuestionnaire()!=null){
            if(bean.getQuestionnaire().surveyId!=null){
                holder.surveyId.setText(bean.getQuestionnaire().surveyId);

            }
            if(bean.getQuestionnaire().questionNo!=null){
                holder.questionNo.setText(bean.getQuestionnaire().questionNo);

            }
            if(bean.getQuestionnaire().province!=null){
                holder.province.setText(bean.getQuestionnaire().province);

            }
        }

        //holder.age.setText(bean.getGeoTag());
        //holder.education.setText(bean.getId());


        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityUser, MainActivity.class);
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
