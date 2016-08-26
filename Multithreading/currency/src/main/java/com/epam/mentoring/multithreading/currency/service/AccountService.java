package com.epam.mentoring.multithreading.currency.service;

import com.epam.mentoring.multithreading.currency.model.Account;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface AccountService extends ReadInterface<Account, String>, WriteInterface<Account, String> {
}
