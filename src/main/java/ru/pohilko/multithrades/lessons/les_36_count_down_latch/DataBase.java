package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import ru.pohilko.multithrades.lessons.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class DataBase<T> {

    private volatile List<T> result;
    private final ReadWriteLock lock;
    private final Lock readLock;
    private final Lock wrightLock;

    private final String TEMPLATE_THREAD_WORKING = "thread: %s working...\n";
    private final String TEMPLATE_THREAD_FINISH = "thread: %s finish\n";

    public DataBase() {
        this.result = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.wrightLock = this.lock.writeLock();
    }

    public void getResult() {
        readLock.lock();
        try {
            PrintUtils.printTemplate(TEMPLATE_THREAD_WORKING, Thread.currentThread().getName());
            result.forEach(this::printT);
            PrintUtils.printTemplate(TEMPLATE_THREAD_FINISH, Thread.currentThread().getName());
        } finally {
            readLock.unlock();
        }
    }

    private void printT(T t) {
        System.out.printf(t.toString());
    }

    public void add(T t) {
        wrightLock.lock();
        try {
            result.add(t);
        } finally {
            wrightLock.unlock();
        }
    }
}
