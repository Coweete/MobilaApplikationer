package com.mah.ag0071.rockpapersissor;

import java.util.Random;

/**
 * Created by User on 2017-09-08.
 */

public class RPSPlayer {
    private Random random = new Random();

    public int nextChoice(){
        int choice = random.nextInt(3);
        if(choice == 0){
            return RPSController.ROCK;
        }else if(choice == 1){
            return RPSController.PAPER;
        }else{
            return RPSController.SCISSORS;
        }
    }
}
