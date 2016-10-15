package com.epam.mentoring.app.business;

import com.epam.mentoring.app.dao.RandomStringsDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Endeg on 15.10.2016.
 */
public class RandomStringService {

    private final RandomStringsDao dao;

    public RandomStringService() {
        this.dao = new RandomStringsDao();
    }

    public List<String> getStrings(int count) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            result.add(dao.get());
        }

        return result;
    }

}
