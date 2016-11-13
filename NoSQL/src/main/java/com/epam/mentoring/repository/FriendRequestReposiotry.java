package com.epam.mentoring.repository;

import com.epam.mentoring.data.FriendRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Endeg on 13.11.2016.
 */
public interface FriendRequestReposiotry extends MongoRepository<FriendRequest, String> {
}
