package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Endeg on 26.08.2016.
 */
public class MultithreadedQuickSortRunner extends SortFramework<Integer> {

    public MultithreadedQuickSortRunner(ImmutableList<Integer> unsorted, boolean doOutput) {
        super(unsorted, doOutput);
    }

    public static void main(String[] args) {
        new MultithreadedQuickSortRunner(SortUtils.genIntegers(10000), true).run();
    }

    @Override
    protected ImmutableList<Integer> sort() {
        return new ForkJoinPool().invoke(new RecursiveQuickSortTask<>(unsorted));
    }
}
