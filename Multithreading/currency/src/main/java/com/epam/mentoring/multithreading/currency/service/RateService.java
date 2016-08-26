package com.epam.mentoring.multithreading.currency.service;

import com.epam.mentoring.multithreading.currency.model.Rate;
import com.epam.mentoring.multithreading.currency.model.RatePk;

/**
 * Created by Endeg on 26.08.2016.
 */
public interface RateService extends ReadInterface<Rate, RatePk>, WriteInterface<Rate, RatePk> {
}
