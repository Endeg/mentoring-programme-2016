package com.epam.mentoring.multithreading.currency.dao;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Endeg on 26.08.2016.
 */
public abstract class AbstractGsonReadOnlyDao<E, PK> implements ReadOnlyDao<E, PK> {
    public E get(PK id) {
        final Gson gson = new Gson();
        final InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(id + ".json");
        return gson.fromJson(new InputStreamReader(jsonStream), getEntityClass());
    }

    protected abstract Class<E> getEntityClass();
}
