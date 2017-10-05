package com.mah.ag0071.assigment1;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        controller = new MainController(this,intent);

        if(savedInstanceState == null){
            controller.setStartFragment();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*
        Log.v("EXP","On back pressed");
        FragmentManager fm = getFragmentManager();
        Log.v("FRAG","Fragments on stack " + fm.getBackStackEntryCount());
        super.onBackPressed();
        controller.setFragmentValue();
        */
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            Log.v("FRAGMENT","In bundle");
            controller.rescueFragment(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        controller.saveInfo(outState);
        super.onSaveInstanceState(outState);
    }

    public void setFragment(Fragment fragment, boolean backStack){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFragmentContainer,fragment);
        if(backStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public MainController getController(){
        return this.controller;
    }
}
