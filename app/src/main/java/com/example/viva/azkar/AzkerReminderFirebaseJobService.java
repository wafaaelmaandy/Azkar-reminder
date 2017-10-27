package com.example.viva.azkar;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.RetryStrategy;

/**
 * Created by Viva on 10/5/2017.
 */

public class AzkerReminderFirebaseJobService extends JobService {
    AsyncTask mBackgroundTask ;
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
        Context context = AzkerReminderFirebaseJobService.this ;
                AzkarNotification.remindUser(context);
                return null;
            }



        @Override
        protected void onPostExecute(Object o) {



           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                jobFinished(jobParameters, false);
            }
        }
    };

        mBackgroundTask.execute();
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }



}
