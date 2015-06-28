package com.example.next.firsapp.activity.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.example.next.firsapp.R;
import com.example.next.firsapp.activity.ShowDetailsActivity;
import com.example.next.firsapp.model.ShowUpdate;
import com.example.next.firsapp.util.FormatUtil;

/**
 * Created by movile on 28/06/15.
 */
public class UpdateLocalBroadCast extends BroadcastReceiver{
    private final static String PREFERENCE = "preference_file";
    private final static String KEY_LAST_UPDATE = "last_update";

    public void onReceive(Context context, Intent intent){
        String lastUpdate;
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        Bundle extras = intent.getExtras();
        if (extras != null) {
            ShowUpdate update = (ShowUpdate) extras.get("UPDATE_MSG");

            lastUpdate = preferences.getString(KEY_LAST_UPDATE,null);
            if (lastUpdate != null) {
                if (FormatUtil.formatDate(lastUpdate).equals(FormatUtil.formatDate(update.date())) == false) {
                    createNotification(update, context);
                    savePreferences(update.date(), preferences);
                }
            } else {
                createNotification(update, context);
                savePreferences(update.date(), preferences);
            }
        }

    }

    public void savePreferences(String lastUpdate, SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LAST_UPDATE,lastUpdate);
        editor.commit();
    }

    public PendingIntent getNewIntentTask(ShowUpdate update, Context mContext){
        Intent intent = new Intent(mContext, ShowDetailsActivity.class);
        intent.putExtra("SHOW", update.show());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(ShowDetailsActivity.class);
        stackBuilder.addNextIntent(intent);

        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


    }

    public void createNotification(ShowUpdate update, Context mContext){


       // PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pendingIntent = getNewIntentTask(update, mContext);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(update.title())
                .setContentText(update.message())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(update.message()));

        Notification notification = builder.build();

        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, notification);

    }

}
