package ru.pohilko.multithrades.lessons.les_39_phaser.tasks;

import java.util.List;

public class MainTask extends AbstractTopTask<SubTask> {


    public MainTask(Integer id, List<SubTask> listTask) {
        super(id, listTask);
    }

    @Override
    public void perform() {
        listTask.forEach(t -> {
            Thread thread = new Thread(t, "thread_%s".formatted( "subtask_".concat(String.valueOf(t.id))));
            thread.start();
        });

    }

}
