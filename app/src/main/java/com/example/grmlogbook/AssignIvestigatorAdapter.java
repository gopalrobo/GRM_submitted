package com.example.grmlogbook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ravi on 16/11/17.
 */

public class AssignIvestigatorAdapter extends RecyclerView.Adapter<AssignIvestigatorAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private ArrayList<RegMainbean> contactList;
    private ArrayList<RegMainbean> contactListFiltered;
    private OnInvestCheck listener;
    HashSet<String> selectInvest;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone, level, district, commune;
        public ImageView thumbnail;
        CheckBox inevestCheck;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            level = view.findViewById(R.id.level);
            commune = view.findViewById(R.id.commune);
            district = view.findViewById(R.id.district);
            thumbnail = view.findViewById(R.id.thumbnail);
            inevestCheck = view.findViewById(R.id.inevestCheck);
        }
    }


    public AssignIvestigatorAdapter(Context context, ArrayList<RegMainbean> contactList,
                                    OnInvestCheck listener, HashSet<String> selectInvest) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
        this.contactListFiltered = contactList;
        this.selectInvest = selectInvest;
    }

    public void notifyData(ArrayList<RegMainbean> myList, HashSet<String> selectInvest) {
        Log.d("notifyData ", myList.size() + "");
        this.contactList = myList;
        this.selectInvest = selectInvest;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invest_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final RegMainbean contact = contactListFiltered.get(position);
        holder.name.setText(contact.salutation + "." + contact.getName());
        holder.phone.setText(contact.getPosition());
        holder.level.setText(contact.level);
        holder.commune.setText(contact.commune);
        holder.district.setText(contact.district);
        if (selectInvest.contains(contact.id)) {
            holder.inevestCheck.setChecked(true);
        } else {
            holder.inevestCheck.setChecked(false);
        }
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.inevestCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onChecked(position, isChecked);
            }
        });


    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty() || charString.length() == 0) {
                    contactListFiltered = contactList;
                } else {
                    ArrayList<RegMainbean> filteredList = new ArrayList<>();
                    for (RegMainbean row : contactList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPosition().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<RegMainbean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(RegMainbean contact);

    }
}
