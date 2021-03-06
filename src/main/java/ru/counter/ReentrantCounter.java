package ru.counter;

import ru.ICounter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantCounter implements ICounter {

    private long counter;

    private ReentrantLock lock = new ReentrantLock();

    public ReentrantCounter() {
        this(0);
    }

    public ReentrantCounter(long initialVal) {
        this.counter = initialVal;
    }

    @Override
    public void set(long value) {
        this.counter = value;
    }

    @Override
    public void dec() {
        lock.lock();
        try {
            --counter;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void decUntilZero() {
        lock.lock();
        try {
            if (counter == 0) return;
            --counter;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long get() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getMissedCount() {
        return 0;
    }

    @Override
    public String toString() {
        return "ReentrantCounter{" +
                "counter=" + get() +
                '}';
    }
}
