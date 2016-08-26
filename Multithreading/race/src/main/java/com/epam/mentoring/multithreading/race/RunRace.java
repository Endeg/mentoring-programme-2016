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

        Car[] cars = new Car[]{new Car("Mario", 100),
                new Car("Luigi", 120),
                new Car("Peach", 120),
                new Car("Toad", 140),
                new Car(Car.TROUBLE_MAKER_NAME, 160)};

        List<Thread> carThreads = new ArrayList<>();
        for (Car car : cars) {
            carThreads.add(new Thread(car, car.name));
        }

        final List<String> places = new ArrayList<>();

        for (Thread car : carThreads) {
            car.start();
        }

        while (!allThreadsInState(carThreads, Thread.State.TERMINATED)) {
            for (Car car : cars) {
                if (car.getDistance() >= Car.MAX_DISTANCE && !places.contains(car.name)) {
                    places.add(car.name);
                    log.info(car.name + " reached FINISH as #" + places.size() + "!");
                    break;
                }
            }
        }

        for (Thread car : carThreads) {
            car.join();
        }

        log.info("And the WINNER IS " + places.get(0) + "!");
    }

    private static boolean allThreadsInState(List<Thread> threads, Thread.State state) {
        boolean result = true;
        for (Thread thread : threads) {
            if (thread.getState() != state) {
                result = false;
                break;
            }
        }
        return result;
    }
}
