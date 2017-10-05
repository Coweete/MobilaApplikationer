package com.mah.ag0071.fragmenttest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FirstFragment firstFragment = new FirstFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framelayout, (android.app.Fragment) firstFragment);
        transaction.commit();
    }
}
