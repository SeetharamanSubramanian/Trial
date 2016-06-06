package com.example.seetharaman.trial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText firstNameET, lastNameET, emailET, phoneET, collegeET;

    //SUBMITTED INFORMATION IS STORED IN THE FOLLOWING VARIABLES
    String firstName, lastName, email, phone, college;
    int selectedItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.event_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.events_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void submitInfo(View view) {

        firstNameET = (EditText) findViewById(R.id.et_first_name);
        lastNameET = (EditText) findViewById(R.id.et_last_name);
        emailET = (EditText) findViewById(R.id.et_email);
        phoneET = (EditText) findViewById(R.id.et_phone);
        collegeET = (EditText) findViewById(R.id.et_school_name);

        firstName = firstNameET.getText().toString();
        lastName = lastNameET.getText().toString();
        email = emailET.getText().toString();
        phone = phoneET.getText().toString();
        college = collegeET.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.event_spinner);
        selectedItemPosition = spinner.getSelectedItemPosition();

        //Check whether the inputs given by user are valid:
        int errorCode = checkValidity(firstName, lastName, email, phone, college);
//      ERROR CODES RETURNED BY THE FUNCTION checkValidity:
//        No error               :   errorcode produced is 0
//        Error in First Name    :   errorcode produced is 1
//        Error in Second Name   :   errorcode produced is 2
//        Error in Email         :   errorcode produced is 3
//        Error in Phone         :   errorcode produced is 4
//        Error in schoolname    :   errorcode produced is 5

        //Show a Toast
        //If inputs are not valid, ask to enter valid inputs
        //If inputs are all valid, show successfully submitted
        int duration = Toast.LENGTH_SHORT;
        Context presentContext;
        CharSequence text;
        Toast submitInfoToast;

        //Update the text to be displayed in Toast as per the errorcode produced by the function checkValidity
        switch (errorCode) {

            case 0: {
                presentContext = getApplicationContext();
                text = "Successfully submitted!";
                submitInfoToast = Toast.makeText(presentContext, text, duration);
                submitInfoToast.show();

                Context context = view.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                break;
            }
            case 1: {
                presentContext = getApplicationContext();
                text = "Enter valid First Name";
                submitInfoToast = Toast.makeText(presentContext, text, duration);
                submitInfoToast.show();
                break;
            }
            case 2: {
                presentContext = getApplicationContext();
                text = "Enter valid Last Name";
                submitInfoToast = Toast.makeText(presentContext, text, duration);
                submitInfoToast.show();
                break;
            }
            case 3: {
                presentContext = getApplicationContext();
                text = "Enter a valid Email ID";
                submitInfoToast = Toast.makeText(presentContext, text, duration);
                submitInfoToast.show();
                break;
            }
            case 4: {
                presentContext = getApplicationContext();
                text = "Enter a valid Phone Number";
                submitInfoToast = Toast.makeText(presentContext, text, duration);
                submitInfoToast.show();
                break;
            }
            case 5: {
                presentContext = getApplicationContext();
                text = "Enter valid School Name";
                submitInfoToast = Toast.makeText(presentContext, text, duration);
                submitInfoToast.show();
                break;
            }
        }

    }

    public int checkValidity(String name1, String name2, String email, String phone, String schoolName) {

        int errorCode = 0;

//      ERROR CODES RETURNED BY THE FUNCTION checkValidity:
//        No error               :   errcode = 0
//        Error in First Name    :   errcode = 1
//        Error in Second Name   :   errcode = 2
//        Error in Email         :   errcode = 3
//        Error in Phone         :   errcode = 4
//        Error in schoolname    :   errcode = 5

        //Check validity of First Name
        if (name1.length() == 0 || !name1.matches("[a-zA-Z ]+")) {
            errorCode = 1;
            return errorCode;
        }

        //Check validity of Last Name
        if (name2.length() == 0 || !name2.matches("[a-zA-Z ]+")) {
            errorCode = 2;
            return errorCode;
        }

        //Check validity of Email Address
        Boolean emailBool = isValidEmail(email);
        if (!emailBool) {
            errorCode = 3;
            return errorCode;
        }

        //Check validity of Phone Number
        Boolean phoneBool = isValidPhone(phone);
        if (!phoneBool) {
            errorCode = 4;
            return errorCode;
        }

        //Check validity of School Name
        if (schoolName.length() == 0 || !schoolName.matches("[a-zA-Z ]+")) {
            errorCode = 5;
            return errorCode;
        }

        //If everything is valid return errorCode(which is 0, its initial value)
        return errorCode;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidPhone(CharSequence number) {
        if (number == null) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(number).matches();
        }
    }

}
