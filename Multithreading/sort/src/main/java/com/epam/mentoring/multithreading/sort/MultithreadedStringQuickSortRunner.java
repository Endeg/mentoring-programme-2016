package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Andrey_Gladyshev on 29.08.2016.
 */
public class MultithreadedStringQuickSortRunner extends SortFramework<String> {

    public MultithreadedStringQuickSortRunner(ImmutableList<String> unsorted, boolean doOutput) {
        super(unsorted, doOutput);
    }

    public static void main(String[] args) {
        new SingleThreadedStringQuickSortRunner(SortUtils.genStrings(10000, 20), true).run();
    }

    @Override
    protected ImmutableList<String> sort() {
        return new ForkJoinPool().invoke(new RecursiveQuickSortTask<>(unsorted));
    }
}
