package ru.counter;

import ru.ICounter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantCounter implements ICounter {

    private long counter;

    private ReentrantLock lock = new ReentrantLock();

    public ReentrantCounter(long initialVal) {
        this.counter = initialVal;
    }

    @Override
    public long dec() {
        lock.lock();
        try {
            return --counter;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long decUntilZero() {
        lock.lock();
        try {
            if (counter == 0) {
                return counter;
            }
            return --counter;
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
