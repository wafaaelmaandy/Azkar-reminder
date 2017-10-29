package com.example.viva.azkar;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Viva on 10/29/2017.
 */

public class StartupService extends IntentService {
    public StartupService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
