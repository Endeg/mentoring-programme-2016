package com.epam.mentoring.multithreading.currency.service.impl;

import com.epam.mentoring.multithreading.currency.exception.ServiceException;
import com.epam.mentoring.multithreading.currency.exception.ServiceExceptionCode;
import com.epam.mentoring.multithreading.currency.model.Account;
import com.epam.mentoring.multithreading.currency.model.Currency;
import com.epam.mentoring.multithreading.currency.model.Rate;
import com.epam.mentoring.multithreading.currency.model.RatePk;
import com.epam.mentoring.multithreading.currency.service.AccountService;
import com.epam.mentoring.multithreading.currency.service.CurrencyService;
import com.epam.mentoring.multithreading.currency.service.ExchangeService;
import com.epam.mentoring.multithreading.currency.service.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by Endeg on 26.08.2016.
 */
public class ExchangeServiceImpl implements ExchangeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeServiceImpl.class);

    private final AccountService accountService = new AccountServiceImpl();
    private final CurrencyService currencyService = new CurrencyServiceImpl();
    private final RateService rateService = new RateServiceImpl();

    @Override
    public BigDecimal exchange(String accountId, BigDecimal sourceAmount, String sourceCurrencyId, String destCurrencyId)
            throws ServiceException {
        LOGGER.info("Starting exchange");
        final Account account = accountService.get(accountId);
        if (!account.getCurrencies().contains(sourceCurrencyId)) {
            throw new ServiceException("Source currency '" + sourceCurrencyId + "' was not found",
                    ServiceExceptionCode.CURRENCY_NOT_SUPPORTED);
        }

        if (!account.getCurrencies().contains(destCurrencyId)) {
            throw new ServiceException("Destination currency '" + sourceCurrencyId + "' was not found",
                    ServiceExceptionCode.CURRENCY_NOT_SUPPORTED);
        }

        final Currency sourceCurrency = currencyService.get(sourceCurrencyId);
        final Currency destCurrency = currencyService.get(destCurrencyId);

        LOGGER.info("Making currency conversion from {} to {} for account {}",
                sourceCurrency.getName(), destCurrency.getName(), account.getName());

        final Rate rate = rateService.get(new RatePk(sourceCurrencyId, destCurrencyId));

        final BigDecimal converted = sourceAmount.multiply(rate.getValue());
        LOGGER.info("Converted result is {}", converted);
        LOGGER.info("Starting completed");
        return converted;
    }
}
