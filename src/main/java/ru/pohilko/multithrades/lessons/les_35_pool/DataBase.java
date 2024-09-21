package ru.pohilko.multithrades.lessons.les_35_pool;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DataBase {

    private final String TEMPLATE_MAKE_OPERATION = "Thread: %s connect to DB\n";
    private final int INTERRUPT_THREAD_SECONDS;

    public DataBase(int interruptThread) {
        this.INTERRUPT_THREAD_SECONDS = interruptThread;
    }

    public void makeSomeOperation() {
        String name = currentThread().getName();
        try {
            out.printf(TEMPLATE_MAKE_OPERATION, name);
            SECONDS.sleep(INTERRUPT_THREAD_SECONDS);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
