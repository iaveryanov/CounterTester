CounterTester
=============

Counter benchmarks

Tested simple decrement operation.

|         counterImpl|          initialVal|            poolSize|        opsPerThread|            finalVal|            opsCount|         missedCount|         TimeElapsed|
|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|
|       BrokenCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                 799|
|       BrokenCounter|           400000000|                   2|           200000000|            73439989|           326560011|                   0|                 473|
|       BrokenCounter|           400000000|                   3|           133333333|           266217472|           133782528|                   0|                 155|
|       BrokenCounter|           400000000|                   4|           100000000|           299173466|           100826534|                   0|                 161|
|       BrokenCounter|           400000000|                  10|            40000000|           310516333|            89483667|                   0|                 138|
|       BrokenCounter|           400000000|                 100|             4000000|           296862501|           103137499|                   0|                 149|
|     VolatileCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                3821|
|     VolatileCounter|           400000000|                   2|           200000000|           119485643|           280514357|                   0|                5422|
|     VolatileCounter|           400000000|                   3|           133333333|           218988176|           181011824|                   0|                8009|
|     VolatileCounter|           400000000|                   4|           100000000|           272238870|           127761130|                   0|                6366|
|     VolatileCounter|           400000000|                  10|            40000000|           277885077|           122114923|                   0|                6756|
|     VolatileCounter|           400000000|                 100|             4000000|           358492516|            41507484|                   0|                6388|
| SynchronizedCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                9719|
| SynchronizedCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               58728|
| SynchronizedCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               60170|
| SynchronizedCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               65204|
| SynchronizedCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               59171|
| SynchronizedCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               59808|
|    ReentrantCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                9323|
|    ReentrantCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               22782|
|    ReentrantCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               16917|
|    ReentrantCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               15668|
|    ReentrantCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               15574|
|    ReentrantCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               16418|
|       AtomicCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                3770|
|       AtomicCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|                6294|
|       AtomicCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|                8978|
|       AtomicCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|                9914|
|       AtomicCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|                9854|
|       AtomicCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               10248|
|    LongAdderCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                4816|
|    LongAdderCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|                3483|
|    LongAdderCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|                2119|
|    LongAdderCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|                1863|
|    LongAdderCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|                1871|
|    LongAdderCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|                2027|
|   ConcurrentCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                4877|
|   ConcurrentCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               10064|
|   ConcurrentCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               11877|
|   ConcurrentCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               10336|
|   ConcurrentCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               11267|
|   ConcurrentCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               10870|

with fair

|         counterImpl|          initialVal|            poolSize|        opsPerThread|            finalVal|            opsCount|         missedCount|         TimeElapsed|
|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|
|       BrokenCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                 823|
|       BrokenCounter|           400000000|                   2|           200000000|           176911174|           223088826|                   0|                 536|
|       BrokenCounter|           400000000|                   3|           133333333|           266628956|           133371044|                   0|                 153|
|       BrokenCounter|           400000000|                   4|           100000000|           273669163|           126330837|                   0|                 151|
|       BrokenCounter|           400000000|                  10|            40000000|           286523116|           113476884|                   0|                 142|
|       BrokenCounter|           400000000|                 100|             4000000|           291843270|           108156730|                   0|                 140|
|     VolatileCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                3738|
|     VolatileCounter|           400000000|                   2|           200000000|           134186687|           265813313|                   0|                6460|
|     VolatileCounter|           400000000|                   3|           133333333|           218356371|           181643629|                   0|                7859|
|     VolatileCounter|           400000000|                   4|           100000000|           229045690|           170954310|                   0|                6607|
|     VolatileCounter|           400000000|                  10|            40000000|           261301887|           138698113|                   0|                6668|
|     VolatileCounter|           400000000|                 100|             4000000|           351907833|            48092167|                   0|                6624|
| SynchronizedCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                9839|
| SynchronizedCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               57931|
| SynchronizedCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               57768|
| SynchronizedCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               62379|
| SynchronizedCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               60887|
| SynchronizedCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               62447|
|    ReentrantCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                9756|
|    ReentrantCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               22737|
|    ReentrantCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               15726|
|    ReentrantCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               14813|
|    ReentrantCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               15017|
|    ReentrantCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               15218|
|       AtomicCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                3735|
|       AtomicCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|                6638|
|       AtomicCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|                8607|
|       AtomicCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|                9130|
|       AtomicCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|                8529|
|       AtomicCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|                8711|
|    LongAdderCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                4590|
|    LongAdderCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|                3042|
|    LongAdderCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|                2193|
|    LongAdderCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|                1929|
|    LongAdderCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|                2015|
|    LongAdderCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|                1903|
|   ConcurrentCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                4603|
|   ConcurrentCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               10365|
|   ConcurrentCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               11652|
|   ConcurrentCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               11506|
|   ConcurrentCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               11654|
|   ConcurrentCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               11300|
|         FairCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                9397|
|         FairCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|              637279|
|         FairCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|             1635383|
|         FairCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|             1635384|
|         FairCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|             1592979|
|         FairCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|             1633132|

JMH
===

Benchmark                                        Mode   Samples         Mean   Mean error    Units
r.j.CounterBenchmark.perfAtomicCounter          thrpt        10    38835,448      463,824   ops/ms
r.j.CounterBenchmark.perfBrokenCounter          thrpt        10   132171,730     6129,487   ops/ms
r.j.CounterBenchmark.perfConcurrentCounter      thrpt        10    35543,551      780,374   ops/ms
r.j.CounterBenchmark.perfLongAdderCounter       thrpt        10   211933,868     4518,836   ops/ms
r.j.CounterBenchmark.perfReentrantCounter       thrpt        10    27488,631      737,897   ops/ms
r.j.CounterBenchmark.perfSynchronizedCounter    thrpt        10    19849,468      755,405   ops/ms
r.j.CounterBenchmark.perfVolatileCounter        thrpt        10    61132,360     3475,195   ops/ms