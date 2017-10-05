package com.example.ag6505.lab7e;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ColorTextview textView1;
    private Controller controller;
    private TimerTask timerTask;
    private Timer timer;
    private int[] colors = {Color.RED,Color.BLUE,Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new Controller(this);
        initComp();
        controller.setColorTextView(textView1);


        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                controller.onPress();
            }
        };
        timer.schedule(timerTask,50,500);
    }




    private void initComp() {
        textView1 = (ColorTextview) findViewById(R.id.tv1);
    }
}
