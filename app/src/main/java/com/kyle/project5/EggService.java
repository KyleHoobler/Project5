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
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by Clifton Dent on 4/12/2017.
 */
public class EggService extends Service {


    /**
     * Creates notifications based on the actions the user sends
     *
     * @param intent with info
     * @param flags
     * @param startId
     * @return number of eggs
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //Shared Prefs
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                "com.kyle.project5", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();

        //puts info into a single bundle
        Bundle b = intent.getBundleExtra("Info");

        //if egg count has no value set to 0 in edit
        if( prefs.getInt("eggCount", 0) == 0) {
            edit.putInt("eggCount", 0);
            edit.commit();
        }

        //Get the starting number of eggs
       int eggCount = prefs.getInt("eggCount", 0);

        //if breakfast is sent
        if(b.getString("description").equals("Breakfast")){

            //if there are enough eggs to make breakfast
            if(eggCount >= 6){
                //Subtract 6
                eggCount -= 6;
                //update eggCount
                edit.putInt("eggCount", eggCount);
                edit.commit();

                //Put out a notification
                notifyUser("We are having omelets, we have " + eggCount + " eggs available.");
            }
            //if not enough eggs
            else{
                //Tell user they don't have enough eggs
                notifyUser("We are having gruel, we have " + eggCount + " eggs available." );
            }
        }
        //Else if egg count stays above 0
        else if(eggCount + b.getInt("num") >= 0) {
            //add new eggs
            eggCount += b.getInt("num");
            edit.putInt("eggCount", eggCount);
            edit.commit();

            //Output sent message in notification
            notifyUser(b.getString("description"));
        }

        return START_NOT_STICKY;
    }

    /**
     * This method creates the notification
     * @param desc String to be displayed in notification
     */
    private void notifyUser(String desc){
        //creates manager to output notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //allow for multiple notification
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;


        //Notification builder
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
