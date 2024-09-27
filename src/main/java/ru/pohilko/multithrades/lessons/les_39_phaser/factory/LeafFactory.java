package ru.pohilko.multithrades.lessons.les_39_phaser.factory;

import ru.pohilko.multithrades.lessons.les_39_phaser.tasks.LeafTask;

import java.util.List;
import java.util.stream.IntStream;

public class LeafFactory extends AbstractFactory<LeafTask> {

    private final int timePerformSeconds;
    public LeafFactory(int timePerformSecond) {
        super();
        this.timePerformSeconds = timePerformSecond;
    }

    @Override
    public List<LeafTask> createTasks(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> new LeafTask(++numbers, timePerformSeconds))
            .toList();
    }
}
