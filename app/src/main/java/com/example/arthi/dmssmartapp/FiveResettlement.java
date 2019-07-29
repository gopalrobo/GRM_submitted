package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class FiveResettlement extends AppCompatActivity {
    EditText affectedLand;
    EditText affectedCompensation;
    EditText lossOfCrops;
    EditText eligiblehouseholds;
    EditText proximity;
    EditText district;
    EditText sameprovince;
    EditText accesstoSchools;
    EditText market;
    EditText area;
    EditText pagoda;
    EditText community;
    EditText groupRelocation;
    EditText individualRelocation;
    EditText selfRelocation;




    TextView submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resettlementoption);

        affectedLand = (EditText) findViewById(R.id.affectedLand);
        affectedCompensation = (EditText) findViewById(R.id.affectedCompensation);
        lossOfCrops = (EditText) findViewById(R.id.lossOfCrops);
        eligiblehouseholds = (EditText) findViewById(R.id.eligiblehouseholds);
        proximity = (EditText) findViewById(R.id.proximity);
        district = (EditText) findViewById(R.id.district);
        sameprovince = (EditText) findViewById(R.id.sameprovince);
        accesstoSchools = (EditText) findViewById(R.id.accesstoSchools);
        market = (EditText) findViewById(R.id.market);
        area = (EditText) findViewById(R.id.area);
        pagoda = (EditText) findViewById(R.id.pagoda);
        community = (EditText) findViewById(R.id.community);
        groupRelocation = (EditText) findViewById(R.id.groupRelocation);
        individualRelocation = (EditText) findViewById(R.id.individualRelocation);
        selfRelocation = (EditText) findViewById(R.id.selfRelocation);

        submit = (TextView) findViewById(R.id.submit);
       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResettlementOption tempResettlementOption = new ResettlementOption(

                        affectedLand.getText().toString(),
                        affectedCompensation.getText().toString(),
                        lossOfCrops.getText().toString(),
                        eligiblehouseholds.getText().toString(),
                        proximity.getText().toString(),
                        district.getText().toString(),
                        sameprovince.getText().toString(),
                        accesstoSchools.getText().toString(),
                        market.getText().toString(),
                        area.getText().toString(),
                        pagoda.getText().toString(),
                        community.getText().toString(),
                        groupRelocation.getText().toString(),
                        individualRelocation.getText().toString(),
                        selfRelocation.getText().toString()
                        );

                        //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempResettlementOption);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );



        try {
            ResettlementOption resettlementOption = (ResettlementOption) getIntent().getSerializableExtra("data");
            if (resettlementOption != null) {

                affectedLand.setText(resettlementOption.affectedLand);
                affectedCompensation.setText(resettlementOption.affectedCompensation);
                lossOfCrops.setText(resettlementOption.lossOfCrops);
                eligiblehouseholds.setText(resettlementOption.eligiblehouseholds);
                proximity.setText(resettlementOption.proximity);
                district.setText(resettlementOption.district);
                sameprovince.setText(resettlementOption.sameprovince);
                accesstoSchools.setText(resettlementOption.accesstoSchools);
                market.setText(resettlementOption.market);
                area.setText(resettlementOption.area);
                pagoda.setText(resettlementOption.pagoda);
                community.setText(resettlementOption.community);
                groupRelocation.setText(resettlementOption.groupRelocation);
                individualRelocation.setText(resettlementOption.individualRelocation);
                selfRelocation.setText(resettlementOption.selfRelocation);

            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }

}