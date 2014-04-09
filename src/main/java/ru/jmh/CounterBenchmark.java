package ru.jmh;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.ICounter;
import ru.counter.AtomicCounter;
import ru.counter.LongAdderCounter;
import ru.counter.ReentrantCounter;

@State(Scope.Benchmark)
public class CounterBenchmark {

    private final ICounter reentrantCounter = new ReentrantCounter(4_000_000_000L);
    private final ICounter atomicCounter = new AtomicCounter(4_000_000_000L);
    private final ICounter longAdderCounter = new LongAdderCounter(4_000_000_000L);

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


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + CounterBenchmark.class.getSimpleName() + ".*")
                .warmupIterations(3)
                .measurementIterations(3)
                .threads(4)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}