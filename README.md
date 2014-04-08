CounterTester
=============

counter benchmarks

|         counterImpl|          initialVal|            poolSize|        opsPerThread|            finalVal|            opsCount|         missedCount|         TimeElapsed|
|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|
|       BrokenCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                 789|
|       BrokenCounter|           400000000|                   2|           200000000|           181811248|           218188752|                   0|                 556|
|       BrokenCounter|           400000000|                   3|           133333333|           192196005|           207803995|                   0|                 518|
|       BrokenCounter|           400000000|                   4|           100000000|           265902229|           134097771|                   0|                 504|
|       BrokenCounter|           400000000|                  10|            40000000|           311626992|            88373008|                   0|                 499|
|       BrokenCounter|           400000000|                 100|             4000000|           265852008|           134147992|                   0|                 505|
|     VolatileCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                3734|
|     VolatileCounter|           400000000|                   2|           200000000|           122183958|           277816042|                   0|                6206|
|     VolatileCounter|           400000000|                   3|           133333333|           218055611|           181944389|                   0|                7917|
|     VolatileCounter|           400000000|                   4|           100000000|           244578390|           155421610|                   0|                6742|
|     VolatileCounter|           400000000|                  10|            40000000|           266042693|           133957307|                   0|                6669|
|     VolatileCounter|           400000000|                 100|             4000000|           324248721|            75751279|                   0|                6735|
| SynchronizedCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|               10520|
| SynchronizedCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               16020|
| SynchronizedCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               20466|
| SynchronizedCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               18696|
| SynchronizedCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               20825|
| SynchronizedCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               21109|
|    ReentrantCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|               10123|
|    ReentrantCounter|           400000000|                   2|           200000000|                   0|           400000000|                   0|               22937|
|    ReentrantCounter|           400000000|                   3|           133333333|                   1|           399999999|                   0|               16088|
|    ReentrantCounter|           400000000|                   4|           100000000|                   0|           400000000|                   0|               16172|
|    ReentrantCounter|           400000000|                  10|            40000000|                   0|           400000000|                   0|               16317|
|    ReentrantCounter|           400000000|                 100|             4000000|                   0|           400000000|                   0|               16197|
|       AtomicCounter|           400000000|                   1|           400000000|                   0|           400000000|                   0|                4694|
|       AtomicCounter|           400000000|                   2|           200000000|                   0|           400000000|           180781738|               18236|
|       AtomicCounter|           400000000|                   3|           133333333|                   1|           399999999|           246307107|               23085|
|       AtomicCounter|           400000000|                   4|           100000000|                   0|           400000000|           437025017|               32527|
|       AtomicCounter|           400000000|                  10|            40000000|                   0|           400000000|           386394405|               31509|
|       AtomicCounter|           400000000|                 100|             4000000|                   0|           400000000|           492481034|               35654|
|    LongAdderCounter|           400000000|                   1|           400000000|           400000000|                   0|                   0|                1661|
|    LongAdderCounter|           400000000|                   2|           200000000|           400000000|                   0|                   0|                 880|
|    LongAdderCounter|           400000000|                   3|           133333333|           400000000|                   0|                   0|                 696|
|    LongAdderCounter|           400000000|                   4|           100000000|           400000000|                   0|                   0|                 664|
|    LongAdderCounter|           400000000|                  10|            40000000|           400000000|                   0|                   0|                 668|
|    LongAdderCounter|           400000000|                 100|             4000000|           400000000|                   0|                   0|                 679|
