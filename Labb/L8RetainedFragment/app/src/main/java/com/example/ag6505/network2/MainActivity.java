package com.example.ag6505.network2;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity {
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        MainFragment mainFragment = (MainFragment)fm.findFragmentById(R.id.fragment);
        controller = new Controller(this,mainFragment);

    }

    @Override
    protected void onDestroy() {
        controller.disconnectClicked();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        controller.saveOutState(outState);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        controller.onRestored(savedInstanceState);
    }
}
