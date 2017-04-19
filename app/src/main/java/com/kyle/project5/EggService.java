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

import java.util.Random;

/**
 * Created by taylo on 4/12/2017.
 */

public class EggService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                "com.kyle.project5", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();

        Bundle b = intent.getBundleExtra("Info");
       // Toast.makeText(this,b.getInt("num") + ""Toast.LENGTH_SHORT).show();

        if( prefs.getInt("eggCount", 0) == 0) {
            edit.putInt("eggCount", 0);
            edit.commit();
        }

       int eggCount = prefs.getInt("eggCount", 0);

        if(b.getString("description").equals("Breakfast")){

            if(eggCount >= 6){
                eggCount -= 6;
                edit.putInt("eggCount", eggCount);
                edit.commit();
                notifyUser("We are having omelets, we have " + eggCount + " eggs available.");
            }
            else{
                notifyUser("We are having gruel, we have " + eggCount + " eggs available." );
            }


        }
        else if(eggCount + b.getInt("num") >= 0) {
            eggCount += b.getInt("num");
            edit.putInt("eggCount", eggCount);
            edit.commit();

           // Toast.makeText(this, eggCount + "", Toast.LENGTH_SHORT).show();

            notifyUser(b.getString("description"));
        }

        return START_NOT_STICKY;
    }

    private void notifyUser(String desc){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;

        Notification noti = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(desc)
                .setSmallIcon(R.drawable.egg)
                .setOngoing(false)						//true only dismissable by app
                .build();
        noti.flags |= Notification.FLAG_INSISTENT;
        notificationManager.notify(m, noti);
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
