package ru.pohilko.multithrades.lessons.supplier;

import java.util.function.Supplier;

public class RunnerSupplier {

    public static void main(String[] args) {
        Person o  = (Person) createObject(() -> new Person());
        System.out.println(o.getName());

    }

    private static Object createObject(Supplier<Object> supplier){
        return supplier.get();
    }
}
