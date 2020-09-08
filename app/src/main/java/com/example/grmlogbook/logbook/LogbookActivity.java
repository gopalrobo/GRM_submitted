package com.example.grmlogbook.logbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.grmlogbook.AppController;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.R;
import com.example.grmlogbook.app.BaseActivity;
import com.example.grmlogbook.app.LocaleManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LogbookActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private RecyclerView loglist;
    private LogbookAdapter mRecyclerAdapterMaster;
    private ArrayList<ComplaintLetterbean> itemArrayList = new ArrayList<>();



    public static final String mypreference = "mypref";
    public static final String dbval = "sensors";
    public static final String buSurveyerId = "buSurveyerIdKey";
    SharedPreferences sharedpreferences;

    public static final String staffpositionId = "staffpositionId";
    public static final String staffnameId = "staffnameId";

    EditText project;
    EditText focalLocation;
    EditText focalPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        project=(EditText)findViewById(R.id.project);
        focalPerson=(EditText)findViewById(R.id.focalPerson);

        focalPerson.setText(sharedpreferences.getString(staffnameId,""));


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        loglist = (RecyclerView) findViewById(R.id.loglist);
        mRecyclerAdapterMaster = new LogbookAdapter(this, itemArrayList);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        loglist.setLayoutManager(thirdManager);
        loglist.setAdapter(mRecyclerAdapterMaster);

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
                        for (int i = 0; i < 10; i++) {
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

    private void hideDialog() {
        if (this.pDialog.isShowing())
            this.pDialog.dismiss();
    }


    private void showDialog() {
        if (!this.pDialog.isShowing())
            this.pDialog.show();
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
