package ru.pohilko.multithrades.lessons.les_27_test_reentrantlock_vs_reentrantreadwrite;

import lombok.Getter;

@Getter
public class ReadTask implements Runnable {

    private final AbstractCounter counter;
    private long times;

    public ReadTask(AbstractCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            counter.getValue();
            times++;
        }
    }
}
