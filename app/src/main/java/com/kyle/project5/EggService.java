package com.kyle.project5;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by taylo on 4/12/2017.
 */

public class EggService extends Service {


    private static final int MYNOTIFICATION = 1;
    private static final int TWO_SECONDS = 2000;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                "com.kyle.project5", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();

        Bundle b = intent.getBundleExtra("Info");
       // Toast.makeText(this,b.getInt("num") + "",Toast.LENGTH_SHORT).show();

        if( prefs.getInt("eggCount", 0) == 0) {
            edit.putInt("eggCount", 0);
            edit.commit();
        }

       int eggCount = prefs.getInt("eggCount", 0);
        if(eggCount + b.getInt("num") >= 0) {
            eggCount += b.getInt("num");
            edit.putInt("eggCount", eggCount);
            edit.commit();

            Toast.makeText(this, eggCount + "", Toast.LENGTH_SHORT).show();
            notifyUser(b);
        }



        return START_NOT_STICKY;
    }

    private void notifyUser(Bundle bundle){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification noti = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(bundle.getString("description"))
                .setSmallIcon(R.drawable.egg)
                .setOngoing(false)						//true only dismissable by app
                .build();
        noti.flags |= Notification.FLAG_INSISTENT;
        notificationManager.notify(MYNOTIFICATION, noti);
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
