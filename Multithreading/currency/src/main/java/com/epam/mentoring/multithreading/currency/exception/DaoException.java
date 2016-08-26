package com.epam.mentoring.multithreading.currency.exception;

/**
 * Created by Endeg on 26.08.2016.
 */
public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
