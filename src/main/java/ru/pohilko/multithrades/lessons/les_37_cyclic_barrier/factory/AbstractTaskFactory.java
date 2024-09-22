package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.factory;

import java.util.List;

public abstract class AbstractTaskFactory<T> {

    protected int numberTask;
    protected String prefixTask;

    public AbstractTaskFactory(String prefixTask) {
        this.numberTask = 0;
        this.prefixTask = prefixTask;
    }

    public abstract List<T> createTasks(Object... args);
}
