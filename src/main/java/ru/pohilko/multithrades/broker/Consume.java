package ru.pohilko.multithrades.broker;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

@Service
@Data
@NoArgsConstructor
public class Consume implements Runnable {

    private Broker broker;
    private static final Integer TIME_SEPARATE_MESSAGE_SECONDS = 3;
    private Integer maxBrokersSize;


    public Consume(Broker broker, Integer maxBrokersSize) {
        this.broker = broker;
        this.maxBrokersSize = maxBrokersSize;
    }


    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                TimeUnit.SECONDS.sleep(TIME_SEPARATE_MESSAGE_SECONDS);
                Message message = broker.getMessage(this)
                    .orElseThrow(MessageConsumingException::new);
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }


    }

}
