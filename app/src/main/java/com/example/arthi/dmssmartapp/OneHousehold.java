package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arthi.dmssmartapp.OneHouseholdData.HouseholdDate;
import com.example.arthi.dmssmartapp.OneHouseholdData.OnItemClickOne;

import java.util.ArrayList;

public class OneHousehold extends AppCompatActivity implements OnItemClickOne {

    EditText householdHead;
    EditText gender;
    EditText age;
    EditText maritalStatus;
    EditText education;
    EditText ethnicGroup;
    EditText regularWages;
    EditText dailyWages;
    EditText nonwagedEarnings;
    EditText seasonalEarnings;
    EditText mainIncome;
    EditText secondIncome;
    EditText fullTimeMale;
    EditText fullTimeFemale;
    EditText partTimeMale;
    EditText partTimeFemale;
    EditText governmentService;
    EditText privateSector;
    EditText services;
    EditText trade;
    EditText construction;
    EditText agriculture;
    EditText casuallabor;
    EditText totalother;
    EditText govPension;
    EditText govAssistance;
    EditText remittances;
    EditText rentalIncome;
    EditText nonearnedOthers;
    EditText vegetables;
    EditText rice;
    EditText otherCrop;
    EditText livestock;
    EditText poultry;
    EditText forestProducts;
    EditText handicrafts;
    EditText estimateOthers;
    EditText roof;
    EditText walls;
    EditText floor;
    EditText disabledMale;
    EditText disabledFemale;
    EditText disabledillness;
    EditText fmailyLiving;


    TextView submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.householddate);

       householdHead = (EditText) findViewById(R.id.householdHead);
       gender = (EditText) findViewById(R.id.gender);
       age = (EditText) findViewById(R.id.age);
       maritalStatus = (EditText) findViewById(R.id.maritalStatus);
       education = (EditText) findViewById(R.id.education);
       ethnicGroup = (EditText) findViewById(R.id.ethnicGroup);
       regularWages = (EditText) findViewById(R.id.regularWages);
       dailyWages = (EditText) findViewById(R.id.dailyWages);
       nonwagedEarnings = (EditText) findViewById(R.id.nonwagedEarnings);
       seasonalEarnings = (EditText) findViewById(R.id.seasonalEarnings);
       mainIncome = (EditText) findViewById(R.id.mainIncome);
       secondIncome = (EditText) findViewById(R.id.secondIncome);
       fullTimeMale = (EditText) findViewById(R.id.fullTimeMale);
       fullTimeFemale = (EditText) findViewById(R.id.fullTimeFemale);
       partTimeMale = (EditText) findViewById(R.id.partTimeMale);
       partTimeFemale = (EditText) findViewById(R.id.partTimeFemale);
       governmentService = (EditText) findViewById(R.id.governmentService);
       privateSector = (EditText) findViewById(R.id.privateSector);
       services = (EditText) findViewById(R.id.services);
       trade = (EditText) findViewById(R.id.trade);
       construction = (EditText) findViewById(R.id.construction);
       agriculture = (EditText) findViewById(R.id.agriculture);
       casuallabor = (EditText) findViewById(R.id.casuallabor);
       totalother = (EditText) findViewById(R.id.totalother);
       govPension = (EditText) findViewById(R.id.govPension);
       govAssistance = (EditText) findViewById(R.id.govAssistance);
       remittances = (EditText) findViewById(R.id.remittances);
       rentalIncome = (EditText) findViewById(R.id.rentalIncome);
       nonearnedOthers = (EditText) findViewById(R.id.nonearnedOthers);
       vegetables = (EditText) findViewById(R.id.vegetables);
       rice = (EditText) findViewById(R.id.rice);
       otherCrop = (EditText) findViewById(R.id.otherCrop);
       livestock = (EditText) findViewById(R.id.livestock);
       poultry = (EditText) findViewById(R.id.poultry);
       forestProducts = (EditText) findViewById(R.id.forestProducts);
       handicrafts = (EditText) findViewById(R.id.handicrafts);
       estimateOthers = (EditText) findViewById(R.id.estimateOthers);
       roof = (EditText) findViewById(R.id.roof);
       walls = (EditText) findViewById(R.id.walls);
       floor = (EditText) findViewById(R.id.floor);
       disabledMale = (EditText) findViewById(R.id.disabledMale);
       disabledFemale = (EditText) findViewById(R.id.disabledFeMale);
       disabledillness = (EditText) findViewById(R.id.disabledillness);
       fmailyLiving = (EditText) findViewById(R.id.fmailyLiving);


        submit = (TextView) findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HouseholdDate tempHouseholdDate = new HouseholdDate(

                        householdHead.getText().toString(),
                        gender.getText().toString(),
                        age.getText().toString(),
                        maritalStatus.getText().toString(),
                        education.getText().toString(),
                        ethnicGroup.getText().toString(),
                        regularWages.getText().toString(),
                        dailyWages.getText().toString(),
                        nonwagedEarnings.getText().toString(),
                        seasonalEarnings.getText().toString(),
                        "",
                        mainIncome.getText().toString(),
                        secondIncome.getText().toString(),
                        fullTimeMale.getText().toString(),
                        fullTimeFemale.getText().toString(),
                        partTimeMale.getText().toString(),
                        partTimeFemale.getText().toString(),
                        "",
                        governmentService.getText().toString(),
                        privateSector.getText().toString(),
                        services.getText().toString(),
                        trade.getText().toString(),
                        construction.getText().toString(),
                        agriculture.getText().toString(),
                        casuallabor.getText().toString(),
                        totalother.getText().toString(),
                        "",
                        govPension.getText().toString(),
                        govAssistance.getText().toString(),
                        remittances.getText().toString(),
                        rentalIncome.getText().toString(),
                        nonearnedOthers.getText().toString(),
                        "",
                        vegetables.getText().toString(),
                        rice.getText().toString(),
                        otherCrop.getText().toString(),
                        livestock.getText().toString(),
                        poultry.getText().toString(),
                        forestProducts.getText().toString(),
                        handicrafts.getText().toString(),
                        estimateOthers.getText().toString(),
                        "",
                        roof.getText().toString(),
                        walls.getText().toString(),
                        floor.getText().toString(),
                        disabledMale.getText().toString(),
                        disabledFemale.getText().toString(),
                        disabledillness.getText().toString(),
                        fmailyLiving.getText().toString(),
                       null,
                        null
                        );

                        //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempHouseholdDate);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );





        try {
            HouseholdDate householdDate = (HouseholdDate) getIntent().getSerializableExtra("data");
            if (householdDate != null) {

                householdHead.setText(householdDate.householdHead);
                gender.setText(householdDate.gender);
                age.setText(householdDate.age);
                maritalStatus.setText(householdDate.maritalStatus);
                education.setText(householdDate.education);
                ethnicGroup.setText(householdDate.ethnicGroup);
                regularWages.setText(householdDate.regularWages);
                dailyWages.setText(householdDate.dailyWages);
                nonwagedEarnings.setText(householdDate.nonwagedEarnings);
                seasonalEarnings.setText(householdDate.seasonalEarnings);
                mainIncome.setText(householdDate.mainIncome);
                secondIncome.setText(householdDate.secondIncome);
                fullTimeMale.setText(householdDate.fullTimeMale);
                fullTimeFemale.setText(householdDate.fullTimeFemale);
                partTimeMale.setText(householdDate.partTimeMale);
                partTimeFemale.setText(householdDate.partTimeFemale);
                governmentService.setText(householdDate.governmentService);
                privateSector.setText(householdDate.privateSector);
                services.setText(householdDate.services);
                trade.setText(householdDate.trade);
                construction.setText(householdDate.construction);
                agriculture.setText(householdDate.agriculture);
                casuallabor.setText(householdDate.casuallabor);
                totalother.setText(householdDate.totalother);
                govPension.setText(householdDate.govPension);
                govAssistance.setText(householdDate.govAssistance);
                remittances.setText(householdDate.remittances);
                rentalIncome.setText(householdDate.rentalIncome);
                nonearnedOthers.setText(householdDate.nonearnedOthers);
                vegetables.setText(householdDate.vegetables);
                rice.setText(householdDate.rice);
                otherCrop.setText(householdDate.otherCrop);
                livestock.setText(householdDate.livestock);
                poultry.setText(householdDate.poultry);
                forestProducts.setText(householdDate.forestProducts);
                handicrafts.setText(householdDate.handicrafts);
                estimateOthers.setText(householdDate.estimateOthers);
                roof.setText(householdDate.roof);
                walls.setText(householdDate.walls);
                floor.setText(householdDate.floor);
                disabledMale.setText(householdDate.disabledMale);
                disabledFemale.setText(householdDate.disabledFemale);
                disabledillness.setText(householdDate.disabledillness);
                fmailyLiving.setText(householdDate.fmailyLiving);



            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }


    @Override
    public void itemMaleClick(int position) {

    }

    @Override
    public void itemFemaleClick(int position) {

    }
}