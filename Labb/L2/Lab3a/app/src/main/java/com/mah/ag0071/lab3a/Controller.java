package com.mah.ag0071.lab3a;

import android.graphics.Color;

/**
 * Created by User on 2017-09-13.
 */

public class Controller {

    private InputFragment inputFragment;
    private ViewerFragment viewerFragment;
    private int activeColor;
    private int[] colors = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN};

    public Controller(InputFragment inputFragment,ViewerFragment viewerFragment){
        this.viewerFragment = viewerFragment;
        this.inputFragment = inputFragment;
    }


    public void btnText(String color,int index) {
        activeColor = colors[index];
        inputFragment.setBtnText(color);
    }

    public void setColor() {
      viewerFragment.setColor(activeColor);
    }
}
