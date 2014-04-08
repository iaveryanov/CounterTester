package ru.rrc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    private final int roundRobinBase;

    public RoundRobinCounter(int roundRobinBase) {
        this.roundRobinBase = roundRobinBase;
    }

    // non-blocking atomic round-robing
    public int getNext() {
        while (true) {
            int cur = counter.get();
            int next = (cur + 1) % roundRobinBase;
            if (counter.compareAndSet(cur, next)) {
                System.out.println(next);
                return next;
            }
        }
    }

    public static void main(String[] args) {
        RoundRobinCounter rr = new RoundRobinCounter(10);

        int poolSize = 10;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < poolSize; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        rr.getNext();
                    }
                }
            });
        }

    }
}
