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

    private Receiver receiver;
    private IntentFilter filter;
    private int eggCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Loads the xml
        setContentView(R.layout.activity_main);
    }

    /**
     * When app is reopenned
     */
    @Override
    protected void onResume(){
        super.onResume();
        //since receiver is closed when paused creates a new reciever
        filter = new IntentFilter();
        receiver = new Receiver();
        registerReceiver(receiver, filter);
    }

    /**
     * Unregisters reciever
     */
    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(receiver);
    }

    /**
     * Adds one egg to the service
     * @param view the view
     */
    public void addOne(View view){
        Intent intent = new Intent();
        intent.putExtra("num", 1);
        //Loads service faster
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.putExtra("description", "One added!");
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }

    /**
     * Adds 2 eggs to service
     * @param view the view
     */
    public void addTwo(View view){
        Intent intent = new Intent();
        intent.putExtra("num", 2);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.putExtra("description", "Two added!");
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }

    /**
     * Subtracts an egg from the service
     * @param view the view
     */
    public void minusOne(View view){
        Intent intent = new Intent();
        intent.putExtra("num", -1);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.putExtra("description", "One removed!");
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);

    }

    /**
     * Subtracts 6 eggs if there are more than 6, and makes omlettes; otherwise, does nothing and makes gruel
     * @param view teh view
     */
    public void breakfast(View view){
        Intent intent = new Intent();
        intent.putExtra("description", "Breakfast");
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.setAction("com.kyle.project5.EGGCELANT");
        sendBroadcast(intent);
    }


}
