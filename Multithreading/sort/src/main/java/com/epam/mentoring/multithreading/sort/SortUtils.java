package com.epam.mentoring.multithreading.sort;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.Random;

/**
 * Created by Endeg on 26.08.2016.
 */
public class SortUtils {

    public static <E extends Comparable<E>> ImmutableList<E> filterMinElements(ImmutableList<E> xs, final E x) {
        final Iterable<E> filtered = Iterables.filter(xs, new Predicate<E>() {
            public boolean apply(E e) {
                return e.compareTo(x) < 0;
            }
        });

        return ImmutableList.copyOf(filtered);
    }

    public static <E extends Comparable<E>> ImmutableList<E> filterMaxElements(ImmutableList<E> xs, final E x) {
        final Iterable<E> filtered = Iterables.filter(xs, new Predicate<E>() {
            public boolean apply(E e) {
                return e.compareTo(x) >= 0;
            }
        });

        return ImmutableList.copyOf(filtered);
    }

    public static ImmutableList<Integer> genIntegers(int count) {
        final Random rnd = new Random();

        final ImmutableList.Builder<Integer> builder = ImmutableList.builder();
        for (int i = 0; i < count; i++) {
            builder.add(rnd.nextInt());
        }

        return builder.build();
    }

    public static <T extends Comparable<T>> ImmutableList<T> qsort(ImmutableList<T> unsorted) {
        if (unsorted.isEmpty()) {
            return unsorted;
        }

        if (unsorted.size() == 1) {
            return unsorted;
        }

        final T x = unsorted.get(0);
        final ImmutableList<T> xs = unsorted.subList(1, unsorted.size() - 1);

        return ImmutableList.<T>builder().
                addAll(qsort(SortUtils.filterMinElements(xs, x))).
                add(x).
                addAll(qsort(SortUtils.filterMaxElements(xs, x))).
                build();
    }

    public static <E> void outputList(ImmutableList<E> sorted) {
        for (E elem : sorted) {
            System.out.println(elem);
        }
    }
}
