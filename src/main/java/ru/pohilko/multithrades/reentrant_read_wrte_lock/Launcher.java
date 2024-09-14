package ru.pohilko.multithrades.reentrant_read_wrte_lock;

import ru.pohilko.multithrades.broker.ThreadFactory;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Launcher {

    private static int TIME_BETWEEN_READ_MILLISECONDS = 1000;
    private static int TIME_BETWEEN_INCREMENT_SECONDS = 2;


    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory();
        Counter counter = new Counter();
        Runnable readTask =
            () -> {
                while (true) {
                    try {
                        MILLISECONDS.sleep(TIME_BETWEEN_READ_MILLISECONDS);
                        counter.getValue();
                    } catch (InterruptedException e) {
                        currentThread().interrupt();
                    }
                }
            };
        Runnable incrementTask = () -> {
            while (true) {
                try {
                    SECONDS.sleep(TIME_BETWEEN_INCREMENT_SECONDS);
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        List<Thread> readThreads = IntStream.range(0, 5)
            .boxed()
            .map(e -> threadFactory.newThread(readTask))
            .toList();
        List<Thread> incrementThreads = IntStream.range(0, 1)
            .boxed()
            .map(e -> threadFactory.newThread(incrementTask))
            .toList();

        runThreads(readThreads);
        runThreads(incrementThreads);

    }


    private static List<Runnable> createTasks(IntConsumer operation, int countTasks)
    {
        return IntStream.range(0, countTasks)
            .boxed()
            .map(i -> createTask(operation))
            .toList();
    }

    private static Runnable createTask(IntConsumer operation) {
        return () -> IntStream.iterate(0, i -> i + 1).forEach(operation);
    }

    private static void runThreads(List<Thread> threads) {
        threads.forEach(Thread::start);
    }
}
