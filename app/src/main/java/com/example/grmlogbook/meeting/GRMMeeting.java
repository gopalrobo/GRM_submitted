package com.example.grmlogbook.meeting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.grmlogbook.AppController;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.R;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.LocaleManager;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GRMMeeting extends AppCompatActivity {

    BetterSpinner Province;
    BetterSpinner district;
    BetterSpinner commune;
    BetterSpinner village;

    private String TAG = getClass().getSimpleName();
    ProgressDialog pDialog;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";

    private String[] PROVINCE = new String[]{
            "Loading",
    };
    private String[] DISTRICT = new String[]{
            "Loading",
    };
    private String[] COMMUNE = new String[]{
            "Loading",
    };
    private String[] VILLAGE = new String[]{
            "Loading",
    };

    private RecyclerView meetinglist;
    private MeetingAdapter mRecyclerAdapterMaster;
    private ArrayList<ComplaintLetterbean> itemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_form);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        Province = (BetterSpinner) findViewById(R.id.Province);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PROVINCE);
        Province.setAdapter(provinceAdapter);
        Province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                fetchProviceData("province", PROVINCE[position], "district");
            }
        });


        district = (BetterSpinner) findViewById(R.id.district);
        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DISTRICT);
        district.setAdapter(districtAdapter);
        district.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fetchProviceData("district", DISTRICT[position], "commune");

            }
        });

        commune = (BetterSpinner) findViewById(R.id.commune);
        ArrayAdapter<String> communeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COMMUNE);
        commune.setAdapter(communeAdapter);
        commune.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fetchProviceData("commune", COMMUNE[position], "village");

            }
        });

        village = (BetterSpinner) findViewById(R.id.village);
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, VILLAGE);
        village.setAdapter(villageAdapter);


        fetchProviceData("province", "all", "province");

        meetinglist = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapterMaster = new MeetingAdapter(this, itemArrayList);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        meetinglist.setLayoutManager(thirdManager);
        meetinglist.setAdapter(mRecyclerAdapterMaster);

        getAllData();
    }

    private void getAllData() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://climatesmartcity.com/UBA/get_all_grfdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        JSONArray jsonArray = jObj.getJSONArray("datas");
                        itemArrayList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject dataObject = jsonArray.getJSONObject(i);
                                ComplaintLetterbean complaintLetterbean = new Gson().fromJson(dataObject.getString("data"), ComplaintLetterbean.class);
                                complaintLetterbean.setId(dataObject.getString("formId"));
                                complaintLetterbean.setUniId(dataObject.getString("id"));
                                complaintLetterbean.setStatus(dataObject.getString("status"));
//                                complaintLetterbean.setProjectname(dataObject.getString("projectname"));
                                itemArrayList.add(complaintLetterbean);
                            } catch (Exception e) {
                                Log.e("xxxxxxxxxxx", e.toString());
                                //   Toast.makeText(getApplicationContext(), "Some Network Error.Try after some time", Toast.LENGTH_SHORT).show();

                            }
                        }
                        mRecyclerAdapterMaster.notifyData(itemArrayList);
                    }
                } catch (Exception e) {
                    Log.e("xxxxxxxxxxx", e.toString());

                    //     Toast.makeText(getApplicationContext(), "Some Network Error.Try after some time", Toast.LENGTH_SHORT).show();

                }
                hideDialog();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),
                        "Some Network Error.Try after some time", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap localHashMap = new HashMap();
                localHashMap.put("key", "all");
                localHashMap.put("dbname", "grievanceform");
                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void fetchProviceData(final String key, final String val, final String val1) {
        this.pDialog.setMessage("fetching...");
        showDialog();
        StringRequest local16 = new StringRequest(1, "http://climatesmartcity.com/rua/students/ajax/readVillageDetails.php", new Response.Listener<String>() {
            public void onResponse(String paramString) {
                Log.d("tag", "Register Response: " + paramString.toString());
                hideDialog();
                try {
                    JSONObject localJSONObject1 = new JSONObject(paramString);
                    if (localJSONObject1.getBoolean("success")) {
                        JSONArray jsonArray = localJSONObject1.getJSONArray(AppConfig.convertKhmer("data",getApplicationContext()));

                        if (val1.equals("province")) {
                            PROVINCE = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                PROVINCE[i] = jsonArray.getString(i);
                            }
                            DISTRICT = new String[1];
                            DISTRICT[0] = "Select Province";
                            COMMUNE = new String[1];
                            COMMUNE[0] = "Select District";
                            VILLAGE = new String[1];
                            VILLAGE[0] = "Select Commune";
                            district.setText("");
                            commune.setText("");
                            village.setText("");
                        } else if (val1.equals("district")) {
                            DISTRICT = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                DISTRICT[i] = jsonArray.getString(i);
                            }
                            ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(GRMMeeting.this,
                                    android.R.layout.simple_dropdown_item_1line, DISTRICT);
                            district.setAdapter(districtAdapter);
                            COMMUNE = new String[1];
                            COMMUNE[0] = "Select District";
                            VILLAGE = new String[1];
                            VILLAGE[0] = "Select Commune";
                            district.setText("");
                            commune.setText("");
                            village.setText("");

                        } else if (val1.equals("commune")) {
                            COMMUNE = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                COMMUNE[i] = jsonArray.getString(i);
                            }
                            VILLAGE = new String[1];
                            VILLAGE[0] = "Select Commune";
                            commune.setText("");
                            village.setText("");

                        } else if (val1.equals("village")) {
                            VILLAGE = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                VILLAGE[i] = jsonArray.getString(i);
                            }
                            village.setText("");
                        }

                        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(GRMMeeting.this,
                                android.R.layout.simple_dropdown_item_1line, PROVINCE);
                        Province.setAdapter(provinceAdapter);
                        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(GRMMeeting.this,
                                android.R.layout.simple_dropdown_item_1line, DISTRICT);
                        district.setAdapter(districtAdapter);
                        ArrayAdapter<String> communeAdapter = new ArrayAdapter<String>(GRMMeeting.this,
                                android.R.layout.simple_dropdown_item_1line, COMMUNE);
                        commune.setAdapter(communeAdapter);
                        ArrayAdapter<String> villageAdapter = new ArrayAdapter<String>(GRMMeeting.this,
                                android.R.layout.simple_dropdown_item_1line, VILLAGE);
                        village.setAdapter(villageAdapter);

                        return;
                    }
                    String str = localJSONObject1.getString("data");
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
                localHashMap.put("key", key);
                localHashMap.put("val", val);
                localHashMap.put("val1", val1);


                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(local16, TAG);
    }

    private void hideDialog() {

        if (this.pDialog.isShowing()) this.pDialog.dismiss();
    }

    private void showDialog() {

        if (!this.pDialog.isShowing()) this.pDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_lan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.local_english) {
            setNewLocale(this, LocaleManager.ENGLISH);
            return true;
        }
        if (id == R.id.local_khmer) {
            setNewLocale(this, LocaleManager.KHMER);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
