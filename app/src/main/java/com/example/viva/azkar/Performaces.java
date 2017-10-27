package com.example.viva.azkar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Viva on 10/4/2017.
 */

public class Performaces {

    public  static void setCounter(Context context , int count){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit() ;
        editor.putInt("count",count);
        editor.putInt("nmin",270);
        editor.apply();
    }
    public static int getCounter(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int counter = prefs.getInt("count",0);
        return counter ;
    }

    synchronized public static void incrementCount(Context context) {
        int count = Performaces.getCounter(context);
        Performaces.setCounter(context, ++count);
    }
}
