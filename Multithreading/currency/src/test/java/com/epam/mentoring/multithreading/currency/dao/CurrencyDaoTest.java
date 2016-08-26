package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.epam.mentoring.multithreading.currency.model.Currency;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Endeg on 26.08.2016.
 */
public class CurrencyDaoTest {

    private final CurrencyDao currencyDao = new CurrencyDao();

    @Test
    public void shouldReadCorrectFile() throws DaoException {
        //GIVEN
        //WHEN
        final Currency rub = currencyDao.get("RUB");

        //THEN
        assertThat(rub, IsNull.notNullValue());
        assertThat(rub.getName(), is("Russian Ruble"));
    }

    @Test(expected = DaoException.class)
    public void shouldThrowExceptionWhenCurrencyNotExists() throws DaoException {
        //GIVEN
        //WHEN
        currencyDao.get("RUB_111");

        //THEN
        //Expecting exception
    }

}