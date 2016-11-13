package com.epam.mentoring.data;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Endeg on 13.11.2016.
 */
public class FriendRequest {

    @Id
    public String id;

    public String userName;

    public Date requestDate;

    public FriendRequest() {
    }

    public FriendRequest(String id, String userName, Date requestDate) {
        this.id = id;
        this.userName = userName;
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }
}
