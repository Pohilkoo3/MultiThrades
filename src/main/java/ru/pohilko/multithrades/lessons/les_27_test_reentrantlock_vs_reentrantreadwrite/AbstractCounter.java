package ru.pohilko.multithrades.lessons.les_27_test_reentrantlock_vs_reentrantreadwrite;

import lombok.Getter;

import java.util.concurrent.locks.Lock;

@Getter
public abstract class AbstractCounter {

    private int count;


    protected abstract Lock getReadLock();

    protected abstract Lock getWrightLock();


    public void increment() {
        Lock lock = getWrightLock();
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getValue() {
        Lock lock = getReadLock();
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
