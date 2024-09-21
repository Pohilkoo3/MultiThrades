package ru.pohilko.multithrades.lessons.task_about_wisers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Runner_34 {


    public static void main(String[] args) {
        Table table = new Table(1);
        Runnable eat = table::eat;
        Thread[] wiseMans = createWiseMans(eat, 6);
        startThreads(wiseMans);
        joinThreads(wiseMans);
        System.out.println("All wise man ate");
    }

    private static Thread[] createWiseMans(Runnable task, int count){
        AtomicInteger number = new AtomicInteger(1);
        return IntStream.range(0, count)
            .boxed()
            .map(thread -> {
           return new Thread(task, String.format("wisdom_%d", number.getAndIncrement()));
        })
            .toArray(Thread[]::new);
    }

    private static void startThreads(Thread... threads){
        Arrays.stream(threads).forEach(Thread::start);
    }

    private static void joinThreads(Thread... threads){
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
