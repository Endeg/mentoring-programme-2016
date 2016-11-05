package com.epam.mentoring.data.aggr;

import org.springframework.data.annotation.Id;

/**
 * Created by Endeg on 05.11.2016.
 */
public class Simple {

    @Id
    public String id;
    public int day;
    public int total;
    public int avgMsgCount;

    public Simple() {
    }

    public Simple(String id, int day, int total) {
        this.id = id;
        this.day = day;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "id='" + id + '\'' +
                ", day=" + day +
                ", total=" + total +
                '}';
    }

}
