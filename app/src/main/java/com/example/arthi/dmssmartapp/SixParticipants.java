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


public class SixParticipants extends AppCompatActivity  {
    EditText householdHead;
    EditText signature;
    EditText date;
    EditText witnessName;
    EditText witnessSignature;
    EditText witnessDate;
    EditText surveyNameOne;
    EditText surveySignatureOne;
    EditText surveyDateOne;
    EditText surveyNameTwo;
    EditText surveySignatureTwo;
    EditText surveyDateTwo;
    EditText surveyNameThree;
    EditText surveySignatureThree;
    EditText surveyDateThree;




    TextView submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participants);

       householdHead = (EditText) findViewById(R.id.householdHead);
       signature = (EditText) findViewById(R.id.signature);
       date = (EditText) findViewById(R.id.date);
       witnessName = (EditText) findViewById(R.id.witnessName);
       witnessSignature = (EditText) findViewById(R.id.witnessSignature);
       witnessDate = (EditText) findViewById(R.id.witnessDate);
       surveyNameOne = (EditText) findViewById(R.id.surveyNameOne);
       surveySignatureOne = (EditText) findViewById(R.id.surveySignatureOne);
       surveyDateOne = (EditText) findViewById(R.id.surveyDateOne);
       surveyNameTwo = (EditText) findViewById(R.id.surveyNameTwo);
       surveySignatureTwo = (EditText) findViewById(R.id.surveySignatureTwo);
       surveyDateTwo = (EditText) findViewById(R.id.surveyDateTwo);
       surveyNameThree = (EditText) findViewById(R.id.surveyNameThree);
       surveySignatureThree = (EditText) findViewById(R.id.surveySignatureThree);
       surveyDateThree = (EditText) findViewById(R.id.surveyDateThree);

        submit = (TextView) findViewById(R.id.submit);
       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Participants tempParticipants = new Participants(

                        householdHead.getText().toString(),
                        signature.getText().toString(),
                        date.getText().toString(),
                        witnessName.getText().toString(),
                        witnessSignature.getText().toString(),
                        witnessDate.getText().toString(),
                        surveyNameOne.getText().toString(),
                        surveySignatureOne.getText().toString(),
                        surveyDateOne.getText().toString(),
                        surveyNameTwo.getText().toString(),
                        surveySignatureTwo.getText().toString(),
                        surveyDateTwo.getText().toString(),
                        surveyNameThree.getText().toString(),
                        surveySignatureThree.getText().toString(),
                        surveyDateThree.getText().toString()
                        );

                        //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempParticipants);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );



        try {
            Participants participants = (Participants) getIntent().getSerializableExtra("data");
            if (participants != null) {

                householdHead.setText(participants.householdHead);
                signature.setText(participants.signature);
                date.setText(participants.date);
                witnessName.setText(participants.witnessName);
                witnessSignature.setText(participants.witnessSignature);
                witnessDate.setText(participants.witnessDate);
                surveyNameOne.setText(participants.surveyNameOne);
                surveySignatureOne.setText(participants.surveySignatureOne);
                surveyDateOne.setText(participants.surveyDateOne);
                surveyNameTwo.setText(participants.surveyNameTwo);
                surveySignatureTwo.setText(participants.surveySignatureTwo);
                surveyDateTwo.setText(participants.surveyDateTwo);
                surveyNameThree.setText(participants.surveyNameThree);
                surveySignatureThree.setText(participants.surveySignatureThree);
                surveyDateThree.setText(participants.surveyDateThree);


            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }

}