package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

/**
 * Created by Endeg on 26.08.2016.
 */
public class BenchmarkRunner {

    public static void main(String[] args) {
        System.out.println("size\tsingle\tmulti");
        for (int i = 10; i <= 10_000_000; i *= 10) {
            final ImmutableList<Integer> unsorted = SortUtils.genIntegers(i);
            final long single = process(new SingleThreadedQuickSortRunner(unsorted, false));
            final long multi = process(new MultithreadedQuickSortRunner(unsorted, false));
            System.out.println(i + "\t" + single + "ms\t" + multi + "ms");
        }
    }

    private static long process(SortFramework<Integer> runner) {
        runner.run();
        return runner.getElapsed();
    }
}
