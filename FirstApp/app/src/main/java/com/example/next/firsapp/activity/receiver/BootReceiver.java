package com.example.next.firsapp.activity.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.next.firsapp.activity.service.UpdateShowService;

/**
 * Created by movile on 28/06/15.
 */
public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateShowService.class), 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 20000, pendingIntent);
    }
}
