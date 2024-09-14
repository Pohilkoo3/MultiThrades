package ru.pohilko.multithrades.broker;

import org.springframework.stereotype.Service;

@Service
public class ThreadFactory implements java.util.concurrent.ThreadFactory {
        private final Thread.UncaughtExceptionHandler handler = (thread, ex) -> System.out.printf("Hello. In thread %s was mistake => %s \n", thread.getName(), ex.getMessage());

    @Override
    public  Thread newThread(Runnable r) {
        Thread thread =  new Thread(r);
        thread.setUncaughtExceptionHandler(handler);
        return thread;
    }
}
