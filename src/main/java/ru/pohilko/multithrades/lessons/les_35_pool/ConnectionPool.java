package ru.pohilko.multithrades.lessons.les_35_pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

@Slf4j
public class ConnectionPool<T> {

    private final Semaphore semaphore;
    private List<T> listT;
    

    private final static String TEMPLATE_RELEASE_SEMAFOR = "Thread %s release aquire\n";

    public ConnectionPool(int countConnections, List<T> listT) {
        this.semaphore = new Semaphore(countConnections);
        this.listT = new ArrayList<>(listT);
    }

    public T openSession(){
        T t = null;
        try {
            this.semaphore.acquire();
            try {
                if (!CollectionUtils.isEmpty(listT)){
                    t = listT.get(0);
                    listT.remove(0);
                }else {
                    System.out.println("Sessions list is Empty");
                }
            }finally {

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return t;
    }

    public void closeSession(T t){
        listT.add(t);
        this.semaphore.release();
        System.out.printf(TEMPLATE_RELEASE_SEMAFOR, Thread.currentThread().getName());
    }

}
