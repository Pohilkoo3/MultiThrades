package ru.pohilko.multithrades.reentrant_read_wrte_lock;

import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.currentThread;


public class Counter {

    private int count;
    private final ReadWriteLock lock;
    private final Lock readLock;
    private final Lock writeLock;
    private final String TEMPLATE_INCREMENT = "Thread %s: increment counter to: %d\n";
    private final String TEMPLATE_GET_VALUE = "Thread %s: Counter's value is: %d\n";

    public Counter() {
        this.count = 0;
        this.lock = new ReentrantReadWriteLock(true);
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    public void increment(){
        writeLock.lock();
        try {
            count++;
            printer(TEMPLATE_INCREMENT, count);
        }finally {
            writeLock.unlock();
        }
    }

    public void getValue(){
        readLock.lock();

        try {
            printer(TEMPLATE_GET_VALUE, count);
        }finally {
            readLock.unlock();
        }
    }

    public void printer(String template, int number){
        System.out.printf(template, currentThread().getName(), number);
    }

}
