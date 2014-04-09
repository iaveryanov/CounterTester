package ru;

public interface ICounter {

    void set(long value);

    void dec();

    void decUntilZero();

    long get();

    // счетчик холостых ходов, считаешся только на операции decUntilZero()
    long getMissedCount();
}
