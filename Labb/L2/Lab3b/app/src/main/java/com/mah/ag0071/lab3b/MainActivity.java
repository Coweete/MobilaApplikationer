package com.mah.ag0071.lab3b;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSystem();
    }

    private void initSystem() {

        FragmentManager fm = getFragmentManager();
        ViewerFragment viewerFragment = (ViewerFragment) fm.findFragmentById(R.id.frView);
        InputFragment inputFragment = (InputFragment) fm.findFragmentById(R.id.frInput);
        Controller controller = new Controller(inputFragment,viewerFragment,this);
        inputFragment.setController(controller);

    }
}
