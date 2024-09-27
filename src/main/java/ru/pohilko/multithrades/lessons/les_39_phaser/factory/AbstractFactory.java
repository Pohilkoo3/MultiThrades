package ru.pohilko.multithrades.lessons.les_39_phaser.factory;

import java.util.List;
import java.util.concurrent.Phaser;

public abstract class AbstractFactory<T> {

    protected int numbers;

    public AbstractFactory() {
        this.numbers = 0;
    }

    public abstract List<T> createTasks(int count);
}
