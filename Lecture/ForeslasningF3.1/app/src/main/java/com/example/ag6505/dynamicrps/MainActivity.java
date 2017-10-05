package com.example.ag6505.dynamicrps;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initController();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initController() {
        RPSPlayer computerPlayer = new RPSPlayer();
        new RPSController(this, computerPlayer );
    }

    public void setViewer(Viewer viewer) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.upper_container, (Fragment)viewer);
        fragmentTransaction.commit();
    }

    public void setInput(Input input) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lower_container, (Fragment)input);
        fragmentTransaction.commit();
    }
}