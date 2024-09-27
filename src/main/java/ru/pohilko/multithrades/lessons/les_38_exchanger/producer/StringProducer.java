package ru.pohilko.multithrades.lessons.les_38_exchanger.producer;

import java.util.Queue;
import java.util.concurrent.Exchanger;

public class StringProducer extends AbstractProducer<String> {

    private final String TEMPLATE_STRING = "string_%d";

    public StringProducer(int LIMIT_QUEUE_SIZE, int sleepBetweenAddNewString, Exchanger<Queue<String>> exchanger) {
        super(LIMIT_QUEUE_SIZE, sleepBetweenAddNewString, exchanger);
    }

    @Override
    public String createObject() {
        return TEMPLATE_STRING.formatted(++this.countObjects);
    }
}
