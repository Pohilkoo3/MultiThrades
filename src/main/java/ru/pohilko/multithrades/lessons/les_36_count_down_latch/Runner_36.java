package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import ru.pohilko.multithrades.lessons.PrintUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Runner_36 {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory();
        List<String> input = List.of("Hello", " my", " friend", ",", " Oleg", "!");
        int size = input.size();
        Resource<String> resource = new ResourceString(input, 1);

        DataBase<String> dataBase = new DataBaseString();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        Loader<String> loadTask = new LoaderString(countDownLatch, resource,dataBase);
        Handler<String> handlerTask = new HandlerString(dataBase, countDownLatch);
        Thread[] loaders = threadFactory.createThreads(loadTask, size, "loader");
        Thread[] handlers = threadFactory.createThreads(handlerTask, 1, "handler");
        startThread(loaders);
        startThread(handlers);


    }

    private static void startThread(Thread[] threads){
        Arrays.stream(threads).forEach(Thread::start);
    }
}
