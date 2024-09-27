package ru.pohilko.multithrades.lessons.les_42_concurrent_hash_map;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.*;

public class Runner_42 {

    private static final int COUNT_THREADS = 2;

    public static void main(String[] args) {
        StringParser parser = new StringParser();
        String text = "AAA bbsBB A";
        List<String> texts = splitText(COUNT_THREADS, text);
        Runnable taskParse = () -> parser.parse(texts.get(0));
        Runnable taskParse2 = () -> parser.parse(texts.get(1));
        Thread thread = new Thread(taskParse, "thread_1");
        Thread thread2 = new Thread(taskParse2, "thread_2");
        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
        parser.printResult();
    }

    private static List<String> splitText(int countThreads, String text) {
        int i = text.length() / 2;
        String substring1 = text.substring(0, i);
        String substring2 = text.substring(i);
        return new ArrayList<>(List.of(substring1, substring2));
    }
}
