package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.arthi.dmssmartapp.TwoAffectedLand.AffectedLand;

public class TwoLand extends AppCompatActivity  {

    EditText legalOwner;
    EditText tenure;
    EditText legalRight;
    EditText residential;
    EditText agricultural;
    EditText commercial;
    EditText landResidential;
    EditText landAgricultural;
    EditText landCommercial;
    EditText landOthers;
    EditText underHarvest;


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
                        "",
                        landResidential.getText().toString(),
                        landAgricultural.getText().toString(),
                        landCommercial.getText().toString(),
                        landOthers.getText().toString(),
                        underHarvest.getText().toString(),
                        null,
                        null,
                        null

                        );

                //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempAffectedLand);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );





        try {
            AffectedLand affectedLand = (AffectedLand) getIntent().getSerializableExtra("data");
            if (affectedLand != null) {

                legalOwner.setText(affectedLand.legalOwner);
                tenure.setText(affectedLand.tenure);
                legalRight.setText(affectedLand.legalRight);
                residential.setText(affectedLand.residential);
                agricultural.setText(affectedLand.agricultural);
                commercial.setText(affectedLand.commercial);
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
}