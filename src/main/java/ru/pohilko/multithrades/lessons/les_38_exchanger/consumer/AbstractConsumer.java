package ru.pohilko.multithrades.lessons.les_38_exchanger.consumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class AbstractConsumer<T> implements Runnable{

    protected Queue<T> queue;
    protected final Exchanger<Queue<T>> exchanger;

    private final int SLEEP_BETWEEN_READ_NEW_STRING;
    private final int LIMIT_QUEUE_SIZE;
    private final String TEMPLATE_ERROR_DUE_EXCHANGE = "thread %s throw error due exchange\n";
    private final String TEMPLATE_TAKE_EXCHANGE = "thread %s take new queue\n";
    private final String TEMPLATE_READ_ELEMENT = "thread %s read_element: %s\n";

    public AbstractConsumer(Exchanger<Queue<T>> exchanger, int SLEEP_BETWEEN_READ_NEW_STRING, int limitQueueSize) {
        LIMIT_QUEUE_SIZE = limitQueueSize;
        this.queue = new ArrayDeque<>(LIMIT_QUEUE_SIZE);
        this.exchanger = exchanger;
        this.SLEEP_BETWEEN_READ_NEW_STRING = SLEEP_BETWEEN_READ_NEW_STRING;
    }




    @Override
    public void run() {
        while (!currentThread().isInterrupted()){
            if (!queue.isEmpty()){
                try {
                    TimeUnit.SECONDS.sleep(SLEEP_BETWEEN_READ_NEW_STRING);
                    System.out.println(TEMPLATE_READ_ELEMENT.formatted(currentThread().getName(), queue.poll()));
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                }
            }else {
                try {
                    System.out.println(TEMPLATE_TAKE_EXCHANGE.formatted(currentThread().getName()));
                    queue = exchanger.exchange(queue);
                } catch (InterruptedException e) {
                    System.out.println(TEMPLATE_ERROR_DUE_EXCHANGE.formatted(currentThread().getName()));
                    currentThread().interrupt();
                }
            }
        }
    }
}
