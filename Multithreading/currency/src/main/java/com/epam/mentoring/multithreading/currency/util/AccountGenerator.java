package com.epam.mentoring.multithreading.currency.util;

import com.epam.mentoring.multithreading.currency.exception.ServiceException;
import com.epam.mentoring.multithreading.currency.model.Account;
import com.epam.mentoring.multithreading.currency.service.AccountService;
import com.epam.mentoring.multithreading.currency.service.impl.AccountServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Endeg on 26.08.2016.
 */
public class AccountGenerator {
    public static List<String> genAccounts() throws ServiceException {
        final List<String> CURRENCTIES = Arrays.asList("RUB", "USD", "EUR");

        final Random rnd = new Random();
        final AccountService accountService = new AccountServiceImpl();
        final List<String> accountIds = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            final Account account = new Account();
            account.setName("User" + rnd.nextInt());
            account.setCurrencies(new ArrayList<String>());
            for (String s : CURRENCTIES) {
                if (rnd.nextBoolean()) {
                    account.getCurrencies().add(s);
                }
            }

            final String id = account.getName().toUpperCase();
            accountService.save(id, account);
            accountIds.add(id);
        }
        return accountIds;
    }

}
