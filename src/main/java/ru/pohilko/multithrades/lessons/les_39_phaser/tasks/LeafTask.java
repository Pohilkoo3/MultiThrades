package ru.pohilko.multithrades.lessons.les_39_phaser.tasks;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class LeafTask extends AbstractBaseTask {

    private final int timePerforming;
    private final String TEMPLATE_NAME = "leaf_task_%d";


    public LeafTask(Integer id, int timePerforming) {
        super(id);
        this.timePerforming = timePerforming;
        this.name = TEMPLATE_NAME.formatted(this.id);
    }

    @Override
    public void perform() {
        try {
            System.out.println("thread: %s performing task %s".formatted(currentThread().getName(), name));
            SECONDS.sleep(timePerforming);
            isPerform = true;
        } catch (InterruptedException e) {
            isPerform = false;
            currentThread().interrupt();
        }
    }

    @Override
    protected boolean isPerform() {
        return isPerform;
    }
}
