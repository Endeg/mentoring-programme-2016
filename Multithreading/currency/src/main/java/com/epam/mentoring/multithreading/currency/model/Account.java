package com.epam.mentoring.multithreading.currency.model;

import java.util.List;

/**
 * Created by Endeg on 26.08.2016.
 */
public class Account {

    private String name;
    private List<String> currencies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }
}
