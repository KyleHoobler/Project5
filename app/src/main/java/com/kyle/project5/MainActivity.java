package com.kyle.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver;
    IntentFilter filter;
    private int eggCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = new IntentFilter();

        receiver = new BroadcastReceiver(){


            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();
                if(action != null){

                    

                }

            }
        };

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void addOne(View view){
        Intent i = new Intent("One Added!");
        sendBroadcast(i);
        eggCount++;

    }

    public void addTwo(View view){

    }

    public void minusOne(View view){

    }

    public void breakfast(View view){
        Intent myService = new Intent(this, EggService.class);
        startService(myService);
    }


}
