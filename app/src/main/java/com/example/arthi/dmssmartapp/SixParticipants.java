package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.example.arthi.dmssmartapp.MainActivityAllSurvey.mypreference;


public class SixParticipants extends AppCompatActivity  implements Imageutils.ImageAttachmentListener{

    private static final int FINE_LOCATION_CODE = 199;
    Imageutils imageutils;

    ProgressDialog pDialog;

    EditText householdHead;
    EditText date;
    EditText witnessName;
    EditText witnessDate;
    EditText surveyNameOne;
    EditText surveyDateOne;
    EditText surveyNameTwo;
    EditText surveyDateTwo;
    EditText surveyNameThree;
    EditText surveyDateThree;

    CircleImageView signature;
    CircleImageView cancelsign;

    CircleImageView witnessSignature;
    CircleImageView cancelwitnessSignature;

    CircleImageView surveySignatureOne;
    CircleImageView cancelsurveySignatureOne;

    CircleImageView surveySignatureTwo;
    CircleImageView cancelsurveySignatureTwo;

    CircleImageView surveySignatureThree;
    CircleImageView cancelsurveySignatureThree;

    TextView submit;

    public static final String mypreference = "mypref";
    public static final String buSurveyerId = "buSurveyerIdKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participants);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        imageutils = new Imageutils(this);

       householdHead = (EditText) findViewById(R.id.householdHead);
       signature = (CircleImageView) findViewById(R.id.signature);
       cancelsign= (CircleImageView) findViewById(R.id.cancelsign);
       date = (EditText) findViewById(R.id.date);
       witnessName = (EditText) findViewById(R.id.witnessName);
       witnessSignature = (CircleImageView) findViewById(R.id.witnessSignature);
        cancelwitnessSignature= (CircleImageView) findViewById(R.id.cancelwitnessSignature);
       witnessDate = (EditText) findViewById(R.id.witnessDate);
       surveyNameOne = (EditText) findViewById(R.id.surveyNameOne);
       surveySignatureOne = (CircleImageView) findViewById(R.id.surveySignatureOne);
        cancelsurveySignatureOne= (CircleImageView) findViewById(R.id.cancelsurveySignatureOne);
       surveyDateOne = (EditText) findViewById(R.id.surveyDateOne);
       surveyNameTwo = (EditText) findViewById(R.id.surveyNameTwo);
       surveySignatureTwo = (CircleImageView) findViewById(R.id.surveySignatureTwo);
        cancelsurveySignatureTwo= (CircleImageView) findViewById(R.id.cancelsurveySignatureTwo);
       surveyDateTwo = (EditText) findViewById(R.id.surveyDateTwo);
       surveyNameThree = (EditText) findViewById(R.id.surveyNameThree);
       surveySignatureThree = (CircleImageView) findViewById(R.id.surveySignatureThree);
        cancelsurveySignatureThree= (CircleImageView) findViewById(R.id.cancelsurveySignatureThree);
       surveyDateThree = (EditText) findViewById(R.id.surveyDateThree);

        signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageutils.imagepicker(1);
                imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                    @Override
                    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                        imageAttachment(from, filename, file, uri, signature);
                    }
                });
            }
        });
        cancelsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideApp.with(SixParticipants.this).load(R.drawable.file)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(signature);
            }
        });

        witnessSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageutils.imagepicker(1);
                imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                    @Override
                    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                        imageAttachment(from, filename, file, uri, witnessSignature);
                    }
                });
            }
        });
        cancelwitnessSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideApp.with(SixParticipants.this).load(R.drawable.file)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(witnessSignature);
            }
        });

        surveySignatureOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageutils.imagepicker(1);
                imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                    @Override
                    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                        imageAttachment(from, filename, file, uri, surveySignatureOne);
                    }
                });
            }
        });
        cancelsurveySignatureOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideApp.with(SixParticipants.this).load(R.drawable.file)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(surveySignatureOne);
            }
        });

        surveySignatureTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageutils.imagepicker(1);
                imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                    @Override
                    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                        imageAttachment(from, filename, file, uri, surveySignatureTwo);
                    }
                });
            }
        });
        cancelsurveySignatureTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideApp.with(SixParticipants.this).load(R.drawable.file)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(surveySignatureTwo);
            }
        });

        surveySignatureThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageutils.imagepicker(1);
                imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                    @Override
                    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                        imageAttachment(from, filename, file, uri, surveySignatureThree);
                    }
                });
            }
        });
        cancelsurveySignatureThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlideApp.with(SixParticipants.this).load(R.drawable.file)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(surveySignatureThree);
            }
        });

        submit = (TextView) findViewById(R.id.submit);
       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Participants tempParticipants = new Participants(

                        householdHead.getText().toString(),
                        signature.getMfilePath(),
                        date.getText().toString(),
                        witnessName.getText().toString(),
                        witnessSignature.getMfilePath(),
                        witnessDate.getText().toString(),
                        surveyNameOne.getText().toString(),
                        surveySignatureOne.getMfilePath(),
                        surveyDateOne.getText().toString(),
                        surveyNameTwo.getText().toString(),
                        surveySignatureTwo.getMfilePath(),
                        surveyDateTwo.getText().toString(),
                        surveyNameThree.getText().toString(),
                        surveySignatureThree.getMfilePath(),
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
                signature = (CircleImageView) findViewById(R.id.signature);
                cancelsign= (CircleImageView) findViewById(R.id.cancelsign);
                date.setText(participants.date);
                witnessName.setText(participants.witnessName);
                witnessSignature = (CircleImageView) findViewById(R.id.witnessSignature);
                cancelwitnessSignature= (CircleImageView) findViewById(R.id.cancelwitnessSignature);
                witnessDate.setText(participants.witnessDate);
                surveyNameOne.setText(participants.surveyNameOne);
                surveySignatureOne = (CircleImageView) findViewById(R.id.surveySignatureOne);
                cancelsurveySignatureOne= (CircleImageView) findViewById(R.id.cancelsurveySignatureOne);
                surveyDateOne.setText(participants.surveyDateOne);
                surveyNameTwo.setText(participants.surveyNameTwo);
                surveySignatureTwo = (CircleImageView) findViewById(R.id.surveySignatureTwo);
                cancelsurveySignatureTwo= (CircleImageView) findViewById(R.id.cancelsurveySignatureTwo);
                surveyDateTwo.setText(participants.surveyDateTwo);
                surveyNameThree.setText(participants.surveyNameThree);
                surveySignatureThree = (CircleImageView) findViewById(R.id.surveySignatureThree);
                cancelsurveySignatureThree= (CircleImageView) findViewById(R.id.cancelsurveySignatureThree);
                surveyDateThree.setText(participants.surveyDateThree);


            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

    }

    private void imageAttachment(int from, String filename, Bitmap file, Uri uri, CircleImageView circleImageView) {
        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        circleImageView.setMfilePath(imageutils.getPath(uri));
        circleImageView.setIsImage("false");
        if (filename != null) {
            circleImageView.setIsImage("true");
            imageutils.createImage(file, filename, path, false);
        }

        GlideApp.with(SixParticipants.this).load(imageutils.getPath(uri))
                .dontAnimate()
                .thumbnail(0.5f)
                .placeholder(R.drawable.file)
                .into(circleImageView);
    }

    private void hideDialog() {

        if (this.pDialog.isShowing()) this.pDialog.dismiss();
    }

    private void showDialog() {

        if (!this.pDialog.isShowing()) this.pDialog.show();
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);
    }
}