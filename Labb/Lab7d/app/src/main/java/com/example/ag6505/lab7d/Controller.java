package com.example.ag6505.lab7d;

/**
 * Created by User on 2017-09-25.
 */

public class Controller {

    private MainActivity mainActivity;
    private ColorTextview colorTextView;

    public Controller(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }



    public void onPress() {
        colorTextView.party(3,400,mainActivity);
    }

    public void setColorTextView(ColorTextview colorTextView) {
        this.colorTextView = colorTextView;
    }
}
