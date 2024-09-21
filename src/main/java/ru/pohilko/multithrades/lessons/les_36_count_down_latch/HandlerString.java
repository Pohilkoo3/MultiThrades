package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import java.util.concurrent.CountDownLatch;

public class HandlerString extends Handler<String>{
    public HandlerString(DataBase<String> dataBase, CountDownLatch countDownLatch) {
        super(dataBase, countDownLatch);
    }
}
