package com.epam.mentoring.multithreading.currency;

import java.nio.file.Paths;

/**
 * Created by Endeg on 26.08.2016.
 */
public class CurrencyRunner {

    public static void main(String[] args) {
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
    }
}
