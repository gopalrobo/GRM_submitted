package com.example.grmlogbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.grmlogbook.app.BaseActivity;
import com.example.grmlogbook.app.LocaleManager;
import com.example.grmlogbook.dp.DbFarmer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.dimorinny.floatingtextbutton.FloatingTextButton;


public class ProjectMasterSurvey extends BaseActivity {


    private ProgressDialog pDialog;
    private RecyclerView mastersList;
    DbFarmer dbFarmer;
    private ProjectMasterAdapter mRecyclerAdapterMaster;
    private ArrayList<Projectmasterbean> itemArrayList = new ArrayList<>();
    private Projectmasterbean projectmasterbean;


    public static final String mypreference = "mypref";
    public static final String buSurveyerId = "buSurveyerIdKey";
    SharedPreferences sharedpreferences;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_master);



        dbFarmer = new DbFarmer(this);

        FloatingTextButton addproject=(FloatingTextButton)findViewById(R.id.addproject);

        addproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectMasterSurvey.this, ProjectMaster.class);
                startActivity(intent);

            }
        });


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle("Project List");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);



        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mastersList = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapterMaster = new ProjectMasterAdapter(this, itemArrayList);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mastersList.setLayoutManager(thirdManager);
        mastersList.setAdapter(mRecyclerAdapterMaster);


        getAllData();
    }

    private void getAllData() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://climatesmartcity.com/UBA/grmProjrctMaster_fetch.php", new Response.Listener<String>() {
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
                                Projectmasterbean projectmasterbean = new Projectmasterbean();
                                projectmasterbean.setId(dataObject.getString("id"));
                                projectmasterbean.setProjectNumber(dataObject.getString("projectNumber"));
                                projectmasterbean.setCountry(dataObject.getString("country"));
                                projectmasterbean.setProvince(dataObject.getString("Province"));
                                projectmasterbean.setCommune(dataObject.getString("commune"));
                                projectmasterbean.setDistrict(dataObject.getString("district"));
                                projectmasterbean.setVillage(dataObject.getString("village"));
                                projectmasterbean.setImplemntingAgency(dataObject.getString("implemntingAgency"));
                                projectmasterbean.setExcecutiveAgency(dataObject.getString("excecutiveAgency"));
                                projectmasterbean.setConsultant(dataObject.getString("consultant"));
                                projectmasterbean.setContracterName(dataObject.getString("contracterName"));
                                projectmasterbean.setContracterDesignation(dataObject.getString("contracterDesignation"));
                                projectmasterbean.setContracterCompany(dataObject.getString("contracterCompany"));
                                projectmasterbean.setVilresPerson(dataObject.getString("villagePerson"));




                                itemArrayList.add(projectmasterbean);
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
                localHashMap.put("dbname", "grmProjectMaster");
                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void hideDialog() {
        if (this.pDialog.isShowing())
            this.pDialog.dismiss();
    }


    private void showDialog() {
        if (!this.pDialog.isShowing())
            this.pDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        getAllData();
        //if offline

        //  dbHelperSurvey.getAllNotes();
//        itemArrayList = new ArrayList<>();
//        ArrayList<Survey> surveys = dbHelperSurvey.getAllNotes(sharedpreferences.getString(buStudentId, ""));
//        itemArrayList = dbHelperSurvey.getStudentsNotes(sharedpreferences.getString(buStudentId, ""));
//        mRecyclerAdapterMaster.notifyData(itemArrayList);

        //online

    }


    private boolean isValidString(String string) {

        if (string == null) {
            return false;
        } else if (string.length() <= 0) {
            return false;
        } else if (string.startsWith("http")) {
            return false;
        }

        return true;
    }

    public String getfilename_from_path(String path) {
        return path.substring(path.lastIndexOf('/') + 1, path.length());

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


