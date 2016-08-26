package com.epam.mentoring.multithreading.currency.service;

import com.epam.mentoring.multithreading.currency.model.Currency;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface CurrencyService extends ReadInterface<Currency, String>, WriteInterface<Currency, String> {
}
