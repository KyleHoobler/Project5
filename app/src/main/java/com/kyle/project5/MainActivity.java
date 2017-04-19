package com.kyle.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Receiver receiver;
    IntentFilter filter;
    private int eggCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = new IntentFilter();

        receiver = new Receiver();

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
        Intent intent = new Intent();
        intent.putExtra("num", 1);
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }

    public void addTwo(View view){
        Intent intent = new Intent();
        intent.putExtra("num", 2);
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }

    public void minusOne(View view){
        Intent intent = new Intent();
        intent.putExtra("num", -1);
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);

    }

    public void broadcastIntent(View view){

    }

    public void breakfast(View view){
        Intent myService = new Intent(this, EggService.class);
        startService(myService);
    }


}
