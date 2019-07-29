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

public class FourImpact extends AppCompatActivity{
    EditText otherLivelihood;
    EditText businessShop;
    EditText businessFood;
    EditText businessMechanic;
    EditText businessCafé;
    EditText businessOther;
    EditText membersMale;
    EditText membersFemale;
    EditText earnings;
    EditText businessRegistered;
   


   
    TextView submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otherlivelihood);

        otherLivelihood = (EditText) findViewById(R.id.otherLivelihood);
        businessShop = (EditText) findViewById(R.id.businessShop);
        businessFood = (EditText) findViewById(R.id.businessFood);
        businessMechanic = (EditText) findViewById(R.id.businessMechanic);
        businessCafé = (EditText) findViewById(R.id.businessCafé);
        businessOther = (EditText) findViewById(R.id.businessOther);
        membersMale = (EditText) findViewById(R.id.membersMale);
        membersFemale = (EditText) findViewById(R.id.membersFemale);
        earnings = (EditText) findViewById(R.id.earnings);
        businessRegistered = (EditText) findViewById(R.id.businessRegistered);
        

        submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OtherLivelihood tempOtherLivelihood = new OtherLivelihood(

                        otherLivelihood.getText().toString(),
                        businessShop.getText().toString(),
                        businessFood.getText().toString(),
                        businessMechanic.getText().toString(),
                        businessCafé.getText().toString(),
                        businessOther.getText().toString(),
                        membersMale.getText().toString(),
                        membersFemale.getText().toString(),
                        earnings.getText().toString(),
                        businessRegistered.getText().toString()

                        );

                        //step 2
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",tempOtherLivelihood);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }



        } );



        try {
            OtherLivelihood otherLive = (OtherLivelihood) getIntent().getSerializableExtra("data");
            if (otherLive != null) {

               otherLivelihood.setText(otherLive.otherLivelihood);
               businessShop.setText(otherLive.businessShop);
               businessFood.setText(otherLive.businessFood);
               businessMechanic.setText(otherLive.businessMechanic);
               businessCafé.setText(otherLive.businessCafé);
               businessOther.setText(otherLive.businessOther);
               membersMale.setText(otherLive.membersMale);
               membersFemale.setText(otherLive.membersFemale);
               earnings.setText(otherLive.earnings);
               businessRegistered.setText(otherLive.businessRegistered);

               
            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }

}