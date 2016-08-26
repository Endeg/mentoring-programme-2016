package com.epam.mentoring.multithreading.race;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Endeg on 26.08.2016.
 */
public class RunRace {

    private static final Logger log = Logger.getLogger(RunRace.class);

    public static void main(String[] args) throws InterruptedException {
        Thread[] cars = new Thread[]{new Thread(new Car("Mario", 100), "Mario"),
                new Thread(new Car("Luigi", 120), "Luigi"),
                new Thread(new Car("Peach", 120), "Peach"),
                new Thread(new Car("Toad", 140), "Toad"),
                new Thread(new Car("Bowser", 160), "Bowser")};


        final List<String> places = new ArrayList<>();

        for (Thread car : cars) {
            car.start();
        }

        while (places.size() < cars.length) {
            for (Thread car : cars) {
                final String carName = car.getName();
                if (car.getState() == Thread.State.TERMINATED && !places.contains(carName)) {
                    places.add(carName);
                    log.info(carName + " reached FINISH as #" + places.size() + "!");
                    break;
                }
            }

        }

        for (Thread car : cars) {
            car.join();
        }

        log.info("And the WINNER IS " + places.get(0) + "!");
    }
}
