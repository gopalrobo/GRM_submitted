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

public class OwnedAddapter extends RecyclerView.Adapter<OwnedAddapter.MyViewHolder> {

        private Context mainActivityUser;
        private ArrayList<Owned> ownedList;
        private OnItemClickTwo onItemClick;
        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView typesOfOwned,
            ownedAreaA,
            ownedAreaB;
            LinearLayout parentLinear;

            public MyViewHolder(View view){
                super((view));
                typesOfOwned =(TextView) view.findViewById(R.id.typesOfOwned);
                ownedAreaA =(TextView) view.findViewById(R.id.ownedAreaA);
                ownedAreaB =(TextView) view.findViewById(R.id.ownedAreaB);

                parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);

            }
        }
    public OwnedAddapter(Context mainActivityUser, ArrayList<Owned> moviesList, OnItemClickTwo onItemClick) {
            this.ownedList = moviesList;
            this.mainActivityUser = mainActivityUser;
            this.onItemClick = onItemClick;
        }
    public void notifyData(ArrayList<Owned> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.ownedList = myList;
        notifyDataSetChanged();
    }
    public OwnedAddapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.owned_row, parent, false);

        return new OwnedAddapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(OwnedAddapter.MyViewHolder holder, final int position) {
        Owned bean = ownedList.get(position);
        holder.typesOfOwned.setText(bean.typesOfOwned);
        holder.ownedAreaA.setText(bean.ownedAreaA);
        holder.ownedAreaB.setText(bean.ownedAreaB);



        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemOwnedClick(position);
            }
        });
    }
    public  int getItemCount(){
        return ownedList.size();
    }

}
