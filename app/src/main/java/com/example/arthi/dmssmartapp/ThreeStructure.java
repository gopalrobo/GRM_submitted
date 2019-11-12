package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arthi.dmssmartapp.ThreeAffectedStructure.AffectedStructure;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.OnItemClickThree;

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
    EditText  hutArea;
    EditText  shopArea;
    EditText  stableArea;
    EditText  wallArea;
    EditText  otherAreaOne;
    EditText  otherAreaTwo;
    EditText  mainHouseAffected;
    EditText  hutAffected;
    EditText  shopAffected;
    EditText  stableAffected;
    EditText  wallAffected;
    EditText  otherAffectedOne;
    EditText  otherAffectedTwo;
    EditText  viableUse;
    EditText  rebuilt;
    EditText  mainHouseWeeks;
    EditText  otherStructureWeeks;
    EditText  pagodaAffected;
    EditText  publicAffected;
    EditText  description;





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
         hutArea = (EditText) findViewById(R.id. hutArea);
         shopArea = (EditText) findViewById(R.id. shopArea);
         stableArea = (EditText) findViewById(R.id. stableArea);
         wallArea = (EditText) findViewById(R.id. wallArea);
         otherAreaOne = (EditText) findViewById(R.id. otherAreaOne);
         otherAreaTwo = (EditText) findViewById(R.id. otherAreaTwo);
         mainHouseAffected = (EditText) findViewById(R.id. mainHouseAffected);
         hutAffected = (EditText) findViewById(R.id. hutAffected);
         shopAffected = (EditText) findViewById(R.id. shopAffected);
         stableAffected = (EditText) findViewById(R.id. stableAffected);
         wallAffected = (EditText) findViewById(R.id. wallAffected);
         otherAffectedOne = (EditText) findViewById(R.id. otherAffectedOne);
         otherAffectedTwo = (EditText) findViewById(R.id. otherAffectedTwo);
         viableUse = (EditText) findViewById(R.id. viableUse);
         rebuilt = (EditText) findViewById(R.id. rebuilt);
         mainHouseWeeks = (EditText) findViewById(R.id. mainHouseWeeks);
         otherStructureWeeks = (EditText) findViewById(R.id. otherStructureWeeks);
         pagodaAffected = (EditText) findViewById(R.id. pagodaAffected);
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
                         "",
                         hutArea.getText().toString(),
                         shopArea.getText().toString(),
                         stableArea.getText().toString(),
                         wallArea.getText().toString(),
                         otherAreaOne.getText().toString(),
                         otherAreaTwo.getText().toString(),
                         mainHouseAffected.getText().toString(),
                         "",
                         hutAffected.getText().toString(),
                         shopAffected.getText().toString(),
                         stableAffected.getText().toString(),
                         wallAffected.getText().toString(),
                         otherAffectedOne.getText().toString(),
                         otherAffectedTwo.getText().toString(),
                         viableUse.getText().toString(),
                         rebuilt.getText().toString(),
                         "",
                         mainHouseWeeks.getText().toString(),
                         otherStructureWeeks.getText().toString(),
                         pagodaAffected.getText().toString(),
                        "",
                         publicAffected.getText().toString(),
                         description.getText().toString(),
null

                        );

                        //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempAffectedStructure);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );



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

                 hutArea.setText(affectedStructure. hutArea);
                 shopArea.setText(affectedStructure. shopArea);
                 stableArea.setText(affectedStructure. stableArea);
                 wallArea.setText(affectedStructure. wallArea);
                 otherAreaOne.setText(affectedStructure. otherAreaOne);
                 otherAreaTwo.setText(affectedStructure. otherAreaTwo);
                 mainHouseAffected.setText(affectedStructure. mainHouseAffected);

                 hutAffected.setText(affectedStructure. hutAffected);
                 shopAffected.setText(affectedStructure. shopAffected);
                 stableAffected.setText(affectedStructure. stableAffected);
                 wallAffected.setText(affectedStructure. wallAffected);
                 otherAffectedOne.setText(affectedStructure. otherAffectedOne);
                 otherAffectedTwo.setText(affectedStructure. otherAffectedTwo);
                 viableUse.setText(affectedStructure. viableUse);
                 rebuilt.setText(affectedStructure. rebuilt);
                 mainHouseWeeks.setText(affectedStructure. mainHouseWeeks);
                 otherStructureWeeks.setText(affectedStructure. otherStructureWeeks);
                 pagodaAffected.setText(affectedStructure. pagodaAffected);

                 publicAffected.setText(affectedStructure. publicAffected);
                 description.setText(affectedStructure. description);


            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }

    @Override
    public void itemConstClick(int position) {

    }
}