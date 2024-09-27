package ru.pohilko.multithrades.lessons.les_39_phaser.tasks;

import java.util.List;

public abstract class AbstractTopTask<T extends AbstractBaseTask> extends AbstractBaseTask {

    protected List<T> listTask;


    public AbstractTopTask(Integer id, List<T> listTask) {
        super(id);
        this.listTask = listTask;

    }
    protected boolean isPerform() {
        return listTask.stream().allMatch(t -> t.isPerform);
    }
}
