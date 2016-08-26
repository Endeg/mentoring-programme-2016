package com.epam.mentoring.multithreading.currency.exception;

/**
 * Created by Endeg on 26.08.2016.
 */
public class ServiceException extends Exception {

    private final ServiceExceptionCode code;

    public ServiceException(ServiceExceptionCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, ServiceExceptionCode code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(String message, ServiceExceptionCode code) {
        super(message);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return code + ": " + super.getMessage();
    }

    public ServiceExceptionCode getCode() {
        return code;
    }
}
