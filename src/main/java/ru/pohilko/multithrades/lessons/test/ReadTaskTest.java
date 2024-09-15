package ru.pohilko.multithrades.lessons.test;

import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class ReadTaskTest implements Runnable {


    private AtomicInteger times = new AtomicInteger(0);
    private int PERIOD_BETWEEN_OPERATION;

    public ReadTaskTest(int PERIOD_BETWEEN_OPERATION) {
        this.PERIOD_BETWEEN_OPERATION = PERIOD_BETWEEN_OPERATION;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.MILLISECONDS.sleep(PERIOD_BETWEEN_OPERATION);
            } catch (InterruptedException e) {
                Thread.currentThread().stop();
            }
            times.incrementAndGet();
        }
    }
}
