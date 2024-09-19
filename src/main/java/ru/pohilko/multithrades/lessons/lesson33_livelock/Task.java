package ru.pohilko.multithrades.lessons.lesson33_livelock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
public class Task implements Runnable {

    private final String TEMPLATE_TRY_LOCK = "Thread %s try lock name: %s";
    private final String TEMPLATE_REQUIRE_LOCK = "Thread %s require lock name: %s";
    private final String TEMPLATE_CODE_FULLFILL = "Thread %s FULLFILL";
    private final String TEMPLATE_UNLOCK_LOCK = "Thread %s unlock lock name: %s";

    private final String lock1Name;
    private final String lock2Name;
    private final Lock lock1;
    private final Lock lock2;
    private final int timeSleep;

    public Task(String lock1Name, String lock2Name, Lock lock1, Lock lock2, int timeSleep) {
        this.lock1Name = lock1Name;
        this.lock2Name = lock2Name;
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.timeSleep = timeSleep;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        while (!Thread.currentThread().isInterrupted()) {
            log.info(String.format(TEMPLATE_TRY_LOCK, threadName, lock1Name));
            lock1.lock();
            try {
                MILLISECONDS.sleep(timeSleep);
                log.info(String.format(TEMPLATE_REQUIRE_LOCK, threadName, lock1Name));
                log.info(String.format(TEMPLATE_TRY_LOCK, threadName, lock2Name));
                while (!lock2.tryLock()) {
                    lock1.unlock();
                    MILLISECONDS.sleep(1500);
                    lock1.lock();
                }
                log.info(String.format(TEMPLATE_CODE_FULLFILL, threadName));
                lock2.unlock();
                log.info(String.format(TEMPLATE_UNLOCK_LOCK, threadName, lock2Name));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock1.unlock();
                log.info(String.format(TEMPLATE_UNLOCK_LOCK, threadName, lock1Name));
            }
        }

    }
}
