package com.epam.mentoring.multithreading.currency.model;

import java.math.BigDecimal;

/**
 * Created by Endeg on 26.08.2016.
 */
public class Rate {
    private String sourceCurrency;
    private String destCurrency;
    private BigDecimal value;

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getDestCurrency() {
        return destCurrency;
    }

    public void setDestCurrency(String destCurrency) {
        this.destCurrency = destCurrency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
