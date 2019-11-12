package com.example.arthi.dmssmartapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.arthi.dmssmartapp.OneHouseholdData.HouseholdDate;
import com.example.arthi.dmssmartapp.ThreeAffectedStructure.AffectedStructure;
import com.example.arthi.dmssmartapp.TwoAffectedLand.AffectedLand;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class MainActivity extends AppCompatActivity {
    public static final String mypreference = "mypref";
    public static final String buSurveyerId = "buSurveyerIdKey";
    private static final int FINE_LOCATION_CODE = 199;
    ProgressDialog pDialog;
    EditText id;

    CardView householdDate;
    CardView affectedStructure;
    CardView affectedLand;
    CardView participants;
    CardView questionnaire,singlePageSurvey;
    CardView otherLivelihood;
    CardView resettlementOption;


    FloatingTextButton submit;
    Mainbean mainbean = null;
    SharedPreferences sharedpreferences;
    HouseholdDate householdDateBean;
    AffectedStructure affectedStructureBean;
    AffectedLand affectedLandBean;
    Participants participantsBean;
    Questionnaire questionnaireBean;
    OtherLivelihood otherLivelihoodBean;
    ResettlementOption resettlementOptionBean;

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        sharedpreferences = this.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        householdDate = (CardView) findViewById(R.id.householdDate);
        affectedStructure = (CardView) findViewById(R.id.affectedStructure);
        affectedLand = (CardView) findViewById(R.id.affectedLand);
        participants = (CardView) findViewById(R.id.participants);
        questionnaire = (CardView) findViewById(R.id.questionnaire);
        singlePageSurvey = (CardView) findViewById(R.id.singlePageSurvey);
        otherLivelihood = (CardView) findViewById(R.id.otherLivelihood);
        resettlementOption = (CardView) findViewById(R.id.resettlementOption);


        submit = (FloatingTextButton) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mainbean tempMainbean = new Mainbean(
                        householdDateBean,
                        affectedLandBean,
                        affectedStructureBean,
                        otherLivelihoodBean,
                        participantsBean,
                        questionnaireBean,
                        resettlementOptionBean);

                String jsonVal = new Gson().toJson(tempMainbean);
                Log.e("xxxxxxxxxxxxx", jsonVal);
                if (mainbean == null) {
                    tempMainbean.setId(String.valueOf(System.currentTimeMillis()));
                } else {
                    tempMainbean.setId(mainbean.id);
                }

                getCreateTest(tempMainbean.id, sharedpreferences.getString(buSurveyerId, ""), jsonVal);
            }
        });
        try {
            mainbean = (Mainbean) getIntent().getSerializableExtra("object");
            if (mainbean != null) {
                householdDateBean=mainbean.householdDate;
                affectedLandBean=mainbean.affectedLand;
                 affectedStructureBean=mainbean.affectedStructure;
                participantsBean=mainbean.participants;
                questionnaireBean=mainbean.questionnaire;
                otherLivelihoodBean=mainbean.otherLivelihood;
                resettlementOptionBean=mainbean.resettlementOption;

            }
        } catch (Exception e) {
            Log.e("xxxxxx", "Something went wrong");
        }




        householdDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OneHousehold.class);
                //step 1
                if(mainbean!=null&&mainbean.getHouseholdDate()!=null) {
                    intent.putExtra("data", mainbean.householdDate);
                }
                startActivityForResult(intent, 1);
            }
        });
        affectedLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TwoLand .class);
                //step 1
                if(mainbean!=null&&mainbean.getAffectedLand()!=null) {
                    intent.putExtra("data", mainbean.affectedLand);
                }    startActivityForResult(intent, 2);
            }
        });

        affectedStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThreeStructure.class);
                //step 1
                if(mainbean!=null&&mainbean.getAffectedStructure()!=null) {
                    intent.putExtra("data", mainbean.affectedStructure);
                }  startActivityForResult(intent, 3);
            }
        });

        otherLivelihood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FourImpact.class);
                //step 1
                if(mainbean!=null&&mainbean.getOtherLivelihood()!=null) {
                    intent.putExtra("data", mainbean.otherLivelihood);
                } startActivityForResult(intent, 4);
            }
        });
        resettlementOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FiveResettlement.class);
                //step 1
                if(mainbean!=null&&mainbean.getResettlementOption()!=null) {
                    intent.putExtra("data", mainbean.resettlementOption);
                } startActivityForResult(intent, 5);
            }
        });

        participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SixParticipants.class);
                //step 1
                if(mainbean!=null&&mainbean.getParticipants()!=null) {
                    intent.putExtra("data", mainbean.participants);
                }  startActivityForResult(intent, 6);
            }
        });
        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SevenQuestionnaire.class);
                //step 1
                if(mainbean!=null&&mainbean.getQuestionnaire()!=null) {
                    intent.putExtra("data", mainbean.questionnaire);
                } startActivityForResult(intent, 7);
            }
        });

        singlePageSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SinglePageSurvey.class);
                startActivityForResult(intent, 7);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //step 3
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                householdDateBean = (HouseholdDate) data.getSerializableExtra("result");
            }
        } if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                affectedLandBean = (AffectedLand) data.getSerializableExtra("result");
            }
        }
        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                affectedStructureBean = (AffectedStructure) data.getSerializableExtra("result");
            }
        }
        if (requestCode == 4) {
            try {
                if (resultCode == Activity.RESULT_OK) {
                    otherLivelihoodBean = (OtherLivelihood) data.getSerializableExtra("result");
                }
            }catch (Exception e){
                Log.e("xxxxxxxxxxxxxxxxxx",e.toString());
            }
        }
        if (requestCode == 5) {
            if (resultCode == Activity.RESULT_OK) {
                resettlementOptionBean = (ResettlementOption) data.getSerializableExtra("result");
            }
        }
        if (requestCode == 6) {
            if (resultCode == Activity.RESULT_OK) {
                participantsBean = (Participants) data.getSerializableExtra("result");
            }
        }
        if (requestCode == 7) {
            if (resultCode == Activity.RESULT_OK) {
                questionnaireBean = (Questionnaire) data.getSerializableExtra("result");
            }
        }

    }
    private void getCreateTest(final String mId, final String surveyer, final String data) {
        this.pDialog.setMessage("Creating...");
        showDialog();
        StringRequest local16 = new StringRequest(1, "http://climatesmartcity.com/UBA/create_data.php", new Response.Listener<String>() {
            public void onResponse(String paramString) {
                Log.d("tag", "Register Response: " + paramString.toString());
                hideDialog();
                try {
                    JSONObject localJSONObject1 = new JSONObject(paramString);
                    String str = localJSONObject1.getString("message");
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
                localHashMap.put("formId", mId);
                localHashMap.put("surveyer", surveyer);
                localHashMap.put("data", data);
                localHashMap.put("dbname", "DMSmart");


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
    }}
