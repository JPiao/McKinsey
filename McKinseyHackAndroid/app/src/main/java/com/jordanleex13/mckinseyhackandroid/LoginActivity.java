package com.jordanleex13.mckinseyhackandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jordanleex13.mckinseyhackandroid.Helpers.RunnableParseJobs;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstName;
    EditText preferredJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolBar = (Toolbar) findViewById(R.id.activity_login_toolbar);

        if (toolBar != null) {
            toolBar.setTitle("dönum");
            //toolBar.setNavigationIcon(R.mipmap.donum_app_logo);
            toolBar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolBar);

        }

        Button button = (Button) findViewById(R.id.activity_login_button);
        button.setOnClickListener(this);

        firstName = (EditText) findViewById(R.id.activity_login_first_name);
        preferredJob = (EditText) findViewById(R.id.activity_login_preferred_job);

    }


    @Override
    public void onClick(View v) {
        if (preferredJob.getText().toString().equals("")) {
            preferredJob.setError("This field cannot be left blank");

        } else {

            // by default fill, the job list with whatever the person put in
            new Thread(new RunnableParseJobs(preferredJob.getText().toString())).start();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
