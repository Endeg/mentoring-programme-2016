package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.exception.DaoException;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface ReadOnlyDao<E, PK> {
    E get(PK id) throws DaoException;
}
