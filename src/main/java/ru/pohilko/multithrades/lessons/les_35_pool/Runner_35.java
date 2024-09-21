package ru.pohilko.multithrades.lessons.les_35_pool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Runner_35 {

    private static final String TEMPLATE_THREAD_NAME = "thread_%d";

    private static int COUNT_CONNECTIONS = 1;
    private static int COUNT_SESSIONS = 1;

    public static void main(String[] args) {
        DataBase dataBase = new DataBase(1);
        List<Session> lisT = createSessions(COUNT_SESSIONS, dataBase);
        ConnectionPool<Session> connectionPool = new ConnectionPool(COUNT_CONNECTIONS, lisT);
        OperationWithDb task = new OperationWithDb(connectionPool);
        Thread[] threads = makeThreads(task, 10);
        startThreads(threads);
        joinThreads(threads);
        System.out.println("Main finished work");
    }

    private static List<Session> createSessions(int countSessions, DataBase dataBase) {
        return IntStream.range(0, countSessions)
            .mapToObj(e -> new Session(dataBase))
            .toList();
    }


    public static Thread[] makeThreads(Runnable task, int countThread) {
        AtomicInteger count = new AtomicInteger(1);
        return IntStream.range(0, countThread)
            .mapToObj(n -> new Thread(task, TEMPLATE_THREAD_NAME.formatted(count.getAndIncrement())))
            .toArray(Thread[]::new);
    }

    private static void startThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }

    private static void joinThreads(Thread... threads) {
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
