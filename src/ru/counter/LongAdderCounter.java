package ru.counter;

import ru.ICounter;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderCounter implements ICounter {

    private LongAdder counter;

    public LongAdderCounter() {
        this(0);
    }

    public LongAdderCounter(long initialVal) {
        this.counter = new LongAdder();
        counter.add(initialVal);
    }

    @Override
    public void set(long value) {
        this.counter = new LongAdder();
        counter.add(value);
    }

    @Override
    public void dec() {
        counter.decrement();
    }

    @Override
    public void decUntilZero() {
        // todo not implement yet, just decrement
        counter.decrement();
    }

    @Override
    public long get() {
        return counter.sum();
    }

    @Override
    public long getMissedCount() {
        return 0;
    }
}
