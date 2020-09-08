package com.example.grmlogbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.app.AndroidMultiPartEntity;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.MyDividerItemDecoration;
import com.example.grmlogbook.dp.DbFarmer;
import com.google.android.material.chip.Chip;
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
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MeetingInvitation extends AppCompatActivity implements OnInvestMeetingCheck, OnInvestMeetingClose {
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


    private RecyclerView adbList;
    private ArrayList<RegMainbean> regMainbeans = new ArrayList<>();
    private ArrayList<RegMainbean> regAllMainbeans = new ArrayList<>();
    private MeetingAttendeeAdapater adbListAdapater;
    AssignAttendeeAdapter mAdapter;
    HashSet<String> selectInvestMap = new HashSet<>();


    private RecyclerView complainantList;
    private ArrayList<RegMainbean> complainantListbeans = new ArrayList<>();
    private ArrayList<RegMainbean> complainantListAllbeans = new ArrayList<>();
    private MeetingAttendeeAdapater complainantListAdapater;
    AssignAttendeeAdapter complainantmAdapter;
    HashSet<String> selectcomplainantMap = new HashSet<>();

    private RecyclerView repList;
    private ArrayList<RegMainbean> repbeans = new ArrayList<>();
    private ArrayList<RegMainbean> repAllbeans = new ArrayList<>();
    private MeetingAttendeeAdapater repAdapater;
    AssignAttendeeAdapter repmAdapter;
    HashSet<String> selectRepMap = new HashSet<>();


    ExtendedFloatingActionButton submit;
    TextInputEditText place;
    TextInputLayout placeInput;
    TextInputEditText date;
    TextInputLayout dateInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_invitation);

        dbFarmer = new DbFarmer(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        locationTrack = new LocationTrack(MeetingInvitation.this);
        try {
            regMainbean = new Gson().fromJson(dbFarmer
                    .getDataByfarmerId(sharedpreferences.getString(AppConfig.farmerId, "")).get(1), RegMainbean.class);

        } catch (Exception e) {

        }


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


        place = findViewById(R.id.place);
        placeInput = findViewById(R.id.placeInput);
        date = findViewById(R.id.date);
        dateInput = findViewById(R.id.dateInput);

        adbList = findViewById(R.id.adbList);
        adbListAdapater = new MeetingAttendeeAdapater(this, regMainbeans, this, "1");
        final LinearLayoutManager addManager5 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adbList.setLayoutManager(addManager5);
        adbList.setAdapter(adbListAdapater);


        complainantList = findViewById(R.id.complainantList);
        complainantListAdapater = new MeetingAttendeeAdapater(this, complainantListbeans, this, "2");
        final LinearLayoutManager addManager6 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        complainantList.setLayoutManager(addManager6);
        complainantList.setAdapter(complainantListAdapater);

        repList = findViewById(R.id.representativelist);
        repAdapater = new MeetingAttendeeAdapater(this, repbeans, this, "3");
        final LinearLayoutManager addManager7 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        repList.setLayoutManager(addManager7);
        repList.setAdapter(repAdapater);

        Chip assignADB = findViewById(R.id.assignADB);
        assignADB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = MeetingInvitation.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_main_invest_list, null);
                PopupWindow window = new PopupWindow(dialogView, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);

                EditText search_field = dialogView.findViewById(R.id.search_field);

                RecyclerView result_list = dialogView.findViewById(R.id.result_list);
                mAdapter = new AssignAttendeeAdapter(MeetingInvitation.this,
                        regAllMainbeans, MeetingInvitation.this, selectInvestMap, "1");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                result_list.setLayoutManager(mLayoutManager);
                result_list.setItemAnimator(new DefaultItemAnimator());
                result_list.addItemDecoration(new
                        MyDividerItemDecoration(MeetingInvitation.this,
                        DividerItemDecoration.VERTICAL, 50));
                result_list.setAdapter(mAdapter);
                search_field.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().length() > 2) {
                            mAdapter.getFilter().filter(s.toString());
                        } else {
                            mAdapter.getFilter().filter("");
                        }
                    }
                });

                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                window.setOutsideTouchable(true);
                window.setAnimationStyle(android.R.style.Animation_Dialog);
                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setElevation(20);
                }
                window.showAsDropDown(v, 0, 0);

            }
        });


        Chip assignComplainant = findViewById(R.id.assignComplainant);
        assignComplainant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = MeetingInvitation.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_main_invest_list, null);
                PopupWindow window = new PopupWindow(dialogView, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);

                EditText search_field = dialogView.findViewById(R.id.search_field);

                RecyclerView result_list = dialogView.findViewById(R.id.result_list);
                complainantmAdapter = new AssignAttendeeAdapter(MeetingInvitation.this,
                        complainantListAllbeans, MeetingInvitation.this, selectcomplainantMap, "2");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                result_list.setLayoutManager(mLayoutManager);
                result_list.setItemAnimator(new DefaultItemAnimator());
                result_list.addItemDecoration(new
                        MyDividerItemDecoration(MeetingInvitation.this,
                        DividerItemDecoration.VERTICAL, 50));
                result_list.setAdapter(complainantmAdapter);
                search_field.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().length() > 2) {
                            complainantmAdapter.getFilter().filter(s.toString());
                        } else {
                            complainantmAdapter.getFilter().filter("");
                        }
                    }
                });

                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                window.setOutsideTouchable(true);
                window.setAnimationStyle(android.R.style.Animation_Dialog);
                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setElevation(20);
                }
                window.showAsDropDown(v, 0, 0);

            }
        });

        Chip assignRepresentative = findViewById(R.id.assignRepresentative);
        assignRepresentative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = MeetingInvitation.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_main_invest_list, null);
                PopupWindow window = new PopupWindow(dialogView, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, true);

                EditText search_field = dialogView.findViewById(R.id.search_field);

                RecyclerView result_list = dialogView.findViewById(R.id.result_list);
                repmAdapter = new AssignAttendeeAdapter(MeetingInvitation.this,
                        repAllbeans, MeetingInvitation.this, selectRepMap, "3");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                result_list.setLayoutManager(mLayoutManager);
                result_list.setItemAnimator(new DefaultItemAnimator());
                result_list.addItemDecoration(new
                        MyDividerItemDecoration(MeetingInvitation.this,
                        DividerItemDecoration.VERTICAL, 50));
                result_list.setAdapter(repmAdapter);
                search_field.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().length() > 2) {
                            repmAdapter.getFilter().filter(s.toString());
                        } else {
                            repmAdapter.getFilter().filter("");
                        }
                    }
                });

                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                window.setOutsideTouchable(true);
                window.setAnimationStyle(android.R.style.Animation_Dialog);
                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setElevation(20);
                }
                window.showAsDropDown(v, 0, 0);

            }
        });
        submit = findViewById(R.id.submit);

        place.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    placeInput.setError(null);
                } else {
                    placeInput.setError("Enter valid place");
                }
            }
        });
        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    dateInput.setError(null);
                } else {
                    dateInput.setError("Enter valid place");
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (place.getText().toString().length() <= 0) {
                    placeInput.setError("Enter valid place");
                } else if (date.getText().toString().length() <= 0) {
                    dateInput.setError("Enter valid date");
                } else {
                    placeInput.setError(null);
                    dateInput.setError(null);
                    new UploadDataToServer().execute();
                }


            }


        });


        try {
            complaintLetterbean = (ComplaintLetterbean) getIntent().getSerializableExtra("object");
            if (complaintLetterbean != null) {
                header_view_sub_title.setText(complaintLetterbean.date);
                header_view_sub_title2.setText(complaintLetterbean.compliantNo);
                projectDetail.setText(complaintLetterbean.projectname);


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
                        if (localJSONObject1.getString("dateVal") != null
                                && !localJSONObject1.getString("dateVal").equals("null")) {
                            date.setText(localJSONObject1.getString("dateVal"));
                        }
                        if (localJSONObject1.getString("place") != null
                                && !localJSONObject1.getString("place").equals("null")) {
                            place.setText(localJSONObject1.getString("place"));
                        }

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
                                    selectInvestMap.add(regAllMainbeans.get(k).getId());
                                    regMainbeans.add(regAllMainbeans.get(k));
                                }
                            }
                        }
                        adbListAdapater.notifyData(regMainbeans);

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
                                    selectcomplainantMap.add(complainantListAllbeans.get(k).getId());
                                    complainantListbeans.add(complainantListAllbeans.get(k));
                                }
                            }
                        }
                        complainantListAdapater.notifyData(complainantListbeans);


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
                                    selectRepMap.add(repAllbeans.get(k).getId());
                                    repbeans.add(repAllbeans.get(k));
                                }
                            }
                        }
                        repAdapater.notifyData(repbeans);

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


    @Override
    public void onChecked(int position, boolean isCheck, String type) {
        if (isCheck) {
            if (type.equals("1")) {
                selectInvestMap.add(regAllMainbeans.get(position).getId());
                regMainbeans.add(regAllMainbeans.get(position));
            } else if (type.equals("2")) {
                selectcomplainantMap.add(complainantListAllbeans.get(position).getId());
                complainantListbeans.add(complainantListAllbeans.get(position));
            } else if (type.equals("3")) {
                selectRepMap.add(repAllbeans.get(position).getId());
                repbeans.add(repAllbeans.get(position));
            }
        } else {
            if (type.equals("1")) {
                for (int i = 0; i < regMainbeans.size(); i++) {
                    if (regAllMainbeans.get(position).id.equals(regMainbeans.get(i).id)) {
                        if (selectInvestMap.contains(regMainbeans.get(i).getId())) {
                            selectInvestMap.remove(regMainbeans.get(i).getId());
                        }
                        regMainbeans.remove(i);
                    }
                }
            } else if (type.equals("2")) {
                for (int i = 0; i < complainantListbeans.size(); i++) {
                    if (complainantListbeans.get(position).id.equals(complainantListbeans.get(i).id)) {
                        if (selectcomplainantMap.contains(complainantListbeans.get(i).getId())) {
                            selectcomplainantMap.remove(complainantListbeans.get(i).getId());
                        }
                        complainantListbeans.remove(i);
                    }
                }
            } else if (type.equals("3")) {
                for (int i = 0; i < repbeans.size(); i++) {
                    if (repbeans.get(position).id.equals(repbeans.get(i).id)) {
                        if (selectRepMap.contains(repbeans.get(i).getId())) {
                            selectRepMap.remove(repbeans.get(i).getId());
                        }
                        repbeans.remove(i);
                    }
                }
            }
        }
        adbListAdapater.notifyData(regMainbeans);
        complainantListAdapater.notifyData(complainantListbeans);
        repAdapater.notifyData(repbeans);
    }

    @Override
    public void onClosed(int position, String type) {
        if (type.equals("1")) {
            if (selectInvestMap.contains(regMainbeans.get(position).getId())) {
                selectInvestMap.remove(regMainbeans.get(position).getId());
            }
            regMainbeans.remove(position);
            adbListAdapater.notifyData(regMainbeans);
        } else if (type.equals("2")) {
            if (selectcomplainantMap.contains(complainantListbeans.get(position).getId())) {
                selectcomplainantMap.remove(complainantListbeans.get(position).getId());
            }
            complainantListbeans.remove(position);
            complainantListAdapater.notifyData(complainantListbeans);
        } else if (type.equals("3")) {
            if (selectRepMap.contains(repbeans.get(position).getId())) {
                selectRepMap.remove(repbeans.get(position).getId());
            }
            repbeans.remove(position);
            repAdapater.notifyData(repbeans);
        }
    }

    private void hideDialog() {
        if (this.pDialog.isShowing()) this.pDialog.dismiss();
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
            HttpPost httppost = new HttpPost("http://climatesmartcity.com/UBA/grmMeeting_update.php");

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
                entity.addPart("dateVal", new StringBody(date.getText().toString()));
                entity.addPart("place", new StringBody(place.getText().toString()));
                entity.addPart("cname", new StringBody(complaintLetterbean.projectname));
                entity.addPart("cdate", new StringBody(complaintLetterbean.date));
                entity.addPart("cnumber", new StringBody(complaintLetterbean.compliantNo));
                entity.addPart("csign", new StringBody(complaintLetterbean.charSign));
                entity.addPart("level", new StringBody("First Level"));


                for (int i = 0; i < regMainbeans.size(); i++) {
                    entity.addPart("adb[]", new StringBody(regMainbeans.get(i).id));
                    entity.addPart("mail[]", new StringBody(regMainbeans.get(i).email));
                }
                if (complainantListbeans.size() > 0) {
                    for (int i = 0; i < complainantListbeans.size(); i++) {
                        entity.addPart("complainant[]", new StringBody(complainantListbeans.get(i).id));
                        entity.addPart("mail[]", new StringBody(complainantListbeans.get(i).email));
                        entity.addPart("cdistrict", new StringBody(complainantListbeans.get(i).district));
                        entity.addPart("cprovince", new StringBody(complainantListbeans.get(i).getProvince()));
                        entity.addPart("ccommune", new StringBody(complainantListbeans.get(i).getCommune()));
                    }
                } else if (complainantListAllbeans.size() > 0) {
                    entity.addPart("cdistrict", new StringBody(complainantListAllbeans.get(0).district));
                    entity.addPart("cprovince", new StringBody(complainantListAllbeans.get(0).getProvince()));
                    entity.addPart("ccommune", new StringBody(complainantListAllbeans.get(0).getCommune()));

                } else {
                    entity.addPart("cdistrict", new StringBody("not found"));
                    entity.addPart("cprovince", new StringBody("not found"));
                    entity.addPart("ccommune", new StringBody("not found"));
                }

                for (int i = 0; i < repbeans.size(); i++) {
                    entity.addPart("representative[]", new StringBody(repbeans.get(i).id));
                    entity.addPart("mail[]", new StringBody(complainantListbeans.get(i).email));
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
                JSONObject localJSONObject1 = new JSONObject(result.split("00000")[1]);
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


}

