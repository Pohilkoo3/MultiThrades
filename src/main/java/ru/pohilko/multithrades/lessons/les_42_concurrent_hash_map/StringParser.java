package ru.pohilko.multithrades.lessons.les_42_concurrent_hash_map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringParser {

    private Map<String, Integer> result;

    public StringParser() {
        this.result = new ConcurrentHashMap<>();
    }


    public void parse(String text) {
        for (int i = 0; i < text.length(); i++) {
            String charAt = String.valueOf(text.charAt(i));
            result.compute(charAt, (k, v) -> v == null ? 1 : v + 1);
        }
    }

    public void printResult() {
        result.entrySet()
            .forEach(s -> System.out.println(s.getKey().concat(" -> ").concat(String.valueOf(s.getValue()))));
    }
}
