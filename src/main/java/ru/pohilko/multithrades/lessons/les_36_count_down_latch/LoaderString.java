package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import java.util.concurrent.CountDownLatch;

public class LoaderString extends Loader<String>{
    public LoaderString(CountDownLatch countDownLatch, Resource<String> resource,
        DataBase<String> dataBase)
    {
        super(countDownLatch, resource, dataBase);
    }
}
