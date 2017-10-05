package com.example.ag6505.lab7b;

import android.graphics.Color;

/**
 * Created by User on 2017-09-25.
 */

public class Controller {

    private MainActivity mainActivity;

    public Controller(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void onPress(){
        Colorize colorize = new Colorize();
        colorize.run();
    }


    private class Colorize extends Thread{

        public void run(){
            mainActivity.runOnUiThread(new ColorChange(Color.RED));
        }
    }


    private class ColorChange implements Runnable{

        private int color;

        public ColorChange(int color) {
            this.color = color;
        }

        @Override
        public void run() {
            mainActivity.setColor1(color);
            mainActivity.setColor2(color);
        }
    };

}
