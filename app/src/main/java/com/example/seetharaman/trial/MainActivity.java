package com.example.seetharaman.trial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText firstNameET, lastNameET, emailET, phoneET, collegeET;
    private TextInputLayout tilFirstName, tilLastName, tilEmail, tilPhone, tilSchoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert toolbar != null;
        toolbar.setTitle("Registrations");

        spinner = (Spinner) findViewById(R.id.event_spinner);
        List<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_spinner_item, list);

        list.add("Raagapella");
        list.add("Alankar");
        list.add("Decibels");
        list.add("Vox");
        list.add("$treet$");

        spinner.setAdapter(adapter);
        spinner.setPrompt("Select an event");

        firstNameET = (EditText) findViewById(R.id.et_first_name);
        lastNameET = (EditText) findViewById(R.id.et_last_name);
        emailET = (EditText) findViewById(R.id.et_email);
        phoneET = (EditText) findViewById(R.id.et_phone);
        collegeET = (EditText) findViewById(R.id.et_school_name);


        tilFirstName = (TextInputLayout) findViewById(R.id.til_first_name);
        tilLastName = (TextInputLayout) findViewById(R.id.til_last_name);
        tilEmail = (TextInputLayout) findViewById(R.id.til_email);
        tilPhone = (TextInputLayout) findViewById(R.id.til_phone);
        tilSchoolName = (TextInputLayout) findViewById(R.id.til_school_name);

        firstNameET.addTextChangedListener(new MyTextWatcher(firstNameET));
        lastNameET.addTextChangedListener(new MyTextWatcher(lastNameET));
        emailET.addTextChangedListener(new MyTextWatcher(emailET));
        phoneET.addTextChangedListener(new MyTextWatcher(phoneET));
        collegeET.addTextChangedListener(new MyTextWatcher(collegeET));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void submitInfo(View view) {
        //Check whether the inputs given by user are valid:
        Boolean isValid = checkValidity();
        hideKeyboard();

        if (isValid) {
            Toast submitToast = Toast.makeText(getApplicationContext(), "Successfully submitted!", Toast.LENGTH_SHORT);
            submitToast.show();

            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public Boolean checkValidity() {
        Boolean isValid;

        //Check validity of First Name
        isValid = isValidFirstName();
        if (!isValid) {
            requestFocus(firstNameET);
            return isValid;
        }

        //Check validity of Last Name
        isValid = isValidLastName();
        if (!isValid) {
            requestFocus(lastNameET);
            return isValid;
        }

        //Check validity of Email Address
        isValid = isValidEmail();
        if (!isValid) {
            requestFocus(emailET);
            return isValid;
        }

        //Check validity of Phone Number
        isValid = isValidPhone();
        if (!isValid) {
            requestFocus(phoneET);
            return isValid;
        }

        //Check validity of School Name
        isValid = isValidSchoolName();
        if (!isValid) {
            requestFocus(collegeET);
            return isValid;
        }

        return isValid;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    //Check validity of First Name
    public boolean isValidFirstName() {
        Boolean isValid = true;
        String firstName = firstNameET.getText().toString().trim();
        if (firstName.length() == 0) {
            tilFirstName.setError("Field cannot be blank");
            isValid = false;
        } else if (!firstName.matches("[a-zA-Z ]+")) {
            tilFirstName.setError("Enter valid First Name");
            isValid = false;
        } else {
            tilFirstName.setError(null);
            tilFirstName.setErrorEnabled(false);
        }
        return isValid;
    }

    //Check validity of Last Name
    public boolean isValidLastName() {
        Boolean isValid = true;
        String lastName = lastNameET.getText().toString().trim();
        if (lastName.isEmpty()) {
            tilLastName.setError("Field cannot be blank");
            isValid = false;
        } else if (!lastName.matches("[a-zA-Z ]+")) {
            tilLastName.setError("Enter valid First Name");
            isValid = false;
        } else {
            tilLastName.setError(null);
            tilLastName.setErrorEnabled(false);
        }
        return isValid;
    }

    //Check validity of Email Address
    public boolean isValidEmail() {
        Boolean isValid;
        String email = emailET.getText().toString().trim();
        if (email.isEmpty()) {
            tilEmail.setError("Field cannot be blank");
            isValid = false;
        } else {
            isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            if (!isValid) {
                tilEmail.setError("Enter a valid Email Address");
            } else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }
        }
        return isValid;
    }

    //Check validity of Phone Number
    public boolean isValidPhone() {
        Boolean isValid;
        String phone = phoneET.getText().toString().trim();
        if (phone.isEmpty()) {
            tilPhone.setError("Field cannot be blank");
            isValid = false;
        } else if (phone.length() > 13) {
            tilPhone.setError("Enter a valid Phone Number");
            isValid = false;
        } else if (phone.length() < 10) {
            tilPhone.setError("Enter a valid Phone Number");
            isValid = false;
        } else if (!android.util.Patterns.PHONE.matcher(phone).matches()) {
            tilPhone.setError("Enter a valid Phone Number");
            isValid = false;
        } else {
            tilPhone.setError(null);
            tilPhone.setErrorEnabled(false);
            isValid = true;
        }

        return isValid;
    }

    //Check validity of School Name
    public boolean isValidSchoolName() {
        Boolean isValid = true;
        String college = collegeET.getText().toString().trim();
        if (college.isEmpty()) {
            tilSchoolName.setError("Field cannot be blank");
            isValid = false;
        } else if (!college.matches("[a-zA-Z ]+")) {
            tilSchoolName.setError("Enter valid School/College Name");
            isValid = false;
        } else {
            tilSchoolName.setError(null);
            tilSchoolName.setErrorEnabled(false);
        }
        return isValid;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
           hideKeyboard1(view);
        }
    }

    public void hideKeyboard1(View view) {
        tilFirstName.clearFocus();
        tilLastName.clearFocus();
        tilEmail.clearFocus();
        tilPhone.clearFocus();
        tilSchoolName.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_first_name:
                    isValidFirstName();
                    break;
                case R.id.et_last_name:
                    isValidLastName();
                    break;
                case R.id.et_email:
                    isValidEmail();
                    break;
                case R.id.et_phone:
                    isValidPhone();
                    break;
                case R.id.et_school_name:
                    isValidSchoolName();
                    break;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.action_reset) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

