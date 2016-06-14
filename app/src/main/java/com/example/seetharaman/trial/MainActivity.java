package com.example.seetharaman.trial;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextInputLayout til_firstNameWrapper,til_lastNameWrapper,til_contactWrapper,til_collegeWrapper,til_emailWrapper;
    Spinner s_category;
    String[] category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        til_firstNameWrapper=(TextInputLayout)findViewById(R.id.til_firstNameWrapper);
        til_firstNameWrapper.setHint("First Name");
        //til_firstNameWrapper.setError(" ");

        til_lastNameWrapper=(TextInputLayout)findViewById(R.id.til_lastNameWrapper);
        til_lastNameWrapper.setHint("Last Name");
        //til_lastNameWrapper.setError(" ");

        til_emailWrapper=(TextInputLayout)findViewById(R.id.til_emailWrapper);
        til_emailWrapper.setHint("Email");
        //til_emailWrapper.setError(" ");

        til_contactWrapper=(TextInputLayout)findViewById(R.id.til_contactWrapper);
        til_contactWrapper.setHint("Contact No");
        //til_contactWrapper.setError(" ");

        til_collegeWrapper=(TextInputLayout)findViewById(R.id.til_collegeWrapper);
        til_collegeWrapper.setHint("School/College Name");
        //til_collegeWrapper.setError(" ");

        category=new String[]{"Decibels","Creative Writing","Street Sports","Power chords","Acapella"};

        s_category=(Spinner)findViewById((R.id.s_category));
        s_category.setAdapter(new MyAdapter(this,R.layout.spinner_layout,category));
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void submit(View v)
    {
        String firstName = til_firstNameWrapper.getEditText().getText().toString();
        String lastName = til_lastNameWrapper.getEditText().getText().toString();
        String email = til_emailWrapper.getEditText().getText().toString();
        String contact = til_contactWrapper.getEditText().getText().toString();
        String college = til_collegeWrapper.getEditText().getText().toString();
        if(!validatefirstname(firstName))
        {
            til_firstNameWrapper.setError("Not a valid first name!");
            changeColorFilter(til_firstNameWrapper);
            this.showSoftKeyboard(til_firstNameWrapper);
            // til_firstNameWrapper.getEditText().setBackground(getResources().getDrawable(R.drawable.edittextbox));
        }
        else {
            til_firstNameWrapper.setError(" ");
            changeColorFilter(til_firstNameWrapper);
            if (!validatelastname(lastName))
            {
                til_lastNameWrapper.setError("Not a valid last name!");
                changeColorFilter(til_lastNameWrapper);
                this.showSoftKeyboard(til_lastNameWrapper);
            }
            else {
                til_lastNameWrapper.setError(" ");
                changeColorFilter(til_lastNameWrapper);
                if(!validateemail(email))
                {
                    til_emailWrapper.setError("Not a valid email!");
                    changeColorFilter(til_emailWrapper);
                    this.showSoftKeyboard(til_emailWrapper);
                }
                else {
                    til_emailWrapper.setError(" ");
                    changeColorFilter(til_emailWrapper);
                    if (!validatecontact(contact))
                    {
                        til_contactWrapper.setError("Not a valid contact number!");
                        changeColorFilter(til_contactWrapper);
                        this.showSoftKeyboard(til_contactWrapper);
                    }
                    else {
                        til_contactWrapper.setError(" ");
                        changeColorFilter(til_contactWrapper);
                        if(!validatecollege(college)) {
                            til_collegeWrapper.setError("Not a valid school/college name!");
                            changeColorFilter(til_collegeWrapper);
                            this.showSoftKeyboard(til_collegeWrapper);
                        }
                        else {
                            til_collegeWrapper.setError(" ");
                            changeColorFilter(til_collegeWrapper);
                            this.hideSoftKeyboard();
                            Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }

        }
    }

    public boolean validateemail(String email) {
        return (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public boolean validatecontact(String contact) {
        return contact.length()==10;
    }
    public boolean validatefirstname(String firstname){
        return firstname.trim().length()>1;
    }
    public boolean validatelastname(String lastname){
        return lastname.trim().length()>1;
    }
    public boolean validatecollege(String college){
        return college.trim().length()>0;
    }
    public void changeColorFilter(TextInputLayout til){
        til.getEditText().getBackground().setColorFilter(ContextCompat.getColor(this, R.color.red), PorterDuff.Mode.SRC_IN);}
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context ctx, int txtViewResourceId, String[] objects) {
            super(ctx, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return getCustomView(position, cnvtView, prnt);
        }
        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return getCustomView(pos, cnvtView, prnt);
        }
        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View mySpinner = inflater.inflate(R.layout.spinner_layout, parent, false);
            TextView main_text = (TextView) mySpinner.findViewById(R.id.t_category);
            main_text.setText(category[position]);
            return mySpinner;
        }
    }
}



