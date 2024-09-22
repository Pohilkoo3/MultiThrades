package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks;

import java.util.List;

public class MainTask extends TaskLevel<SubTask> {

    private static final String NAME_THREAD = "thread_%s_%d";
    private static final String MAIN_TASK_FINISHED = "thread_%s main task finished\n";

    public MainTask(int id, String name, List<SubTask> tasks) {
        super(id, name, tasks);
    }

    @Override
    public void fullFill() {
        List<Thread> threads = createThreads();
        startThreads(threads);
        joinThreads(threads);
        isPerform = this.isPerformed();
        System.out.printf(MAIN_TASK_FINISHED, Thread.currentThread().getName());
    }

    private void joinThreads(List<Thread> threads) {
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    private void startThreads(List<Thread> threads) {
        threads.forEach(Thread::start);
    }

    private List<Thread> createThreads() {
        return this.tasks.stream()
            .map(task -> new Thread(task, NAME_THREAD.formatted(task.prefixName, task.id)))
            .toList();
    }
}
