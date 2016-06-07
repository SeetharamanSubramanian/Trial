package com.example.seetharaman.trial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText firstNameET, lastNameET, emailET, phoneET, collegeET;
    TextInputLayout tilFirstName, tilLastName, tilEmail, tilPhone, tilSchoolName;
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
        toolbar.setTitle("Registrations");

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
        assert spinner != null;
        selectedItemPosition = spinner.getSelectedItemPosition();

        tilFirstName = (TextInputLayout) findViewById(R.id.til_first_name);
        tilLastName = (TextInputLayout) findViewById(R.id.til_last_name);
        tilEmail = (TextInputLayout) findViewById(R.id.til_email);
        tilPhone = (TextInputLayout) findViewById(R.id.til_phone);
        tilSchoolName = (TextInputLayout) findViewById(R.id.til_school_name);

        //Check whether the inputs given by user are valid:
        Boolean isValid = checkValidity();

        if(isValid) {
            Toast submitToast = Toast.makeText(getApplicationContext(),  "Successfully submitted!", Toast.LENGTH_SHORT);
            submitToast.show();

            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        }

    }

    public Boolean checkValidity() {

        Boolean isValid = true;

        //Check validity of First Name
        if (firstName.length() == 0) {
            tilFirstName.setError("Field cannot be blank");
            isValid = false;
        }
        else if (!firstName.matches("[a-zA-Z ]+")) {
            tilFirstName.setError("Enter valid First Name");
            isValid = false;
        } else {
            tilFirstName.setErrorEnabled(false);
        }

        //Check validity of Last Name
        if (lastName.length() == 0) {
            tilLastName.setError("Field cannot be blank");
            isValid = false;
        }
        else if (!lastName.matches("[a-zA-Z ]+")) {
            tilLastName.setError("Enter valid Last Name");
            isValid = false;
        } else {
            tilLastName.setErrorEnabled(false);
        }

        //Check validity of Email Address
        Boolean emailBool = isValidEmail(email);
        if (email.length() == 0) {
            tilEmail.setError("Field cannot be blank");
            isValid = false;
        }
        else if (!emailBool) {
            tilEmail.setError("Enter a valid Email Address");
            isValid = false;
        }
        else {
            tilEmail.setErrorEnabled(false);
        }

        //Check validity of Phone Number
        Boolean phoneBool = isValidPhone(phone);
        if (phone.length() == 0) {
            tilPhone.setError("Field cannot be blank");
            isValid = false;
        }
        else if (!phoneBool) {
            tilPhone.setError("Enter a valid Phone number");
            isValid = false;
        }
        else {
            tilPhone.setErrorEnabled(false);
        }

        //Check validity of School Name
        if (college.length() == 0) {
            tilSchoolName.setError("Field cannot be blank");
            isValid = false;
        }
        else if (!college.matches("[a-zA-Z ]+")) {
            tilSchoolName.setError("Enter valid School Name");
            isValid = false;
        } else {
            tilSchoolName.setErrorEnabled(false);
        }
        //If everything is valid return errorCode(which is 0, its initial value)
        return isValid;
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
