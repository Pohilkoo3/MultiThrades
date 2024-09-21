package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public abstract class Resource<T> {

    private volatile List<T> objects;
    private final Lock lock = new ReentrantLock();
    private final int timeForLoadResource;

    public Resource(List<T> objects, int timeForLoadResource) {
        this.objects = new ArrayList<>(objects);
        this.timeForLoadResource = timeForLoadResource;
    }

    public Optional<T> loadSource(){
        lock.lock();
        try {
            if (objects.isEmpty()){
                return Optional.empty();
            }
            try {
                TimeUnit.SECONDS.sleep(timeForLoadResource);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return Optional.empty();
            }
            T t = objects.get(0);
            objects.remove(t);
            return Optional.of(t);
        }finally {
            lock.unlock();
        }
    }
}
