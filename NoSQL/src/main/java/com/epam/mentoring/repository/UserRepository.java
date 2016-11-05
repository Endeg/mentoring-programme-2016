package com.epam.mentoring.repository;

import com.epam.mentoring.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

/**
 * Created by Endeg on 05.11.2016.
 */
public interface UserRepository extends MongoRepository<User, String> {

    Collection<User> findByFirstName(String firstName);

    Collection<User> findByLastName(String lastName);
}
