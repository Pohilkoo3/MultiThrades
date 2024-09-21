package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import ru.pohilko.multithrades.lessons.PrintUtils;

import java.util.concurrent.CountDownLatch;

public abstract class Loader<T> implements Runnable{

    private final Resource<T> resource;
    private final CountDownLatch countDownLatch;
    private final DataBase<T> dataBase;
    private final static String TEMPLATE_LOAD = "thread: %s loading...";


    public Loader(CountDownLatch countDownLatch, Resource<T> resource, DataBase<T> dataBase) {
        this.countDownLatch = countDownLatch;
        this.resource = resource;
        this.dataBase = dataBase;
    }

    public void load() {
        while (!Thread.currentThread().isInterrupted()) {
            resource.loadSource()
                .ifPresentOrElse(e -> {
                    dataBase.add(e);
                    PrintUtils.printTemplate(TEMPLATE_LOAD, Thread.currentThread().getName());
                    countDownLatch.countDown();
                }, () -> Thread.currentThread().interrupt());
        }

    }

    @Override
    public void run() {
        load();
    }
}
