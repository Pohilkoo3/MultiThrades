package ru.pohilko.multithrades.lessons.les_39_phaser.tasks;

import java.util.List;
import java.util.concurrent.Phaser;

public class SubTask extends AbstractTopTask<LeafTask> implements Runnable{

    private final Phaser phaser;
    public SubTask(Integer id, List<LeafTask> listTask, Phaser phaser) {
        super(id, listTask);
        this.phaser = phaser;
    }

    @Override
    public void perform() {
            for (int i = 0; i < listTask.size(); i++) {
                LeafTask leafTask = listTask.get(i);
                leafTask.perform();
               if (i+1 == listTask.size()){
                   System.out.println("вызвал  снятие с регистрации: " + Thread.currentThread().getName());
                   int i1 = phaser.arriveAndDeregister();
               }else {
                   System.out.println("преодолел барьер регистрации: " + Thread.currentThread().getName());
                   int i1 = phaser.arriveAndAwaitAdvance();
               }
            }

    }

    @Override
    protected boolean isPerform() {
        return false;
    }

    @Override
    public void run() {
        perform();
    }
}
