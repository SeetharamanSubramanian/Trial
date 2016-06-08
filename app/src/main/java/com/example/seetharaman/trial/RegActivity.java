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
        toolbar.setTitle("Registrations");
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

        EditText phno = (EditText) findViewById(R.id.et_phonenumber);
        /*Checking if any of the field in the form are not filled
        Check for lastname is excluded for people without last names
        Check for Spinner value is excluded since it cannot take an empty value
        Check for phno also checks if only 10 digits are included by using regex
        A variable is created for phno to avoid multiple calls to findViewById()
        */
        if (((EditText) findViewById(R.id.et_firstname)).getText().toString().isEmpty())
            Toast.makeText(this, "First name field is required.", Toast.LENGTH_SHORT).show();
        else if (((EditText) findViewById(R.id.et_email)).getText().toString().isEmpty())
            Toast.makeText(this, "Email ID field is required.", Toast.LENGTH_SHORT).show();
        else if (phno.getText().toString().isEmpty())
            Toast.makeText(this, "Phone number field is required.", Toast.LENGTH_SHORT).show();
        else if (!(phno.getText().toString().matches("[0-9]{10}")))
            Toast.makeText(this, "Please enter a valid 10 digit phone number.", Toast.LENGTH_SHORT).show();
        else if (((EditText) findViewById(R.id.et_college)).getText().toString().isEmpty())
            Toast.makeText(this, "College name field is required.", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "Successfully Registered.", Toast.LENGTH_LONG).show();
        }
    }
}
