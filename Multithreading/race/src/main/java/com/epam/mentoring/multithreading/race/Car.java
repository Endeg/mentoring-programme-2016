package com.epam.mentoring.multithreading.race;


import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Endeg on 26.08.2016.
 */
public class Car implements Runnable {

    public static final String TROUBLE_MAKER_NAME = "Bowser";

    private static final long MAX_DISTANCE = 10000;

    Logger log = Logger.getLogger(getClass());

    private long friction;
    private long distance;

    private String name;

    public Car(String name, long friction) {
        this.name = name;
        this.friction = friction;
    }

    public void run() {
        final long startTime = System.currentTimeMillis();
        try {
            while (distance < MAX_DISTANCE) {
                Thread.sleep(friction);
                distance += 100;
                log.info(name + " " + distance);
                long elapsed = System.currentTimeMillis();
                if (TROUBLE_MAKER_NAME.equals(name) && (elapsed - startTime) >= TimeUnit.SECONDS.toMillis(5)) {
                    log.warn(name + " car was disqualified for troublemaking.");
                    break;
                }
            }
        } catch (InterruptedException e) {
            log.error(e);
        }
    }


}
