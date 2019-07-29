package com.example.arthi.dmssmartapp.TwoAffectedLand;

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

public class CropAddapter extends RecyclerView.Adapter<CropAddapter.MyViewHolder> {

        private Context mainActivityUser;
        private ArrayList<Crop> cropList;
        private OnItemClickTwo onItemClick;
        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView typesOfCrop,
            cropAreaA,
            cropAreaB;
            LinearLayout parentLinear;

            public MyViewHolder(View view){
                super((view));
                typesOfCrop =(TextView) view.findViewById(R.id.typesOfCrop);
                cropAreaA =(TextView) view.findViewById(R.id.cropAreaA);
                cropAreaB =(TextView) view.findViewById(R.id.cropAreaB);

                parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);

            }
        }
    public CropAddapter(Context mainActivityUser, ArrayList<Crop> moviesList, OnItemClickTwo onItemClick) {
            this.cropList = moviesList;
            this.mainActivityUser = mainActivityUser;
            this.onItemClick = onItemClick;
        }
    public void notifyData(ArrayList<Crop> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.cropList = myList;
        notifyDataSetChanged();
    }
    public CropAddapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crop_row, parent, false);

        return new CropAddapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(CropAddapter.MyViewHolder holder, final int position) {
        Crop bean = cropList.get(position);
        holder.typesOfCrop.setText(bean.typesOfCrop);
        holder.cropAreaA.setText(bean.cropAreaA);
        holder.cropAreaB.setText(bean.cropAreaB);



        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemCropClick(position);
            }
        });
    }
    public  int getItemCount(){
        return cropList.size();
    }

}
