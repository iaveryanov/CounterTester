package ru.counter;

import ru.ICounter;

public class SynchronizedCounter implements ICounter {

    private long counter;

    public SynchronizedCounter(long initialVal) {
        this.counter = initialVal;
    }

    @Override
    public synchronized long dec() {
        return --counter;
    }

    @Override
    public synchronized long decUntilZero() {
        if (counter == 0) {
            return counter;
        }
        return --counter;
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
