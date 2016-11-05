package com.epam.mentoring.data;

import java.util.Date;

/**
 * Created by Endeg on 05.11.2016.
 */
public class Message {

    public Date date;
    public String title;
    public String contents;

    public Message() {
    }

    public Message(Date date, String title, String contents) {
        this.date = date;
        this.title = title;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Message{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
