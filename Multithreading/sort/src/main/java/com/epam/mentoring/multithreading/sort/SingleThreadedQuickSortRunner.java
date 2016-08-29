package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

/**
 * Created by Endeg on 26.08.2016.
 */
public class SingleThreadedQuickSortRunner extends SortFramework<Integer> {

    public SingleThreadedQuickSortRunner(ImmutableList<Integer> unsorted, boolean doOutput) {
        super(unsorted, doOutput);
    }

    public static void main(String[] args) {
        new SingleThreadedQuickSortRunner(SortUtils.genIntegers(10000), true).run();
    }



    @Override
    protected ImmutableList<Integer> sort() {
        return SortUtils.qsort(unsorted);
    }
}
