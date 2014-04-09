package ru.counter;

import ru.ICounter;

public class SynchronizedCounter implements ICounter {

    private long counter;

    public SynchronizedCounter() {
        this(0);
    }

    public SynchronizedCounter(long initialVal) {
        this.counter = initialVal;
    }

    @Override
    public synchronized void set(long value) {
        this.counter = value;
    }

    @Override
    public synchronized void dec() {
        --counter;
    }

    @Override
    public synchronized void decUntilZero() {
        if (counter == 0) return;
        --counter;
    }

    @Override
    public synchronized long get() {
        return counter;
    }

    @Override
    public long getMissedCount() {
        return 0;
    }

    @Override
    public String toString() {
        return "SynchronizedCounter{" +
                "counter=" + counter +
                '}';
    }
}
