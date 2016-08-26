package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Endeg on 26.08.2016.
 */
public class RecursiveQuickSortTask<E extends Comparable<E>> extends RecursiveTask<ImmutableList<E>> {

    private final ImmutableList<E> unsorted;

    public RecursiveQuickSortTask(ImmutableList<E> unsorted) {
        this.unsorted = unsorted;
    }

    protected ImmutableList<E> compute() {
        if (unsorted.isEmpty() || unsorted.size() == 1) {
            return unsorted;
        }

        final E x = unsorted.get(0);
        final ImmutableList<E> xs = unsorted.subList(1, unsorted.size() - 1);

        final RecursiveQuickSortTask<E> minElementsTask = new RecursiveQuickSortTask<>(SortUtils.filterMinElements(xs, x));
        minElementsTask.fork();

        final RecursiveQuickSortTask<E> maxElementsTask = new RecursiveQuickSortTask<>(SortUtils.filterMaxElements(xs, x));
        maxElementsTask.fork();

        return ImmutableList.<E>builder().
                addAll(minElementsTask.join()).
                add(x).
                addAll(maxElementsTask.join()).
                build();
    }
}
