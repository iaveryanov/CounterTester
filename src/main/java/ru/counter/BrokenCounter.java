package ru.counter;

import ru.ICounter;

/**
 * broken because no-atomic, no-visible
 */
public class BrokenCounter implements ICounter {

    private long counter;

    public BrokenCounter() {
        this(0);
    }

    public BrokenCounter(long initialVal) {
        this.counter = initialVal;
    }

    @Override
    public void set(long value) {
        this.counter = value;
    }

    @Override
    public void dec() {
        --counter;
    }

    @Override
    public void decUntilZero() {
        if (counter == 0) return;
        --counter;
    }

    @Override
    public long get() {
        return counter;
    }

    @Override
    public long getMissedCount() {
        return 0;
    }

    @Override
    public String toString() {
        return "BrokenCounter{" +
                "counter=" + get() +
                '}';
    }
}
