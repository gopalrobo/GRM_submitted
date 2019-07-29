package com.example.arthi.dmssmartapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String buSurveyerId = "buSurveyerIdKey";
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final TextView login = (TextView) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() > 0 && password.getText().toString().length() > 0) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(buSurveyerId, username.getText().toString() + "_" + password.getText().toString());
                    editor.commit();
                    Intent localIntent1 = new Intent(LoginActivity.this, MainActivityAllSurvey.class);
                    startActivity(localIntent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter username and Password/Mobile", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
