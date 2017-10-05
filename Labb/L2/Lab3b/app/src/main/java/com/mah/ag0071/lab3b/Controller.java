package com.mah.ag0071.lab3b;

import android.content.res.Resources;

/**
 * Created by User on 2017-09-14.
 */

public class Controller {

    private ViewerFragment viewerFragment;
    private InputFragment inputFragment;
    private MainActivity ui;
    private Instruction[] instructions = new Instruction[3];

    public Controller(InputFragment inputFragment,ViewerFragment viewerFragment,MainActivity ui){
        this.inputFragment = inputFragment;
        this.viewerFragment = viewerFragment;
        this.ui = ui;
        initRes();
    }

    private void initRes() {
        Resources res = ui.getResources();
        instructions[0] = new Instruction(res.getString(R.string.what_to_do),res.getString(R.string.content));
        instructions[1] = new Instruction(res.getString(R.string.what_to_do2),res.getString(R.string.content2));
        instructions[2] = new Instruction(res.getString(R.string.what_to_do3),res.getString(R.string.content3));

        String[] whatToDo = new String[3];
        for (int i = 0; i < instructions.length; i++) {
            whatToDo[i] = instructions[i].getWhatToDO();
        }

        viewerFragment.updateText(instructions[0].getContent());
    }


    public void setText(int position) {
        viewerFragment.updateText(instructions[position].getContent());
    }
}

