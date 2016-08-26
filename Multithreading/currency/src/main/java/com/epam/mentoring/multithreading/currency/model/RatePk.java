package com.epam.mentoring.multithreading.currency.model;

/**
 * Created by Endeg on 26.08.2016.
 */
public class RatePk {
    private final String from;
    private final String to;

    public RatePk(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from + "-TO-" + to;
    }
}
