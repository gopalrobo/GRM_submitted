package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.arthi.dmssmartapp.OneHouseholdData.Female;
import com.example.arthi.dmssmartapp.OneHouseholdData.FemaleAddapter;
import com.example.arthi.dmssmartapp.OneHouseholdData.HouseholdDate;
import com.example.arthi.dmssmartapp.OneHouseholdData.Male;
import com.example.arthi.dmssmartapp.TwoAffectedLand.AffectedLand;
import com.example.arthi.dmssmartapp.TwoAffectedLand.Crop;
import com.example.arthi.dmssmartapp.TwoAffectedLand.CropAddapter;
import com.example.arthi.dmssmartapp.TwoAffectedLand.OnItemClickTwo;
import com.example.arthi.dmssmartapp.TwoAffectedLand.Owned;
import com.example.arthi.dmssmartapp.TwoAffectedLand.OwnedAddapter;
import com.example.arthi.dmssmartapp.TwoAffectedLand.Project;
import com.example.arthi.dmssmartapp.TwoAffectedLand.ProjectAddapter;

import java.util.ArrayList;

public class TwoLand extends AppCompatActivity implements OnItemClickTwo {

    EditText legalOwner;
    EditText tenure;
    EditText legalRight;
    EditText residential;
    EditText agricultural;
    EditText commercial;
    EditText landowner;
    EditText landResidential;
    EditText landAgricultural;
    EditText landCommercial;
    EditText landOthers;
    EditText underHarvest;
   CropAddapter cropAddapter;
   ProjectAddapter projectAddapter;
   OwnedAddapter ownedAddapter;

    private RecyclerView cropList;
    private ArrayList<Crop> cropArrayList = new ArrayList<Crop>();

    private RecyclerView projectList;
    private ArrayList<Project> projectArrayList = new ArrayList<Project>();

    private RecyclerView ownedList;
    private ArrayList<Owned> ownedArrayList = new ArrayList<Owned>();


    TextView submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affectedland);

        legalOwner = (EditText) findViewById(R.id.legalOwner);
        tenure = (EditText) findViewById(R.id.tenure);
        legalRight = (EditText) findViewById(R.id.legalRight);
        residential = (EditText) findViewById(R.id.residential);
        agricultural = (EditText) findViewById(R.id.agricultural);
        commercial = (EditText) findViewById(R.id.commercial);
        landowner = (EditText) findViewById(R.id.landowner);
        landResidential = (EditText) findViewById(R.id.landResidential);
        landAgricultural = (EditText) findViewById(R.id.landAgricultural);
        landCommercial = (EditText) findViewById(R.id.landCommercial);
        landOthers = (EditText) findViewById(R.id.landOthers);
        underHarvest = (EditText) findViewById(R.id.underHarvest);


        submit = (TextView) findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AffectedLand tempAffectedLand = new AffectedLand(

                        legalOwner.getText().toString(),
                        tenure.getText().toString(),
                        legalRight.getText().toString(),
                        residential.getText().toString(),
                        agricultural.getText().toString(),
                        commercial.getText().toString(),
                        landowner.getText().toString(),
                        landResidential.getText().toString(),
                        landAgricultural.getText().toString(),
                        landCommercial.getText().toString(),
                        landOthers.getText().toString(),
                        underHarvest.getText().toString(),
                        ownedArrayList,
                        projectArrayList,
                        cropArrayList

                        );

                //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempAffectedLand);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );


        cropList = (RecyclerView) findViewById(R.id.cropList);
        cropAddapter = new CropAddapter(this, cropArrayList, this);
        final LinearLayoutManager Managercrop = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cropList.setLayoutManager(Managercrop);
        cropList.setAdapter(cropAddapter);
        TextView croptext = (TextView) findViewById(R.id.croptext);
        croptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCropDialog(-1);
            }
        });


        projectList = (RecyclerView) findViewById(R.id.projectList);
        projectAddapter = new ProjectAddapter(this, projectArrayList, this);
        final LinearLayoutManager Managerproject = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectList.setLayoutManager(Managerproject);
        projectList.setAdapter(projectAddapter);
        TextView projecttext = (TextView) findViewById(R.id.projecttext);
        projecttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProjectDialog(-1);
            }
        });

        ownedList = (RecyclerView) findViewById(R.id.ownedList);
        ownedAddapter = new OwnedAddapter(this,ownedArrayList, this);
        final LinearLayoutManager Managerowned = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ownedList.setLayoutManager(Managerowned);
        ownedList.setAdapter(ownedAddapter);
        TextView ownedtext = (TextView) findViewById(R.id.ownedtext);
        ownedtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOwnedDialog(-1);
            }
        });



        try {
            AffectedLand affectedLand = (AffectedLand) getIntent().getSerializableExtra("data");
            if (affectedLand != null) {

                legalOwner.setText(affectedLand.legalOwner);
                tenure.setText(affectedLand.tenure);
                legalRight.setText(affectedLand.legalRight);
                residential.setText(affectedLand.residential);
                agricultural.setText(affectedLand.agricultural);
                commercial.setText(affectedLand.commercial);
                landowner.setText(affectedLand.landowner);
                landResidential.setText(affectedLand.landResidential);
                landAgricultural.setText(affectedLand.landAgricultural);
                landCommercial.setText(affectedLand.landCommercial);
                landOthers.setText(affectedLand.landOthers);
                underHarvest.setText(affectedLand.underHarvest);


            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }
    public void showCropDialog(final int position) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TwoLand.this);
        LayoutInflater inflater = TwoLand.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.crop, null);

        final TextView submit = (TextView) dialogView.findViewById(R.id.submit);
        final EditText typesOfCrop = (EditText) dialogView.findViewById(R.id.typesOfCrop);
        final EditText cropAreaA = (EditText) dialogView.findViewById(R.id.cropAreaA);
        final EditText cropAreaB = (EditText) dialogView.findViewById(R.id.cropAreaB);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();


        if (position != -1) {
            submit.setText("UPDATE");
            Crop bean = cropArrayList.get(position);
            typesOfCrop.setText(bean.typesOfCrop);
            cropAreaA.setText(bean.cropAreaA);
            cropAreaB.setText(bean.cropAreaB);

        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == -1) {
                    Crop bean = new Crop(
                            typesOfCrop.getText().toString(),
                            cropAreaA.getText().toString(),
                            cropAreaB.getText().toString()

                            );
                    cropArrayList.add(bean);
                } else {
                    cropArrayList.get(position).setTypesOfCrop(typesOfCrop.getText().toString());
                    cropArrayList.get(position).setCropAreaA(cropAreaA.getText().toString());
                    cropArrayList.get(position).setCropAreaB(cropAreaB.getText().toString());


                }
                cropAddapter.notifyData(cropArrayList);
                b.cancel();
            }
        });
        b.show();
    }
    public void showProjectDialog(final int position) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TwoLand.this);
        LayoutInflater inflater = TwoLand.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.project, null);

        final TextView submit = (TextView) dialogView.findViewById(R.id.submit);
        final EditText typesOfProject = (EditText) dialogView.findViewById(R.id.typesOfProject);
        final EditText projectAreaaA = (EditText) dialogView.findViewById(R.id.projectAreaaA);
        final EditText projectAreaB = (EditText) dialogView.findViewById(R.id.projectAreaB);



        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();


        if (position != -1) {
            submit.setText("UPDATE");
            Project bean = projectArrayList.get(position);
            typesOfProject.setText(bean.typesOfProject);
            projectAreaaA.setText(bean.projectAreaaA);
            projectAreaB.setText(bean.projectAreaB);


        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == -1) {
                    Project bean = new Project(
                            typesOfProject.getText().toString(),
                            projectAreaaA.getText().toString(),
                            projectAreaB.getText().toString()


                            );
                    projectArrayList.add(bean);
                } else {
                    projectArrayList.get(position).setTypesOfProject(typesOfProject.getText().toString());
                    projectArrayList.get(position).setProjectAreaaA(projectAreaaA.getText().toString());
                    projectArrayList.get(position).setProjectAreaB(projectAreaB.getText().toString());

                }
                projectAddapter.notifyData(projectArrayList);
                b.cancel();
            }
        });
        b.show();
    }
    public void showOwnedDialog(final int position) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TwoLand.this);
        LayoutInflater inflater = TwoLand.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.owned, null);

        final TextView submit = (TextView) dialogView.findViewById(R.id.submit);
        final EditText typesOfOwned = (EditText) dialogView.findViewById(R.id.typesOfOwned);
        final EditText ownedAreaA = (EditText) dialogView.findViewById(R.id.ownedAreaA);
        final EditText ownedAreaB = (EditText) dialogView.findViewById(R.id.ownedAreaB);



        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();


        if (position != -1) {
            submit.setText("UPDATE");
            Owned bean = ownedArrayList.get(position);
            typesOfOwned.setText(bean.typesOfOwned);
            ownedAreaA.setText(bean.ownedAreaA);
            ownedAreaB.setText(bean.ownedAreaB);


        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == -1) {
                    Owned bean = new Owned(
                            typesOfOwned.getText().toString(),
                            ownedAreaA.getText().toString(),
                            ownedAreaB.getText().toString()


                            );
                    ownedArrayList.add(bean);
                } else {
                    ownedArrayList.get(position).setTypesOfOwned(typesOfOwned.getText().toString());
                    ownedArrayList.get(position).setOwnedAreaA(ownedAreaA.getText().toString());
                    ownedArrayList.get(position).setOwnedAreaB(ownedAreaB.getText().toString());

                }
                ownedAddapter.notifyData(ownedArrayList);
                b.cancel();
            }
        });
        b.show();
    }

    @Override
    public void itemCropClick(int position) {

    }

    @Override
    public void itemProjectClick(int position) {

    }

    @Override
    public void itemOwnedClick(int position) {

    }
}