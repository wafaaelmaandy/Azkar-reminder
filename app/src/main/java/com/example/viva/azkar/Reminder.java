package com.example.viva.azkar;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.nio.charset.MalformedInputException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Viva on 10/5/2017.
 */

public class Reminder extends Application {
    public static Context g() {
        return MainActivity.ma.getApplicationContext();
    }



    public static  int getmin(Context context){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( context  );
        return prefs.getInt("nmin",270);

    }
    private   static int REMINDER_INTERVAL_MINUTES = getmin(g());

    private   static int REMINDER_INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES));
    private    static int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

    private static final String REMINDER_JOB_TAG = "zeker_reminder_tag";

    private static boolean sInitialized;

    synchronized public  static void scheduleChargingReminder(@NonNull final Context context) {


        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);


        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(AzkerReminderFirebaseJobService.class)

                .setTag(REMINDER_JOB_TAG)

                .setLifetime(Lifetime.FOREVER)

                .setRecurring(true)


                .setTrigger(Trigger.executionWindow(
                        REMINDER_INTERVAL_SECONDS,
                        REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))

                .setReplaceCurrent(true)
                .build();


       dispatcher.schedule(constraintReminderJob);


        sInitialized = true;
    }
}
