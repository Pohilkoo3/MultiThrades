package ru.pohilko.multithrades.lessons.les_37_cyclic_barrier;

import ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.factory.SubTaskFactory;
import ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.factory.TaskFactory;
import ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks.MainTask;
import ru.pohilko.multithrades.lessons.les_37_cyclic_barrier.tasks.SubTask;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Runner_37 {

    public static int QUANTITY_SUBTASKS = 2;
    public static int QUANTITY_TASKS = 3;
    public static int TIME_PERFORMING = 1500;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("*****************************************************"));
        TaskFactory taskFactory = new TaskFactory("task");
        SubTaskFactory subTaskFactory = new SubTaskFactory("sub_task", taskFactory);
        List<SubTask> subTasks =
            subTaskFactory.createTasks(QUANTITY_SUBTASKS, QUANTITY_TASKS, TIME_PERFORMING, cyclicBarrier);
        MainTask mainTask = new MainTask(1, "main_task", subTasks);
        mainTask.fullFill();

    }
}
