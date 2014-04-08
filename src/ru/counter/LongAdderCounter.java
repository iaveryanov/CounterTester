package ru.counter;

import ru.ICounter;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderCounter implements ICounter {

    private final LongAdder counter;

    public LongAdderCounter(long initialVal) {
        this.counter = new LongAdder();
        counter.add(initialVal);
    }

    @Override
    public long dec() {
        counter.decrement();
        return 0;
    }

    @Override
    public long decUntilZero() {
        return 0;  //TODO not implemented yet
    }

    @Override
    public long get() {
        return counter.sum();
    }

    @Override
    public long getMissedCount() {
        return 0;
    }

    public static void main(String[] args) {
        LongAdder l = new LongAdder();
        l.add(10);
        System.out.println(l.longValue());
        System.out.println(l.sum());
        l.add(10);
        l.add(-10);
        System.out.println(l.longValue());
        System.out.println(l.sum());


    }
}
