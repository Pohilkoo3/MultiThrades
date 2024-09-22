package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Task extends AbstractTask {

    private int timePerformingMilliseconds;
    private static String TEMPLATE_PERFORMING = "thread %s performing task %s_%d...\n";

    public Task(int id, String name, int timePerformingMilliseconds) {
        super(id, name);
        this.timePerformingMilliseconds = timePerformingMilliseconds;
    }


    @Override
    public void fullFill() {
        out.printf(TEMPLATE_PERFORMING, Thread.currentThread().getName(), prefixName, id);
        try {
            MILLISECONDS.sleep(timePerformingMilliseconds);
            isPerform = true;
        } catch (InterruptedException e) {
            isPerform = false;
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean isPerformed() {
        return isPerform;
    }
}
