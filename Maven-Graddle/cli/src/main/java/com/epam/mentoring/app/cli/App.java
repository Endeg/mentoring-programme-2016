package com.epam.mentoring.app.cli;

import com.epam.mentoring.app.business.RandomStringService;

/**
 * Created by Endeg on 15.10.2016.
 */
public class App {

    final RandomStringService service = new RandomStringService();

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        System.out.println("Printing some random strings:");
        for (String randomString : service.getStrings(32)) {
            System.out.println(randomString);
        }
        System.out.println("Done.");
    }
}
