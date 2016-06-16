package com.example.seetharaman.trial;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    ArrayList<Boolean>  selection_status;   //True:selected    False:Not selected
    Boolean content_status[]= new Boolean[6];   //true:valid    false:invalid
    public int io;

    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0; i<6;i++)
            content_status[i]=false;


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
        //final ImageButton reg = (ImageButton)findViewById(R.id.ib_register);
        final ImageButton add_clr = (ImageButton)findViewById(R.id.ib_add_clear_event);

        Spinner spinner = (Spinner)findViewById(R.id.sp_event);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, events_list_orig);
        spinner.setAdapter(adapter);


        fn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(isEmpty(fn) ||(!isName(fn))) {
                    fn.setError("Invalid Name");
                    content_status[0]=false;
                }
                else
                    content_status[0]=true;

            }
        });

        sn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(isEmpty(sn) || (!isName(sn))) {
                    sn.setError("Invalid Name");
                    content_status[1] = false;
                }
                else
                    content_status[1]=true;
            }
        });

        phno.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(isEmpty(phno) || phno.getText().toString().length()!=10) {
                    phno.setError("Invalid Number");
                    content_status[2] = false;
                }
                else
                    content_status[2]=true;
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(isEmpty(email)  || !isEmailValid(email.getText().toString())) {
                    email.setError("Invalid Email Id.");
                    content_status[3] = false;
                }
                else
                    content_status[3]=true;
            }
        });

        school.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(isEmpty(school)) {
                    school.setError("Invalid Name");
                    content_status[4] = false;
                }
                else
                    content_status[4]=true;
            }
        });

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





       final ImageButton b_verify = (ImageButton)findViewById(R.id.ib_verify);

        b_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fn.clearFocus();
                sn.clearFocus();
                phno.clearFocus();
                email.clearFocus();
                school.clearFocus();


                first_name = fn.getText().toString();
                second_name = sn.getText().toString();
                ph_no = phno.getText().toString();
                email_id = email.getText().toString();
                school_clg = school.getText().toString();




                if ((events.isEmpty()))
                    content_status[5]=false;
                else
                    content_status[5]=true;


                for(int i=0;i<6;i++)
                {
                    if(content_status[i])
                        flag=1;
                    else {
                        flag = 0;
                        Toast.makeText(MainActivity.this, "Complete The Form To Register", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }



            }
        });








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

    public boolean isName(EditText e)
    {
        String s= e.getText().toString();
        char ch;
        for(int i =0; i<s.length(); i++)
        {
            ch = s.charAt(i);
            if(!((ch<='z'&&ch>='a')||((ch<='Z')&&(ch>='A'))))
                return false;
        }
        return true;
    }




}






