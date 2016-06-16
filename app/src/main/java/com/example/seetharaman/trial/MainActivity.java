package com.example.seetharaman.trial;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String first_name, second_name , ph_no , email_id , school_clg;
    String [] events_list_orig;
    ArrayList<String> events;
    ArrayList<Boolean>  selection_status;
    public int io;

    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        events = new ArrayList<String>();
        selection_status = new ArrayList<Boolean>();


        events_list_orig = getResources().getStringArray(R.array.events);
        if(events_list_orig.length == 0) {
            Toast.makeText(MainActivity.this, "Empty Events", Toast.LENGTH_SHORT).show();
            System.exit(1);
        }

        for(int i =0; i<events_list_orig.length; i++) {
            selection_status.add(false);
        }


        final EditText fn = (EditText)findViewById(R.id.et_firstname);
        final EditText sn = (EditText)findViewById(R.id.et_secondname);
        final EditText phno = (EditText)findViewById(R.id.et_phno);
        final EditText email = (EditText)findViewById(R.id.et_email);
        final EditText school = (EditText)findViewById(R.id.et_school);
        final ImageButton reg = (ImageButton)findViewById(R.id.ib_register);
        final ImageButton add_clr = (ImageButton)findViewById(R.id.ib_add_clear_event);

        Spinner spinner = (Spinner)findViewById(R.id.sp_event);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, events_list_orig);
        spinner.setAdapter(adapter);


        fn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b)
                {
                    if(!(isEmpty(fn)))
                        fn.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                    else
                        fn.setBackgroundColor(Color.parseColor("#FFFD9797"));
                }
            }
        });

        sn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                    if((!isEmpty(sn)))
                        sn.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                    else
                        sn.setBackgroundColor(Color.parseColor("#FFFD9797"));

            }
        });

        phno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                    if((!isEmpty(phno)) && phno.getText().toString().length()==10)
                        phno.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                    else
                        phno.setBackgroundColor(Color.parseColor("#FFFD9797"));

            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                    if(((!isEmpty(email)) && isEmailValid(email_id)))
                        email.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                    else
                        email.setBackgroundColor(Color.parseColor("#FFFD9797"));

            }
        });
        try{

        school.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                    if(((!isEmpty(school))))
                        school.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                    else
                        school.setBackgroundColor(Color.parseColor("#FFFD9797"));

            }
        });}
        catch (NullPointerException e){}










        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                io=i;
                if(selection_status.get(i))
                    add_clr.setImageResource(R.drawable.clear);
                else
                    add_clr.setImageResource(R.drawable.add);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });



        add_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selection_status.get(io))
                {
                    events.remove(search(events_list_orig[io]));
                    selection_status.set(io,false);
                    Toast.makeText(MainActivity.this, "Event removed", Toast.LENGTH_SHORT).show();
                    add_clr.setImageResource(R.drawable.add);
                }
                else
                {
                    events.add(events_list_orig[io]);
                    selection_status.set(io,true);
                    Toast.makeText(MainActivity.this, "Event Added", Toast.LENGTH_SHORT).show();
                    add_clr.setImageResource(R.drawable.clear);
                }
            }
        });





       /* ImageButton b_verify = (ImageButton)findViewById(R.id.ib_verify);

        b_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                first_name = fn.getText().toString();
                second_name = sn.getText().toString();
                ph_no = phno.getText().toString();
                email_id = email.getText().toString();
                school_clg = school.getText().toString();




                if ((!(isEmpty(fn)))&&(!isEmpty(sn)) && ((!isEmpty(phno)) && isValidPhoneNumber(phno.getText())) && ((!isEmpty(email)) && isEmailValid(email_id)) && ((!isEmpty(school))))

                {
                    reg.setImageResource(R.drawable.tick);
                    flag=1;
                }
                else
                {
                    flag=0;
                    reg.setImageResource(R.drawable.cross);

                }



                if(!(isEmpty(fn)))
                    fn.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                else
                    fn.setBackgroundColor(Color.parseColor("#FFFD9797"));

                if((!isEmpty(sn)))
                    sn.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                else
                    sn.setBackgroundColor(Color.parseColor("#FFFD9797"));


                if((!isEmpty(phno)) && isValidPhoneNumber(phno.getText()))
                    phno.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                else
                    phno.setBackgroundColor(Color.parseColor("#FFFD9797"));


                if(((!isEmpty(email)) && isEmailValid(email_id)))
                    email.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                else
                    email.setBackgroundColor(Color.parseColor("#FFFD9797"));

                if(((!isEmpty(school))))
                    school.setBackgroundColor(Color.parseColor("#FFC5FCB1"));
                else
                    school.setBackgroundColor(Color.parseColor("#FFFD9797"));







            }
        });*/








    }


    //To check for empty string
    private boolean isEmpty(EditText myeditText) {     //False If Not Empty .....True If Empty
        return myeditText.getText().toString().trim().length() == 0;
    }


    //To Check the format of the email id....
    public static boolean isEmailValid(String email) {     //check email id pattern
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;


    }

    public int search(String s)
    {
        int i=0;
        while(!(s.equals(events.get(i))))
            i++;

        return i;

    }



    public final static boolean isValidPhoneNumber(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            if (target.length() < 6) {
                return false;
            } else {
                return android.util.Patterns.PHONE.matcher(target).matches();
            }
        }
    }
}






