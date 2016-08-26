package com.epam.mentoring.multithreading.sort;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Endeg on 26.08.2016.
 */
public class SingleThreadedQuickSortRunner {

    public static void main(String[] args) {
        final Random rnd = new Random();
        List<Integer> raw = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            raw.add(rnd.nextInt());
        }

        final ImmutableList<Integer> unsorted = ImmutableList.copyOf(raw);

        final ImmutableList<Integer> sorted = qsort(unsorted);

        for (Integer elem : sorted) {
            System.out.println(elem);
        }
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
}
