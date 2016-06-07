package trial;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.example.seetharaman.trial.R;

public class Registration_Activity extends Activity{

    TextInputLayout til_first_name, til_last_name, til_email, til_phone_number, til_college_name;
    String first_name, last_name, email, phone_number, college_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        Toolbar t_reg_toolbar=(Toolbar)findViewById(R.id.t_reg_toolbar);
        t_reg_toolbar.setTitle("Registration Page");
        t_reg_toolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));

        til_first_name=(TextInputLayout)findViewById(R.id.til_first_name);
        til_last_name=(TextInputLayout)findViewById(R.id.til_last_name);
        til_email=(TextInputLayout)findViewById(R.id.til_email);
        til_phone_number=(TextInputLayout)findViewById(R.id.til_phone_number);
        til_college_name=(TextInputLayout)findViewById(R.id.til_college_name);
    }

    public void submit(View view){

        first_name=til_first_name.getEditText().getText().toString();
        last_name=til_last_name.getEditText().getText().toString();
        email=til_email.getEditText().getText().toString();
        phone_number=til_phone_number.getEditText().getText().toString();
        college_name=til_college_name.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

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

