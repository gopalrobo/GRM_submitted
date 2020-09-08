package com.example.grmlogbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class MeetingAttendeeAdapater extends RecyclerView.Adapter<MeetingAttendeeAdapater.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<RegMainbean> regMainbeans;
    private OnInvestMeetingClose onItemClick;
    private String type;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final Chip chipname;

        public MyViewHolder(View view) {
            super((view));
            chipname = view.findViewById(R.id.chipname);

        }
    }

    public MeetingAttendeeAdapater(Context mainActivityUser, ArrayList<RegMainbean> regMainbeans,
                                   OnInvestMeetingClose onItemClick, String type) {
        this.mainActivityUser = mainActivityUser;
        this.regMainbeans = regMainbeans;
        this.onItemClick = onItemClick;
        this.type=type;
    }

    public void notifyData(ArrayList<RegMainbean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.regMainbeans = myList;
        notifyDataSetChanged();
    }

    public MeetingAttendeeAdapater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.investigator_row, parent, false);

        return new MeetingAttendeeAdapater.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeetingAttendeeAdapater.MyViewHolder holder, final int position) {
        RegMainbean bean = regMainbeans.get(position);
        if (bean.email != null && bean.email.length() > 0) {
            holder.chipname.setText(bean.email);
        } else {
            holder.chipname.setText(bean.salutation + "." + bean.name);
        }

        holder.chipname.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClosed(position,type);
            }
        });
    }

    public int getItemCount() {
        return regMainbeans.size();
    }

}
