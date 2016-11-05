package com.epam.mentoring.data.aggr;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Endeg on 05.11.2016.
 */
public class Simple {

    @Id
    public String id;
    public Date date;
    public int day;

    public Simple() {
    }

    public Simple(String id, Date date, int day) {
        this.id = id;
        this.date = date;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", day=" + day +
                '}';
    }
}
