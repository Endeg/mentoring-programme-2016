package com.epam.mentoring.data.aggr;

/**
 * Created by Endeg on 05.11.2016.
 */
public class AverageNumberOfMessagesByDay {
    public int count;
    public String dayOfTheWeek;

    public AverageNumberOfMessagesByDay() {
    }

    public AverageNumberOfMessagesByDay(int count, String dayOfTheWeek) {
        this.count = count;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    @Override
    public String toString() {
        return "AverageNumberOfMessagesByDay{" +
                "count=" + count +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                '}';
    }
}
