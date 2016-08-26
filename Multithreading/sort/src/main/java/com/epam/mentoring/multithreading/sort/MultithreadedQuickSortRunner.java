package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Endeg on 26.08.2016.
 */
public class MultithreadedQuickSortRunner extends SortFramework<Integer> {
    public MultithreadedQuickSortRunner(int count) {
        super(SortUtils.genIntegers(count));
    }

    public static void main(String[] args) {
        new MultithreadedQuickSortRunner(10000).run();
    }

    @Override
    protected ImmutableList<Integer> sort() {
        return new ForkJoinPool().invoke(new RecursiveQuickSortTask<>(unsorted));
    }
}
