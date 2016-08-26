package com.epam.mentoring.multithreading.currency;

import com.epam.mentoring.multithreading.currency.exception.ServiceException;
import com.epam.mentoring.multithreading.currency.model.Account;
import com.epam.mentoring.multithreading.currency.service.AccountService;
import com.epam.mentoring.multithreading.currency.service.ExchangeService;
import com.epam.mentoring.multithreading.currency.service.impl.AccountServiceImpl;
import com.epam.mentoring.multithreading.currency.service.impl.ExchangeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Endeg on 26.08.2016.
 */
public class CurrencyRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyRunner.class);

    public static void main(String[] args) throws ServiceException, InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newCachedThreadPool();

        //final List<String> accountIds = AccountGenerator.genAccounts();
        //renameAccounts(accountIds, executorService);

        final ExchangeService exchangeService = new ExchangeServiceImpl();

        for (int i = 0; i < 1000; i++) {
            submitExchangeTask(executorService, exchangeService, "RUB");
            submitExchangeTask(executorService, exchangeService, "EUR");
            submitExchangeTask(executorService, exchangeService, "USD");
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } finally {
            if (!executorService.isTerminated()) {
                LOGGER.warn("Cannot wait for executor service shutdown!");
                executorService.shutdownNow();
            }
        }
    }

    private static void submitExchangeTask(ExecutorService executorService,
                                           final ExchangeService exchangeService,
                                           final String destCurrencyId) {
        final Random rnd = new Random();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    final BigDecimal exchange = exchangeService.exchange("JOHN", BigDecimal.valueOf(Math.abs(rnd.nextDouble()) * 100), "RUB", destCurrencyId);
                    LOGGER.info("Exchange result = {}", exchange);
                } catch (ServiceException e) {
                    LOGGER.error("Problem with exchange", e);
                }
            }
        });
    }

    private static void renameAccounts(List<String> accountIds, ExecutorService executorService) {
        final AccountService accountService = new AccountServiceImpl();

        for (final String accountId : accountIds) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Account account = accountService.get(accountId);
                        LOGGER.info("Got account {}", account);
                        account.setName(account.getName() + "_Changed");
                        accountService.save(accountId, account);
                    } catch (ServiceException e) {
                        LOGGER.error("Problems with renaming accounts", e);
                    }
                }
            });
        }
    }
}
