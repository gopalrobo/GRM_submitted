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

import com.example.arthi.dmssmartapp.app.GlideApp;
import com.example.arthi.dmssmartapp.app.Imageutils;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;


public class SevenQuestionnaire extends AppCompatActivity  {
    EditText surveyId;
    EditText questionNo;
    EditText province;
    EditText district;
    EditText commune;
    EditText nameOfHouseholdHead;
    EditText idorFamilyCardNo;



    TextView submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);

       surveyId = (EditText) findViewById(R.id.surveyId);
       questionNo = (EditText) findViewById(R.id.questionNo);
       province = (EditText) findViewById(R.id.province);
       district = (EditText) findViewById(R.id.district);
       commune = (EditText) findViewById(R.id.commune);
       nameOfHouseholdHead = (EditText) findViewById(R.id.nameOfHouseholdHead);
       idorFamilyCardNo = (EditText) findViewById(R.id.idorFamilyCardNo);

        submit = (TextView) findViewById(R.id.submit);
       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Questionnaire tempQuestionnaire = new Questionnaire(

                        surveyId.getText().toString(),
                        questionNo.getText().toString(),
                        province.getText().toString(),
                        district.getText().toString(),
                        commune.getText().toString(),
                        nameOfHouseholdHead.getText().toString(),
                        idorFamilyCardNo.getText().toString()

                        );

                        //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempQuestionnaire);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );



        try {
            Questionnaire questionnaire = (Questionnaire) getIntent().getSerializableExtra("data");
            if (questionnaire != null) {

                surveyId.setText(questionnaire.surveyId);
                questionNo.setText(questionnaire.questionNo);
                province.setText(questionnaire.province);
                district.setText(questionnaire.district);
                commune.setText(questionnaire.commune);
                nameOfHouseholdHead.setText(questionnaire.nameOfHouseholdHead);
                idorFamilyCardNo.setText(questionnaire.idorFamilyCardNo);


            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }

}