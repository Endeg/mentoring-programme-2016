package com.epam.mentoring.multithreading.currency.service;

import com.epam.mentoring.multithreading.currency.exception.ServiceException;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface WriteInterface<E, PK> {

    void save(PK id, E entity) throws ServiceException;
}
