package com.epam.mentoring.data.aggr;

/**
 * Created by Endeg on 13.11.2016.
 */
public class FriendshipPerMonth {
    public int requestCount;
    public int month;
    public int year;

    public FriendshipPerMonth() {
    }

    public FriendshipPerMonth(int requestCount, int month, int year) {
        this.requestCount = requestCount;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return "FriendshipPerMonth{" +
                "requestCount=" + requestCount +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
