package ru.jmh;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.ICounter;
import ru.counter.*;

@State(Scope.Benchmark)
public class CounterBenchmark {

    public static final long INITIAL_VAL = 4_000_000_000L;


    private final ICounter brokenCounter = new BrokenCounter(INITIAL_VAL);
    private final ICounter volatileCounter = new VolatileCounter(INITIAL_VAL);
    private final ICounter synchronizedCounter = new SynchronizedCounter(INITIAL_VAL);
    private final ICounter reentrantCounter = new ReentrantCounter(INITIAL_VAL);
    private final ICounter atomicCounter = new AtomicCounter(INITIAL_VAL);
    private final ICounter longAdderCounter = new LongAdderCounter(INITIAL_VAL);
    private final ICounter concurrentCounter = new ConcurrentCounter(INITIAL_VAL);

    @GenerateMicroBenchmark
    public Object perfBrokenCounter() {
        brokenCounter.dec();
        return brokenCounter;
    }


    @GenerateMicroBenchmark
    public Object perfVolatileCounter() {
        volatileCounter.dec();
        return volatileCounter;
    }

    @GenerateMicroBenchmark
    public Object perfSynchronizedCounter() {
        synchronizedCounter.dec();
        return synchronizedCounter;
    }

    @GenerateMicroBenchmark
    public Object perfAtomicCounter() {
        atomicCounter.dec();
        return atomicCounter;
    }

    @GenerateMicroBenchmark
    public Object perfLongAdderCounter() {
        longAdderCounter.dec();
        return longAdderCounter;
    }

    @GenerateMicroBenchmark
    public Object perfReentrantCounter() {
        reentrantCounter.dec();
        return reentrantCounter;
    }

    @GenerateMicroBenchmark
    public Object perfConcurrentCounter() {
        concurrentCounter.dec();
        return concurrentCounter;
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + CounterBenchmark.class.getSimpleName() + ".*")
                .warmupIterations(10)
                .measurementIterations(10)
                .threads(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}