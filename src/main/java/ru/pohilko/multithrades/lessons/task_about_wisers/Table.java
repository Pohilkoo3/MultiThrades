package ru.pohilko.multithrades.lessons.task_about_wisers;

import java.util.concurrent.Semaphore;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Table {

    private final Semaphore semaphore;
    private final String TEMPLATE_START_EAT = "Wisdom %s start eat\n";
    private final String TEMPLATE_END_EAT = "Wisdom %s finish eat\n";
    private final String TEMPLATE_TRY_SEAT = "Wisdom %s try seat\n";
    private final String TEMPLATE_LEFT = "Wisdom %s left\n";

    public Table(int permit) {
        this.semaphore = new Semaphore(permit);
    }


    public void eat() {
        String name = currentThread().getName();
        out.printf(TEMPLATE_TRY_SEAT, name);
        try {
            this.semaphore.acquire();
            try {
                out.printf(TEMPLATE_START_EAT, name);
                SECONDS.sleep(3);
                out.printf(TEMPLATE_END_EAT, name);
            } finally {
                this.semaphore.release();
                out.printf(TEMPLATE_LEFT, name);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
