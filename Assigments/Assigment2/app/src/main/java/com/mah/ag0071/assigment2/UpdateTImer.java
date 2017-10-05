package com.mah.ag0071.assigment2;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 2017-10-03.
 */

public class UpdateTImer {

    private Timer timer;
    private TimerTask timerTask;
    private Controller controller;

    public UpdateTImer(Controller controller){
        this.controller = controller;
    }

    public void stopTimer(){
        timer.purge();
    }

    public void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                controller.timerUpdate();
            }
        };
        timer.schedule(timerTask,10,30000);
    }

}
