package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.model.Currency;

/**
 * Created by Endeg on 26.08.2016.
 */
public class CurrencyDao extends AbstractGsonReadOnlyDao<Currency, String> {

    protected Class<Currency> getEntityClass() {
        return Currency.class;
    }
}
