package com.epam.mentoring.multithreading.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Endeg on 26.08.2016.
 */
public class QuickSortRunner {

    public static void main(String[] args) {
        final Random rnd = new Random();
        List<Integer> unsorted = new ArrayList<Integer>();
        for (int i = 0; i < 100000; i++) {
            unsorted.add(rnd.nextInt());
        }
    }

}
