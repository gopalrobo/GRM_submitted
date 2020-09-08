package com.example.grmlogbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.app.AndroidMultiPartEntity;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.BaseActivity;
import com.example.grmlogbook.app.ComplaintreceiptPdfConfig;
import com.example.grmlogbook.app.GlideApp;
import com.example.grmlogbook.app.Imageutils;
import com.example.grmlogbook.app.LocaleManager;
import com.example.grmlogbook.dp.DbFarmer;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import info.abdolahi.CircularMusicProgressBar;
import info.abdolahi.OnCircularSeekBarChangeListener;

public class Complaintreceipt extends BaseActivity implements Imageutils.ImageAttachmentListener {

    private static final int FINE_LOCATION_CODE = 199;
    Imageutils imageutils;

    ProgressDialog pDialog;
    private Context mainActivityUser;

    private static final int START_SIGN_CODE = 198;

    public static final String mypreference = "mypref";
    public static final String dates = "dates";
    public static final String buSurveyerId = "buSurveyerIdKey";
    SharedPreferences sharedpreferences;

    public static final String staffpositionId = "staffpositionId";
    public static final String staffnameId = "staffnameId";

    private ArrayList<Projectbean> projectbeans = new ArrayList<>();
    ProjectAdapterCopy projectAdapter;
    private Map<String, String> nameProjectMap = new HashMap<>();

    EditText toaddress;
    EditText fromaddress;
    EditText date;
    EditText complaint;
    EditText personname;
    EditText name;
    EditText position;
    TextView dpname;

    private String imagePath;
    private String signPath;
    LocationTrack locationTrack;
    DbFarmer dbFarmer;

    CircularMusicProgressBar projectimage;
    ImageView signImage;

    boolean projectSubmitClick = false;



    MaterialBetterSpinner type;
    private String[] Type = new String[]{
            "Type A - Inquiry clarification suggestion request,","Type B - Complaint regarding alleged breach of the SPS 2009 or Public Communication Policy 2011,","Type C - Allegation of fraud or corruption","ENV - Environment"
    };



    TextView submit;
    private String TAG = getClass().getSimpleName();

    ComplaintLetterbean complaintLetterbean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaintreceipt);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        dbFarmer = new DbFarmer(this);

        locationTrack = new LocationTrack(Complaintreceipt.this);
        try {
            complaintLetterbean = new Gson().fromJson(dbFarmer
                    .getDataByfarmerId(sharedpreferences.getString(AppConfig.farmerId, "")).get(1), ComplaintLetterbean.class);

        } catch (Exception e) {

        }
        imageutils = new Imageutils(this);



        getSupportActionBar().setTitle("GRM");
        getSupportActionBar().setSubtitle("Compliant Receipt");

        imageutils = new Imageutils(this);

        toaddress = (EditText) findViewById(R.id.toaddress);
        fromaddress = (EditText) findViewById(R.id.fromaddress);
        date = (EditText) findViewById(R.id.date);
        complaint = (EditText) findViewById(R.id.complaint);
        personname = (EditText) findViewById(R.id.personname);
        name = (EditText) findViewById(R.id.name);
        position = (EditText) findViewById(R.id.position);
        dpname=(TextView)findViewById(R.id.dpname);

        final ImageView signImg = findViewById(R.id.signImg);
        final FloatingActionButton addImage =findViewById(R.id.addImage);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signImage = signImg;
                signpicker(0);
            }
        });


        type = (MaterialBetterSpinner) findViewById(R.id.type);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Type);
        type.setAdapter(typeAdapter);


        submit = (TextView) findViewById(R.id.submit);

//        getAllData();


       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                ComplaintLetterbean tempcomplaintLetterbean = new ComplaintLetterbean(
                        type.getText().toString());

                //step 2
                String jsonVal = new Gson().toJson(tempcomplaintLetterbean);
                Log.e("xxxxxxxxxxxxx", jsonVal);
                if (complaintLetterbean == null) {
                    tempcomplaintLetterbean.setId(String.valueOf(System.currentTimeMillis()));
                } else {
                    tempcomplaintLetterbean.setId(complaintLetterbean.id);
                }
                getCreateTest(tempcomplaintLetterbean.id, type.getText().toString());
           }

        });

        try {
            complaintLetterbean = (ComplaintLetterbean) getIntent().getSerializableExtra("object");
            if (complaintLetterbean != null) {
                ArrayList<Mailbean> mailbeans=complaintLetterbean.mailbeans;
                StringBuilder stringBuilder=new StringBuilder(1);
                for(int k=0;k<mailbeans.size();k++){
                    stringBuilder.append(mailbeans.get(k).name).append("\n");
                    stringBuilder.append(mailbeans.get(k).doornumber).append(", ");
                    stringBuilder.append(mailbeans.get(k).village).append("\n");
                    stringBuilder.append(mailbeans.get(k).commune).append("\n");
                    stringBuilder.append(mailbeans.get(k).district).append(", ");
                    stringBuilder.append(mailbeans.get(k).Province).append("\n\n");
                }
                toaddress.setText(stringBuilder.toString());
                fromaddress.setText(complaintLetterbean.adboffice);
                date.setText(complaintLetterbean.date);
                ArrayList<Projectbean> projectbeans=complaintLetterbean.projectbeans;
                StringBuilder stringBuilder1=new StringBuilder(2);
                for(int j=0;j<projectbeans.size();j++){
                    stringBuilder1.append(projectbeans.get(j).detail).append("\n");
                }
                complaint.setText(stringBuilder1.toString());
                personname.setText(sharedpreferences.getString(staffnameId,""));

                StringBuilder stringBuilder2=new StringBuilder(3);
                for(int k=0;k<mailbeans.size();k++){
                    stringBuilder2.append(mailbeans.get(k).name).append("\n");
                }
                dpname.setText(stringBuilder2.toString());
                name.setText(sharedpreferences.getString(staffnameId,""));
                position.setText(sharedpreferences.getString(staffpositionId,""));
                if(!complaintLetterbean.type.equals("Unclassified")){
                    type.setText(complaintLetterbean.type);
                }

            }
        } catch (Exception e) {
            Log.e("xxxxxx", AppConfig.convertKhmer("Something went wrong",getApplicationContext()));
        }

    }


    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {

    }



    private void getCreateTest(final String mId, final String type) {
        this.pDialog.setMessage("Creating...");
        showDialog();
        StringRequest local16 = new StringRequest(1, "http://climatesmartcity.com/UBA/grmType_update.php", new Response.Listener<String>() {
            public void onResponse(String paramString) {
                Log.d("tag", "Register Response: " + paramString.toString());
                hideDialog();
                try {
                    JSONObject localJSONObject1 = new JSONObject(paramString);
                    String str = localJSONObject1.getString(AppConfig.convertKhmer("message",getApplicationContext()));
                    if (localJSONObject1.getInt("success") == 1) {
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    return;
                } catch (JSONException localJSONException) {
                    localJSONException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError paramVolleyError) {
                Log.e("tag", "Fetch Error: " + paramVolleyError.getMessage());
                Toast.makeText(getApplicationContext(), paramVolleyError.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> localHashMap = new HashMap<String, String>();
                if (mId != null) {
                    localHashMap.put("id", mId);
                }
                localHashMap.put("id", mId);
                localHashMap.put("formId", mId);
                localHashMap.put("type", type);


                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(local16, TAG);
    }

    public void addImage(final int position) {
        imagePath = "";
        projectSubmitClick = false;
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(Complaintreceipt.this, R.style.RoundShapeTheme);

        LayoutInflater inflater = Complaintreceipt.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addimage_popup, null);
        final TextInputEditText description = dialogView.findViewById(R.id.description);
        final CircularMusicProgressBar image = dialogView.findViewById(R.id.addImage);
        final TextInputEditText geotag = dialogView.findViewById(R.id.geotag);
        final TextInputLayout geotagInput = dialogView.findViewById(R.id.geotagInput);
        final TextInputLayout descriptionInput = dialogView.findViewById(R.id.descriptionInput);

        final ImageView georefresh = (ImageView) dialogView.findViewById(R.id.refresh);
        if (position != 0) {
            Projectbean projectbean = projectbeans.get(position);
            geotag.setText(projectbean.getGeotag());
            description.setText(projectbean.getDetail());
            GlideApp.with(Complaintreceipt.this).load(projectbean.getAttachment())
                    .dontAnimate()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.addimage)
                    .into(image);
            imagePath = projectbean.getAttachment();


        }

        geotag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (projectSubmitClick && (geotag.getText().toString().length() <= 0
                            || Float.parseFloat(
                            geotag.getText().toString().split(",")[0]) <= 0 || Float.parseFloat(
                            geotag.getText().toString().split(",")[1]) <= 0)) {
                        geotagInput.setError("Enter valid Geotag");
                    } else {
                        geotagInput.setError(null);
                    }
                } catch (Exception e) {
                    geotagInput.setError("Enter valid Geotag");
                }
            }
        });

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (projectSubmitClick && (description.getText().toString().length() <= 0)) {
                        descriptionInput.setError("Enter valid Geotag");
                    } else {
                        descriptionInput.setError(null);
                    }
                } catch (Exception e) {
                    descriptionInput.setError("Enter valid Geotag");
                }
            }
        });

        // get user update
        image.setOnCircularBarChangeListener(new OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularMusicProgressBar circularBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: progress: " + progress + " / from user? " + fromUser);
            }

            @Override
            public void onClick(CircularMusicProgressBar circularBar) {
                projectimage = image;
                projectimage.setValue(0);
                imageutils.imagepicker(1);
                imageutils.setImageAttachmentListener(new Imageutils.ImageAttachmentListener() {
                    @Override
                    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
                        String path = Environment.getExternalStorageDirectory() + File.separator
                                + "ImageAttach" + File.separator;
                        if (filename != null) {
                            imageutils.createImage(file, filename, path, false);
                        }
                        String imgPath = imageutils.getPath(uri);
                        GlideApp.with(Complaintreceipt.this).load(imgPath)
                                .into(projectimage);
                        new Complaintreceipt.UploadFileToServer().execute(imgPath);
                    }
                });
            }

            @Override
            public void onLongPress(CircularMusicProgressBar circularBar) {
                Log.d(TAG, "onLongPress");
            }

        });

        georefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if GPS enabled
                locationTrack = new LocationTrack(Complaintreceipt.this);
                if (locationTrack.canGetLocation()) {

                    double latitude = locationTrack.getLatitude();
                    double longitude = locationTrack.getLongitude();

                    geotag.setText(latitude + "," + longitude);
                } else {
                    locationTrack.showSettingsAlert();
                }
            }
        });
        // check if GPS enabled
        if (locationTrack.canGetLocation()) {
            locationTrack = new LocationTrack(Complaintreceipt.this);
            double latitude = locationTrack.getLatitude();
            double longitude = locationTrack.getLongitude();
            geotag.setText(latitude + "," + longitude);
        } else {
            locationTrack.showSettingsAlert();
        }
        dialogBuilder.setTitle("Compliant Photos & Videos").setPositiveButton("SUBMIT", null)
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        b.setCancelable(false);

        b.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) b).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (image.getmProgressValue() > 0 && image.getmProgressValue() < 100) {
                            Toast.makeText(getApplicationContext(), "Image Uploading, Please wait", Toast.LENGTH_SHORT).show();
                        } else if (imagePath == null || imagePath.length() <= 0) {
                            image.setValue(0);
                            image.setBorderColor(getResources().getColor(R.color.colortint));
                            Toast.makeText(getApplicationContext(), "Please Select Image", Toast.LENGTH_SHORT).show();
                        } else if (geotag.getText().toString().length() <= 0
                                || Float.parseFloat(
                                geotag.getText().toString().split(",")[0]) <= 0 || Float.parseFloat(
                                geotag.getText().toString().split(",")[1]) <= 0) {
                            geotagInput.setError("Enter valid Geotag");
                            projectSubmitClick = true;
                        } else if (description.getText().toString().length() <= 0) {
                            descriptionInput.setError("Enter valid Description");
                            projectSubmitClick = true;
                        } else {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("geotag", geotag.getText().toString());
                                jsonObject.put("description", description.getText().toString());
                                jsonObject.put("image", imagePath);
                                String name = String.valueOf(System.currentTimeMillis());
                                if (position == 0) {
                                    Projectbean projectbean = new Projectbean(name, imagePath,
                                            description.getText().toString(), geotag.getText().toString());
                                    projectbeans.add(projectbean);
                                    projectAdapter.notifyData(projectbeans);
                                } else {
                                    projectbeans.get(position).setGeotag(geotag.getText().toString());
                                    projectbeans.get(position).setDetail(description.getText().toString());
                                    projectbeans.get(position).setAttachment(imagePath);
                                    name = projectbeans.get(position).getName();
                                    projectAdapter.notifyData(projectbeans);
                                }
                                nameProjectMap.put(name, imagePath);

                            } catch (JSONException e) {
                                Log.d(AppConfig.convertKhmer("HistoricalTimelinePhoto", getApplicationContext()), e.toString());
                            }
                            dialogInterface.cancel();
                        }

                    }
                });
            }
        });

        WindowManager.LayoutParams lp = b.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        b.show();
    }

    private void imageAttachment(int from, String filename, Bitmap file, Uri uri, CircleImageView circleImageView) {
        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        circleImageView.setMfilePath(imageutils.getPath(uri));
        circleImageView.setIsImage("false");
        if (filename != null) {
            circleImageView.setIsImage("true");
            imageutils.createImage(file, filename, path, false);
        }

        GlideApp.with(Complaintreceipt.this).load(imageutils.getPath(uri))
                .dontAnimate()
                .thumbnail(0.5f)
                .placeholder(R.drawable.file)
                .into(circleImageView);
    }

    private class UploadFileToServer extends AsyncTask<String, Integer, String> {
        public long totalSize = 0;
        String filepath;

        @Override
        protected void onPreExecute() {
            // setting progress bar to zero

            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            projectimage.setValue(progress[0]);
        }

        @Override
        protected String doInBackground(String... params) {
            filepath = params[0];

            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(AppConfig.URL_IMAGE_UPLOAD);
            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(filepath);
                // Adding file data to http body
                entity.addPart("image", new FileBody(sourceFile));

                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);

                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;

                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("Response from server: ", result);
            try {
                JSONObject jsonObject = new JSONObject(result.toString());
                if (!jsonObject.getBoolean("error")) {
                    GlideApp.with(Complaintreceipt.this).load(filepath)
                            .into(projectimage);
                    imagePath = "http://" + AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(filepath);
                } else {
                    imagePath = "";
                }
                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Image not uploaded", Toast.LENGTH_SHORT).show();
            }
            hideDialog();
            // showing the server response in an alert dialog
            //showAlert(result);


            super.onPostExecute(result);
        }

    }


    private class UploadSignToServer extends AsyncTask<String, Integer, String> {
        public long totalSize = 0;
        String filepath;

        @Override
        protected void onPreExecute() {
            // setting progress bar to zero

            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            pDialog.setMessage("Uploading..." + (String.valueOf(progress[0])));
        }

        @Override
        protected String doInBackground(String... params) {
            filepath = params[0];
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(AppConfig.URL_IMAGE_UPLOAD);
            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(filepath);
                // Adding file data to http body
                entity.addPart("image", new FileBody(sourceFile));

                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);

                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;

                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("Response from server: ", result);
            try {
                JSONObject jsonObject = new JSONObject(result.toString());
                if (!jsonObject.getBoolean("error")) {
                    GlideApp.with(Complaintreceipt.this).load(filepath)
                            .into(signImage);
                    signPath = "http://" + AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(filepath);
                } else {
                    signPath = "";
                }
                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Image not uploaded", Toast.LENGTH_SHORT).show();
            }
            hideDialog();
            // showing the server response in an alert dialog
            //showAlert(result);


            super.onPostExecute(result);
        }

    }

    public void signpicker(final int position) {

        final CharSequence[] items;


        items = new CharSequence[1];
        items[0] = "Signpad";


        android.app.AlertDialog.Builder alertdialog =
                new android.app.AlertDialog.Builder(Complaintreceipt.this);
        alertdialog.setTitle("Add Signature");
        alertdialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Signpad")) {
                    Intent intent = new Intent(Complaintreceipt.this, MainActivitySignature.class);
                    intent.putExtra("position", String.valueOf(position));
                    intent.putExtra("sign","nameOfSign");
                    startActivityForResult(intent, START_SIGN_CODE);
                }
            }
        });
        alertdialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_pdf, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.local_english) {
            setNewLocale(this, LocaleManager.ENGLISH);
            return true;
        }
        if (id == R.id.local_khmer) {
            setNewLocale(this, LocaleManager.KHMER);
            return true;
        }

        if (id == R.id.action_pdf) {
            ComplaintreceiptPdfConfig.printFunction(Complaintreceipt.this,complaintLetterbean,sharedpreferences.getString(staffnameId,""),sharedpreferences.getString(staffpositionId,""));
            return true;
        }
       return super.onOptionsItemSelected(item);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    private void hideDialog() {

        if (this.pDialog.isShowing()) this.pDialog.dismiss();
    }

    private void showDialog() {

        if (!this.pDialog.isShowing()) this.pDialog.show();
    }



}