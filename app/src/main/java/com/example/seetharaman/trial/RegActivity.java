package com.example.seetharaman.trial;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitle("Registration");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);

        List<String> events = new ArrayList<>();
        events.add("Decibels");
        events.add("Acapella");
        events.add("Scrabble");
        events.add("Fine Arts");
        events.add("Crossie");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, events);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Spinner Operations
        Spinner spinner = (Spinner) findViewById(R.id.spinner_event);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    public void registerButton(View view) {
        int errorFlag = 0;
        EditText fname = (EditText) findViewById(R.id.et_firstname);
        EditText lname = (EditText) findViewById(R.id.et_lastname);
        EditText email = (EditText) findViewById(R.id.et_email);
        EditText phno = (EditText) findViewById(R.id.et_phonenumber);
        EditText college = (EditText) findViewById(R.id.et_college);

        /*Checking if any of the field in the form are not filled
        Check for lastname is excluded for people without last names
        Check for Spinner value is excluded since it cannot take an empty value
        Check for phno also checks if only 10 digits are included by using regex
        A variable is created for phno to avoid multiple calls to findViewById()
        */
        if (fname.getText().toString().isEmpty()) {
            fname.setError("Required field");
            errorFlag = 1;
        } else
            fname.setError(null);


        if (lname.getText().toString().isEmpty()) {
            lname.setError("Required field");
            errorFlag = 1;
        } else
            lname.setError(null);

        if (email.getText().toString().isEmpty()) {
            email.setError("Required field");
            errorFlag = 1;
        } else
            email.setError(null);

        if (phno.getText().toString().isEmpty()) {
            phno.setError("Required field.");
            errorFlag = 1;
        } else if (!(phno.getText().toString().matches("[0-9]{10}"))) {
            phno.setError("Enter a valid 10 digit phone number.");
            errorFlag = 1;
        } else
            phno.setError(null);

        if (((EditText) findViewById(R.id.et_college)).getText().toString().isEmpty()) {
            college.setError("College name field is required.");
            errorFlag = 1;
        } else
            college.setError(null);
        //Add something to indicate successful registration
        if(errorFlag==0){
            Toast.makeText(getApplicationContext(),"Successfully Registered!",Toast.LENGTH_LONG).show();
        }
    }
}
