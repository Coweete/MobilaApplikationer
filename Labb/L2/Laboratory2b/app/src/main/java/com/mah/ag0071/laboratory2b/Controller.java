package com.mah.ag0071.laboratory2b;

import android.content.res.Resources;

import layout.InputFragment;

/**
 * Created by User on 2017-09-07.
 */

public class Controller {

    private Instruction[] instructions = new Instruction[3];
    private int index = 0;
    private InputFragment fragment;


    public Controller(InputFragment fragment){
        this.fragment = fragment;
        initializeResources();
    }

    private void initializeResources() {
        Resources res = fragment.getActivity().getResources();
        instructions[0] = new Instruction(res.getString(R.string.whatToDo),res.getString(R.string.content));
        instructions[1] = new Instruction(res.getString(R.string.whatToDo2),res.getString(R.string.content2));
        instructions[2] = new Instruction(res.getString(R.string.whatToDo3),res.getString(R.string.content3));

    }

    public void previousClick(){
        index--;
        if(index<0){
            index = instructions.length-1;
        }
        fragment.setWhatToDo(instructions[index].getWhatToDO());
        fragment.setContent(instructions[index].getContent());
    }

    public void nextClick(){
        index++;
        if(index>instructions.length-1){
            index = 0;
        }
        fragment.setWhatToDo(instructions[index].getWhatToDO());
        fragment.setContent(instructions[index].getContent());
    }
}
