package com.mah.ag0071.lab3a;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeSystem();
    }

    private void initializeSystem() {
        FragmentManager fm = getFragmentManager();
        ViewerFragment viewerFragment = (ViewerFragment) fm.findFragmentById(R.id.frViewer);
        InputFragment inputFragment = (InputFragment) fm.findFragmentById(R.id.frInput);
        Controller controller = new Controller(inputFragment,viewerFragment);
        inputFragment.setController(controller);

    }
}
