package ru;

public interface ICounter {

    long dec();

    long decUntilZero();

    long get();

    // счетчик холостых ходов, считаешся только на операции decUntilZero()
    long getMissedCount();
}
