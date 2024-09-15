package ru.pohilko.multithrades.lessons.les_27_test_reentrantlock_vs_reentrantreadwrite;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterReentrantLock extends AbstractCounter {

    private final Lock lock = new ReentrantLock(true);


    @Override
    protected Lock getReadLock() {
        return lock;
    }

    @Override
    protected Lock getWrightLock() {
        return lock;
    }
}
