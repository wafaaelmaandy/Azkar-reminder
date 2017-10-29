package com.example.viva.azkar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Viva on 10/29/2017.
 */

public class Startupreciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Intent serviceIntent = new Intent(context,StartupService.class);
            context.startService(serviceIntent);
        }
    }
}
