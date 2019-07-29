package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arthi.dmssmartapp.OneHouseholdData.Male;
import com.example.arthi.dmssmartapp.OneHouseholdData.MaleAddapter;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.AffectedStructure;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.Construction;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.ConstructionAddapter;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.OnItemClickThree;
import com.example.arthi.dmssmartapp.TwoAffectedLand.AffectedLand;
import com.example.arthi.dmssmartapp.app.GlideApp;
import com.example.arthi.dmssmartapp.app.Imageutils;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThreeStructure extends AppCompatActivity implements OnItemClickThree {

    EditText  householdAffected;
    EditText  structuresAffected;
    EditText  mainHouse;
    EditText  bath;
    EditText  hut;
    EditText  shop;
    EditText  stable;
    EditText  wall;
    EditText  otherStructuresOne;
    EditText  otherStructuresTwo;
    EditText  mainHouseArea;
    EditText  outdoortoiletArea;
    EditText  hutArea;
    EditText  shopArea;
    EditText  stableArea;
    EditText  wallArea;
    EditText  otherAreaOne;
    EditText  otherAreaTwo;
    EditText  mainHouseAffected;
    EditText  outdoorAffected;
    EditText  hutAffected;
    EditText  shopAffected;
    EditText  stableAffected;
    EditText  wallAffected;
    EditText  otherAffectedOne;
    EditText  otherAffectedTwo;
    EditText  viableUse;
    EditText  rebuilt;
    EditText  construction;
    EditText  mainHouseWeeks;
    EditText  otherStructureWeeks;
    EditText  pagodaAffected;
    EditText  gravesAffected;
    EditText  publicAffected;
    EditText  description;
    ConstructionAddapter constructionAddapter;

    private RecyclerView constList;
    private ArrayList<Construction> constArrayList = new ArrayList<Construction>();


    TextView submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affectedstructure);

         householdAffected = (EditText) findViewById(R.id. householdAffected);
         structuresAffected = (EditText) findViewById(R.id. structuresAffected);
         mainHouse = (EditText) findViewById(R.id. mainHouse);
         bath = (EditText) findViewById(R.id. bath);
         hut = (EditText) findViewById(R.id. hut);
         shop = (EditText) findViewById(R.id. shop);
         stable = (EditText) findViewById(R.id. stable);
         wall = (EditText) findViewById(R.id. wall);
         otherStructuresOne = (EditText) findViewById(R.id. otherStructuresOne);
         otherStructuresTwo = (EditText) findViewById(R.id. otherStructuresTwo);
         mainHouseArea = (EditText) findViewById(R.id. mainHouseArea);
         outdoortoiletArea = (EditText) findViewById(R.id. outdoortoiletArea);
         hutArea = (EditText) findViewById(R.id. hutArea);
         shopArea = (EditText) findViewById(R.id. shopArea);
         stableArea = (EditText) findViewById(R.id. stableArea);
         wallArea = (EditText) findViewById(R.id. wallArea);
         otherAreaOne = (EditText) findViewById(R.id. otherAreaOne);
         otherAreaTwo = (EditText) findViewById(R.id. otherAreaTwo);
         mainHouseAffected = (EditText) findViewById(R.id. mainHouseAffected);
         outdoorAffected = (EditText) findViewById(R.id. outdoorAffected);
         hutAffected = (EditText) findViewById(R.id. hutAffected);
         shopAffected = (EditText) findViewById(R.id. shopAffected);
         stableAffected = (EditText) findViewById(R.id. stableAffected);
         wallAffected = (EditText) findViewById(R.id. wallAffected);
         otherAffectedOne = (EditText) findViewById(R.id. otherAffectedOne);
         otherAffectedTwo = (EditText) findViewById(R.id. otherAffectedTwo);
         viableUse = (EditText) findViewById(R.id. viableUse);
         rebuilt = (EditText) findViewById(R.id. rebuilt);
         construction = (EditText) findViewById(R.id. construction);
         mainHouseWeeks = (EditText) findViewById(R.id. mainHouseWeeks);
         otherStructureWeeks = (EditText) findViewById(R.id. otherStructureWeeks);
         pagodaAffected = (EditText) findViewById(R.id. pagodaAffected);
         gravesAffected = (EditText) findViewById(R.id. gravesAffected);
         publicAffected = (EditText) findViewById(R.id. publicAffected);
         description = (EditText) findViewById(R.id. description);


        submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AffectedStructure tempAffectedStructure = new AffectedStructure(

                         householdAffected.getText().toString(),
                         structuresAffected.getText().toString(),
                         mainHouse.getText().toString(),
                         bath.getText().toString(),
                         hut.getText().toString(),
                         shop.getText().toString(),
                         stable.getText().toString(),
                         wall.getText().toString(),
                         otherStructuresOne.getText().toString(),
                         otherStructuresTwo.getText().toString(),
                         mainHouseArea.getText().toString(),
                         outdoortoiletArea.getText().toString(),
                         hutArea.getText().toString(),
                         shopArea.getText().toString(),
                         stableArea.getText().toString(),
                         wallArea.getText().toString(),
                         otherAreaOne.getText().toString(),
                         otherAreaTwo.getText().toString(),
                         mainHouseAffected.getText().toString(),
                         outdoorAffected.getText().toString(),
                         hutAffected.getText().toString(),
                         shopAffected.getText().toString(),
                         stableAffected.getText().toString(),
                         wallAffected.getText().toString(),
                         otherAffectedOne.getText().toString(),
                         otherAffectedTwo.getText().toString(),
                         viableUse.getText().toString(),
                         rebuilt.getText().toString(),
                         construction.getText().toString(),
                         mainHouseWeeks.getText().toString(),
                         otherStructureWeeks.getText().toString(),
                         pagodaAffected.getText().toString(),
                         gravesAffected.getText().toString(),
                         publicAffected.getText().toString(),
                         description.getText().toString(),
constArrayList

                        );

                        //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempAffectedStructure);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );

        constList = (RecyclerView) findViewById(R.id.constList);
        constructionAddapter = new ConstructionAddapter(this, constArrayList, this);
        final LinearLayoutManager Manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        constList.setLayoutManager(Manager);
        constList.setAdapter(constructionAddapter);
        TextView consttext = (TextView) findViewById(R.id.consttext);
        consttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConstDialog(-1);
            }
        });


        try {
            AffectedStructure affectedStructure = (AffectedStructure) getIntent().getSerializableExtra("data");
            if (affectedStructure != null) {

                 householdAffected.setText(affectedStructure. householdAffected);
                 structuresAffected.setText(affectedStructure. structuresAffected);
                 mainHouse.setText(affectedStructure. mainHouse);
                 bath.setText(affectedStructure. bath);
                 hut.setText(affectedStructure. hut);
                 shop.setText(affectedStructure. shop);
                 stable.setText(affectedStructure. stable);
                 wall.setText(affectedStructure. wall);
                 otherStructuresOne.setText(affectedStructure. otherStructuresOne);
                 otherStructuresTwo.setText(affectedStructure. otherStructuresTwo);
                 mainHouseArea.setText(affectedStructure. mainHouseArea);
                 outdoortoiletArea.setText(affectedStructure. outdoortoiletArea);
                 hutArea.setText(affectedStructure. hutArea);
                 shopArea.setText(affectedStructure. shopArea);
                 stableArea.setText(affectedStructure. stableArea);
                 wallArea.setText(affectedStructure. wallArea);
                 otherAreaOne.setText(affectedStructure. otherAreaOne);
                 otherAreaTwo.setText(affectedStructure. otherAreaTwo);
                 mainHouseAffected.setText(affectedStructure. mainHouseAffected);
                 outdoorAffected.setText(affectedStructure. outdoorAffected);
                 hutAffected.setText(affectedStructure. hutAffected);
                 shopAffected.setText(affectedStructure. shopAffected);
                 stableAffected.setText(affectedStructure. stableAffected);
                 wallAffected.setText(affectedStructure. wallAffected);
                 otherAffectedOne.setText(affectedStructure. otherAffectedOne);
                 otherAffectedTwo.setText(affectedStructure. otherAffectedTwo);
                 viableUse.setText(affectedStructure. viableUse);
                 rebuilt.setText(affectedStructure. rebuilt);
                 construction.setText(affectedStructure. construction);
                 mainHouseWeeks.setText(affectedStructure. mainHouseWeeks);
                 otherStructureWeeks.setText(affectedStructure. otherStructureWeeks);
                 pagodaAffected.setText(affectedStructure. pagodaAffected);
                 gravesAffected.setText(affectedStructure. gravesAffected);
                 publicAffected.setText(affectedStructure. publicAffected);
                 description.setText(affectedStructure. description);


            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }
    public void showConstDialog(final int position) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ThreeStructure.this);
        LayoutInflater inflater = ThreeStructure.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.construction, null);

        final TextView submit = (TextView) dialogView.findViewById(R.id.submit);
        final EditText types = (EditText) dialogView.findViewById(R.id.types);
        final EditText roof = (EditText) dialogView.findViewById(R.id.roof);
        final EditText walls = (EditText) dialogView.findViewById(R.id.walls);
        final EditText floor = (EditText) dialogView.findViewById(R.id.floor);

        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();


        if (position != -1) {
            submit.setText("UPDATE");
            Construction bean = constArrayList.get(position);
            types.setText(bean.types);
            roof.setText(bean.roof);
            walls.setText(bean.walls);
            floor.setText(bean.floor);


        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == -1) {
                    Construction bean = new Construction(
                            types.getText().toString(),
                            roof.getText().toString(),
                            walls.getText().toString(),
                            floor.getText().toString()

                            );
                    constArrayList.add(bean);
                } else {
                    constArrayList.get(position).setTypes(types.getText().toString());
                    constArrayList.get(position).setRoof(roof.getText().toString());
                    constArrayList.get(position).setWalls(walls.getText().toString());
                    constArrayList.get(position).setFloor(floor.getText().toString());

                }
                constructionAddapter.notifyData(constArrayList);
                b.cancel();
            }
        });
        b.show();
    }

    @Override
    public void itemConstClick(int position) {

    }
}