package com.example.seetharaman.trial;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

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

}
