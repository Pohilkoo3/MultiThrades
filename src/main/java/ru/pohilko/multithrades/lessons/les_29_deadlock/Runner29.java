package ru.pohilko.multithrades.lessons.les_29_deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner29 {


    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Task
            task1 = new Task("Lock1", "Lock2", lock1, lock2, 500);
        Task task2 = new Task("Lock2", "Lock1", lock2, lock1, 200);
        Thread thread1 = new Thread(task1, "Thread_1");
        Thread thread2 = new Thread(task2, "Thread_2");
        thread1.start();
        thread2.start();
    }
}