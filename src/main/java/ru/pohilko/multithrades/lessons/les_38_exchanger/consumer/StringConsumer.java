package ru.pohilko.multithrades.lessons.les_38_exchanger.consumer;

import java.util.Queue;
import java.util.concurrent.Exchanger;

public class StringConsumer extends AbstractConsumer<String> {
    public StringConsumer(Exchanger<Queue<String>> exchanger,
        int SLEEP_BETWEEN_READ_NEW_STRING, int limitQueueSize)
    {
        super(exchanger, SLEEP_BETWEEN_READ_NEW_STRING, limitQueueSize);
    }
}
