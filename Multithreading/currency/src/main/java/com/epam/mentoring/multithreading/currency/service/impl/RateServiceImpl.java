package com.epam.mentoring.multithreading.currency.service.impl;

import com.epam.mentoring.multithreading.currency.dao.RateDao;
import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.epam.mentoring.multithreading.currency.model.Rate;
import com.epam.mentoring.multithreading.currency.model.RatePk;
import com.epam.mentoring.multithreading.currency.service.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Endeg on 26.08.2016.
 */
public class RateServiceImpl implements RateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateServiceImpl.class);

    private final RateDao rateDao = new RateDao();

    @Override
    public Rate get(RatePk id) {
        try {
            return rateDao.get(id);
        } catch (DaoException e) {
            LOGGER.error("Problem getting rate of " + id, e);
            return null;
        }
    }

    @Override
    public void save(RatePk id, Rate entity) {
        try {
            rateDao.save(id, entity);
        } catch (DaoException e) {
            LOGGER.error("Problem saving rate of " + id, e);
        }
    }
}
