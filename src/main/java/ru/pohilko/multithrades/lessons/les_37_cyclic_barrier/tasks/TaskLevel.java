package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks;

import java.util.List;

public abstract class TaskLevel<T extends AbstractTask> extends AbstractTask {

    List<T> tasks;
    protected static String TEMPLATE_BEGIN_PERFORMING = "thread %s began perform task %s_%d...\n";
    protected static String TEMPLATE_FINISH_PERFORMING = "thread %s finish perform task %s_%d...\n";

    public TaskLevel(int id, String name, List<T> tasks) {
        super(id, name);
        this.tasks = tasks;
    }


    @Override
    public abstract void fullFill();

    @Override
    public boolean isPerformed() {
        return this.tasks.stream().filter(task -> !task.isPerformed()).toList().size() == 0;
    }
}
