package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.epam.mentoring.multithreading.currency.model.Account;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Endeg on 26.08.2016.
 */
public class AccountDaoTest {

    private final AccountDao accountDao = new AccountDao();

    @Test
    public void shouldReadCorrectFile() throws DaoException {
        //GIVEN
        //WHEN
        final Account john = accountDao.get("JOHN");

        //THEN
        assertThat(john, IsNull.notNullValue());
        assertThat(john.getName(), is("John"));
        assertThat(john.getCurrencies(), is(Arrays.asList("RUB", "USD", "EUR")));
    }


    @Test(expected = DaoException.class)
    public void shouldThrowExceptionWhenAccountNotExists() throws DaoException {
        //GIVEN
        //WHEN
        accountDao.get("THOMAS_111");

        //THEN
        //Expecting exception
    }
}