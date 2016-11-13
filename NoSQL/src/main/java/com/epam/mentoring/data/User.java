package com.epam.mentoring.data;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Endeg on 05.11.2016.
 */
public class User {

    @Id
    public String id;

    public String firstName;

    public String lastName;

    public List<Message> messages;

    public List<FriendRequest> friendRequests;

    public User() {
    }

    public User(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", messages=" + messages +
                ", friendRequests=" + friendRequests +
                '}';
    }
}
