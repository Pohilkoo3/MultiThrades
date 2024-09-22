package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.factory;

import ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks.Task;

import java.util.List;
import java.util.stream.IntStream;

public class TaskFactory extends AbstractTaskFactory<Task> {

    public TaskFactory(String prefixTask) {
        super(prefixTask);
    }

    @Override
    public List<Task> createTasks(Object... args) {
        int count = (int) args[0];
        int timePerforming = (int) args[1];
        return IntStream.range(0, count)
            .mapToObj(e -> new Task(++numberTask, prefixTask, timePerforming))
            .toList();
    }
}
