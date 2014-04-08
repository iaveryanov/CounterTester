package ru.counter;

import ru.ICounter;

/**
 * broken because no-atomic
 */
public class VolatileCounter implements ICounter {

    private volatile long counter;

    public VolatileCounter(long initialVal) {
        this.counter = initialVal;
    }

    @Override
    public long dec() {
        return --counter;
    }

    @Override
    public long decUntilZero() {
        if (counter == 0) {
            return counter;
        }
        return --counter;
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
