package com.example.grmlogbook;

import android.app.Activity;
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
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.app.AndroidMultiPartEntity;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.GlideApp;
import com.example.grmlogbook.app.Imageutils;
import com.example.grmlogbook.dp.DbFarmer;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.BetterSpinner;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.abdolahi.CircularMusicProgressBar;
import info.abdolahi.OnCircularSeekBarChangeListener;


public class InvestigateReport extends AppCompatActivity implements
        Imageutils.ImageAttachmentListener, OnItemClick {

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
    CircularMusicProgressBar projectimage;
    RegMainbean regMainbean;
    ComplaintLetterbean complaintLetterbean = null;

    private RecyclerView projectlist;
    private ArrayList<Projectbean> projectbeans = new ArrayList<>();
    ProjectReportAdapter projectAdapter;
    private Map<String, String> nameProjectMap = new HashMap<>();


    private String[] TYPE = new String[]{
            "Type A - Inquiry clarification suggestion request",
            "Type B - Social Involuntary Resettlement(Refer it to GDR / IRC GRM)",
            "Type C - Indigenous People",
            "ENV - Environment"
    };


    boolean projectSubmitClick = false;
    TextView header_view_title;
    TextView header_view_sub_title2;
    TextView projectDetail;

    ExtendedFloatingActionButton submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigate_report);

        dbFarmer = new DbFarmer(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        locationTrack = new LocationTrack(InvestigateReport.this);
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
        projectAdapter = new ProjectReportAdapter(this, projectbeans, this);
        final LinearLayoutManager addManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectlist.setLayoutManager(addManager1);
        projectlist.setAdapter(projectAdapter);

        submit = findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //step 2
                String jsonVal = new Gson().toJson(complaintLetterbean);
                getCreateTest(complaintLetterbean.getUniId(),
                        jsonVal, regMainbean.id);

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

                if(complaintLetterbean.status.equals("Investigate Report Submitted")){
                    submit.setVisibility(View.GONE);
                }
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
    }

    @Override
    public void itemProjectDetailClick(int position) {
        addImage(position);
    }

    @Override
    public void itemProjectReportDetailClick(int position) {
        addReportImage(position);
    }


    @Override
    public void itemRepresentativeClick(int position) {

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

    private void getCreateTest(final String mId, final String data, final String investigate) {
        this.pDialog.setMessage("Creating...");
        showDialog();
        StringRequest local16 = new StringRequest(1, "http://climatesmartcity.com/UBA/grmInvestigate_report.php", new Response.Listener<String>() {
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
                    localHashMap.put("id", mId);
                }
                localHashMap.put("formId", complaintLetterbean.id);
                localHashMap.put("investigate", investigate);
                localHashMap.put("data", data);
                localHashMap.put("dbname", "grievanceform");


                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(local16, TAG);
    }


    public void addImage(final int position) {
        imagePath = "";
        projectSubmitClick = false;
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(InvestigateReport.this, R.style.RoundShapeTheme);

        LayoutInflater inflater = InvestigateReport.this.getLayoutInflater();
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
        GlideApp.with(InvestigateReport.this).load(projectbean.getAttachment())
                .placeholder(R.drawable.addimage)
                .into(image);
        imagePath = projectbean.getAttachment();
        type.setText(projectbean.type);

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

        dialogBuilder.setTitle("Compliant Photos & Videos")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        b.setCancelable(false);

        WindowManager.LayoutParams lp = b.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        b.show();
    }

    public void addReportImage(final int position) {
        imagePath = "";
        projectSubmitClick = false;
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(InvestigateReport.this, R.style.RoundShapeTheme);

        LayoutInflater inflater = InvestigateReport.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addimage_report_popup, null);
        final TextInputEditText description = dialogView.findViewById(R.id.description);
        final CircularMusicProgressBar image = dialogView.findViewById(R.id.addImage);
        final TextInputEditText geotag = dialogView.findViewById(R.id.geotag);
        final TextInputLayout geotagInput = dialogView.findViewById(R.id.geotagInput);
        final TextInputLayout descriptionInput = dialogView.findViewById(R.id.descriptionInput);

        final ImageView georefresh = (ImageView) dialogView.findViewById(R.id.refresh);

        Projectbean projectbean = projectbeans.get(position);
        if (projectbean.getGeotagReport() != null) {
            geotag.setText(projectbean.getGeotagReport());
        }
        if (projectbean.getDetailReport() != null) {
            description.setText(projectbean.getDetail());
        }
        if (projectbean.getAttachmentReport() != null) {
            GlideApp.with(InvestigateReport.this).load(projectbean.getAttachmentReport())
                    .dontAnimate()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.addimage)
                    .into(image);
            imagePath = projectbean.getAttachmentReport();
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
                        GlideApp.with(InvestigateReport.this).load(imgPath)
                                .into(projectimage);
                        new UploadFileToServer().execute(imgPath);
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
                locationTrack = new LocationTrack(InvestigateReport.this);
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
            locationTrack = new LocationTrack(InvestigateReport.this);
            double latitude = locationTrack.getLatitude();
            double longitude = locationTrack.getLongitude();
            geotag.setText(latitude + "," + longitude);
        } else {
            locationTrack.showSettingsAlert();
        }
        dialogBuilder.setTitle("Report Photos & Videos").setPositiveButton("SUBMIT", null)
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
                            projectbeans.get(position).setGeotagReport(geotag.getText().toString());
                            projectbeans.get(position).setDetailReport(description.getText().toString());
                            projectbeans.get(position).setAttachmentReport(imagePath);

                            projectAdapter.notifyData(projectbeans);

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

                    } else {

                    }
                }
                break;
            case 2:
                try {
                    String message = data.getStringExtra("MESSAGE");
                    header_view_sub_title2.setText(message.split("@")[0] + "-001");
                    projectDetail.setText(message.split("@")[0] + "-" + message.split("@")[1]);
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
            if (progress[0] > 10) {
                projectimage.setValue(progress[0] - 10);
            } else {
                projectimage.setValue(progress[0]);
            }

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
                    projectimage.setValue(100);
                    GlideApp.with(InvestigateReport.this).load(filepath)
                            .into(projectimage);
                    imagePath = "http://" + AppConfig.ipcloud + "/uploads/" + imageutils.getfilename_from_path(filepath);
                } else {
                    projectimage.setValue(0);
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

}
