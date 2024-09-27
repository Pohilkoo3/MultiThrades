package ru.pohilko.multithrades.lessons.les_38_exchanger;

import ru.pohilko.multithrades.lessons.les_38_exchanger.consumer.AbstractConsumer;
import ru.pohilko.multithrades.lessons.les_38_exchanger.consumer.StringConsumer;
import ru.pohilko.multithrades.lessons.les_38_exchanger.producer.AbstractProducer;
import ru.pohilko.multithrades.lessons.les_38_exchanger.producer.StringProducer;

import java.util.Queue;
import java.util.concurrent.Exchanger;

public class Runner38 {

    private final static int QuEUE_SIZE = 3;

    public static void main(String[] args) {
        Exchanger<Queue<String>> exchanger = new Exchanger<>();
        AbstractProducer<String> producer = new StringProducer(QuEUE_SIZE, 1, exchanger);
        AbstractConsumer<String> consumer = new StringConsumer(exchanger, 2, QuEUE_SIZE);
        Thread threadProducer = new Thread(producer, "thread_producer");
        Thread threadConsumer = new Thread(consumer, "thread_consumer");

        threadProducer.start();
        threadConsumer.start();
    }
}
