package ru.counter;


import ru.ICounter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements ICounter {

    private final AtomicLong counter;

    // счетчик холостого хода
    // overhead!!!
    private final AtomicLong missedCount;

    public AtomicCounter() {
        this(0);
    }

    public AtomicCounter(long initialVal) {
        counter = new AtomicLong(initialVal);
        missedCount = new AtomicLong(0);
    }


    @Override
    public void set(long value) {
        counter.set(value);
    }

    @Override
    public void dec() {
        counter.decrementAndGet();
    }

    @Override
    public void decUntilZero() {
        while (true) {
            long cur = counter.get();
            if (cur == 0) {
                return;
            }
            long next = cur - 1;
            if (counter.compareAndSet(cur, next)) {
                return;
            }
            missedCount.incrementAndGet();
        }
    }

    @Override
    public long get() {
        return counter.get();
    }

    @Override
    public long getMissedCount() {
        return missedCount.get();
    }

    @Override
    public String toString() {
        return "AtomicCounter{" +
                "counter=" + counter +
                ", missedCount=" + missedCount +
                '}';
    }
}
