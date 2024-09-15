package ru.pohilko.multithrades.lessons.les_27_test_reentrantlock_vs_reentrantreadwrite;

import java.util.concurrent.TimeUnit;

public class IncrementTask implements Runnable {

    private final AbstractCounter counter;

    public IncrementTask(AbstractCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Thread.currentThread().stop();
            }
            counter.increment();
        }
    }
}
