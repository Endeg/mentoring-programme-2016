package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

/**
 * Created by Endeg on 26.08.2016.
 */
public class SingleThreadedQuickSortRunner extends SortFramework<Integer> {

    private SingleThreadedQuickSortRunner() {
        super(SortUtils.genIntegers(10000));
    }

    public static void main(String[] args) {
        new SingleThreadedQuickSortRunner().run();
    }

    private static ImmutableList<Integer> qsort(ImmutableList<Integer> unsorted) {
        if (unsorted.isEmpty()) {
            return unsorted;
        }

        if (unsorted.size() == 1) {
            return unsorted;
        }

        final Integer x = unsorted.get(0);
        final ImmutableList<Integer> xs = unsorted.subList(1, unsorted.size() - 1);

        return ImmutableList.<Integer>builder().
                addAll(qsort(SortUtils.filterMinElements(xs, x))).
                add(x).
                addAll(qsort(SortUtils.filterMaxElements(xs, x))).
                build();
    }

    @Override
    protected ImmutableList<Integer> sort() {
        return qsort(unsorted);
    }
}
