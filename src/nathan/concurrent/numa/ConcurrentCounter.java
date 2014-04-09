package nathan.concurrent.numa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
//import nathan.concurrent.ConcurrentHashMap;

// TODO - Allow for snap shotting for accurate get()

/**
 * Maintains one variable per processor that together maintain a {@code long}
 * counter.  Method {@link #get()} (or, equivalently, {@link #longValue})
 * returns the current total combined across the variables maintaining the
 * counter.
 *
 * <p>Under heavy concurrency {@link java.util.concurrent.atomic.AtomicLong}
 * will become a processor bottleneck.  The cache coherency traffic bottlenecks
 * while shuttling the cache line holding the <tt>AtomicLong</tt> among the
 * processors.  For example, maintaining the size of a concurrent
 * collection could become very expensive.  This class solves those issues by
 * partitioning the counter among processors.
 *
 * <p>Each processor owns an <tt>AtomicLong</tt> instance.  Except for the
 * retrieval operations, the <tt>AtomicLong</tt> will most likely only be
 * accessed by the processor that owns it.  Each <tt>AtomicLong</tt> has
 * a very good chance of residing only in the cache or memory local to the
 * processor that owns it.  Updates should proceed very quickly (i.e. 10s of
 * cycles) without bottlenecks.  This is because the atomic instructions will
 * rarely be contended.  Contention occurs when a thread selects a
 * <tt>AtomicLong</tt> and then context switches to another processor.  This
 * should happen very rarely.
 *
 * <p>Each processor will allocate its own <tt>AtomicLong</tt> and the JVM's
 * allocator and GC ensures that the <tt>AtomicLong</tt> remains in local
 * memory.  False sharing should not be possible.  Hence, no padding is used
 * needed between <tt>AtomicLong</tt>.
 *
 * <p>I recommend placing this class in a new package java.util.concurrent.numa
 * or java.util.concurrent.atomic.  The new package will have concurrent data
 * structures that optimize for NUMA architectures.
 *
 * @author Nathan Reynolds
 */
public class ConcurrentCounter extends Number implements Serializable
{
    @SuppressWarnings("compatibility:2289273073645113299")
    private static final long serialVersionUID = 8804942147172407801L;

    private static final AtomicReferenceFieldUpdater<ConcurrentCounter, ConcurrentHashMap> s_atomicPartitions = AtomicReferenceFieldUpdater.newUpdater(ConcurrentCounter.class, ConcurrentHashMap.class, "m_partitions");

    /**
     * Maps the processor id to a <tt>Partition</tt>.
     */
    private transient volatile ConcurrentHashMap<Integer, AtomicLong> m_partitions = new ConcurrentHashMap<Integer, AtomicLong>();

    /**
     * Initializes the counter to 0.
     */
    public ConcurrentCounter()
    {
        this(0);
    }

    /**
     * Initializes the counter to the given value.
     *
     * @param initialValue - the first value of the counter
     */
    public ConcurrentCounter(long initialValue)
    {
        add(initialValue);
    }

    private static int getID()
    {
        // TODO - Uncomment this when the JDK/JVM supports it: return(Thread.getProcessorId());
        return(0);
    }

    /**
     * @return the partition for the current processor
     */
    private AtomicLong getPartition()
    {
        AtomicLong result, other;
        int id;

        id     = getID();
        result = m_partitions.get(id);

        if (result != null)
            return(result);

        result = new AtomicLong();
        other  = m_partitions.putIfAbsent(id, result);

        if (other != null)
            result = other;

        return(result);
    }

    /**
     * Adds 1 to the value of the counter.  On some architectures, this will be
     * implemented with a cheaper increment instruction than a potentially more
     * expensive add instruction.
     */
    public void increment()
    {
        getPartition().incrementAndGet();
    }

    /**
     * Subtracts 1 from the value of the counter.  On some architectures, this
     * will be implemented with a cheaper decrement instruction than a
     * potentially more expensive subtract instruction.
     */
    public void decrement()
    {
        getPartition().decrementAndGet();
    }

    /**
     * Adds the given value to the value of the counter.
     *
     * @param delta - the amount to change the counter
     */
    public void add(long delta)
    {
        getPartition().addAndGet(delta);
    }

    /**
     * Atomically sets the value of the counter to 0.  It does so by forcing all
     * of the partitions to be reallocated.
     */
    public void clear()
    {
        m_partitions = makePartitions();
    }

    /**
     * Atomically sets the counter to the given value.  All of the partitions
     * will be reallocated.
     *
     * @param newValue - the counter is set to this value
     */
    public void set(long newValue)
    {
        m_partitions = makePartitions(newValue);
    }

    /**
     * Returns the current value of the counter.  The returned value is
     * <em>NOT</em> an atomic snapshot.  Invocation in the absence of current
     * updates returns an accurate result, but concurrent updates that occur
     * while the result is being calculated may or may not be incorported.
     *
     * <p>This method loads all of the internal partitions in to the current
     * processor's cache and is slow with respect to the update operations.
     *
     * @return the current value of the counter
     */
    public long get()
    {
        return(get(m_partitions));
    }

    /**
     * Returns the current value of the counter and then atomically clears the
     * counter.
     *
     * <p>See {@link #get()} and {@link #clear} documentation for impact.
     *
     * @return the current value of the counter
     */
    public long getAndClear()
    {
        ConcurrentHashMap<Integer, AtomicLong> partitions;

        partitions = makePartitions();
        partitions = getAndSetPartitions(partitions);

        return(get(partitions));
    }

    /**
     * Returns the current value of the counter and then atomically sets the
     * counter.
     *
     * <p>See {@link #get()} and {@link #set} documentation for impact.
     *
     * @return the current value of the counter
     */
    public long getAndSet(long newValue)
    {
        ConcurrentHashMap<Integer, AtomicLong> partitions;

        partitions = makePartitions(newValue);
        partitions = getAndSetPartitions(partitions);

        return(get(partitions));
    }

    /**
     * Atomically replaces the current partitions with the given partitions.
     *
     * @return the previous set of partitions
     */
    private ConcurrentHashMap<Integer, AtomicLong> getAndSetPartitions(ConcurrentHashMap<Integer, AtomicLong> partitions)
    {
        ConcurrentHashMap<Integer, AtomicLong> result;

        do
        {
            result = m_partitions;
        }
        while (!s_atomicPartitions.compareAndSet(this, result, partitions));

        return(result);
    }

    /**
     * Calculates the current value of the counter using the given partitions.
     */
    private static long get(ConcurrentHashMap<Integer, AtomicLong> partitions)
    {
        long result;

        result = 0;

        for (AtomicLong partition : partitions.values())
            result += partition.get();

        return(result);
    }

    /**
     * Creates a new empty set of partitions.
     */
    private static ConcurrentHashMap<Integer, AtomicLong> makePartitions()
    {
        ConcurrentHashMap<Integer, AtomicLong> result;

        result = new ConcurrentHashMap<Integer, AtomicLong>();

        return(result);
    }

    /**
     * Creates a new set of partitions with 1 partition set to the given value.
     */
    private static ConcurrentHashMap<Integer, AtomicLong> makePartitions(long initialValue)
    {
        ConcurrentHashMap<Integer, AtomicLong> result;
        AtomicLong partition;
        int id;

        result    = makePartitions();
        partition = new AtomicLong(initialValue);
        id        = getID();

        result.put(id, partition);

        return(result);
    }

    /**
     * Returns the current value of the counter formatted as a {@link String}.
     *
     * @return the current value in String form
     */
    public String toString()
    {
        return(Long.toString(get()));
    }

    /**
     * Returns the value as an {@code int} after a narrowing primitive
     * conversion.
     *
     * @return the current value of the counter as an {@code int}
     */
    public int intValue()
    {
        return((int) get());
    }

    /**
     * Same as calling {@link #get()}
     *
     * @return the current value of the counter
     */
    public long longValue()
    {
        return(get());
    }

    /**
     * Returns the value as a {@code float} after a widening primitive
     * conversion.
     *
     * @return the current value of the counter as a {@code float}
     */
    public float floatValue()
    {
        return(get());
    }

    /**
     * Returns the value as a {@code double} after a widening primitive
     * conversion.
     *
     * @return the current value of the counter as a {@code double}
     */
    public double doubleValue()
    {
        return(get());
    }

    /**
     * Writes the value of the counter.  Can be called while concurrent updates
     * are happening.
     */
    private void writeObject(ObjectOutputStream output) throws IOException
    {
        output.writeLong(get());
    }

    /**
     * Read the value of the counter and atomically sets it.  Can be called while
     * concurrent updates are happening.
     */
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException
    {
        set(input.readLong());
    }
}
