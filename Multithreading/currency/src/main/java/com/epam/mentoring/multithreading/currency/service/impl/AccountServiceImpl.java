package com.epam.mentoring.multithreading.currency.service.impl;

import com.epam.mentoring.multithreading.currency.dao.AccountDao;
import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.epam.mentoring.multithreading.currency.exception.ServiceException;
import com.epam.mentoring.multithreading.currency.exception.ServiceExceptionCode;
import com.epam.mentoring.multithreading.currency.model.Account;
import com.epam.mentoring.multithreading.currency.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Endeg on 26.08.2016.
 */
public class AccountServiceImpl implements AccountService {

    private static final Object o = new java.lang.Object();

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountDao accountDao = new AccountDao();

    @Override
    public Account get(String id) throws ServiceException {
        try {
            synchronized (o) {
                return accountDao.get(id);
            }
        } catch (DaoException e) {
            LOGGER.error("Problem getting account of " + id, e);
            throw new ServiceException("Account '" + id + "' not found", e, ServiceExceptionCode.ACCOUNT_NOT_FOUND);
        }
    }

    @Override
    public void save(String id, Account entity) throws ServiceException {
        try {
            synchronized (o) {
                accountDao.save(id, entity);
            }
        } catch (DaoException e) {
            LOGGER.error("Problem saving account of " + id, e);
            throw new ServiceException("Account '" + id + "' cannot be saved", e, ServiceExceptionCode.PROBLEM_SAVING_ACCOUNT);
        }
    }
}
