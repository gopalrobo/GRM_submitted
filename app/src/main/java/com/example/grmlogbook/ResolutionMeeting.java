package com.example.grmlogbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
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
import com.example.grmlogbook.dp.DbFarmer;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
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
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.abdolahi.CircularMusicProgressBar;
import info.abdolahi.OnCircularSeekBarChangeListener;


public class ResolutionMeeting extends AppCompatActivity implements OnResolution, OnItemClick {
    private static final int START_SIGN_CODE = 198;
    private String TAG = getClass().getSimpleName();
    Date todaysdate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    String todaydate = format.format(todaysdate);
    public static final String mypreference = "mypref";
    ProgressDialog pDialog;
    LocationTrack locationTrack;
    SharedPreferences sharedpreferences;
    DbFarmer dbFarmer;
    RegMainbean regMainbean;
    ComplaintLetterbean complaintLetterbean = null;
    TextView header_view_title;
    TextView header_view_sub_title2;
    TextView projectDetail;


    private ChipGroup adbList;
    private ArrayList<RegMainbean> regMainbeans = new ArrayList<>();
    private ArrayList<RegMainbean> regAllMainbeans = new ArrayList<>();
    HashSet<String> selectInvestMap = new HashSet<>();


    private ChipGroup complainantList;
    private ArrayList<RegMainbean> complainantListbeans = new ArrayList<>();
    private ArrayList<RegMainbean> complainantListAllbeans = new ArrayList<>();
    HashSet<String> selectcomplainantMap = new HashSet<>();

    private ChipGroup repList;
    private ArrayList<RegMainbean> repbeans = new ArrayList<>();
    private ArrayList<RegMainbean> repAllbeans = new ArrayList<>();
    HashSet<String> selectRepMap = new HashSet<>();


    ExtendedFloatingActionButton submit;
    TextView grcTitle, representativesTitle, complainantsTitle;

    private RecyclerView projectlist;
    private ArrayList<Projectbean> projectbeans = new ArrayList<>();
    ProjectResolutionAdapter projectAdapter;

    boolean projectSubmitClick = false;
    private String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolution_meeting);

        dbFarmer = new DbFarmer(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        locationTrack = new LocationTrack(ResolutionMeeting.this);
        try {
            regMainbean = new Gson().fromJson(dbFarmer
                    .getDataByfarmerId(sharedpreferences.getString(AppConfig.farmerId, "")).get(1), RegMainbean.class);

        } catch (Exception e) {

        }
        grcTitle = findViewById(R.id.grcTitle);
        representativesTitle = findViewById(R.id.representativesTitle);
        complainantsTitle = findViewById(R.id.complainantsTitle);

        projectlist = (RecyclerView) findViewById(R.id.projectlist);
        projectAdapter = new ProjectResolutionAdapter(this, projectbeans, this, this);
        final LinearLayoutManager addManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        projectlist.setLayoutManager(addManager1);
        projectlist.setAdapter(projectAdapter);

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

        regMainbeans = new ArrayList<>();

        adbList = findViewById(R.id.adbList);
        complainantList = findViewById(R.id.complainantList);
        repList = findViewById(R.id.representativelist);

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintLetterbean.setProjectbeans(projectbeans);
                new UploadDataToServer().execute();
            }
        });


        try {
            complaintLetterbean = (ComplaintLetterbean) getIntent().getSerializableExtra("object");
            if (complaintLetterbean != null) {
                header_view_sub_title.setText(complaintLetterbean.date);
                header_view_sub_title2.setText(complaintLetterbean.compliantNo);
                projectDetail.setText(complaintLetterbean.projectname);

                projectbeans = complaintLetterbean.projectbeans;
                if (projectbeans == null || projectbeans.size() == 0) {
                    projectbeans = new ArrayList<>();
                }

                projectAdapter.notifyData(projectbeans);


                getAllInvestigators();
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


    private void showDialog() {

        if (!this.pDialog.isShowing()) this.pDialog.show();
    }

    private void getAllInvestigators() {
        this.pDialog.setMessage("Loading...");
        showDialog();
        StringRequest local16 = new StringRequest(1, "http://climatesmartcity.com/UBA/get_all_attendee.php", new Response.Listener<String>() {
            public void onResponse(String paramString) {
                Log.d("tag", "Register Response: " + paramString.toString());
                hideDialog();
                try {
                    JSONObject localJSONObject1 = new JSONObject(paramString);
                    boolean error = localJSONObject1.getBoolean("error");
                    JSONArray jsonArray = localJSONObject1.getJSONArray("datas");

                    if (!error) {


                        regAllMainbeans = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject dataObject = jsonArray.getJSONObject(i);
                                RegMainbean regMainbean = new RegMainbean();
                                regMainbean.setId(dataObject.getString("id"));
                                regMainbean.setName(dataObject.getString("name"));
                                regMainbean.setSurname(dataObject.getString("surname"));
                                regMainbean.setPosition(dataObject.getString("position"));
                                regMainbean.setSalutation(dataObject.getString("salutation"));
                                regMainbean.setMobile(dataObject.getString("mobile"));
                                regMainbean.setLevel(dataObject.getString("level"));
                                regMainbean.setCommune(dataObject.getString("commune"));
                                regMainbean.setDistrict(dataObject.getString("district"));
                                regMainbean.setEmail(dataObject.getString("email"));
                                regAllMainbeans.add(regMainbean);

                            } catch (Exception e) {
                                Log.e("xxxxxxxxxxx", e.toString());
                            }
                        }

                        JSONArray adb = localJSONObject1.getJSONArray("adb");

                        regMainbeans = new ArrayList<>();
                        for (int i = 0; i < adb.length(); i++) {
                            JSONObject jsonObject = adb.getJSONObject(i);
                            String adbId = jsonObject.getString("attendeeId");
                            for (int k = 0; k < regAllMainbeans.size(); k++) {
                                if (adbId.equals(regAllMainbeans.get(k).getId())) {
                                    regMainbeans.add(regAllMainbeans.get(k));

                                    Chip chip = new Chip(ResolutionMeeting.this);
                                    chip.setText(regAllMainbeans.get(k).getSalutation() + "." + regAllMainbeans.get(k).getName());
                                    chip.setChipStrokeWidth(2);
                                    chip.setChipBackgroundColorResource(R.color.grayLight);
                                    chip.setChipStrokeColorResource(R.color.gray);
                                    chip.setTextAppearance(R.style.chipTextAppearance);
                                    chip.setCheckable(true);
                                    chip.setTag(adbId);
                                    chip.setCheckedIconResource(R.drawable.ic_check_circle_black_24dp);
                                    chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                            if (isChecked) {
                                                chip.setChipStrokeColorResource(R.color.chip);
                                                chip.setChipBackgroundColorResource(R.color.chipLite);
                                                selectInvestMap.add((String) chip.getTag());
                                                String count = String.valueOf(
                                                        Integer.parseInt(grcTitle.getText().toString().split("/")[0]) + 1);
                                                grcTitle.setText(count + "/" + String.valueOf(regMainbeans.size()) + " Present");
                                            } else {
                                                chip.setChipStrokeColorResource(R.color.gray);
                                                chip.setChipBackgroundColorResource(R.color.grayLight);
                                                if (selectInvestMap.contains(chip.getTag())) {
                                                    selectInvestMap.remove(chip.getTag());
                                                    String count = String.valueOf(
                                                            Integer.parseInt(grcTitle.getText().toString().split("/")[0]) - 1);
                                                    grcTitle.setText(count + "/" + String.valueOf(regMainbeans.size()) + " Present");
                                                }
                                            }
                                        }
                                    });
                                    adbList.addView(chip);
                                }
                            }
                        }
                        grcTitle.setText("0/" + String.valueOf(regMainbeans.size()) + " Present");

                        complainantListAllbeans = new ArrayList<>();

                        for (int i = 0; i < complaintLetterbean.mailbeans.size(); i++) {

                            Mailbean mailbean = complaintLetterbean.mailbeans.get(i);
                            if (mailbean.name != null && mailbean.salutation != null
                                    && mailbean.surname != null && mailbean.mobile != null) {
                                RegMainbean regMainbean = new RegMainbean();
                                regMainbean.setId(mailbean.name + "," + mailbean.surname);
                                regMainbean.setName(mailbean.name);
                                regMainbean.setSurname(mailbean.surname);
                                regMainbean.setPosition("Complainant");
                                regMainbean.setSalutation(mailbean.salutation);
                                regMainbean.setMobile(mailbean.mobile);
                                regMainbean.setLevel("People");
                                regMainbean.setCommune(mailbean.commune);
                                regMainbean.setDistrict(mailbean.district);
                                regMainbean.setProvince(mailbean.getProvince());
                                regMainbean.setEmail(mailbean.email);
                                complainantListAllbeans.add(regMainbean);
                            }
                        }

                        JSONArray complainant = localJSONObject1.getJSONArray("complainant");

                        complainantListbeans = new ArrayList<>();
                        for (int i = 0; i < complainant.length(); i++) {
                            JSONObject jsonObject = complainant.getJSONObject(i);
                            String adbId = jsonObject.getString("attendeeId");
                            for (int k = 0; k < complainantListAllbeans.size(); k++) {
                                if (adbId.equals(complainantListAllbeans.get(k).getId())) {
                                    complainantListbeans.add(complainantListAllbeans.get(k));


                                    Chip chip = new Chip(ResolutionMeeting.this);
                                    chip.setText(complainantListAllbeans.get(k).getSalutation() + "." + complainantListAllbeans.get(k).getName());
                                    chip.setChipStrokeWidth(2);
                                    chip.setChipBackgroundColorResource(R.color.grayLight);
                                    chip.setChipStrokeColorResource(R.color.gray);
                                    chip.setTextAppearance(R.style.chipTextAppearance);
                                    chip.setCheckable(true);
                                    chip.setTag(adbId);
                                    chip.setCheckedIconResource(R.drawable.ic_check_circle_black_24dp);
                                    chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                            if (isChecked) {
                                                chip.setChipStrokeColorResource(R.color.chip);
                                                chip.setChipBackgroundColorResource(R.color.chipLite);
                                                selectcomplainantMap.add((String) chip.getTag());
                                                String count = String.valueOf(
                                                        Integer.parseInt(complainantsTitle.getText().toString().split("/")[0]) + 1);
                                                complainantsTitle.setText(count + "/" + String.valueOf(complainantListbeans.size()) + " Present");

                                            } else {
                                                chip.setChipStrokeColorResource(R.color.gray);
                                                chip.setChipBackgroundColorResource(R.color.grayLight);
                                                if (selectcomplainantMap.contains(chip.getTag())) {
                                                    selectcomplainantMap.remove(chip.getTag());
                                                    String count = String.valueOf(
                                                            Integer.parseInt(complainantsTitle.getText().toString().split("/")[0]) - 1);
                                                    complainantsTitle.setText(count + "/" + String.valueOf(complainantListbeans.size()) + " Present");
                                                }
                                            }
                                        }
                                    });
                                    complainantList.addView(chip);
                                }
                            }
                        }
                        complainantsTitle.setText("0/" + String.valueOf(complainantListbeans.size()) + " Present");


                        repAllbeans = new ArrayList<>();

                        for (int i = 0; i < complaintLetterbean.representativebeans.size(); i++) {

                            Mailbean mailbean = complaintLetterbean.representativebeans.get(i);
                            if (mailbean.name != null && mailbean.salutation != null
                                    && mailbean.surname != null && mailbean.mobile != null) {
                                RegMainbean regMainbean = new RegMainbean();
                                regMainbean.setId(mailbean.name + "," + mailbean.surname);
                                regMainbean.setName(mailbean.name);
                                regMainbean.setSurname(mailbean.surname);
                                regMainbean.setPosition("Complainant");
                                regMainbean.setSalutation(mailbean.salutation);
                                regMainbean.setMobile(mailbean.mobile);
                                regMainbean.setLevel("People");
                                regMainbean.setCommune(mailbean.commune);
                                regMainbean.setDistrict(mailbean.district);
                                regMainbean.setProvince(mailbean.getProvince());
                                regMainbean.setEmail(mailbean.email);
                                repAllbeans.add(regMainbean);
                            }
                        }

                        JSONArray representative = localJSONObject1.getJSONArray("representative");

                        repbeans = new ArrayList<>();
                        for (int i = 0; i < representative.length(); i++) {
                            JSONObject jsonObject = representative.getJSONObject(i);
                            String adbId = jsonObject.getString("attendeeId");
                            for (int k = 0; k < repAllbeans.size(); k++) {
                                if (adbId.equals(repAllbeans.get(k).getId())) {
                                    repbeans.add(repAllbeans.get(k));

                                    Chip chip = new Chip(ResolutionMeeting.this);
                                    chip.setText(repAllbeans.get(k).getSalutation() + "." + repAllbeans.get(k).getName());
                                    chip.setChipStrokeWidth(2);
                                    chip.setChipBackgroundColorResource(R.color.grayLight);
                                    chip.setChipStrokeColorResource(R.color.gray);
                                    chip.setTextAppearance(R.style.chipTextAppearance);
                                    chip.setCheckable(true);
                                    chip.setTag(adbId);
                                    chip.setCheckedIconResource(R.drawable.ic_check_circle_black_24dp);
                                    chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                            if (isChecked) {
                                                chip.setChipStrokeColorResource(R.color.chip);
                                                chip.setChipBackgroundColorResource(R.color.chipLite);
                                                selectRepMap.add((String) chip.getTag());
                                                String count = String.valueOf(
                                                        Integer.parseInt(representativesTitle.getText().toString().split("/")[0]) + 1);
                                                representativesTitle.setText(count + "/" + String.valueOf(repbeans.size()) + " Present");

                                            } else {
                                                chip.setChipStrokeColorResource(R.color.gray);
                                                chip.setChipBackgroundColorResource(R.color.grayLight);
                                                if (selectRepMap.contains(chip.getTag())) {
                                                    selectRepMap.remove(chip.getTag());
                                                    String count = String.valueOf(
                                                            Integer.parseInt(representativesTitle.getText().toString().split("/")[0]) - 1);
                                                    representativesTitle.setText(count + "/" + String.valueOf(repbeans.size()) + " Present");

                                                }
                                            }
                                        }
                                    });
                                    repList.addView(chip);
                                }
                            }
                        }

                        representativesTitle.setText("0/" + String.valueOf(repbeans.size()) + " Present");

                    }


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
                localHashMap.put("commune", complaintLetterbean.getMailbeans().get(0).commune);
                localHashMap.put("level", "First Level");
                localHashMap.put("compliantId", complaintLetterbean.getUniId());


                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(local16, TAG);
    }

    private void hideDialog() {
        if (this.pDialog.isShowing()) this.pDialog.dismiss();
    }

    @Override
    public void onEditClick(int position) {
        addResolution(position);
    }


    private class UploadDataToServer extends AsyncTask<Integer, Integer, String> {

        public long totalSize = 0;

        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            showDialog();
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

            // updating percentage value
            // txtPercentage.setText(String.valueOf(progress[0]) + "%");
            pDialog.setTitle(String.valueOf(progress[0]) + "%");

        }

        @Override
        protected String doInBackground(Integer... params) {

            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://climatesmartcity.com/UBA/grm_resolutionMeet.php");

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                if (complaintLetterbean != null) {
                    entity.addPart("id", new StringBody(complaintLetterbean.uniId));
                }

                entity.addPart("formId", new StringBody(complaintLetterbean.id));
                entity.addPart("meetingAssign", new StringBody(regMainbean.id));
                entity.addPart("data", new StringBody(new Gson().toJson(complaintLetterbean)));

                Iterator<String> selectInvestMapitr = selectInvestMap.iterator();
                while (selectInvestMapitr.hasNext()) {
                    entity.addPart("adb[]", new StringBody(selectInvestMapitr.next()));
                }
                Iterator<String> selectcomplainantMapitr = selectcomplainantMap.iterator();
                while (selectcomplainantMapitr.hasNext()) {
                    entity.addPart("complainant[]", new StringBody(selectcomplainantMapitr.next()));
                }
                Iterator<String> selectRepMapitr = selectRepMap.iterator();
                while (selectRepMapitr.hasNext()) {
                    entity.addPart("representative[]", new StringBody(selectRepMapitr.next()));
                }


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
            hideDialog();
            try {
                JSONObject localJSONObject1 = new JSONObject(result);
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

            // showing the server response in an alert dialog
            //showAlert(result);
            super.onPostExecute(result);
        }

    }


    public void addResolution(final int position) {

        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(ResolutionMeeting.this, R.style.RoundShapeTheme);

        LayoutInflater inflater = ResolutionMeeting.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_resolution, null);
        final TextInputEditText description = dialogView.findViewById(R.id.description);
        final TextInputLayout descriptionInput = dialogView.findViewById(R.id.descriptionInput);


        Projectbean projectbean = projectbeans.get(position);

        if (projectbean.getResolution() != null) {
            description.setText(projectbean.getResolution());
        }
        dialogBuilder.setTitle("Resolution For Compliant").setPositiveButton("SUBMIT", null)
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        b.setCancelable(false);

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    descriptionInput.setError(null);
                } else {
                    descriptionInput.setError("Enter valid Resolution");
                }
            }
        });

        b.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) b).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (description.getText().toString().length() <= 0) {
                            descriptionInput.setError("Enter valid Resolution");
                        } else {
                            projectbeans.get(position).setResolution(description.getText().toString());
                            dialogInterface.cancel();
                        }
                        projectAdapter.notifyData(projectbeans);
                    }
                });
            }
        });

        WindowManager.LayoutParams lp = b.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        b.show();
    }

    public void addImage(final int position) {
        imagePath = "";
        projectSubmitClick = false;
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(ResolutionMeeting.this, R.style.RoundShapeTheme);

        LayoutInflater inflater = ResolutionMeeting.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addimage_popup, null);
        final TextInputEditText description = dialogView.findViewById(R.id.description);
        final CircularMusicProgressBar image = dialogView.findViewById(R.id.addImage);
        final TextInputEditText geotag = dialogView.findViewById(R.id.geotag);
        final TextInputLayout geotagInput = dialogView.findViewById(R.id.geotagInput);
        final TextInputLayout descriptionInput = dialogView.findViewById(R.id.descriptionInput);
        final BetterSpinner type = dialogView.findViewById(R.id.type);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_dropdown_item_2line, R.id.item, new String[0]);
        type.setAdapter(typeAdapter);
        final TextInputLayout typelayout = dialogView.findViewById(R.id.typelayout);

        final ImageView georefresh = (ImageView) dialogView.findViewById(R.id.refresh);
        Projectbean projectbean = projectbeans.get(position);
        geotag.setText(projectbean.getGeotag());
        description.setText(projectbean.getDetail());
        GlideApp.with(ResolutionMeeting.this).load(projectbean.getAttachment())
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
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(ResolutionMeeting.this, R.style.RoundShapeTheme);

        LayoutInflater inflater = ResolutionMeeting.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addimage_report_popup, null);
        final TextInputEditText description = dialogView.findViewById(R.id.description);
        final CircularMusicProgressBar image = dialogView.findViewById(R.id.addImage);
        final TextInputEditText geotag = dialogView.findViewById(R.id.geotag);
        final TextInputLayout geotagInput = dialogView.findViewById(R.id.geotagInput);
        final TextInputLayout descriptionInput = dialogView.findViewById(R.id.descriptionInput);

        description.setEnabled(false);
        final ImageView georefresh = (ImageView) dialogView.findViewById(R.id.refresh);

        Projectbean projectbean = projectbeans.get(position);
        if (projectbean.getGeotagReport() != null) {
            geotag.setText(projectbean.getGeotagReport());
        }
        if (projectbean.getDetailReport() != null) {
            description.setText(projectbean.getDetail());
        }
        if (projectbean.getAttachmentReport() != null) {
            GlideApp.with(ResolutionMeeting.this).load(projectbean.getAttachmentReport())
                    .dontAnimate()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.addimage)
                    .into(image);
            imagePath = projectbean.getAttachmentReport();
        }

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


        dialogBuilder.setTitle("Report Photos & Videos")
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


}

