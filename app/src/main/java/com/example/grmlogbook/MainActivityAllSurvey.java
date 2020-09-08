package com.example.grmlogbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.app.AppConfig;
import com.example.grmlogbook.app.BaseActivity;
import com.example.grmlogbook.app.LocaleManager;
import com.example.grmlogbook.app.PdfConfig;
import com.example.grmlogbook.app.ProtocolPdfConfig;
import com.example.grmlogbook.app.SnaglistPdfConfig;
import com.example.grmlogbook.app.SummaryPdfConfig;
import com.example.grmlogbook.dp.DbFarmer;
import com.example.grmlogbook.logbook.LogbookActivity;
import com.example.grmlogbook.maps.CustClusterHouseHoldActivity;
import com.example.grmlogbook.meeting.GRMMeeting;
import com.example.grmlogbook.snag.SnagList;
import com.example.grmlogbook.summary.GrievanceSummary;
import com.example.grmlogbook.summary.Summarybean;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.grmlogbook.app.AppConfig.isLogin;


public class MainActivityAllSurvey extends BaseActivity {


    public static final String mypreference = "mypref";
    public static final String buSurveyerId = "buSurveyerIdKey";

    public static final String staffpositionId = "staffpositionId";
    public static final String staffnameId = "staffnameId";
    DbFarmer dbFarmer;
    SharedPreferences sharedpreferences;
    private ProgressDialog pDialog;
    private RecyclerView mastersList;
    private MasterAllSurveyAdapter mRecyclerAdapterMaster;
    private ArrayList<ComplaintLetterbean> itemArrayList = new ArrayList<>();
    TextView submitted;
    TextView acknowledged;
    TextView resolved;
    TextView rejected;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_all_survey);
        dbFarmer = new DbFarmer(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton log = findViewById(R.id.log);
        FloatingActionButton letter = findViewById(R.id.letter);
        FloatingActionButton forms = findViewById(R.id.forms);
        FloatingActionButton snaglist = findViewById(R.id.snaglist);

        submitted = findViewById(R.id.submitted);
        acknowledged = findViewById(R.id.acknowledged);
        resolved = findViewById(R.id.resolved);
        rejected = findViewById(R.id.rejected);

        FloatingActionButton project = findViewById(R.id.project);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAllSurvey.this, LogbookActivity.class);
                startActivity(intent);

            }
        });

        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAllSurvey.this, GrievanceSummary.class);
                startActivity(intent);

            }
        });
        snaglist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityAllSurvey.this, SnagList.class);
                startActivity(intent);
            }
        });
        letter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAllSurvey.this, GRMMeeting.class);
                startActivity(intent);

            }
        });

        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAllSurvey.this, ProjectMasterSurvey.class);
                startActivity(intent);

            }
        });


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.complist));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        sharedpreferences = this.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        NestedScrollView nestedScroll = findViewById(R.id.nestedScroll);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mastersList = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapterMaster = new MasterAllSurveyAdapter(this, itemArrayList);
        final LinearLayoutManager thirdManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mastersList.setLayoutManager(thirdManager);
        mastersList.setAdapter(mRecyclerAdapterMaster);

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);


    }


    private void getAllData(String mobile) {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        progressBar.setVisibility(View.VISIBLE);
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
                                itemArrayList.add(complaintLetterbean);
                            } catch (Exception e) {
                                Log.e("xxxxxxxxxxx", e.toString());
                                //   Toast.makeText(getApplicationContext(), "Some Network Error.Try after some time", Toast.LENGTH_SHORT).show();

                            }
//                          break;
                        }

                        submitted.setText(jObj.getString("submitted"));
                        acknowledged.setText(jObj.getString("acknowledged"));
                        resolved.setText(jObj.getString("resolved"));
                        rejected.setText(jObj.getString("rejected"));

                        mRecyclerAdapterMaster.notifyData(itemArrayList);
                    }
                } catch (Exception e) {
                    Log.e("xxxxxxxxxxx", e.toString());

                    //     Toast.makeText(getApplicationContext(), "Some Network Error.Try after some time", Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.INVISIBLE);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),
                        "Some Network Error.Try after some time", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }) {
            protected Map<String, String> getParams() {
                HashMap localHashMap = new HashMap();
                localHashMap.put("key", "all");
                localHashMap.put("dbname", "grievanceform");
                localHashMap.put("mobile", mobile);
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

        RegMainbean regMainbean = new Gson().fromJson(dbFarmer
                .getDataByfarmerId(sharedpreferences.getString(AppConfig.farmerId, "")).get(1), RegMainbean.class);

        getAllData(regMainbean.mobile);

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
        getMenuInflater().inflate(R.menu.main_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.action_profile) {
            RegMainbean regMainbean = new Gson().fromJson(dbFarmer
                    .getDataByfarmerId(sharedpreferences.getString(AppConfig.farmerId, "")).get(1), RegMainbean.class);

            Intent io = new Intent(MainActivityAllSurvey.this, Registerpage.class);
            io.putExtra("object", regMainbean);
            startActivity(io);
            return true;
        }
        if (id == R.id.action_location) {
            Intent io = new Intent(MainActivityAllSurvey.this, CustClusterHouseHoldActivity.class);
            startActivity(io);
            return true;
        }
        if (id == R.id.action_exit) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.remove(isLogin);
            editor.commit();

            Intent intent = new Intent(MainActivityAllSurvey.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.local_english) {
            setNewLocale(this, LocaleManager.ENGLISH);
            return true;
        }
        if (id == R.id.local_khmer) {
            setNewLocale(this, LocaleManager.KHMER);
            return true;
        }

        if (id == R.id.logbook) {
            printFunction(MainActivityAllSurvey.this, itemArrayList, sharedpreferences.getString(staffnameId, ""), sharedpreferences.getString(staffpositionId, ""));
            return true;
        }
        if (id == R.id.grgmeeting) {
            ProtocolPdfConfig.printFunction(MainActivityAllSurvey.this, itemArrayList);
            return true;
        }
        if (id == R.id.snaglist) {
            SnaglistPdfConfig.printFunction(MainActivityAllSurvey.this, itemArrayList, sharedpreferences.getString(staffnameId, ""));
            return true;
        }
        if (id == R.id.summary) {
            getAllReportedData();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    private void getAllReportedData() {
        String tag_string_req = "req_register";
        pDialog.setMessage("Validating ...");
        showDialog();
        // showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://climatesmartcity.com/UBA/grmSum_compliantReport.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Register Response: ", response.toString());
                try {
                    Summarybean summarybean = new Summarybean();
                    JSONObject jObj = new JSONObject(response);
                    summarybean.setJancomplaint(jObj.getString("jansum"));
                    summarybean.setFebcomplaint(jObj.getString("febsum"));
                    summarybean.setMarcomplaint(jObj.getString("marsum"));

                    int total = Integer.parseInt(jObj.getString("jansum")) + Integer.parseInt(jObj.getString("febsum")) + Integer.parseInt(jObj.getString("marsum"));
                    summarybean.setSubcomplaint(String.valueOf(total));


                    summarybean.setJangovernment(jObj.getString("janresolved"));
                    summarybean.setFebgovernment(jObj.getString("febresolved"));
                    summarybean.setMargovernment(jObj.getString("marresolved"));

                    int totalgov = Integer.parseInt(jObj.getString("janresolved")) + Integer.parseInt(jObj.getString("febresolved")) + Integer.parseInt(jObj.getString("marresolved"));
                    summarybean.setSubgovernment(String.valueOf(totalgov));

                    SummaryPdfConfig.printFunction(MainActivityAllSurvey.this, summarybean);

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
                localHashMap.put("dbname", "grievanceform");
                return localHashMap;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void printFunction(Context context, ArrayList<ComplaintLetterbean>
            complaintLetterbeans, String name, String position) {
        showDialog();

        HandlerThread handlerThread = new HandlerThread("NetworkOperation");
        handlerThread.start();
        Handler requestHandler = new Handler(handlerThread.getLooper());

        final Handler responseHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.obj.toString().equals("Failure")) {
                    hideDialog();
                    Uri photoURI = FileProvider.getUriForFile(context,
                            context.getPackageName()
                                    + ".provider", new
                                    File(
                                    Environment.getExternalStorageDirectory()
                                            .getAbsolutePath() + "/PDF/"
                                            + "Grievance Registration" + ".pdf"));

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(photoURI
                            , "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(intent);
                } else {
                    showDialog();
                    pDialog.setMessage(msg.obj.toString());
                }

            }
        };

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";
                    File dir = new File(path);
                    if (!dir.exists())
                        dir.mkdirs();
                    Log.d("PDFCreator", "PDF Path: " + path);

                    File file = new File(dir, "Grievance Registration" + ".pdf");
                    FileOutputStream fOut = new FileOutputStream(file);


                    Document document = new Document();
                    PdfWriter pdfWriter = PdfWriter.getInstance(document, fOut);
                    Rectangle rect = new Rectangle(175, 20, 530, 800);
                    pdfWriter.setBoxSize("art", rect);

                    document.open();
                    PdfConfig.addMetaData(document);
                    PdfConfig.addContent(document, complaintLetterbeans, name, position, responseHandler);
                    document.close();


                } catch (Error | Exception e) {
                    e.printStackTrace();
                }


                msg.obj = "Failure";
                responseHandler.sendMessage(msg);

            }
        };
        requestHandler.post(myRunnable);


    }

    public void showWebviewDialog() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setTitle("ADB Projects");

        WebView wv = new WebView(this);
        wv.loadUrl("https://www.adb.org/site/accountability-mechanism/main");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();

    }


}


