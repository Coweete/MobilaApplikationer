package com.example.ag6505.lab7c;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by User on 2017-09-25.
 */

public class ColorTextview  extends TextView{

    private int[] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
    private MainActivity mainActivity;

    public ColorTextview(Context context) {
        super(context);
    }

    public ColorTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ColorTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        super.setBackgroundColor(color);
    }

    public void party(int max,int delay,MainActivity mainActivity){
        this.mainActivity = mainActivity;
        Colorize colorize = new Colorize();
        colorize.execute(0,max,delay);
    }


    private class Colorize extends AsyncTask<Integer,Void,Void> {

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
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(50);
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
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            mainActivity.setColor1(colors[position%4]);
        }
    }
}
