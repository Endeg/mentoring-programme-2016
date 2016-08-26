package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.model.Rate;
import com.epam.mentoring.multithreading.currency.model.RatePk;

/**
 * Created by Endeg on 26.08.2016.
 */
public class RateDao extends AbstractGsonReadOnlyDao<Rate, RatePk> {
    protected Class<Rate> getEntityClass() {
        return Rate.class;
    }
}
