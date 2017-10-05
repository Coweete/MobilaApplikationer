package com.mah.ag0071.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Controller controller;
    private Button btnNext;
    private Button btnPrev;
    private TextView tvWhatToDo;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        registerListeners();
        controller = new Controller(this);
    }

    private void initializeComponents() {
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        tvWhatToDo = (TextView) findViewById(R.id.tvWhatToDo);
        tvContent = (TextView) findViewById(R.id.tvContent);
        setWhatToDo(getResources().getString(R.string.what_to_do));
        setContent(getResources().getString(R.string.content));
    }

    private void registerListeners() {
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.previousClick();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                controller.nextClick();
            }
        });
    }

    public void setWhatToDo(String whatToDo) {
        tvWhatToDo.setText(whatToDo);
    }

    public void setContent(String content) {
        tvContent.setText(content);
    }
}
