package com.kyle.project5;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by taylo on 4/12/2017.
 */

public class EggService extends Service {



    private static final int TWO_SECONDS = 2000;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "In Service", Toast.LENGTH_SHORT).show();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                "com.kyle.project5", Context.MODE_PRIVATE);

       // if(prefs.)


        return START_NOT_STICKY;
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
