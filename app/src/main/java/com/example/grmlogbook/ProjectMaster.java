package com.example.grmlogbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.LocaleManager;
import com.example.grmlogbook.app.MyDividerItemDecoration;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectMaster extends AppCompatActivity implements ProjectsAdapter.ContactsAdapterListener {

    ProgressDialog pDialog;

    TextInputLayout provinceLayout,districtLayout,communeLayout,villageLayout,implemntingLayout,executiveLayout,
            consultantLayout,contnameLayout,contdesignLayout,contcomLayout,vilresLayout;

    MaterialBetterSpinner Province;
    MaterialBetterSpinner district;
    MaterialBetterSpinner commune;
    MaterialBetterSpinner village;

    CountryCodePicker country;
    EditText implemntingAgency;
    EditText excecutiveAgency;
    EditText consultant;
    EditText contracterName;
    EditText contracterDesignation;
    EditText contracterCompany;
    EditText vilresPerson;

    Projectmasterbean Projectmasterbean = null;



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
    private String TAG = getClass().getSimpleName();

    TextView submit;

    private RecyclerView recyclerView;
    private ArrayList<Project> contactList;
    private ProjectsAdapter mAdapter;
    RoundedBottomSheetDialog mBottomSheetDialog;

    TextView projectDetail;
    String projectName = "Integrated Urban Environmental Management in the Tonle Sap Basin";
    String projectCode = "42285-013";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project_master);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        provinceLayout=(TextInputLayout)findViewById(R.id.provinceLayout);
        districtLayout=(TextInputLayout)findViewById(R.id.districtLayout);
        communeLayout=(TextInputLayout)findViewById(R.id.communeLayout);
        villageLayout=(TextInputLayout)findViewById(R.id.villageLayout);
        implemntingLayout=(TextInputLayout)findViewById(R.id.implemntingLayout);
        executiveLayout=(TextInputLayout)findViewById(R.id.executiveLayout);
        consultantLayout=(TextInputLayout)findViewById(R.id.consultantLayout);
        contnameLayout=(TextInputLayout)findViewById(R.id.contnameLayout);
        contdesignLayout=(TextInputLayout)findViewById(R.id.contdesignLayout);
        contcomLayout=(TextInputLayout)findViewById(R.id.contcomLayout);
        vilresLayout=(TextInputLayout)findViewById(R.id.vilresLayout);

        country = (CountryCodePicker) findViewById(R.id.country);
        implemntingAgency = (EditText) findViewById(R.id.implemntingAgency);
        excecutiveAgency = (EditText) findViewById(R.id.excecutiveAgency);
        consultant = (EditText) findViewById(R.id.consultant);
        contracterName = (EditText) findViewById(R.id.contracterName);
        contracterDesignation = (EditText) findViewById(R.id.contracterDesignation);
        contracterCompany = (EditText) findViewById(R.id.contracterCompany);
        vilresPerson = (EditText) findViewById(R.id.vilresPerson);

        projectDetail = findViewById(R.id.projectDetail);

        LinearLayout projectDetailLinear = findViewById(R.id.projectDetailLinear);
        projectDetailLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBottomSheetDialog = new RoundedBottomSheetDialog(ProjectMaster.this);
                LayoutInflater inflater = ProjectMaster.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_main_project_list, null);

                EditText search_field = dialogView.findViewById(R.id.search_field);

                recyclerView = dialogView.findViewById(R.id.result_list);
                contactList = new ArrayList<>();
                contactList.add(new Project("Integrated Urban Environmental Management in the Tonle Sap Basin Project", "", "42285-013"));
                contactList.add(new Project("Integrated Urban Environmental Management in the Tonle Sap Basin", "", "42285-012"));
                contactList.add(new Project("Irrigated Agriculture Improvement Project", "", "51159-002"));
                contactList.add(new Project("Power Network Development Project", "", "50020-002"));
                contactList.add(new Project("Southeast Asia Transport Project Preparatory Facility", "", "52084-001"));
                contactList.add(new Project("Agricultural Value Chain Infrastructure Improvement Project", "", "50264-001"));
                contactList.add(new Project("Provincial Water Supply and Sanitation Project", "", "48158-002"));
                contactList.add(new Project("Strengthening Capacity for Improved Implementation of Externally Funded Projects in Cambodia", "", "50111-001"));
                contactList.add(new Project("Greater Mekong Subregion Health Security Project", "", "48118-002"));
                contactList.add(new Project("Skills for Competitiveness Project", "", "50394-001"));
                contactList.add(new Project("Provincial Water Supply and Sanitation Project", "", "48158-002"));
                mAdapter = new ProjectsAdapter(ProjectMaster.this, contactList, ProjectMaster.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new
                        MyDividerItemDecoration(ProjectMaster.this,
                        DividerItemDecoration.VERTICAL, 50));
                recyclerView.setAdapter(mAdapter);
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
                mBottomSheetDialog.setContentView(dialogView);
                mBottomSheetDialog.show();

            }
        });

        Province = (MaterialBetterSpinner) findViewById(R.id.Province);
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PROVINCE);
        Province.setAdapter(provinceAdapter);
        Province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                provinceLayout.setError(null);
                fetchProviceData("province", PROVINCE[position], "district");
            }
        });

        district = (MaterialBetterSpinner) findViewById(R.id.district);
       ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DISTRICT);
        district.setAdapter(districtAdapter);
        district.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                districtLayout.setError(null);
                fetchProviceData("district", DISTRICT[position], "commune");

            }
        });

        commune = (MaterialBetterSpinner) findViewById(R.id.commune);
        ArrayAdapter<String> communeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COMMUNE);
        commune.setAdapter(communeAdapter);
        commune.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                communeLayout.setError(null);
                fetchProviceData("commune", COMMUNE[position], "village");

            }
        });

        village = (MaterialBetterSpinner) findViewById(R.id.village);
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, VILLAGE);
        village.setAdapter(villageAdapter);
        village.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                villageLayout.setError(null);
            }
        });



        fetchProviceData("province", "all", "province");

        submit = (TextView) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Province.getText().toString().length() <= 0) {
                    provinceLayout.setError("Select the Province");
                }else if (district.getText().toString().length() <= 0) {
                    districtLayout.setError("Select the District");
                }else if (commune.getText().toString().length() <= 0) {
                    communeLayout.setError("Select the Commune");
                }else if (village.getText().toString().length() <= 0) {
                    villageLayout.setError("Select the Village");
                }else if (implemntingAgency.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Select the Implementing Agency", Toast.LENGTH_SHORT).show();
                }else if (excecutiveAgency.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(),"Select the Executive Agency", Toast.LENGTH_SHORT).show();
                }else if (consultant.getText().toString().length() <= 0) {
                    consultantLayout.setError("Enter the Consultant");
                } else if (contracterName.getText().toString().length() <= 0) {
                    contnameLayout.setError("Enter the Contracter Name");
                } else if (contracterDesignation.getText().toString().length() <= 0) {
                    contdesignLayout.setError("Enter the Contracter Designation");
                } else if (contracterCompany.getText().toString().length() <= 0) {
                    contcomLayout.setError("Enter the Contracter Company");
                } else if (vilresPerson.getText().toString().length() <= 0) {
                    vilresLayout.setError("Enter the Village Responsible Person");
                } else {

                    Projectmasterbean tempProjectmasterbean = new Projectmasterbean(

                            projectCode,
                            projectName,
                            country.getSelectedCountryName(),
                            Province.getText().toString(),
                            district.getText().toString(),
                            commune.getText().toString(),
                            village.getText().toString(),
                            implemntingAgency.getText().toString(),
                            excecutiveAgency.getText().toString(),
                            consultant.getText().toString(),
                            contracterName.getText().toString(),
                            contracterDesignation.getText().toString(),
                            contracterCompany.getText().toString(),
                            vilresPerson.getText().toString());


                    String jsonVal = new Gson().toJson(tempProjectmasterbean);
                    Log.e("xxxxxxxxxxxxx", jsonVal);
                    if (Projectmasterbean == null) {
                        tempProjectmasterbean.setId(String.valueOf(System.currentTimeMillis()));
                    } else {
                        tempProjectmasterbean.setId(Projectmasterbean.id);
                    }


                    getCreateTest(tempProjectmasterbean.id,
                            projectCode,
                            projectName,
                            country.getSelectedCountryName(),
                            Province.getText().toString(),
                            district.getText().toString(),
                            commune.getText().toString(),
                            village.getText().toString(),
                            implemntingAgency.getText().toString(),
                            excecutiveAgency.getText().toString(),
                            consultant.getText().toString(),
                            contracterName.getText().toString(),
                            contracterDesignation.getText().toString(),
                            contracterCompany.getText().toString(),
                            vilresPerson.getText().toString());


                }
            }
        });
        try {
            Projectmasterbean = (Projectmasterbean) getIntent().getSerializableExtra("object");
            if (Projectmasterbean != null) {

                projectName=Projectmasterbean.name;
                if(projectName==null){
                    projectName="";
                }
                projectCode=Projectmasterbean.projectNumber;
                country.setCountryForNameCode(Projectmasterbean.country);
                Province.setText(Projectmasterbean.Province);
                district.setText(Projectmasterbean.district);
                commune.setText(Projectmasterbean.commune);
                village.setText(Projectmasterbean.village);
                implemntingAgency.setText(Projectmasterbean.implemntingAgency);
                excecutiveAgency.setText(Projectmasterbean.excecutiveAgency);
                consultant.setText(Projectmasterbean.consultant);
                contracterName.setText(Projectmasterbean.contracterName);
                contracterDesignation.setText(Projectmasterbean.contracterDesignation);
                contracterCompany.setText(Projectmasterbean.contracterCompany);
                vilresPerson.setText(Projectmasterbean.vilresPerson);

            }

        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }

        Intent intent = new Intent(ProjectMaster.this, LoginActivity.class);
    }

    private void getCreateTest(final String mId, final String projectNumber, final String name,final String country, final String Province, final String district, final String commune, final String village, final String implemntingAgency, final String excecutiveAgency, final String consultant, final String contracterName, final String contracterDesignation, final String contracterCompany, final String vilresPerson) {
        this.pDialog.setMessage("Creating...");
        showDialog();
        StringRequest local16 = new StringRequest(1, "http://climatesmartcity.com/UBA/grmProjrctMaster_add.php", new Response.Listener<String>() {
            public void onResponse(String paramString) {
                Log.d("tag", "Register Response: " + paramString.toString());
                hideDialog();
                try {
                    JSONObject localJSONObject1 = new JSONObject(paramString);
                    String str = localJSONObject1.getString(AppConfig.convertKhmer("message", getApplicationContext()));
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


            }
        }) {
            protected Map<String, String> getParams() {
                HashMap<String, String> localHashMap = new HashMap<String, String>();
                if (mId != null) {
                    localHashMap.put("id", mId);
                }
                localHashMap.put("projectName", name);
                localHashMap.put("projectNumber", projectNumber);
                localHashMap.put("country", country);
                localHashMap.put("Province", Province);
                localHashMap.put("district", district);
                localHashMap.put("commune", commune);
                localHashMap.put("village", village);
                localHashMap.put("implemntingAgency", implemntingAgency);
                localHashMap.put("excecutiveAgency", excecutiveAgency);
                localHashMap.put("consultant", consultant);
                localHashMap.put("contracterName", contracterName);
                localHashMap.put("contracterDesignation", contracterDesignation);
                localHashMap.put("contracterCompany", contracterCompany);
                localHashMap.put("villagePerson", vilresPerson);


                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(local16, TAG);
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
                        JSONArray jsonArray = localJSONObject1.getJSONArray(AppConfig.convertKhmer("data", getApplicationContext()));

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

                            try {
                                Projectmasterbean = (Projectmasterbean) getIntent().getSerializableExtra("object");
                                if (Projectmasterbean != null) {

                                    projectName=Projectmasterbean.name;
                                    if(projectName==null){
                                        projectName="";
                                    }
                                    projectCode=Projectmasterbean.projectNumber;
                                    country.setCountryForNameCode(Projectmasterbean.country);
                                    Province.setText(Projectmasterbean.Province);
                                    district.setText(Projectmasterbean.district);
                                    commune.setText(Projectmasterbean.commune);
                                    village.setText(Projectmasterbean.village);
                                    implemntingAgency.setText(Projectmasterbean.implemntingAgency);
                                    excecutiveAgency.setText(Projectmasterbean.excecutiveAgency);
                                    consultant.setText(Projectmasterbean.consultant);
                                    contracterName.setText(Projectmasterbean.contracterName);
                                    contracterDesignation.setText(Projectmasterbean.contracterDesignation);
                                    contracterCompany.setText(Projectmasterbean.contracterCompany);
                                    vilresPerson.setText(Projectmasterbean.vilresPerson);

                                }

                            } catch (Exception e) {
                                Log.e("xxxxxx", "Something went wrong");
                            }

                        } else if (val1.equals("district")) {
                            DISTRICT = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                DISTRICT[i] = jsonArray.getString(i);
                            }
                            ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProjectMaster.this,
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

                        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(ProjectMaster.this,
                                android.R.layout.simple_dropdown_item_1line, PROVINCE);
                        Province.setAdapter(provinceAdapter);
                        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProjectMaster.this,
                                android.R.layout.simple_dropdown_item_1line, DISTRICT);
                        district.setAdapter(districtAdapter);
                        ArrayAdapter<String> communeAdapter = new ArrayAdapter<String>(ProjectMaster.this,
                                android.R.layout.simple_dropdown_item_1line, COMMUNE);
                        commune.setAdapter(communeAdapter);
                        ArrayAdapter<String> villageAdapter = new ArrayAdapter<String>(ProjectMaster.this,
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onContactSelected(Project contact) {
        projectCode=contact.phone;
        projectName=contact.name;
        projectDetail.setText(contact.phone + "-" + contact.name);
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }
}
