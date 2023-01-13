package PartB;

import java.util.concurrent.*;

public class AdptToFut<T> extends FutureTask<T> implements Comparable<AdptToFut<T>> {

    private int prio;
    private Callable<T> c;

    /**
     * Constructor
     * @param callable - the callable to be adapted
     * @param priority - the priority of the task
     */
    public AdptToFut(Callable<T> callable, int priority) {
        super(callable);
        this.prio = priority;
    }

    /**
     * returns the priority of the task
     * @return the priority of the task
     */
    public int getPrio() {
        return this.prio;
    }

    /**
     * compares the priority of the task to the priority of another task
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(AdptToFut<T> o) {
        return (this.prio - o.prio > 0) ? 1:((this.prio - o.prio < 0) ? -1 : 0);
    }
}
