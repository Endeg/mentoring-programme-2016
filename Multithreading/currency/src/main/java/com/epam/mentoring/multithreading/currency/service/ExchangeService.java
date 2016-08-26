package com.epam.mentoring.multithreading.currency.service;

import com.epam.mentoring.multithreading.currency.exception.ServiceException;

import java.math.BigDecimal;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface ExchangeService {

    BigDecimal exchange(String accountId, BigDecimal sourceAmount, String sourceCurrencyId, String destCurrencyId)
            throws ServiceException;

}
