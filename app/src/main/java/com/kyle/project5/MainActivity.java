package com.kyle.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = new IntentFilter();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
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
}
