package com.example.viva.azkar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import static java.security.AccessController.getContext;

/**
 * Created by Viva on 10/4/2017.
 */

public class AzkarNotification {
    private static final int Azkar_NOTIFICATION_ID = 1138;

    private static final int Azkar_PENDING_INTENT_ID = 3417;

    private static final int ACTION_ZEKER_PENDING_INTENT_ID = 1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;
    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }


    public static void remindUser(Context context) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)

                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.icon1)
                .setContentTitle("صلى على النبى ")
                .setContentText("اللهم صلى وسلم وبارك على سبدنا محمد ")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                       "اللهم صلى وسلم وبارك على سبدنا محمد"))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(incremntZeker(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true)

                .setSound(Uri.parse("android.resource://" +"com.example.viva.azkar" + "/" + R.raw.mohammed)) ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);


        notificationManager.notify(Azkar_NOTIFICATION_ID , notificationBuilder.build());
    }
    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(
                context,
                Azkar_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        Intent ignoreReminderIntent = new Intent(context, AzkarIncrementServise.class);
        ignoreReminderIntent.setAction(AzkarIncrementServise.ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_action_stat_reply,
                "No, thanks.",
                ignoreReminderPendingIntent);
        return ignoreReminderAction;
    }

    private static NotificationCompat.Action incremntZeker(Context context) {
        Intent incrementZekerCountIntent = new Intent(context, AzkarIncrementServise.class);
        incrementZekerCountIntent.setAction(AzkarIncrementServise.ACTION_increment);
        PendingIntent incrementZekerPendingIntent = PendingIntent.getService(
                context,
                ACTION_ZEKER_PENDING_INTENT_ID ,
                incrementZekerCountIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Action zekerAction = new NotificationCompat.Action(R.drawable.ic_action_stat_reply,
                "I did it!",incrementZekerPendingIntent
                );
        return zekerAction;
    }




    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.icon1);
        return largeIcon;
    }



}
