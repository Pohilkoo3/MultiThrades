package ru.pohilko.multithrades.lessons.les_29_deadlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
public class Task implements Runnable{

    private final String TEMPLATE_TRY_LOCK = "Thread %s try lock name: %s";
    private final String TEMPLATE_REQUIRE_LOCK = "Thread %s require lock name: %s";
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
        log.info(String.format(TEMPLATE_TRY_LOCK, threadName, lock1Name));
        lock1.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(timeSleep);
            log.info(String.format(TEMPLATE_REQUIRE_LOCK, threadName, lock1Name));
            log.info(String.format(TEMPLATE_TRY_LOCK, threadName, lock2Name));
            lock2.lock();
            try {
                log.info(String.format(TEMPLATE_REQUIRE_LOCK, threadName, lock2Name));
            }
            finally {
                lock2.unlock();
                log.info(String.format(TEMPLATE_UNLOCK_LOCK, threadName, lock2Name));
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        finally {
            lock1.unlock();
            log.info(String.format(TEMPLATE_UNLOCK_LOCK, threadName, lock1Name));
        }
    }
}
