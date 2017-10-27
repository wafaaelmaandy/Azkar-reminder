package com.example.viva.azkar;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Viva on 10/4/2017.
 */

public class AzkarIncrementServise extends IntentService {
     static  String ACTION_increment ="increment" ;
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String ACTION_cycle_NOTIFICATION = "cycle";


    public AzkarIncrementServise() {
        super("AzkarIncrementServise");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        if (ACTION_increment.equals(action))
            Performaces.incrementCount(this);
            else if(ACTION_DISMISS_NOTIFICATION.equals(action))
                AzkarNotification.clearAllNotifications(this);
        else if(ACTION_cycle_NOTIFICATION.equals(action))
             AzkarNotification.remindUser(this);


        }


    }




