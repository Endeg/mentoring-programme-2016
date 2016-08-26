package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

/**
 * Created by Endeg on 26.08.2016.
 */
public abstract class SortFramework<E extends Comparable<E>> {

    protected final ImmutableList<E> unsorted;
    private ImmutableList<E> sorted;
    private long elapsed;

    protected SortFramework(ImmutableList<E> unsorted) {
        this.unsorted = unsorted;
    }

    public void run() {
        final long start = System.currentTimeMillis();
        sorted = sort();
        elapsed = System.currentTimeMillis() - start;
        output();
    }

    private void output() {
        SortUtils.outputList(sorted);
        System.out.println("-----");
        System.out.println("Sorted in " + elapsed + "ms");
    }

    protected abstract ImmutableList<E> sort();

    public long getElapsed() {
        return elapsed;
    }
}
