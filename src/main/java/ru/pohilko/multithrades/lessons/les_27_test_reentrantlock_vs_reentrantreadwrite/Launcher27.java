package ru.pohilko.multithrades.lessons.les_27_test_reentrantlock_vs_reentrantreadwrite;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Launcher27 {

    public static void main(String[] args) {
        ThreadFactory27 threadFactory = new ThreadFactory27();

        /** ReeantrantLock**/
        AbstractCounter counter = new CounterReentrantLock();
        IncrementTask incrementTask = new IncrementTask(counter);
        Thread incrementThread1 = threadFactory.newThread(incrementTask);
        Thread incrementThread2 = threadFactory.newThread(incrementTask);
        ReadTask readTask1 = new ReadTask(counter);
        ReadTask readTask2 = new ReadTask(counter);
        ReadTask readTask3 = new ReadTask(counter);
        ReadTask readTask4 = new ReadTask(counter);
        ReadTask readTask5 = new ReadTask(counter);
        Thread reader1 = threadFactory.newThread(readTask1);
        Thread reader2 = threadFactory.newThread(readTask2);
        Thread reader3 = threadFactory.newThread(readTask3);
        Thread reader4 = threadFactory.newThread(readTask4);
        Thread reader5 = threadFactory.newThread(readTask5);





        /** ReeantranReadWrighttLock**/
        AbstractCounter counterRWL = new CounterReentrantReadWrightLock();
        IncrementTask incrementTask2 = new IncrementTask(counterRWL);
        Thread incrementThread11 = threadFactory.newThread(incrementTask2);
        Thread incrementThread22 = threadFactory.newThread(incrementTask2);
        ReadTask readTask11 = new ReadTask(counterRWL);
        ReadTask readTask22 = new ReadTask(counterRWL);
        ReadTask readTask33 = new ReadTask(counterRWL);
        ReadTask readTask44 = new ReadTask(counterRWL);
        ReadTask readTask55 = new ReadTask(counterRWL);
        Thread reader11 = threadFactory.newThread(readTask11);
        Thread reader22 = threadFactory.newThread(readTask22);
        Thread reader33 = threadFactory.newThread(readTask33);
        Thread reader44 = threadFactory.newThread(readTask44);
        Thread reader55 = threadFactory.newThread(readTask55);

        startThreads(incrementThread1, incrementThread2, reader1, reader2, reader3, reader4, reader5);
        startThreads(incrementThread11, incrementThread22, reader11, reader22, reader33, reader44, reader55);

        System.out.printf("Main sleep\n");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("Main wake up\n");
        interruptThreads(incrementThread1, incrementThread2, reader1, reader2, reader3, reader4, reader5);
        interruptThreads(incrementThread11, incrementThread22, reader11, reader22, reader33, reader44, reader55);

        System.out.println("Total usual lock times: " + getSum(readTask1, readTask2, readTask3, readTask4, readTask5));
        System.out.println("Total read_wright_lock times: " + getSum(readTask11, readTask22, readTask33, readTask44, readTask55));


    }

    private static void startThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }

    private static void interruptThreads(Thread... threads) {
        Arrays.stream(threads).forEach(Thread::interrupt);
    }

    private static long getSum(ReadTask... tasks) {
        long sum = Arrays.stream(tasks).mapToLong(ReadTask::getTimes).sum();
        return sum;
    }
}
