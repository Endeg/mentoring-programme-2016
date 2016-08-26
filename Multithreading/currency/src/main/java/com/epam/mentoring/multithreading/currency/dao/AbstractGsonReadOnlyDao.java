package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Endeg on 26.08.2016.
 */
public abstract class AbstractGsonReadOnlyDao<E, PK> implements ReadOnlyDao<E, PK> {
    public E get(PK id) throws DaoException {
        final Gson gson = new Gson();
        final InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(id + ".json");
        if (jsonStream != null) {
            return gson.fromJson(new InputStreamReader(jsonStream), getEntityClass());
        } else {
            throw new DaoException("Entity of " + getEntityClass() + " with id " + id + " was not found");
        }
    }

    protected abstract Class<E> getEntityClass();
}
