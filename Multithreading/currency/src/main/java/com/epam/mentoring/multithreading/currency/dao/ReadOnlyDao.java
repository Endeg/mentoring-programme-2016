package com.epam.mentoring.multithreading.currency.dao;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface ReadOnlyDao<E, PK> {
    E get(PK id);
}
