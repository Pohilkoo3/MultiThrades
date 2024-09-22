package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks;

import lombok.Getter;

@Getter
public abstract class AbstractTask {

    protected int id;
    protected String prefixName;
    protected boolean isPerform;

    public AbstractTask(int id, String prefixName) {
        this.id = id;
        this.prefixName = prefixName;
        this.isPerform = false;
    }

    public abstract void fullFill();

    public abstract boolean isPerformed();
}
