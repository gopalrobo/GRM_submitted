package com.example.arthi.dmssmartapp.OneHouseholdData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arthi.dmssmartapp.R;
import com.itextpdf.text.Section;

import java.util.ArrayList;

/**
 * Created by arthi on 11/27/2018.
 */

public class MaleAddapter extends RecyclerView.Adapter<MaleAddapter.MyViewHolder> {

        private Context mainActivityUser;
        private ArrayList<Male> maleList;
        private OnItemClickOne onItemClick;
        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView maleFirstYear,
            maleSecondYear,
            maleThirdYear,
            maleFourYear,
            maleFiveYear,
            maleSixYear;
            LinearLayout parentLinear;

            public MyViewHolder(View view){
                super((view));
                maleFirstYear =(TextView) view.findViewById(R.id.maleFirstYear);
                maleSecondYear =(TextView) view.findViewById(R.id.maleSecondYear);
                maleThirdYear =(TextView) view.findViewById(R.id.maleThirdYear);
                maleFourYear =(TextView) view.findViewById(R.id.maleFourYear);
                maleFiveYear =(TextView) view.findViewById(R.id.maleFiveYear);
                maleSixYear =(TextView) view.findViewById(R.id.maleSixYear);

                parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);

            }
        }
    public MaleAddapter(Context mainActivityUser, ArrayList<Male> moviesList, OnItemClickOne onItemClick) {
            this.maleList = moviesList;
            this.mainActivityUser = mainActivityUser;
            this.onItemClick = onItemClick;
        }
    public void notifyData(ArrayList<Male> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.maleList = myList;
        notifyDataSetChanged();
    }
    public MaleAddapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.male_row, parent, false);

        return new MaleAddapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MaleAddapter.MyViewHolder holder, final int position) {
        Male bean = maleList.get(position);
        holder.maleFirstYear.setText(bean.maleFirstYear);
        holder.maleSecondYear.setText(bean.maleSecondYear);
        holder.maleThirdYear.setText(bean.maleThirdYear);
        holder.maleFourYear.setText(bean.maleFourYear);
        holder.maleFiveYear.setText(bean.maleFiveYear);
        holder.maleSixYear.setText(bean.maleSixYear);



        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemMaleClick(position);
            }
        });
    }
    public  int getItemCount(){
        return maleList.size();
    }

}
