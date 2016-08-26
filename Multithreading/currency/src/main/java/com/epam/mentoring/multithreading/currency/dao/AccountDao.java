package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.model.Account;

/**
 * Created by Endeg on 26.08.2016.
 */
public class AccountDao extends AbstractGsonReadOnlyDao<Account, String> {

    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}
