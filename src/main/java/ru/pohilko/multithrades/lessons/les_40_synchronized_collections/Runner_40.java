package ru.pohilko.multithrades.lessons.les_40_synchronized_collections;

import java.util.stream.IntStream;

public class Runner_40 {

    private static final int countElements = 1000;

    public static void main(String[] args) {

        BDInteger bd = new BDInteger();
        Runnable addObjectTask = () -> {
           bd.addObject(4);
        };
        Thread thread1 = new Thread(addObjectTask, "thread_1");
        Thread thread2 = new Thread(addObjectTask, "thread_2");

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            System.out.println("Upps!!!!!!!!!!!!!!!!!!!!!");
        }

        bd.printList();
        System.out.printf("\nSize list equals %d elements.\n", bd.getSizeCollection());

    }
}
