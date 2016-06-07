package trial;


import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.seetharaman.trial.R;

public class Registration_Activity extends ActionBarActivity{

    TextInputLayout til_first_name, til_last_name, til_email, til_phone_number, til_college_name;
    String first_name, last_name, email, phone_number, college_name;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        Toolbar t_reg_toolbar=(Toolbar)findViewById(R.id.t_reg_toolbar);
        t_reg_toolbar.setTitle("Registration Page");
        t_reg_toolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));
        setSupportActionBar(t_reg_toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorIcons), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        til_first_name=(TextInputLayout)findViewById(R.id.til_first_name);
        til_last_name=(TextInputLayout)findViewById(R.id.til_last_name);
        til_email=(TextInputLayout)findViewById(R.id.til_email);
        til_phone_number=(TextInputLayout)findViewById(R.id.til_phone_number);
        til_college_name=(TextInputLayout)findViewById(R.id.til_college_name);


        final EditText et_first_name=(EditText)findViewById(R.id.et_first_name);
        et_first_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_first_name.getText().toString().length() != 0) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_first_name.setCompoundDrawables(null, null, dr, null);
                        til_first_name.setError(" ");
                    } else {
                        et_first_name.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });

        final EditText et_last_name=(EditText)findViewById(R.id.et_last_name);
        et_last_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_last_name.getText().toString().length() != 0) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_last_name.setCompoundDrawables(null, null, dr, null);
                    }
                    else {
                        et_last_name.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });


        final EditText et_email=(EditText)findViewById(R.id.et_email);
        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_email.getText().toString().matches(emailPattern)) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_email.setCompoundDrawables(null, null, dr, null);
                        til_email.setError(" ");
                    } else {
                        et_email.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });


        final EditText et_phone_number=(EditText)findViewById(R.id.et_phone_number);
        et_phone_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_phone_number.getText().toString().length() == 10) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_phone_number.setCompoundDrawables(null, null, dr, null);
                        til_phone_number.setError(" ");
                    } else {
                        et_phone_number.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });


        final EditText et_college_name=(EditText)findViewById(R.id.et_college_name);
        et_college_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_college_name.getText().toString().length() != 0) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_college_name.setCompoundDrawables(null, null, dr, null);
                    } else {
                        et_college_name.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });



    }

    public void submit(View view){

        first_name=til_first_name.getEditText().getText().toString();
        last_name=til_last_name.getEditText().getText().toString();
        email=til_email.getEditText().getText().toString();
        phone_number=til_phone_number.getEditText().getText().toString();
        college_name=til_college_name.getEditText().getText().toString();

        if(first_name.trim().length()==0)
            til_first_name.setError("Field cannot remain empty");
        else
            til_first_name.setError(" ");

        if(!email.matches(emailPattern))
            til_email.setError("Invalid e-mail address");
        else
            til_email.setError(" ");

        if(phone_number.length()<10)
            til_phone_number.setError("Invalid phone number");
        else
            til_phone_number.setError(" ");

        if(first_name.trim().length()!=0 && email.matches(emailPattern) && phone_number.length()==10)
            Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
    }
}

