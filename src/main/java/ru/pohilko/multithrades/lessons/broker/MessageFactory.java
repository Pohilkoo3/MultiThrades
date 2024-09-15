package ru.pohilko.multithrades.lessons.broker;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessageFactory {

    private AtomicInteger nextMessageId = new AtomicInteger(1);
    public Message createMessage(){
        Message message = new Message();
        message.setMessage("Message #" + nextMessageId.getAndIncrement());
        return message;
    }
}
