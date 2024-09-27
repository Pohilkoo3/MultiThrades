package ru.pohilko.multithrades.lessons.les_38_exchanger.producer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Exchanger;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class AbstractProducer<T> implements Runnable {

    protected Queue<T> queue;
    protected final Exchanger<Queue<T>> exchanger;
    private final int SLEEP_BETWEEN_ADD_NEW_STRING;
    protected final int LIMIT_QUEUE_SIZE;
    protected int countObjects;

    private final String TEMPLATE_ERROR_DUE_EXCHANGE = "thread %s throw error due exchange\n";
    private final String TEMPLATE_PUT_EXCHANGE = "thread %s put full queue\n";
    private final String TEMPLATE_ADD_ELEMENT = "thread %s add element %s in queue\n";

    public AbstractProducer(int LIMIT_QUEUE_SIZE, int sleepBetweenAddNewString, Exchanger<Queue<T>> exchanger) {
        this.LIMIT_QUEUE_SIZE = LIMIT_QUEUE_SIZE;
        this.queue = new ArrayDeque<>(LIMIT_QUEUE_SIZE);
        this.SLEEP_BETWEEN_ADD_NEW_STRING = sleepBetweenAddNewString;
        this.exchanger = exchanger;
        this.countObjects = 0;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            if (queue.size() < LIMIT_QUEUE_SIZE) {
                try {
                    SECONDS.sleep(SLEEP_BETWEEN_ADD_NEW_STRING);
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                }
                T object = this.createObject();
                System.out.println(TEMPLATE_ADD_ELEMENT.formatted(currentThread().getName(), object));
                queue.add(object);
            } else {
                try {
                    System.out.println(TEMPLATE_PUT_EXCHANGE.formatted(currentThread().getName()));
                    this.queue = exchanger.exchange(this.queue);
                } catch (InterruptedException e) {
                    System.out.println(TEMPLATE_ERROR_DUE_EXCHANGE.formatted(currentThread().getName()));
                }
            }
        }
    }

    public abstract T createObject();
}
