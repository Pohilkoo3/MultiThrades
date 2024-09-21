package ru.pohilko.multithrades.lessons.les_36_count_down_latch;

import java.util.List;

public class ResourceString extends Resource<String>{
    public ResourceString(List<String> objects, int timeForLoadResource) {
        super(objects, timeForLoadResource);
    }
}
