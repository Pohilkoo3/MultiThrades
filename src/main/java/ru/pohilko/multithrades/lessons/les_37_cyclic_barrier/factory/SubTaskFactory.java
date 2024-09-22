package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.factory;

import ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks.SubTask;
import ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks.Task;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class SubTaskFactory extends AbstractTaskFactory<SubTask> {

    private final TaskFactory taskFactory;

    public SubTaskFactory(String prefixTask, TaskFactory taskFactory) {
        super(prefixTask);
        this.taskFactory = taskFactory;
    }

    @Override
    public List<SubTask> createTasks(Object... args) {
        int countSubtask = (int) args[0];
        int countTask = (int) args[1];
        int timePerforming = (int) args[2];
        CyclicBarrier barrier = (CyclicBarrier) args[3];
        return IntStream.range(0, countSubtask)
            .mapToObj(e -> {
                List<Task> tasks = taskFactory.createTasks(countTask, timePerforming);
                return new SubTask(++numberTask, prefixTask, tasks, barrier);
            })
            .toList();
    }
}
