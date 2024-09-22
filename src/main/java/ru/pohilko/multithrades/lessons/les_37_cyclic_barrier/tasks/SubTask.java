package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks;

import lombok.Setter;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.currentThread;

@Setter
public class SubTask extends TaskLevel<Task> implements Runnable {
    private final CyclicBarrier cyclicBarrier;

    public SubTask(int id, String name, List<Task> tasks, CyclicBarrier cyclicBarrier) {
        super(id, name, tasks);
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void fullFill() {
        System.out.printf(TEMPLATE_BEGIN_PERFORMING, currentThread().getName(), prefixName, id);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            try {
                this.cyclicBarrier.await();
                task.fullFill();
            } catch (InterruptedException | BrokenBarrierException e) {
                currentThread().interrupt();
            }
        }
        isPerform = isPerformed();
        System.out.printf(TEMPLATE_FINISH_PERFORMING, currentThread().getName(), prefixName, id);
    }


    @Override
    public void run() {
        this.fullFill();
    }
}
