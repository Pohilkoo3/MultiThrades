package ru.pohilko.multithrades.buffer;


import ru.pohilko.multithrades.broker.Message;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private int capacity;
    private Stack<Message> messages;
    private final Lock lock;
    private final Condition conditionNotFull;
    private final Condition conditionNotEmpty;


    public Buffer(int capacity) {
        this.capacity = capacity;
        this.messages = new Stack<>();
        this.lock = new ReentrantLock();
        this.conditionNotEmpty = lock.newCondition();
        this.conditionNotFull = lock.newCondition();
    }

    public Message getMessage() {
        Message message = null;
        lock.lock();
        try {
            while (messages.empty()) {
                try {
                    conditionNotEmpty.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
            message = messages.pop();
            print("get", message);
            conditionNotFull.signal();
        } finally {
            lock.unlock();
        }
        return message;
    }

    public void addMessage(Message message) {
        lock.lock();
        try {
            while (messages.size() >= capacity) {
                try {
                    conditionNotFull.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Message push = messages.push(message);
            print("add", push);
            conditionNotEmpty.signal();
        } finally {
            lock.unlock();
        }
}

    private void print(String template, Message message) {
        System.out.printf("Thread %s %s. Message: %s\n", Thread.currentThread().getName(), template, message);
    }
}
