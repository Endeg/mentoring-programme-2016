package com.epam.mentoring.multithreading.currency.service;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface WriteInterface<E, PK> {

    void save(PK id, E entity);
}
