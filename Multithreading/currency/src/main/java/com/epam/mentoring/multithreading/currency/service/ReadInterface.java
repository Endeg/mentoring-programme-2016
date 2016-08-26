package com.epam.mentoring.multithreading.currency.service;

import com.epam.mentoring.multithreading.currency.exception.ServiceException;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface ReadInterface<E, PK> {
    E get(PK id) throws ServiceException;
}
