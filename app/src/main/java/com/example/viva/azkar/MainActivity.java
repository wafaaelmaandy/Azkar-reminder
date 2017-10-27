package com.example.viva.azkar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
    SharedPreferences.OnSharedPreferenceChangeListener {

    static MainActivity ma ;



        static int  id =1 ;
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ma=this;
        setContentView(R.layout.activity_main);
        textView =(TextView)findViewById(R.id.counter);
        textView.setText(Performaces.getCounter(this)+"");

        updateCounter();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        Reminder.scheduleChargingReminder(this);
    }
    public void count (View view){
        Intent incrementWaterCountIntent = new Intent(this, AzkarIncrementServise.class);
        incrementWaterCountIntent.setAction(AzkarIncrementServise.ACTION_increment);
        startService(incrementWaterCountIntent);
    }
    private void updateCounter() {
        int waterCount = Performaces.getCounter(this);
        textView.setText(waterCount+"");
    }


    public void reset(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit() ;
        editor.putInt("count",0);
        editor.apply();
      AzkarNotification.remindUser(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /** Cleanup the shared preference listener **/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if ("count".equals(key)) {
            updateCounter();
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting: {
                Intent intent = new Intent(this,Setting.class);
                startActivity(intent);
                return true;
            }
            case R.id.about: {
                Intent intent = new Intent(this, about.class);
                startActivity(intent);

                return true;
            }

        }
        return false;
    }

    public void setting(View view){
        AzkarNotification.remindUser(this);


    }
}
