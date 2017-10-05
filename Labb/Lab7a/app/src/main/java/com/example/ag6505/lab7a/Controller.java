package com.example.ag6505.lab7a;

import android.graphics.Color;
import android.os.AsyncTask;
import android.provider.CalendarContract;

/**
 * Created by User on 2017-09-25.
 */

public class Controller {

    private ColorChanger colorChanger;
    private MainActivity mainActivity;
    private int[] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
    private final int MAX = 3,DELAY = 500;

    public Controller(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void startAsyncTask(){
        startAsyncTask(0);
    }

    public void startAsyncTask(int startpos){
        colorChanger = new ColorChanger();
        colorChanger.setRunning(true);
        colorChanger.execute(startpos,MAX,DELAY);
    }


    private class ColorChanger extends AsyncTask<Integer,Void,Void>{

        boolean running = false;
        int position;


        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
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
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            mainActivity.setColor1(colors[position%4]);
            mainActivity.setColor2(colors[position%2]);
        }
    };
}
