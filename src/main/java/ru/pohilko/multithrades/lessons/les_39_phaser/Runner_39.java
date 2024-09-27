package ru.pohilko.multithrades.lessons.les_39_phaser;

import ru.pohilko.multithrades.lessons.les_39_phaser.factory.LeafFactory;
import ru.pohilko.multithrades.lessons.les_39_phaser.tasks.LeafTask;
import ru.pohilko.multithrades.lessons.les_39_phaser.tasks.MainTask;
import ru.pohilko.multithrades.lessons.les_39_phaser.tasks.SubTask;

import java.util.List;
import java.util.concurrent.Phaser;

public class Runner_39 {

    private static final int TIME_PERFORM_SECONDS = 2;

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println();
                System.out.println("Thread: " + Thread.currentThread().getName());
                System.out.printf("Current phase is: %d\n", phase);
                System.out.printf("Quantity threads is: %d\n", registeredParties);
                System.out.println();
                return super.onAdvance(phase, registeredParties);
            }
        };
        LeafFactory leafFactory = new LeafFactory(TIME_PERFORM_SECONDS);
        List<LeafTask> tasks3 = leafFactory.createTasks(3);
        List<LeafTask> tasks2 = leafFactory.createTasks(2);
        List<LeafTask> tasks1 = leafFactory.createTasks(1);
        SubTask subTask1 = new SubTask(3, tasks3, phaser );
        SubTask subTask2 = new SubTask(2, tasks2, phaser );
        SubTask subTask3 = new SubTask(1, tasks1, phaser );
        MainTask mainTask = new MainTask(1, List.of(subTask1, subTask2, subTask3));
        mainTask.perform();

    }
}
