package com.mah.ag0071.laboratory2b;

/**
 * Created by User on 2017-09-07.
 */

public class Instruction {

    private String whatToDo;
    private String content;

    public Instruction(String whatToDo, String content){
        this.content = content;
        this.whatToDo = whatToDo;
    }


    public String getWhatToDO() {
        return whatToDo;
    }

    public String getContent() {
        return content;
    }
}
