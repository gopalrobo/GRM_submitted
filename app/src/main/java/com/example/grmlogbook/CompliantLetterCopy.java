package com.example.grmlogbook;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.app.AndroidMultiPartEntity;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.GlideApp;
import com.example.grmlogbook.app.Imageutils;
import com.example.grmlogbook.dp.DbFarmer;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.abdolahi.CircularMusicProgressBar;
import info.abdolahi.OnCircularSeekBarChangeListener;

import static com.example.grmlogbook.LoginActivity.buSurveyerId;


public class CompliantLetterCopy extends AppCompatActivity implements
        Imageutils.ImageAttachmentListener, OnItemClick, OnImageSelect, ProjectsAdapter.ContactsAdapterListener {
    private static final int START_SIGN_CODE = 198;
    private String TAG = getClass().getSimpleName();
    Date todaysdate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    String todaydate = format.format(todaysdate);
    public static final String mypreference = "mypref";
    Imageutils imageutils;
    ProgressDialog pDialog;
    LocationTrack locationTrack;
    SharedPreferences sharedpreferences;
    DbFarmer dbFarmer;
    private String imagePath;
    private String signPath;
    CircularMusicProgressBar projectimage;
    ImageView signImage;
    RegMainbean regMainbean;
    ComplaintLetterbean complaintLetterbean = null;

    private RecyclerView projectlist;
    private ArrayList<Projectbean> projectbeans = new ArrayList<>();
    ProjectAdapterCopy projectAdapter;
    private Map<String, String> nameProjectMap = new HashMap<>();

    private RecyclerView representativelist;
    private ArrayList<Mailbean> representativebeans = new ArrayList<>();
    RepresentativeAdapterCopy representativeAdapter;
    //mail address
    private RecyclerView mailaddresslist;
    private ArrayList<Mailbean> mailbeans = new ArrayList<>();
    MailAdapterCopy mailAdapter;
    BetterSpinner Provincetemp;
    BetterSpinner districttemp;
    BetterSpinner communetemp;
    BetterSpinner villagetemp;
    BetterSpinner Provincerep;
    BetterSpinner districtrep;
    BetterSpinner communerep;
    BetterSpinner villagerep;

    private String[] SALUTATION = new String[]{
            "Mr", "Ms", "Mrs", "Dr",
    };
    private String[] TYPE = new String[]{
            "Type A - Inquiry clarification suggestion request",
            "Type B - Social Involuntary Resettlement(Refer it to GDR / IRC GRM)",
            "Type C - Indigenous People",
            "ENV - Environment"
    };
    private String[] PROVINCEtemp = new String[]{
            "Loading",
    };
    private String[] DISTRICTtemp = new String[]{
            "Loading",
    };
    private String[] COMMUNEtemp = new String[]{
            "Loading",
    };
    private String[] VILLAGEtemp = new String[]{
            "Loading",
    };
    private String[] PROVINCErep = new String[]{
            "Loading",
    };
    private String[] DISTRICTrep = new String[]{
            "Loading",
    };
    private String[] COMMUNErep = new String[]{
            "Loading",
    };
    private String[] VILLAGErep = new String[]{
            "Loading",
    };

    ArrayList<String> mailImages = new ArrayList<>();
    PhotoSelectAdapterCopy photoSelectAdapter;
    String representText = "";
    String confidenceText = "";
    TextView confiYesText;
    TextView confiNoTxt;
    TextView represenYesText;
    TextView represenNoTxt;

    boolean projectSubmitClick = false;
    TextView header_view_title;
    TextView header_view_sub_title2;
    TextView projectDetail;


    private RecyclerView recyclerView;
    private ArrayList<Project> contactList;
    private ProjectsAdapter mAdapter;
    RoundedBottomSheetDialog mBottomSheetDialog;
    ImageView charImg;
    String charImageString = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaintlettercopy);

        dbFarmer = new DbFarmer(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        locationTrack = new LocationTrack(CompliantLetterCopy.this);
        try {
            regMainbean = new Gson().fromJson(dbFarmer
                    .getDataByfarmerId(sharedpreferences.getString(AppConfig.farmerId, "")).get(1), RegMainbean.class);

        } catch (Exception e) {

        }
        imageutils = new Imageutils(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        header_view_title = findViewById(R.id.header_view_title);
        header_view_title.setText("Compliant");
        TextView header_view_sub_title = findViewById(R.id.header_view_sub_title);
        header_view_sub_title.setText(todaydate);
        header_view_sub_title2 = findViewById(R.id.header_view_sub_title2);
        projectDetail = findViewById(R.id.projectDetail);
        header_view_sub_title2.setText("42285-013-001");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        projectlist = (RecyclerView) findViewById(R.id.projectlist);
        projectAdapter = new ProjectAdapterCopy(this, projectbeans, this);
        final LinearLayoutManager addManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        projectlist.setLayoutManager(addManager1);
        projectlist.setAdapter(projectAdapter);


        mailaddresslist = (RecyclerView) findViewById(R.id.mailaddresslist);
        mailAdapter = new MailAdapterCopy(this, mailbeans,
                this, nameProjectMap);
        final LinearLayoutManager addManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mailaddresslist.setLayoutManager(addManager);
        mailaddresslist.setAdapter(mailAdapter);

        representativelist = (RecyclerView) findViewById(R.id.representativelist);
        representativeAdapter = new RepresentativeAdapterCopy(this,
                representativebeans, this, nameProjectMap);
        final LinearLayoutManager addManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        representativelist.setLayoutManager(addManager2);
        representativelist.setAdapter(representativeAdapter);


        confiYesText = findViewById(R.id.confiYesText);
        confiNoTxt = findViewById(R.id.confiNoTxt);
        ExtendedFloatingActionButton submit = findViewById(R.id.submit);


        represenYesText = findViewById(R.id.represenYesText);
        represenNoTxt = findViewById(R.id.represenNoTxt);
        FloatingActionButton addCharImage = findViewById(R.id.addCharImage);
        charImg = findViewById(R.id.charImg);


        addCharImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signImage = charImg;
                signpicker(5,"GRC Chair Person Signature");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (charImageString.length() > 0) {
                    complaintLetterbean.setCharSign(charImageString);
                    complaintLetterbean.setProjectbeans(projectbeans);
                    //step 2
                    String jsonVal = new Gson().toJson(complaintLetterbean);
                    getCreateTest(complaintLetterbean.id, sharedpreferences.getString(buSurveyerId, ""), jsonVal, charImageString);
                } else {
                    Toast.makeText(getApplicationContext(),"Enter chair sign",Toast.LENGTH_SHORT).show();
                }
            }


        });


        try {
            complaintLetterbean = (ComplaintLetterbean) getIntent().getSerializableExtra("object");
            if (complaintLetterbean != null) {
                header_view_sub_title.setText(complaintLetterbean.date);
                //adboffice.setText(complaintLetterbean.adboffice);
                header_view_sub_title2.setText(complaintLetterbean.compliantNo);
                projectDetail.setText(complaintLetterbean.projectname);
                //suffer.setText(complaintLetterbean.suffer);
                if (complaintLetterbean.representative.equals("Yes")) {
                    represenYesText.setTextColor(Color.parseColor("#ffffff"));
                    represenYesText.setBackground(getResources().getDrawable(R.drawable.rectangle_filed));
                    represenNoTxt.setTextColor(Color.parseColor("#000000"));
                    represenNoTxt.setBackground(getResources().getDrawable(R.drawable.rectangle_trans));
                } else {
                    represenNoTxt.setTextColor(Color.parseColor("#ffffff"));
                    represenNoTxt.setBackground(getResources().getDrawable(R.drawable.rectangle_filed));
                    represenYesText.setTextColor(Color.parseColor("#000000"));
                    represenYesText.setBackground(getResources().getDrawable(R.drawable.rectangle_trans));
                }
                if (complaintLetterbean.confidential.equals("Yes")) {
                    confiYesText.setTextColor(Color.parseColor("#ffffff"));
                    confiYesText.setBackground(getResources().getDrawable(R.drawable.rectangle_filed));
                    confiNoTxt.setTextColor(Color.parseColor("#000000"));
                    confiNoTxt.setBackground(getResources().getDrawable(R.drawable.rectangle_trans));
                } else {
                    confiNoTxt.setTextColor(Color.parseColor("#ffffff"));
                    confiNoTxt.setBackground(getResources().getDrawable(R.drawable.rectangle_filed));
                    confiYesText.setTextColor(Color.parseColor("#000000"));
                    confiYesText.setBackground(getResources().getDrawable(R.drawable.rectangle_trans));
                }
                projectbeans = complaintLetterbean.projectbeans;
                if (projectbeans == null || projectbeans.size() == 0) {
                    projectbeans = new ArrayList<>();
                }

                projectAdapter.notifyData(projectbeans);
                nameProjectMap = new HashMap<>();
                for (int i = 0; i < projectbeans.size(); i++) {
                    nameProjectMap.put(projectbeans.get(i).getName(),
                            projectbeans.get(i).attachment);
                }

                mailbeans = complaintLetterbean.mailbeans;
                if (mailbeans == null || mailbeans.size() == 0) {
                    mailbeans = new ArrayList<>();
                }
                mailAdapter.notifyData(mailbeans, nameProjectMap);
                representativebeans = complaintLetterbean.representativebeans;
                if (representativebeans == null || representativebeans.size() == 0) {
                    representativebeans = new ArrayList<>();
                }

                representativeAdapter.notifyData(representativebeans, nameProjectMap);

                if(complaintLetterbean.getCharSign()==null){
                    charImageString="";
                }else{
                    charImageString=complaintLetterbean.charSign;
                }

                GlideApp.with(CompliantLetterCopy.this).load(charImageString)
                        .into(charImg);
            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemMailaddressClick(int position) {
        showMailaddressDialog(position);
    }

    @Override
    public void itemProjectDetailClick(int position) {
        addImage(position);
    }

    @Override
    public void itemProjectReportDetailClick(int position) {

    }

    @Override
    public void itemRepresentativeClick(int position) {
        if (position == 0 && representativebeans.size() > 1) {
            Toast.makeText(getApplicationContext(), "more than one representative not allowed", Toast.LENGTH_SHORT).show();
        } else {
            showRepresentativeDialog(position);
        }
    }

    @Override
    public void itemSignClick(int position) {

    }


    private void showDialog() {

        if (!this.pDialog.isShowing()) this.pDialog.show();
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {

    }

    private void getCreateTest(final String mId, final String surveyer, final String data, final String sign) {
        this.pDialog.setMessage("Creating...");
        showDialog();
        StringRequest local16 = new StringRequest(1, "http://climatesmartcity.com/UBA/grmType_update.php", new Response.Listener<String>() {
            public void onResponse(String paramString) {
                Log.d("tag", "Register Response: " + paramString.toString());
                hideDialog();
                try {
                    JSONObject localJSONObject1 = new JSONObject(paramString);
                    String str = localJSONObject1.getString(AppConfig.convertKhmer("message", getApplicationContext()));
                    if (localJSONObject1.getInt("success") == 1) {
                        Toast.makeText(getApplicationContext(), AppConfig.convertKhmer(str, getApplicationContext()), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), AppConfig.convertKhmer(str, getApplicationContext()), Toast.LENGTH_SHORT).show();
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
                    localHashMap.put("id", complaintLetterbean.getUniId());
                }
                localHashMap.put("formId", mId);
                localHashMap.put("surveyer", surveyer);
                localHashMap.put("data", data);
                localHashMap.put("sign", sign);
                localHashMap.put("ack", String.valueOf(System.currentTimeMillis()));
                localHashMap.put("dbname", "grievanceform");


                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(local16, TAG);
    }

    public void addImage(final int position) {
        imagePath = "";
        projectSubmitClick = false;
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(CompliantLetterCopy.this, R.style.RoundShapeTheme);

        LayoutInflater inflater = CompliantLetterCopy.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addimage_popup, null);
        final TextInputEditText description = dialogView.findViewById(R.id.description);
        final CircularMusicProgressBar image = dialogView.findViewById(R.id.addImage);
        final TextInputEditText geotag = dialogView.findViewById(R.id.geotag);
        final TextInputLayout geotagInput = dialogView.findViewById(R.id.geotagInput);
        final TextInputLayout descriptionInput = dialogView.findViewById(R.id.descriptionInput);
        final BetterSpinner type = dialogView.findViewById(R.id.type);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_dropdown_item_2line, R.id.item, TYPE);
        type.setAdapter(typeAdapter);
        final TextInputLayout typelayout = dialogView.findViewById(R.id.typelayout);

        final ImageView georefresh = (ImageView) dialogView.findViewById(R.id.refresh);
        Projectbean projectbean = projectbeans.get(position);
        geotag.setText(projectbean.getGeotag());
        description.setText(projectbean.getDetail());
        GlideApp.with(CompliantLetterCopy.this).load(projectbean.getAttachment())
                .placeholder(R.drawable.addimage)
                .into(image);
        imagePath = projectbean.getAttachment();
        type.setText(projectbean.type);

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

        type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (projectSubmitClick && (type.getText().toString().length() <= 0)) {
                        typelayout.setError("Enter valid type");
                    } else {
                        typelayout.setError(null);
                    }
                } catch (Exception e) {
                    typelayout.setError("Enter valid type");
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

            }

            @Override
            public void onLongPress(CircularMusicProgressBar circularBar) {
                Log.d(TAG, "onLongPress");
            }

        });

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

                        if (type.getText().toString().length() <= 0) {
                            type.setError("Enter valid Description");
                            projectSubmitClick = true;
                        } else {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("geotag", geotag.getText().toString());
                                jsonObject.put("description", description.getText().toString());
                                jsonObject.put("image", imagePath);

                                projectbeans.get(position).setGeotag(geotag.getText().toString());
                                projectbeans.get(position).setDetail(description.getText().toString());
                                projectbeans.get(position).setAttachment(imagePath);
                                projectbeans.get(position).setType(type.getText().toString());
                                projectAdapter.notifyData(projectbeans);

                                nameProjectMap.put(projectbeans.get(position).getName(), imagePath);

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

    @Override
    public void onChecked(boolean checked, int position, String name) {
        if (checked) {
            mailImages.add(name);
        } else {
            for (int i = 0; i < mailImages.size(); i++) {
                if (mailImages.get(i).equals(name)) {
                    mailImages.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onContactSelected(Project contact) {
        header_view_sub_title2.setText(contact.phone + "-001");
        projectDetail.setText(contact.phone + "-" + contact.name);
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
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
                    GlideApp.with(CompliantLetterCopy.this).load(filepath)
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
                    GlideApp.with(CompliantLetterCopy.this).load(filepath)
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

    private class UploadCharSignToServer extends AsyncTask<String, Integer, String> {
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
                    GlideApp.with(CompliantLetterCopy.this).load(filepath)
                            .into(charImg);
                    charImageString = "http://" + AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(filepath);
                } else {
                    charImageString = "";
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

    private void hideDialog() {

        if (this.pDialog.isShowing()) this.pDialog.dismiss();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (START_SIGN_CODE):
                if (resultCode == Activity.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("sign");
                    int returnPosition = Integer.parseInt(
                            data.getStringExtra("position"));
                    showDialog();
                    if (returnPosition == 5) {
                        new UploadCharSignToServer().execute(returnValue);
                    } else {
                        new UploadSignToServer().execute(returnValue);
                    }
                }
                break;
            case 2:
                try {
                    String message = data.getStringExtra("MESSAGE");
                    header_view_sub_title2.setText(message.split("@")[0] + "-001");
                    projectDetail.setText(message.split("@")[0] + "-" + message.split("@")[1]);
                    if (mBottomSheetDialog != null) {
                        mBottomSheetDialog.dismiss();
                    }
                } catch (Exception e) {

                }
            default:
                imageutils.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            imageutils.request_permission_result(requestCode, permissions, grantResults);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void showMailaddressDialog(final int position) {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(
                CompliantLetterCopy.this, R.style.RoundShapeTheme);
        LayoutInflater inflater = CompliantLetterCopy.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.mailaddress, null);

        final EditText name = (EditText) dialogView.findViewById(R.id.name);
        final EditText doornumber = (EditText) dialogView.findViewById(R.id.doornumber);
        final BetterSpinner salutation = (BetterSpinner) dialogView.findViewById(R.id.salutation);
        if (position != -1) {
            salutation.setFocusable(false);
        }
        final EditText surname = (EditText) dialogView.findViewById(R.id.surname);
        final EditText parentname = (EditText) dialogView.findViewById(R.id.parentname);
        final TextInputLayout parentnameText = (TextInputLayout) dialogView.findViewById(R.id.parentnameText);
        final RadioButton sonofRadio = dialogView.findViewById(R.id.sonofRadio);
        final RadioButton motofRadio = dialogView.findViewById(R.id.motofRadio);
        final RadioButton datofRadio = dialogView.findViewById(R.id.datofRadio);
        final ImageView signImg = dialogView.findViewById(R.id.signImg);
        final EditText mobile = dialogView.findViewById(R.id.mobile);
        final EditText email = dialogView.findViewById(R.id.email);


        mailImages = new ArrayList<>();
        RecyclerView imagelist = (RecyclerView) dialogView.findViewById(R.id.imagelist);
        photoSelectAdapter = new PhotoSelectAdapterCopy(this,
                projectbeans, this, mailImages);
        final GridLayoutManager addManager = new GridLayoutManager(this, 3);
        imagelist.setLayoutManager(addManager);
        imagelist.setAdapter(photoSelectAdapter);

        sonofRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);
                    sonofRadio.setChecked(true);
                } else {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);

                }
            }
        });
        motofRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(true);
                    datofRadio.setChecked(false);
                } else {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);

                }
            }
        });
        datofRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(true);
                } else {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);

                }
            }
        });

        final CountryCodePicker Phonedial = (CountryCodePicker) dialogView.findViewById(R.id.phonedial);


        Provincetemp = dialogView.findViewById(R.id.Province);
        TextInputLayout ProvinceLayout = dialogView.findViewById(R.id.ProvinceLayout);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PROVINCEtemp);
        Provincetemp.setAdapter(provinceAdapter);

        districttemp = dialogView.findViewById(R.id.district);
        TextInputLayout districtlayout = dialogView.findViewById(R.id.districtlayout);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DISTRICTtemp);
        districttemp.setAdapter(districtAdapter);


        TextInputLayout communelayout = dialogView.findViewById(R.id.communelayout);
        communetemp = dialogView.findViewById(R.id.commune);
        ArrayAdapter<String> communeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COMMUNEtemp);
        communetemp.setAdapter(communeAdapter);


        TextInputLayout villagelayout = dialogView.findViewById(R.id.villagelayout);
        villagetemp = dialogView.findViewById(R.id.village);
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, VILLAGEtemp);
        villagetemp.setAdapter(villageAdapter);

        ArrayAdapter<String> salutationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SALUTATION);
        salutation.setAdapter(salutationAdapter);


        Mailbean bean = mailbeans.get(position);
        name.setText(bean.name);
        surname.setText(bean.surname);
        parentname.setText(bean.parentname);
        mobile.setText(bean.mobile);
        salutation.setText(bean.salutation);
        email.setText(bean.email);
        signImage = signImg;
        signPath = bean.sign;
        if (signPath != null) {
            GlideApp.with(CompliantLetterCopy.this).load(signPath)
                    .into(signImage);
        }
        Phonedial.setCountryForNameCode(bean.country);
        doornumber.setText(bean.doornumber);
        Provincetemp.setText(bean.Province);
        districttemp.setText(bean.district);
        communetemp.setText(bean.commune);
        villagetemp.setText(bean.village);
        if (bean.images != null) {
            mailImages = bean.images;
        } else {
            mailImages = new ArrayList<>();
        }
        photoSelectAdapter.notifyData(projectbeans, mailImages);
        if (bean.relation != null) {
            if (bean.relation.equals("sonof")) {
                sonofRadio.setChecked(true);
            } else if (bean.relation.equals("datof")) {
                datofRadio.setChecked(true);
            } else if (bean.relation.equals("wifeof")) {
                motofRadio.setChecked(true);
            } else {
                sonofRadio.setChecked(true);
            }
        }


        dialogBuilder.setTitle("Complainant Address")
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
            }
        });

        WindowManager.LayoutParams lp = b.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        b.show();


    }

    public void showRepresentativeDialog(final int position) {

        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(
                CompliantLetterCopy.this, R.style.RoundShapeTheme);
        LayoutInflater inflater = CompliantLetterCopy.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.mailaddress, null);

        final EditText name = (EditText) dialogView.findViewById(R.id.name);
        final EditText doornumber = (EditText) dialogView.findViewById(R.id.doornumber);

        final EditText salutation = (EditText) dialogView.findViewById(R.id.salutation);
        if (position != -1) {
            salutation.setFocusable(false);
        }


        final EditText surname = (EditText) dialogView.findViewById(R.id.surname);
        final EditText parentname = (EditText) dialogView.findViewById(R.id.parentname);
        final TextInputLayout parentnameText = (TextInputLayout) dialogView.findViewById(R.id.parentnameText);
        final RadioButton sonofRadio = dialogView.findViewById(R.id.sonofRadio);
        final RadioButton motofRadio = dialogView.findViewById(R.id.motofRadio);
        final RadioButton datofRadio = dialogView.findViewById(R.id.datofRadio);
        final ImageView signImg = dialogView.findViewById(R.id.signImg);
        final FloatingActionButton addImage = dialogView.findViewById(R.id.addImage);


        mailImages = new ArrayList<>();


        sonofRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sonofRadio.setChecked(true);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);
                    parentnameText.setHint(AppConfig.convertKhmer("Father/Mother Name", getApplicationContext()));
                } else {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);
                }
            }
        });
        motofRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(true);
                    datofRadio.setChecked(false);
                    parentnameText.setHint(AppConfig.convertKhmer("Son/Daughter Name", getApplicationContext()));
                } else {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);
                }
            }
        });
        datofRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(true);
                    parentnameText.setHint(AppConfig.convertKhmer("Father/Mother Name", getApplicationContext()));
                } else {
                    sonofRadio.setChecked(false);
                    motofRadio.setChecked(false);
                    datofRadio.setChecked(false);
                }
            }
        });

        final CountryCodePicker Phonedial = (CountryCodePicker) dialogView.findViewById(R.id.phonedial);
        final EditText mobile = (EditText) dialogView.findViewById(R.id.mobile);
        final EditText email = (EditText) dialogView.findViewById(R.id.email);
        Provincerep = dialogView.findViewById(R.id.Province);
        TextInputLayout ProvinceLayout = dialogView.findViewById(R.id.ProvinceLayout);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PROVINCErep);
        Provincerep.setAdapter(provinceAdapter);


        districtrep = dialogView.findViewById(R.id.district);
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DISTRICTrep);
        districtrep.setAdapter(districtAdapter);


        communerep = dialogView.findViewById(R.id.commune);
        ArrayAdapter<String> communeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COMMUNErep);
        communerep.setAdapter(communeAdapter);


        villagerep = dialogView.findViewById(R.id.village);
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, VILLAGErep);
        villagerep.setAdapter(villageAdapter);

        Mailbean bean = representativebeans.get(position);
        name.setText(bean.name);
        surname.setText(bean.surname);
        parentname.setText(bean.parentname);
        mobile.setText(bean.mobile);
        salutation.setText(bean.salutation);
        email.setText(bean.email);
        Phonedial.setCountryForNameCode(bean.country);
        signImage = signImg;
        signPath = bean.sign;
        if (signPath != null) {
            GlideApp.with(CompliantLetterCopy.this).load(signPath)
                    .into(signImage);
        }
        if (bean.images != null) {
            mailImages = bean.images;
        } else {
            mailImages = new ArrayList<>();
        }

        doornumber.setText(bean.doornumber);
        Provincerep.setText(bean.Province);
        districtrep.setText(bean.district);
        communerep.setText(bean.commune);
        villagerep.setText(bean.village);

        if (bean.relation != null) {
            if (bean.relation.equals("sonof")) {
                sonofRadio.setChecked(true);
            } else if (bean.relation.equals("datof")) {
                datofRadio.setChecked(true);
            } else if (bean.relation.equals("wifeof")) {
                motofRadio.setChecked(true);
            } else {
                sonofRadio.setChecked(true);
            }
        }


        dialogBuilder.setTitle("Representative Address")
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

            }
        });

        WindowManager.LayoutParams lp = b.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        b.show();
    }


    public void signpicker(final int position,String nameOfSign) {

        final CharSequence[] items;


        items = new CharSequence[1];
        items[0] = "Signpad";

        android.app.AlertDialog.Builder alertdialog =
                new android.app.AlertDialog.Builder(CompliantLetterCopy.this);
        alertdialog.setTitle("Add Signature");
        alertdialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Signpad")) {
                    Intent intent = new Intent(CompliantLetterCopy.this, MainActivitySignature.class);
                    intent.putExtra("position", String.valueOf(position));
                    intent.putExtra("sign",nameOfSign);
                    startActivityForResult(intent, START_SIGN_CODE);
                }
            }
        });
        alertdialog.show();
    }

}
