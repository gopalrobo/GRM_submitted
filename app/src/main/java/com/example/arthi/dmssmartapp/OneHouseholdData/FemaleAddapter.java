package com.example.arthi.dmssmartapp.OneHouseholdData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arthi.dmssmartapp.R;

import java.util.ArrayList;

/**
 * Created by arthi on 11/27/2018.
 */

public class FemaleAddapter extends RecyclerView.Adapter<FemaleAddapter.MyViewHolder> {

        private Context mainActivityUser;
        private ArrayList<Female> femaleList;
        private OnItemClickOne onItemClick;
        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView femaleFirstYear,
            femaleSecondYear,
            femaleThirdYear,
            femaleFourYear,
            femaleFiveYear,
            femaleSixYear;
            LinearLayout parentLinear;

            public MyViewHolder(View view){
                super((view));
                femaleFirstYear =(TextView) view.findViewById(R.id.femaleFirstYear);
                femaleSecondYear =(TextView) view.findViewById(R.id.femaleSecondYear);
                femaleThirdYear =(TextView) view.findViewById(R.id.femaleThirdYear);
                femaleFourYear =(TextView) view.findViewById(R.id.femaleFourYear);
                femaleFiveYear =(TextView) view.findViewById(R.id.femaleFiveYear);
                femaleSixYear =(TextView) view.findViewById(R.id.femaleSixYear);

                parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);

            }
        }
    public FemaleAddapter(Context mainActivityUser, ArrayList<Female> moviesList, OnItemClickOne onItemClick) {
            this.femaleList = moviesList;
            this.mainActivityUser = mainActivityUser;
            this.onItemClick = onItemClick;
        }
    public void notifyData(ArrayList<Female> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.femaleList = myList;
        notifyDataSetChanged();
    }
    public FemaleAddapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.female_row, parent, false);

        return new FemaleAddapter.MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(FemaleAddapter.MyViewHolder holder, final int position) {
        Female bean = femaleList.get(position);
        holder.femaleFirstYear.setText(bean.femaleFirstYear);
        holder.femaleSecondYear.setText(bean.femaleSecondYear);
        holder.femaleThirdYear.setText(bean.femaleThirdYear);
        holder.femaleFourYear.setText(bean.femaleFourYear);
        holder.femaleFiveYear.setText(bean.femaleFiveYear);
        holder.femaleSixYear.setText(bean.femaleSixYear);



        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemFemaleClick(position);
            }
        });
    }
    public  int getItemCount(){
        return femaleList.size();
    }

}
