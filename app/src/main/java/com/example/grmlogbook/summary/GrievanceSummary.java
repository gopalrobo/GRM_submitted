package com.example.grmlogbook.summary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.grmlogbook.R;
import com.example.grmlogbook.app.BaseActivity;
import com.example.grmlogbook.app.LocaleManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GrievanceSummary extends BaseActivity {

    private ProgressDialog pDialog;



    EditText jancomplaint;
    EditText jangovernment;
    EditText jancourt;
    EditText jantotal;
    EditText febcomplaint;
    EditText febgovernment;
    EditText febcourt;
    EditText febtotal;
    EditText marcomplaint;
    EditText margovernment;
    EditText marcourt;
    EditText martotal;
    EditText aprcomplaint;
    EditText aprgovernment;
    EditText aprcourt;
    EditText aprtotal;
    EditText maycomplaint;
    EditText maygovernment;
    EditText maycourt;
    EditText maytotal;
    EditText juncomplaint;
    EditText jungovernment;
    EditText juncourt;
    EditText juntotal;
    EditText julcomplaint;
    EditText julgovernment;
    EditText julcourt;
    EditText jultotal;
    EditText augcomplaint;
    EditText auggovernment;
    EditText augcourt;
    EditText augtotal;
    EditText sepcomplaint;
    EditText sepgovernment;
    EditText sepcourt;
    EditText septotal;
    EditText octcomplaint;
    EditText octgovernment;
    EditText octcourt;
    EditText octtotal;
    EditText novcomplaint;
    EditText novgovernment;
    EditText novcourt;
    EditText novtotal;
    EditText deccomplaint;
    EditText decgovernment;
    EditText deccourt;
    EditText dectotal;
    EditText subcomplaint;
    EditText subgovernment;
    EditText subcourt;
    EditText subtotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        jancomplaint = (EditText) findViewById(R.id.jancomplaint);
        jangovernment = (EditText) findViewById(R.id.jangovernment);
        jancourt = (EditText) findViewById(R.id.jancourt);
        jantotal = (EditText) findViewById(R.id.jantotal);
        febcomplaint = (EditText) findViewById(R.id.febcomplaint);
        febgovernment = (EditText) findViewById(R.id.febgovernment);
        febcourt = (EditText) findViewById(R.id.febcourt);
        febtotal = (EditText) findViewById(R.id.febtotal);
        marcomplaint = (EditText) findViewById(R.id.marcomplaint);
        margovernment = (EditText) findViewById(R.id.margovernment);
        marcourt = (EditText) findViewById(R.id.marcourt);
        martotal = (EditText) findViewById(R.id.martotal);
        aprcomplaint = (EditText) findViewById(R.id.aprcomplaint);
        aprgovernment = (EditText) findViewById(R.id.aprgovernment);
        aprcourt = (EditText) findViewById(R.id.aprcourt);
        aprtotal = (EditText) findViewById(R.id.aprtotal);
        maycomplaint = (EditText) findViewById(R.id.maycomplaint);
        maygovernment = (EditText) findViewById(R.id.maygovernment);
        maycourt = (EditText) findViewById(R.id.maycourt);
        maytotal = (EditText) findViewById(R.id.maytotal);
        juncomplaint = (EditText) findViewById(R.id.juncomplaint);
        jungovernment = (EditText) findViewById(R.id.jungovernment);
        juncourt = (EditText) findViewById(R.id.juncourt);
        juntotal = (EditText) findViewById(R.id.juntotal);
        julcomplaint = (EditText) findViewById(R.id.julcomplaint);
        julgovernment = (EditText) findViewById(R.id.julgovernment);
        julcourt = (EditText) findViewById(R.id.julcourt);
        jultotal = (EditText) findViewById(R.id.jultotal);
        augcomplaint = (EditText) findViewById(R.id.augcomplaint);
        auggovernment = (EditText) findViewById(R.id.auggovernment);
        augcourt = (EditText) findViewById(R.id.augcourt);
        augtotal = (EditText) findViewById(R.id.augtotal);
        sepcomplaint = (EditText) findViewById(R.id.sepcomplaint);
        sepgovernment = (EditText) findViewById(R.id.sepgovernment);
        sepcourt = (EditText) findViewById(R.id.sepcourt);
        septotal = (EditText) findViewById(R.id.septotal);
        octcomplaint = (EditText) findViewById(R.id.octcomplaint);
        octgovernment = (EditText) findViewById(R.id.octgovernment);
        octcourt = (EditText) findViewById(R.id.octcourt);
        octtotal = (EditText) findViewById(R.id.octtotal);
        novcomplaint = (EditText) findViewById(R.id.novcomplaint);
        novgovernment = (EditText) findViewById(R.id.novgovernment);
        novcourt = (EditText) findViewById(R.id.novcourt);
        novtotal = (EditText) findViewById(R.id.novtotal);
        deccomplaint = (EditText) findViewById(R.id.deccomplaint);
        decgovernment = (EditText) findViewById(R.id.decgovernment);
        deccourt = (EditText) findViewById(R.id.deccourt);
        dectotal = (EditText) findViewById(R.id.dectotal);
        subcomplaint = (EditText) findViewById(R.id.subcomplaint);
        subgovernment = (EditText) findViewById(R.id.subgovernment);
        subcourt = (EditText) findViewById(R.id.subcourt);
        subtotal = (EditText) findViewById(R.id.subtotal);

        getAllReportedData();

        jancourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });


        febcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });


        marcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        aprcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });


        maycourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        juncourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        julcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });


        augcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        sepcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        octcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        novcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (deccourt.getText().toString() != null && deccourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(deccourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        deccourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                if (aprcourt.getText().toString() != null && aprcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprcourt.getText().toString());

                }
                if (maycourt.getText().toString() != null && maycourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maycourt.getText().toString());

                }
                if (juncourt.getText().toString() != null && juncourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juncourt.getText().toString());

                }
                if (julcourt.getText().toString() != null && julcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(julcourt.getText().toString());

                }
                if (augcourt.getText().toString() != null && augcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augcourt.getText().toString());

                }
                if (sepcourt.getText().toString() != null && sepcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(sepcourt.getText().toString());

                }
                if (octcourt.getText().toString() != null && octcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octcourt.getText().toString());

                }
                if (novcourt.getText().toString() != null && novcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novcourt.getText().toString());

                }
                subcourt.setText(String.valueOf(total));
            }
        });

        jancomplaint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jangovernment.getText().toString() != null && jangovernment.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jangovernment.getText().toString());

                }
                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                jantotal.setText(String.valueOf(total));
            }
        });

        jangovernment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancomplaint.getText().toString() != null && jancomplaint.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancomplaint.getText().toString());

                }
                if (jancourt.getText().toString() != null && jancourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancourt.getText().toString());

                }
                jantotal.setText(String.valueOf(total));
            }
        });

        jancourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jancomplaint.getText().toString() != null && jancomplaint.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jancomplaint.getText().toString());

                }
                if (jangovernment.getText().toString() != null && jangovernment.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jangovernment.getText().toString());

                }
                jantotal.setText(String.valueOf(total));
            }
        });

        febcomplaint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (febgovernment.getText().toString() != null && febgovernment.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febgovernment.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                febtotal.setText(String.valueOf(total));
            }
        });


        febgovernment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (febcomplaint.getText().toString() != null && febcomplaint.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcomplaint.getText().toString());

                }
                if (febcourt.getText().toString() != null && febcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcourt.getText().toString());

                }
                febtotal.setText(String.valueOf(total));
            }
        });


        febcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (febcomplaint.getText().toString() != null && febcomplaint.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febcomplaint.getText().toString());

                }
                if (febgovernment.getText().toString() != null && febgovernment.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febgovernment.getText().toString());

                }
                febtotal.setText(String.valueOf(total));
            }
        });

        marcomplaint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (margovernment.getText().toString() != null && margovernment.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(margovernment.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                martotal.setText(String.valueOf(total));
            }
        });


        margovernment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (marcomplaint.getText().toString() != null && marcomplaint.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcomplaint.getText().toString());

                }
                if (marcourt.getText().toString() != null && marcourt.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcourt.getText().toString());

                }
                martotal.setText(String.valueOf(total));
            }
        });


        marcourt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (marcomplaint.getText().toString() != null && marcomplaint.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(marcomplaint.getText().toString());

                }
                if (margovernment.getText().toString() != null && margovernment.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(margovernment.getText().toString());

                }
                martotal.setText(String.valueOf(total));
            }
        });


        jantotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });


        febtotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });


        martotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });

        aprtotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });


        maytotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });

        juntotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });

        jultotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });


        augtotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });

        septotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });

        octtotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });

        novtotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (dectotal.getText().toString() != null && dectotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(dectotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });

        dectotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int total = 0;

                if (s.toString() != null && s.toString().length() > 0) {
                    total = total + Integer.parseInt(s.toString());
                }

                if (jantotal.getText().toString() != null && jantotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jantotal.getText().toString());

                }
                if (febtotal.getText().toString() != null && febtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(febtotal.getText().toString());

                }
                if (martotal.getText().toString() != null && martotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(martotal.getText().toString());

                }
                if (aprtotal.getText().toString() != null && aprtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(aprtotal.getText().toString());

                }
                if (maytotal.getText().toString() != null && maytotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(maytotal.getText().toString());

                }
                if (juntotal.getText().toString() != null && juntotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(juntotal.getText().toString());

                }
                if (jultotal.getText().toString() != null && jultotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(jultotal.getText().toString());

                }
                if (augtotal.getText().toString() != null && augtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(augtotal.getText().toString());

                }
                if (septotal.getText().toString() != null && septotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(septotal.getText().toString());

                }
                if (octtotal.getText().toString() != null && octtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(octtotal.getText().toString());

                }
                if (novtotal.getText().toString() != null && novtotal.getText().toString().length() > 0) {
                    total = total + Integer.parseInt(novtotal.getText().toString());

                }
                subtotal.setText(String.valueOf(total));
            }
        });



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
                    JSONObject jObj = new JSONObject(response);
                    jancomplaint.setText(jObj.getString("jansum"));
                    febcomplaint.setText(jObj.getString("febsum"));
                    marcomplaint.setText(jObj.getString("marsum"));

                   int total=Integer.parseInt(jObj.getString("jansum"))+Integer.parseInt(jObj.getString("febsum"))+Integer.parseInt(jObj.getString("marsum"));

                   subcomplaint.setText(String.valueOf(total));

                    jangovernment.setText(jObj.getString("janresolved"));
                    febgovernment.setText(jObj.getString("febresolved"));
                    margovernment.setText(jObj.getString("marresolved"));

                    int totalgov=Integer.parseInt(jObj.getString("janresolved"))+Integer.parseInt(jObj.getString("febresolved"))+Integer.parseInt(jObj.getString("marresolved"));

                    subgovernment.setText(String.valueOf(totalgov));

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

    private void hideDialog() {
        if (this.pDialog.isShowing())
            this.pDialog.dismiss();
    }


    private void showDialog() {
        if (!this.pDialog.isShowing())
            this.pDialog.show();
    }
}
