package com.kyle.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Kyle on 4/12/2017.
 */
public class Receiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent background = new Intent(context, EggService.class);
        background.putExtra("Info", intent.getExtras());

        context.startService(background);

    }



}
