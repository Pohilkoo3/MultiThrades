package com.example.trainigapi.reintrant_lock;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Counter {
    private int count;
    private Object object = new Object();
    private final Lock lock = new ReentrantLock();


    public void lock(){
        lock.lock();
        printer("%s: Thread lock\n");
    }

    public void unLock(){
        printer("%s: Thread unlock\n");
        lock.unlock();
    }

    public void incrementWithLock(){
        count++;
        printer("%s: Thread increment\n");
    }

    public void decrementWithLock(){
        count--;
        printer("%s: Thread decrement\n");

    }

    private void printer(String message){
        System.out.printf(message, Thread.currentThread().getName());
    }

    public int incrementSynchronizedByObject(){
       synchronized (object){
            count++;
            return count;
        }
    }

    public int incrementSynchron(){
            count++;
       return count;
    }


    public int increment(){
        lock.lock();
        try {
            count++;
            return count;
        }finally {
            lock.unlock();
        }
    }



}
