package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import java.util.concurrent.CountDownLatch;

public abstract class Handler<T> implements Runnable{

    private final DataBase<T> dataBase;
    private final CountDownLatch countDownLatch;

    public Handler(DataBase<T> dataBase, CountDownLatch countDownLatch) {
        this.dataBase = dataBase;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        handle();
    }

    public void handle() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dataBase.getResult();
    }
}
