package ru.counter;

import ru.ICounter;

public class ConcurrentCounter implements ICounter {

    private final nathan.concurrent.numa.ConcurrentCounter counter;

    public ConcurrentCounter() {
        this(0);
    }

    public ConcurrentCounter(long initialVal) {
        this.counter = new nathan.concurrent.numa.ConcurrentCounter(initialVal);
    }

    @Override
    public void set(long value) {
        counter.set(value);
    }

    @Override
    public void dec() {
        counter.decrement();
    }

    @Override
    public void decUntilZero() {
        //TODO not implemented yet
        counter.decrement();
    }

    @Override
    public long get() {
        return counter.get();
    }

    @Override
    public long getMissedCount() {
        return 0;
    }
}
