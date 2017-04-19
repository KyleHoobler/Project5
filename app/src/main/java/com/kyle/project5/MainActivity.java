package com.kyle.project5;


import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 * @author Kyle Hoobler and Clifton Dent
 * @version 4/18/17
 *
 * This is an android app that using a broadcast receiver and a service to count eggs
 *
 */
public class MainActivity extends AppCompatActivity {

    Receiver receiver;
    IntentFilter filter;
    private int eggCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        filter = new IntentFilter();
        receiver = new Receiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void addOne(View view){
        Intent intent = new Intent();
        intent.putExtra("num", 1);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.putExtra("description", "One added!");
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }

    public void addTwo(View view){
        Intent intent = new Intent();
        intent.putExtra("num", 2);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.putExtra("description", "Two added!");
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }

    public void minusOne(View view){
        Intent intent = new Intent();
        intent.putExtra("num", -1);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.putExtra("description", "One removed!");
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);

    }

    public void breakfast(View view){
        Intent intent = new Intent();
        intent.putExtra("description", "Breakfast");
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }


}
