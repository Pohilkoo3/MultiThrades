package ru.pohilko.multithrades.lessons.les_27_test_reentrantlock_vs_reentrantreadwrite;


import java.util.concurrent.ThreadFactory;

public class ThreadFactory27 implements ThreadFactory {

    private final Thread.UncaughtExceptionHandler handler = (thread, ex) -> System.out.printf("Hello. In thread %s was mistake => %s \n", thread.getName(), ex.getMessage());
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
//        thread.setUncaughtExceptionHandler(handler);
        return thread;
    }
}
