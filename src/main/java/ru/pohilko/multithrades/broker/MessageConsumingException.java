package ru.pohilko.multithrades.broker;


public class MessageConsumingException extends RuntimeException{

    public MessageConsumingException() {
    }

    public MessageConsumingException(Exception cause) {
        super(cause);
    }

    public MessageConsumingException(String description) {
        super(description);
    }

    public MessageConsumingException(String description, Exception cause) {
        super(description, cause);
    }
}
