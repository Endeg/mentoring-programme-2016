package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.exception.DaoException;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface WriteOnlyDao<E, PK> {
    void save(PK id, E entity) throws DaoException;
}
