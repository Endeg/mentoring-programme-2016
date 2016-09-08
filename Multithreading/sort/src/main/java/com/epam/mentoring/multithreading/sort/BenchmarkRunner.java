package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

/**
 * Created by Endeg on 26.08.2016.
 */
public class BenchmarkRunner {

    public static void main(String[] args) {
        for (int j = 0; j < 20; j++) {
            System.out.println(j + " -------------------------------------------------------------");
            System.out.println("size\tsingle\tmulti");
            for (int i = 10; i <= 100_000; i *= 10) {
                final ImmutableList<String> unsorted = SortUtils.genStrings(i, 20);
                final long single = process(new SingleThreadedStringQuickSortRunner(unsorted, false));
                final long multi = process(new MultithreadedStringQuickSortRunner(unsorted, false));
                System.out.println(i + "\t" + single + "ms\t" + multi + "ms");
            }
        }
    }

    private static long process(SortFramework<?> runner) {
        runner.run();
        return runner.getElapsed();
    }
}
