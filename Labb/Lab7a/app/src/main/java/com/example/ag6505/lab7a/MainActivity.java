package com.example.ag6505.lab7a;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView1;
    private TextView textView2;
    private Controller controller;
    private int[] colors = {Color.RED,Color.BLUE,Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new Controller(this);

        initComp();
        initRes();
    }

    private void initRes() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               controller.startAsyncTask();
            }
        });
    }


    private void initComp() {
        button = (Button) findViewById(R.id.btnButton);
        textView1 = (TextView) findViewById(R.id.tv1);
        textView2 = (TextView) findViewById(R.id.tv2);
    }

    public void setColor1(int color) {
        textView1.setBackgroundColor(color);
    }

    public void setColor2(int color){
        textView2.setBackgroundColor(color);
    }
}
