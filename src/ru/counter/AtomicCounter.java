package ru.counter;


import ru.ICounter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements ICounter {

    private final AtomicLong counter;

    // счетчик холостого хода
    private final AtomicLong missedCount;

    public AtomicCounter(long initialVal) {
        counter = new AtomicLong(initialVal);
        missedCount = new AtomicLong(0);
    }


    @Override
    public long dec() {
        return counter.decrementAndGet();
    }

    @Override
    public long decUntilZero() {
        while (true) {
            long cur = counter.get();
            if (cur == 0) {
                return cur;
            }
            long next = cur - 1;
            if (counter.compareAndSet(cur, next)) {
                return next;
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
