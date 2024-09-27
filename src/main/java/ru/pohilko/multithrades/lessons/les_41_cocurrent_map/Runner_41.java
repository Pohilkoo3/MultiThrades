package ru.pohilko.multithrades.lessons.les_41_cocurrent_map;

import java.util.HashMap;
import java.util.Map;

public class Runner_41 {

    public static void main(String[] args) {
        Map<Integer, String> result = new HashMap<>();
        result.put(1, "O");
        result.put(2, "1");
        for (Map.Entry<Integer, String> e:result.entrySet()) {
            Map.Entry<Integer, String> e1 = e;
            System.out.println();
        }

        System.out.println();
    }
}
