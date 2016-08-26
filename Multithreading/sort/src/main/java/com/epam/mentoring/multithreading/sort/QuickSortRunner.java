package com.epam.mentoring.multithreading.sort;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Endeg on 26.08.2016.
 */
public class QuickSortRunner {

    public static void main(String[] args) {
        final Random rnd = new Random();
        List<Integer> raw = new ArrayList<Integer>();
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
                addAll(qsort(filterMinElements(xs, x))).
                add(x).
                addAll(qsort(filterMaxElements(xs, x))).
                build();
    }

    private static <E extends Comparable<E>> ImmutableList<E> filterMinElements(ImmutableList<E> xs, final E x) {
        final Iterable<E> filtered = Iterables.filter(xs, new Predicate<E>() {
            public boolean apply(E e) {
                return e.compareTo(x) < 0;
            }
        });

        return ImmutableList.copyOf(filtered);
    }

    private static <E extends Comparable<E>> ImmutableList<E> filterMaxElements(ImmutableList<E> xs, final E x) {
        final Iterable<E> filtered = Iterables.filter(xs, new Predicate<E>() {
            public boolean apply(E e) {
                return e.compareTo(x) >= 0;
            }
        });

        return ImmutableList.copyOf(filtered);
    }
}
