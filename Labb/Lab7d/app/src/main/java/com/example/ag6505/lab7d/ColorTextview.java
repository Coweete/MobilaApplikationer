package com.example.ag6505.lab7d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by User on 2017-09-25.
 */

public class ColorTextview extends TextView{

    private int[] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
    private int color;
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

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v("COLORTEXTVIEW","Canvas on Draw");
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawPaint(paint);
        super.onDraw(canvas);
    }

    public void party(int max, int delay, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        Colorize colorize = new Colorize();
        colorize.start();

    }

    private void update(){
        Log.v("COLORTEXTVIEW","In update");
        post(new Runnable() {
            @Override
            public void run() {
                Log.v("COLORTEXTVIEW","in run?");
                invalidate();
            }
        });
    }

    private class Colorize extends Thread{

        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                Log.v("COLORTEXTVIEW","In for loop");
                color = colors[i%4];
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                update();

            }
        }


    }
}
