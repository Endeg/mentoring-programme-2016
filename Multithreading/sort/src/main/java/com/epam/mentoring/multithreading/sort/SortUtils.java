package com.epam.mentoring.multithreading.sort;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

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
}
