package ru;

import ru.counter.*;

import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrencyTester {

    public static void main(String[] args) throws InterruptedException {

        final int initialVal = 400000000;
        ICounter[] counters = {
                new BrokenCounter(),
                new VolatileCounter(),
                new SynchronizedCounter(),
                new ReentrantCounter(),
                new AtomicCounter(),
                new LongAdderCounter(),
                new ConcurrentCounter(),
//                new FairCounter()
        };
        int[] threads = {1,2,3,4,10,100};

        Semaphore semaphore = new Semaphore(1);

        for (ICounter c : counters) {
            for (int t : threads) {
                semaphore.acquire();
                c.set(initialVal);
                test(c, t);
                semaphore.release();
            }
        }
    }

    private static void test(final ICounter counter, int threadPoolSize)
            throws InterruptedException {

//        System.out.println("next test");
        final long initialVal = counter.get();
        final long opsPerThread = initialVal / threadPoolSize;

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        long startedAt = System.currentTimeMillis();

        final CountDownLatch latch = new CountDownLatch(threadPoolSize);

        for (int i = 0; i < threadPoolSize; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < opsPerThread; i++) {
                        counter.dec();
                    }
                    latch.countDown();
                }

            });
        }

//        System.out.println("Please, waiting...");
        latch.await();
        long elapsed = System.currentTimeMillis() - startedAt;
        executor.shutdown();
//        System.out.println("Done.");

//        printReportToConsole(initialVal, threadPoolSize, opsPerThread, counter, elapsed);
        printReportToFile(initialVal, threadPoolSize, opsPerThread, counter, elapsed);
    }

    private static void printReportToFile(
            long initialVal,
            long threadPoolSize,
            long opsPerThread,
            ICounter counter,
            long elapsed) {

        try {
            String fileName = "C:/temp/concurrency_tester.log";
            File file = new File(fileName);
            boolean writeTitle = !file.exists();
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fileWriter));
            if (writeTitle) {
                StringBuilder header = new StringBuilder("|");
                header.append(String.format("%20s|", "counterImpl"));
                header.append(String.format("%20s|", "initialVal"));
                header.append(String.format("%20s|", "poolSize"));

                header.append(String.format("%20s|", "opsPerThread"));
                header.append(String.format("%20s|", "finalVal"));
                header.append(String.format("%20s|", "opsCount"));

                header.append(String.format("%20s|", "missedCount"));
                header.append(String.format("%20s|", "TimeElapsed"));
                pw.println(header.toString());

                header = new StringBuilder("|");
                header.append("--------------------|");
                header.append("--------------------|");
                header.append("--------------------|");
                header.append("--------------------|");
                header.append("--------------------|");
                header.append("--------------------|");
                header.append("--------------------|");
                header.append("--------------------|");
                System.out.println(header);
                pw.println(header);
            }

            StringBuilder line = new StringBuilder("|");
            line.append(String.format("%20s|", counter.getClass().getSimpleName()));
            line.append(String.format("%20s|", initialVal));
            line.append(String.format("%20s|", threadPoolSize));

            line.append(String.format("%20s|", opsPerThread));
            line.append(String.format("%20s|", counter.get()));
            line.append(String.format("%20s|", (initialVal - counter.get())));

            line.append(String.format("%20s|", counter.getMissedCount()));
            line.append(String.format("%20s|", elapsed));

            String lineStr = line.toString();
            System.out.println(lineStr);
            pw.println(lineStr);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printReportToConsole(
            long initialVal,
            long poolSize,
            long opsPerThread,
            ICounter counter,
            long elapsed) {

        System.out.printf("================================================%n");
        long finalVal = counter.get();
        long opsCount = initialVal - finalVal;

        System.out.printf("== INPUT DATA %n");
        line("counterImpl", counter.getClass().getSimpleName());
        line("initialVal", initialVal);
        line("poolSize", poolSize);
        line("opsPerThread", opsPerThread);

        System.out.printf("== RESULTS %n");
        lineWithPercent("finalVal", finalVal, percent(finalVal, initialVal));
        lineWithPercent("opsCount", opsCount, percent(opsCount, initialVal));
        lineWithPercent("missedCount", counter.getMissedCount(), percent(counter.getMissedCount(), initialVal));

        line("TimeElapsed", elapsed);
    }

    private static void line(String key, long value) {
        System.out.printf("%-20s: %-10d %n", key, value);
    }
    private static void line(String key, String value) {
        System.out.printf("%-20s: %-10s %n", key, value);
    }

    private static void lineWithPercent(String key, long value, double percent) {
        System.out.printf("%-20s: %-10d %.2f%% %n", key, value, percent);
    }

    private static float percent(float part, float total) {
        return (part / total * 100);
    }

    private static float percent(long part, long total) {
        return percent((float) part, (float) total);
    }

}
