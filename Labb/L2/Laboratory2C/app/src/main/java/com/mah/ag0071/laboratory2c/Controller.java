package com.mah.ag0071.laboratory2c;

/**
 * Created by User on 2017-09-07.
 */

public class Controller {

    private ViewerFragment viewerFragment;
    private int counter = 0;

    public Controller(){

    }

    public Controller(ViewerFragment viewerFragment) {
        this.viewerFragment = viewerFragment;
    }

    public void pushedButton(){
        counter++;
        viewerFragment.setTvOutPut(String.valueOf(counter));
    }

    public void resetCounter(){
        counter = 0;
        viewerFragment.setTvOutPut(String.valueOf(counter));
    }
}
