package ru.pohilko.multithrades.lessons.les_42_concurrent_hash_map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;

public class RunnerTest {

    public static void main(String[] args) {

        Map<Integer, Integer> result = new HashMap<>(Map.of(1, 1, 2, 2, 3, 3));

        result.merge(1, 5, (v1, v2) -> Math.max(v1, v2));
        result.entrySet().stream()
            .forEach(entry -> System.out.println(
                String.valueOf(entry.getKey()).concat(" -> ").concat(String.valueOf(entry.getValue()))));

    }
}
