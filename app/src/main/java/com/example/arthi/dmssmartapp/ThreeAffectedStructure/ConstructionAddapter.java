package com.example.arthi.dmssmartapp.ThreeAffectedStructure;

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

public class ConstructionAddapter extends RecyclerView.Adapter<ConstructionAddapter.MyViewHolder>   {

        private Context mainActivityUser;
        private ArrayList<Construction> constList;
        private OnItemClickThree onItemClick;
        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView types,
            roof,
            walls,
            floor;
            LinearLayout parentLinear;

            public MyViewHolder(View view){
                super((view));
                types =(TextView) view.findViewById(R.id.types);
                roof =(TextView) view.findViewById(R.id.roof);
                walls =(TextView) view.findViewById(R.id.walls);
                floor =(TextView) view.findViewById(R.id.floor);

                parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);

            }
        }
    public ConstructionAddapter(Context mainActivityUser, ArrayList<Construction> moviesList, OnItemClickThree onItemClick) {
            this.constList = moviesList;
            this.mainActivityUser = mainActivityUser;
            this.onItemClick = onItemClick;
        }
    public void notifyData(ArrayList<Construction> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.constList = myList;
        notifyDataSetChanged();
    }


    public ConstructionAddapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.construction_row, parent, false);

        return new ConstructionAddapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ConstructionAddapter.MyViewHolder holder, final int position) {
        Construction bean = constList.get(position);
        holder.types.setText(bean.types);
        holder.roof.setText(bean.roof);
        holder.walls.setText(bean.walls);
        holder.floor.setText(bean.floor);



        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemConstClick(position);
            }
        });
    }
    public  int getItemCount(){
        return constList.size();
    }

}
