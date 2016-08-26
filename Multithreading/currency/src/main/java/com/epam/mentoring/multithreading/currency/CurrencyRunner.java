package com.epam.mentoring.multithreading.currency;

import com.epam.mentoring.multithreading.currency.exception.ServiceException;
import com.epam.mentoring.multithreading.currency.util.AccountGenerator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Endeg on 26.08.2016.
 */
public class CurrencyRunner {

    public static void main(String[] args) throws ServiceException {
        final List<String> accountIds = AccountGenerator.genAccounts();
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
    }
}
