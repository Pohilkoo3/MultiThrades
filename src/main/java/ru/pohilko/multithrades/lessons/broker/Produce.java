package ru.pohilko.multithrades.lessons.broker;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

@Service
@Data
@NoArgsConstructor
public class Produce implements Runnable {

    private static final Integer TIME_SEPARATE_MESSAGE_SECONDS = 1;

    private Broker broker;
    private MessageFactory messageFactory;
    private Integer maxBrokersSize;

    public Produce(Broker broker, MessageFactory messageFactory, Integer maxBrokersSize) {
        this.broker = broker;
        this.messageFactory = messageFactory;
        this.maxBrokersSize = maxBrokersSize;
    }


    @Override
    public void run() {
        while (true) {
            addMessage();
        }
    }

    private synchronized void addMessage() {
        try {
            while (!currentThread().isInterrupted()) {
                TimeUnit.SECONDS.sleep(TIME_SEPARATE_MESSAGE_SECONDS);
                Message message = messageFactory.createMessage();
                broker.addMessage(message, this);
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
