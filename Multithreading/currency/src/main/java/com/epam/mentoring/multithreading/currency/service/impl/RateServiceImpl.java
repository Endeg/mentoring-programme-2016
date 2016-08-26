package com.epam.mentoring.multithreading.currency.service.impl;

import com.epam.mentoring.multithreading.currency.dao.RateDao;
import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.epam.mentoring.multithreading.currency.exception.ServiceException;
import com.epam.mentoring.multithreading.currency.exception.ServiceExceptionCode;
import com.epam.mentoring.multithreading.currency.model.Rate;
import com.epam.mentoring.multithreading.currency.model.RatePk;
import com.epam.mentoring.multithreading.currency.service.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Endeg on 26.08.2016.
 */
public class RateServiceImpl implements RateService {

    private static final Object o = new java.lang.Object();

    private static final Logger LOGGER = LoggerFactory.getLogger(RateServiceImpl.class);

    private final RateDao rateDao = new RateDao();

    @Override
    public Rate get(RatePk id) throws ServiceException {
        try {
            synchronized (o) {
                return rateDao.get(id);
            }
        } catch (DaoException e) {
            LOGGER.error("Problem getting rate of " + id, e);
            throw new ServiceException("Rate '" + id + "' not found", e, ServiceExceptionCode.RATE_NOT_FOUND);
        }
    }

    @Override
    public void save(RatePk id, Rate entity) throws ServiceException {
        try {
            synchronized (o) {
                rateDao.save(id, entity);
            }
        } catch (DaoException e) {
            LOGGER.error("Problem saving rate of " + id, e);
            throw new ServiceException("Rate '" + id + "' cannot be saved", e, ServiceExceptionCode.PROBLEM_SAVING_RATE);
        }
    }
}
