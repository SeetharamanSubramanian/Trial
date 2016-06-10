package com.example.seetharaman.trial;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etInstitute;
    private EditText etEmailAddrss;
    private EditText etPhoneNumber;
    private Button bRegister;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private Spinner sp_events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sp_events = (Spinner) findViewById(R.id.s_events);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.events_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert sp_events != null;
        sp_events.setAdapter(adapter);
        etFirstName = (EditText) findViewById(R.id.et_firstname);
        etLastName = (EditText) findViewById(R.id.et_lastname);
        etInstitute = (EditText) findViewById(R.id.et_institute);
        etEmailAddrss = (EditText) findViewById(R.id.et_email);
        etPhoneNumber = (EditText) findViewById(R.id.et_phonenumber);
        rbFemale = (RadioButton) findViewById(R.id.rb_female);
        rbMale = (RadioButton) findViewById(R.id.rb_male);
        bRegister = (Button) findViewById(R.id.b_register);
    }

    public void onClickRegisterBtn(View view) {
        Validation.isName(etFirstName,true);
        Validation.isName(etLastName,true);
        Validation.hasText(etInstitute);
        Validation.isPhoneNumber(etPhoneNumber, true);
        Validation.isEmailAddress(etEmailAddrss, true);
        if (checkValidation())
            submitForm();
        else
            Toast.makeText(MainActivity.this, "Form contains error", Toast.LENGTH_LONG).show();
    }

    private void submitForm() {
        // Submit your form here. your form is valid
        Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.isName(etFirstName, true)) ret = false;
        if (!Validation.isName(etLastName, true)) ret = false;
        if (!Validation.hasText(etInstitute)) ret = false;
        if (!Validation.isEmailAddress(etEmailAddrss, true)) ret = false;
        if (!Validation.isPhoneNumber(etPhoneNumber, true)) ret = false;

        return ret;
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        View v = getCurrentFocus();

        if (v!=null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)&&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit")){
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft()-scrcoords[0];
            float y = ev.getRawY() + v.getTop()-scrcoords[1];

            if (x<v.getLeft() || x>v.getRight() || y<v.getTop() || y>v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity)
    {
        if(activity!=null && activity.getWindow()!=null && activity.getWindow().getDecorView()!=null){
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(),0);
        }
    }

    public void onClickRb(View view) {
        if (view.getId()==R.id.rb_male && rbFemale.isChecked()){
            rbFemale.setChecked(false);
        }
        if (view.getId()==R.id.rb_female && rbMale.isChecked()){
            rbMale.setChecked(false);
        }

    }
}

