package ru.pohilko.multithrades;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.pohilko.multithrades.broker.Message;
import ru.pohilko.multithrades.broker.MessageFactory;
import ru.pohilko.multithrades.broker.ThreadFactory;
import ru.pohilko.multithrades.buffer.Buffer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

//@Service
@RequiredArgsConstructor
@Slf4j
public class Launcher implements CommandLineRunner {

    private final ThreadFactory threadFactory;
    private final static int MAX_COUNT_PRODUCE = Integer.MAX_VALUE;
    private final static int MAX_COUNT_CONSUME = Integer.MAX_VALUE;
    private final static int SECONDS_BETWEEN_PRODUCE = 2;
    private final static int SECONDS_BETWEEN_CONSUME = 1;
    private final MessageFactory messageFactory;

    @Override
    public void run(String... args) throws InterruptedException {
        Buffer buffer = new Buffer(3);
        Runnable taskProduce = () -> {
            IntStream.range(0, MAX_COUNT_PRODUCE).boxed()
                .forEach(e -> {
                    Message message = messageFactory.createMessage();
                    sleepThread(TimeUnit.SECONDS, SECONDS_BETWEEN_PRODUCE);
                    buffer.addMessage(message);
                });
        };
        Runnable taskConsume = () -> {
            IntStream.range(0, MAX_COUNT_CONSUME).boxed()
                .forEach(e -> {
                    sleepThread(TimeUnit.SECONDS, SECONDS_BETWEEN_CONSUME);
                    buffer.getMessage();
                });
        };
        Thread producer = threadFactory.newThread(taskProduce);
        producer.setName("producer 1");
        Thread producer2 = threadFactory.newThread(taskProduce);
        producer2.setName("producer 2");
        Thread producer3 = threadFactory.newThread(taskProduce);
        producer3.setName("producer 3");
        Thread consumer = threadFactory.newThread(taskConsume);
        consumer.setName("consumer");
        Thread consumer2 = threadFactory.newThread(taskConsume);
        consumer2.setName("consumer2");
        Thread consumer3 = threadFactory.newThread(taskConsume);
        consumer3.setName("consumer3");

        startThreads(producer,producer2, producer3, consumer, consumer2, consumer3);
    }

    private void sleepThread(TimeUnit timeUnit, int countTimes) {
        try {
            timeUnit.sleep(countTimes);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    private void startThreads(Thread... treads) {
        Arrays.stream(treads).forEach(Thread::start);
    }

    private void joinThread(Thread... threads) {
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Произошла ошибка при join");
            }
        });
    }


}