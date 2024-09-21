package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ThreadFactory {

   public int countThreads;
   private static final String TEMPLATE_NAME_THREAD = "%s_%d";

   public Thread[] createThreads  (Runnable task, int countThread, String prefixNameThreads){
      return IntStream.range(0, countThread)
           .mapToObj(e ->
               new Thread(task, TEMPLATE_NAME_THREAD.formatted(prefixNameThreads, countThreads++))
           )
           .toArray(Thread[]::new);
   }
}
