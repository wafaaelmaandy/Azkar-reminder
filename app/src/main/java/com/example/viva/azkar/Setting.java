package com.example.viva.azkar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    TextView textView ;
    TextView textView2 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        textView =(TextView)findViewById(R.id.hour);
        textView2 =(TextView)findViewById(R.id.min);
    }

    public void save(View view) {

        int nmin =Integer.parseInt(textView.getText().toString())*60 + Integer.parseInt(textView2.getText().toString())  ;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit() ;
        editor.putInt("nmin",nmin);
        editor.apply();
        Toast.makeText(this,"saved ! app will notify you every "+nmin/60 +"hour" + nmin%60 +"min",Toast.LENGTH_SHORT).show();
    }



    }

