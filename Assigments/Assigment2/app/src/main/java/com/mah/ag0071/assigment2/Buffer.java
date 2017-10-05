package com.mah.ag0071.assigment2;

import java.util.LinkedList;

/**
 * Created by User on 2017-09-29.
 */

public class Buffer<T> {

    private LinkedList<T> buffer = new LinkedList<>();

    public synchronized void put(T element){
        buffer.addLast(element);
        notifyAll();
    }

    public synchronized T get() throws InterruptedException{
        while (buffer.isEmpty()){
            wait();
        }
        return buffer.removeFirst();
    }
}
