package com.epam.mentoring.repository;

import com.epam.mentoring.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by Endeg on 05.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateSomeUserAndFindIt() {
        //GIVEN
        final User user = new User(UUID.randomUUID().toString(), "Tommy", "Wiseau");
        userRepository.save(user);

        //WHEN

        final Collection<User> foundUsers = userRepository.findByLastName("Wiseau");

        //THEN
        assertThat(foundUsers, hasSize(1));
        assertThat(foundUsers.iterator().next(), is(user));
    }

}