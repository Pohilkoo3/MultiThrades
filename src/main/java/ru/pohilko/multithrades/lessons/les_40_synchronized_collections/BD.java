package ru.pohilko.multithrades.lessons.les_40_synchronized_collections;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BD<T> {
    protected Vector<T> listObject;
    public BD() {
        this.listObject = new Vector<>();
    }

    public synchronized boolean addObject(T t) {
            if (!listObject.contains(t)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                listObject.add(t);
                return true;
            }
            System.out.printf("Такой элемент уже существует в очереди! Это говорит поток: %s\n", Thread.currentThread().getName());
            return false;

    }

    public void printList() {
        listObject.forEach(System.out::println);
    }

    public void printClassName() {
        System.out.println(this.getClass().getName());
    }

    public int getSizeCollection() {
        return listObject.size();
    }
}
