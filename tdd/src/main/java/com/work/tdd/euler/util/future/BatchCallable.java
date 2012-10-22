package com.work.tdd.euler.util.future;

public interface BatchCallable<T> {
    T batchCall(int start, int end, int batchNumber);
}
