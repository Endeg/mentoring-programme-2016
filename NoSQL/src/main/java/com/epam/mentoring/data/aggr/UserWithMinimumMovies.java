package com.epam.mentoring.data.aggr;

/**
 * Created by Endeg on 13.11.2016.
 */
public class UserWithMinimumMovies {

    public String id;
    public int moviesWatched;
    public int friendsCount;

    public UserWithMinimumMovies() {
    }

    public UserWithMinimumMovies(String id, int moviesWatched, int friendsCount) {
        this.id = id;
        this.moviesWatched = moviesWatched;
        this.friendsCount = friendsCount;
    }

    @Override
    public String toString() {
        return "UserWithMinimumMovies{" +
                "id='" + id + '\'' +
                ", moviesWatched=" + moviesWatched +
                ", friendsCount=" + friendsCount +
                '}';
    }
}
