package com.epam.mentoring.data.aggr;

/**
 * Created by Endeg on 05.11.2016.
 */
public class AverageNumberOfMessagesByDay {
    public int avgMsgCount;
    public int id;

    public AverageNumberOfMessagesByDay() {
    }

    public AverageNumberOfMessagesByDay(int avgMsgCount, int id) {
        this.avgMsgCount = avgMsgCount;
        this.id = id;
    }

    @Override
    public String toString() {
        return "AverageNumberOfMessagesByDay{" +
                "avgMsgCount=" + avgMsgCount +
                ", id='" + id + '\'' +
                '}';
    }
}
