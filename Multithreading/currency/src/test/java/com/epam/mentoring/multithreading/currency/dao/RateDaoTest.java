package com.epam.mentoring.multithreading.currency.dao;

import com.epam.mentoring.multithreading.currency.exception.DaoException;
import com.epam.mentoring.multithreading.currency.model.Rate;
import com.epam.mentoring.multithreading.currency.model.RatePk;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Endeg on 26.08.2016.
 */
public class RateDaoTest {

    private final RateDao rateDao = new RateDao();

    @Test
    public void shouldReadFromCorrectFile() throws DaoException {
        //GIVEN

        //WHEN
        final Rate rate = rateDao.get(new RatePk("RUB", "EUR"));

        //THEN
        assertThat(rate, IsNull.notNullValue());
        assertThat(rate.getSourceCurrency(), is("RUB"));
        assertThat(rate.getDestCurrency(), is("EUR"));
        assertThat(rate.getValue(), is(new BigDecimal("0.24")));
    }

    @Test(expected = DaoException.class)
    public void shouldThrowExceptionWhenRateNotExists() throws DaoException {
        //GIVEN
        //WHEN
        rateDao.get(new RatePk("RUB_111", "EUR_111"));

        //THEN
        //Expecting exception
    }
}