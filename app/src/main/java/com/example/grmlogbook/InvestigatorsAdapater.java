package com.example.grmlogbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grmlogbook.app.GlideApp;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Map;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class InvestigatorsAdapater extends RecyclerView.Adapter<InvestigatorsAdapater.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<RegMainbean> regMainbeans;
    private OnInvestClose onItemClick;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final Chip chipname;

        public MyViewHolder(View view) {
            super((view));
            chipname = view.findViewById(R.id.chipname);

        }
    }

    public InvestigatorsAdapater(Context mainActivityUser, ArrayList<RegMainbean> regMainbeans,
                                 OnInvestClose onItemClick) {
        this.mainActivityUser = mainActivityUser;
        this.regMainbeans=regMainbeans;
        this.onItemClick = onItemClick;
    }

    public void notifyData(ArrayList<RegMainbean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.regMainbeans = myList;
        notifyDataSetChanged();
    }

    public InvestigatorsAdapater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.investigator_row, parent, false);

        return new InvestigatorsAdapater.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InvestigatorsAdapater.MyViewHolder holder, final int position) {
        RegMainbean bean = regMainbeans.get(position);
        holder.chipname.setText(bean.salutation+"."+bean.name);

        holder.chipname.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClosed(position);
            }
        });
    }

    public int getItemCount() {
        return regMainbeans.size();
    }

}
