package com.epam.mentoring.app.dao;

import java.util.Random;

/**
 * Created by Endeg on 15.10.2016.
 */
public class RandomStringsDao {
    public String get() {
        final Random rnd = new Random();
        final byte[] buffer = new byte[64];
        rnd.nextBytes(buffer);
        final StringBuffer sb = new StringBuffer();
        for (byte b : buffer) {
            sb.append(String.valueOf(b));
        }

        return sb.toString();
    }
}
