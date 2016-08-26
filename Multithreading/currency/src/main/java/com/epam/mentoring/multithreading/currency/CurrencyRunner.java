package com.epam.mentoring.multithreading.currency;

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
public class CurrencyRunner {

    public static void main(String[] args) {

        final List<String> CURRENCTIES = Arrays.asList("RUB", "USD", "EUR");

        final Random rnd = new Random();
        final AccountService accountService = new AccountServiceImpl();

        for (int i = 0; i < 20; i++) {

            final Account account = new Account();
            account.setName("User" + rnd.nextInt());
            account.setCurrencies(new ArrayList<String>());
            for (String s : CURRENCTIES) {
                if (rnd.nextBoolean()) {
                    account.getCurrencies().add(s);
                }
            }

            accountService.save(account.getName().toUpperCase(), account);
        }
    }

}
