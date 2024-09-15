package ru.pohilko.multithrades.lessons.test;

import java.util.concurrent.TimeUnit;

public class LauncherTest {

    public static void main(String[] args) {
        ReadTaskTest task = new ReadTaskTest(10);
        ReadTaskTest task2 = new ReadTaskTest(1000);
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
        System.out.println("Main sleep");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("~!!!!!!!!!!!!!!!!!");;
        }
        System.out.println("Main wake up");
        thread1.interrupt();
        thread2.interrupt();
        System.out.println("Times:" + task.getTimes().get());
        System.out.println("Times2:" + task2.getTimes().get());

    }
}
