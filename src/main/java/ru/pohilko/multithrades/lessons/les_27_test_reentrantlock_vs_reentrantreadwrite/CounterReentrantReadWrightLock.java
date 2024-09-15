package ru.pohilko.multithrades.lessons.les_27_test_reentrantlock_vs_reentrantreadwrite;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReentrantReadWrightLock extends AbstractCounter {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Lock readLock = lock.readLock();
    private final Lock wrightLock = lock.writeLock();

    @Override
    protected Lock getReadLock() {
        return this.readLock;
    }

    @Override
    protected Lock getWrightLock() {
        return this.wrightLock;
    }
}
