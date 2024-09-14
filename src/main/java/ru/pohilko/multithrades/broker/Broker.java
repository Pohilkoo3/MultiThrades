package ru.pohilko.multithrades.broker;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

@Service
@Data
@NoArgsConstructor
@Slf4j
public class Broker {

    private Queue<Message> messages;
    private Integer maxAmountMessages;
    private static final String PRODUCE_TEMPLATE_DISPLAY_MESSAGE = "Produce %s : %s\n";
    private static final String CONSUME_TEMPLATE_DISPLAY_MESSAGE = "Consume %s : %s\n";

    public Broker(Integer maxAmountMessages) {
        this.maxAmountMessages = maxAmountMessages;
        this.messages = new ArrayDeque<>(maxAmountMessages);
    }

    public synchronized void addMessage(Message message, Produce produce) {
        while (isShouldProduce(produce)) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        messages.add(message);
        printInfo(PRODUCE_TEMPLATE_DISPLAY_MESSAGE, message.getMessage());
        notifyAll();
    }

    public synchronized Optional<Message> getMessage(Consume consume) {
        while (!isShouldConsume(consume)) {
            try {
                wait();
            } catch (InterruptedException e) {
                return Optional.empty();
            }
        }
        Message last = messages.poll();
        if (Objects.isNull(last)) {
            notifyAll();
            return Optional.empty();
        }
        printInfo(CONSUME_TEMPLATE_DISPLAY_MESSAGE, last.getMessage());
        notifyAll();
        return Optional.of(last);
    }

    private boolean isShouldConsume(Consume consume){
        return !messages.isEmpty() && messages.size() >= consume.getMaxBrokersSize();
    }

    private boolean isShouldProduce(Produce produce){
        return messages.size() >= maxAmountMessages && messages.size() >= produce.getMaxBrokersSize();
    }

    private void printInfo(String template, String arg) {
        System.out.printf(template, Thread.currentThread().getName(), arg);
    }

    public int getMessagesSize(){
        return messages.size();
    }

}

