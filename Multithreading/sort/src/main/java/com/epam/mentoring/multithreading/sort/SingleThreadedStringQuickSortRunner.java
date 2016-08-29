package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

/**
 * Created by Andrey_Gladyshev on 29.08.2016.
 */
public class SingleThreadedStringQuickSortRunner extends SortFramework<String> {

    public SingleThreadedStringQuickSortRunner(ImmutableList<String> unsorted, boolean doOutput) {
        super(unsorted, doOutput);
    }

    public static void main(String[] args) {
        new SingleThreadedStringQuickSortRunner(SortUtils.genStrings(10000, 20), true).run();
    }

    @Override
    protected ImmutableList<String> sort() {
        return SortUtils.qsort(unsorted);
    }
}
