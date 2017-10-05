package com.example.ag6505.lab7c;

import android.graphics.Color;
import android.os.AsyncTask;

/**
 * Created by User on 2017-09-25.
 */

public class Controller {

    private MainActivity mainActivity;
    //private Colorize colorize;
    private ColorTextview colorTextview;
    private final int MAX = 3,DELAY = 500;

    public Controller(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        colorTextview = new ColorTextview(mainActivity);
    }


    public void onPress() {
        colorTextview.party(MAX,DELAY,mainActivity);
    }

/*    private class Colorize extends AsyncTask<Integer,Void,Void>{

        boolean running = false;
        int position;

        public Colorize(){
            running = true;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            position = params[0];
            int times = params[1];
            long pause = params[2];
            while(running && position<times){
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress();
                position++;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mainActivity.setColor1(Color.TRANSPARENT);
            mainActivity.setColor2(Color.TRANSPARENT);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            mainActivity.setColor1(Color.RED);
            mainActivity.setColor2(Color.RED);
        }
    }*/
}
