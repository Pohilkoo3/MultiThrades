package ru.pohilko.multithrades.lessons.les_33_atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class EvenNumberGenerator {

    private final AtomicInteger number;
    private final int incrementValue;

    public EvenNumberGenerator(int number, int incrementValue) {
        this.number = new AtomicInteger(number);
        this.incrementValue = incrementValue;
    }

    public int getAndIncrement(){
        return number.getAndAdd(this.incrementValue);
    }

    public int getValue(){
        return this.number.get();
    }
}
