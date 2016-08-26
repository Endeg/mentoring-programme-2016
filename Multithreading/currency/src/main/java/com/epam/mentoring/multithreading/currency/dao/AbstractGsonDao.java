package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by Endeg on 26.08.2016.
 */
public abstract class AbstractGsonDao<E, PK> implements ReadOnlyDao<E, PK>, WriteOnlyDao<E, PK> {

    public static final String STORAGE_PATH = Paths.get(".").toAbsolutePath().normalize().toString();

    public E get(PK id) throws DaoException {
        final Gson gson = new Gson();

        final String filePath = id + ".json";
        final String storagePath = Paths.get(STORAGE_PATH, filePath).normalize().toString();

        InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(storagePath);
        if (jsonStream == null) {
            jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        }
        if (jsonStream != null) {
            return gson.fromJson(new InputStreamReader(jsonStream), getEntityClass());
        } else {
            throw new DaoException("Entity of " + getEntityClass() + " with id " + id + " was not found");
        }
    }

    public void save(PK id, E entity) throws DaoException {
        final Gson gson = new Gson();

        final String filePath = id + ".json";
        final String storagePath = Paths.get(STORAGE_PATH, filePath).normalize().toString();


        final String json = gson.toJson(entity);

        try (FileWriter writer = new FileWriter(storagePath)) {
            writer.write(json);
        } catch (IOException e) {
            throw new DaoException("Entity of " + getEntityClass() + " cannot be saved with id " + id + " to path " + storagePath);
        }
    }

    protected abstract Class<E> getEntityClass();
}
