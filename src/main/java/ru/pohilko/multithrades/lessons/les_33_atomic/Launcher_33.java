package ru.pohilko.multithrades.lessons.les_33_atomic;

import java.util.Arrays;

import static java.util.stream.IntStream.range;

public class Launcher_33 {

    private final static int COUNT_NUMBERS_FOR_INCREMENT = 2000000;
    private final static String TEMPLATE_NAME_OF_THREADS = "thread_%s_%d";

    public static void main(String[] args) {
        EvenNumberGenerator generator = new EvenNumberGenerator(0, 1);
        Runnable taskIncrement = () -> range(0, COUNT_NUMBERS_FOR_INCREMENT).forEach(e -> generator.getAndIncrement());

        Runnable taskGet = () -> range(0, COUNT_NUMBERS_FOR_INCREMENT).forEach(e -> generator.getValue());
        Thread[] increments = createThreads(taskIncrement, 2, "increment");
        Thread[] gets = createThreads(taskGet, 1, "get");
        startThreads(increments);
        startThreads(gets);
        joinThreads(increments);
        joinThreads(gets);
        System.out.printf(String.format("Value is: %d", generator.getValue()));

    }

    private static Thread[] createThreads(Runnable task, int countThreads, String nameOperation) {
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            String name = String.format(TEMPLATE_NAME_OF_THREADS, nameOperation, i + 1);
            threads[i] = new Thread(task, name);
        }
        return threads;
    }

    public static void startThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }

    public static void joinThreads(Thread... threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
