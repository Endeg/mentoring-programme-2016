package com.epam.mentoring.multithreading.currency.service.impl;

import com.epam.mentoring.multithreading.currency.dao.CurrencyDao;
import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.epam.mentoring.multithreading.currency.exception.ServiceException;
import com.epam.mentoring.multithreading.currency.model.Currency;
import com.epam.mentoring.multithreading.currency.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Endeg on 26.08.2016.
 */
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyDao currencyDao = new CurrencyDao();

    @Override
    public Currency get(String id) throws ServiceException {
        try {
            return currencyDao.get(id);
        } catch (DaoException e) {
            LOGGER.error("Problem getting currency of " + id, e);
            return null;
        }
    }

    @Override
    public void save(String id, Currency entity) throws ServiceException {
        try {
            currencyDao.save(id, entity);
        } catch (DaoException e) {
            LOGGER.error("Problem saving currency of " + id, e);
        }
    }
}
