package com.example.trainigapi.reintrant_lock;

import java.util.function.IntConsumer;

public interface MyInterface extends IntConsumer {

    void accept(int arg);
}
