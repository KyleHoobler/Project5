package com.kyle.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Kyle on 4/12/2017.
 */
public class Receiver extends BroadcastReceiver{

    /**
     * Creates the reciever and handles the information
     * @param context context
     * @param intent intent sent with info
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent background = new Intent(context, EggService.class);
        background.putExtra("Info", intent.getExtras());

        //Starts service
        context.startService(background);

    }



}
