package com.work.tdd.euler.util.future;

public abstract class BatchCallable<T> {
    private int start;
    private int end;

    public BatchCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    protected abstract T batchCall(int start, int end, int batchNumber);
}
