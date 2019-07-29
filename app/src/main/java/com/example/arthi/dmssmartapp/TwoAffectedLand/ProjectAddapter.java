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

public class ProjectAddapter extends RecyclerView.Adapter<ProjectAddapter.MyViewHolder> {

        private Context mainActivityUser;
        private ArrayList<Project> projectList;
        private OnItemClickTwo onItemClick;
        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView typesOfProject,
            projectAreaaA,
            projectAreaB;
            LinearLayout parentLinear;

            public MyViewHolder(View view){
                super((view));
                typesOfProject =(TextView) view.findViewById(R.id.typesOfProject);
                projectAreaaA =(TextView) view.findViewById(R.id.projectAreaaA);
                projectAreaB =(TextView) view.findViewById(R.id.projectAreaB);

                parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);

            }
        }
    public ProjectAddapter(Context mainActivityUser, ArrayList<Project> moviesList, OnItemClickTwo onItemClick) {
            this.projectList = moviesList;
            this.mainActivityUser = mainActivityUser;
            this.onItemClick = onItemClick;
        }
    public void notifyData(ArrayList<Project> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.projectList = myList;
        notifyDataSetChanged();
    }
    public ProjectAddapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_row, parent, false);

        return new ProjectAddapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ProjectAddapter.MyViewHolder holder, final int position) {
        Project bean = projectList.get(position);
        holder.typesOfProject.setText(bean.typesOfProject);
        holder.projectAreaaA.setText(bean.projectAreaaA);
        holder.projectAreaB.setText(bean.projectAreaB);


        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemProjectClick(position);
            }
        });
    }
    public  int getItemCount(){
        return projectList.size();
    }

}
