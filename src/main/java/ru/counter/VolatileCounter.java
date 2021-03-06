package ru.counter;

import ru.ICounter;

/**
 * broken because no-atomic
 */
public class VolatileCounter implements ICounter {

    private volatile long counter;

    public VolatileCounter() {
        this(0);
    }

    public VolatileCounter(long initialVal) {
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
        return "VolatileCounter{" +
                "counter=" + get() +
                '}';
    }
}
