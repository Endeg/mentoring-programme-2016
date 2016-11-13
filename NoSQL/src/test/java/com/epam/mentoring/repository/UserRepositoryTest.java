package com.epam.mentoring.repository;

import com.epam.mentoring.data.FriendRequest;
import com.epam.mentoring.data.Message;
import com.epam.mentoring.data.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryTest.class);

    private final Random rnd = new Random();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestReposiotry friendRequestReposiotry;

    @Ignore
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

    @Ignore
    @Test
    public void shouldRemoveAllEntries() {
        LOG.info("Removing all collections...");
        userRepository.delete(userRepository.findAll());
        friendRequestReposiotry.delete(friendRequestReposiotry.findAll());
        LOG.info("Done.");
    }

    @Test
    public void shouldPopulateDatabaseWithLotsOfData() {
        shouldRemoveAllEntries();

        for (int i = 0; i < 1000 + rnd.nextInt(500); i++) {
            final User user = new User(UUID.randomUUID().toString(), "Name" + rnd.nextInt(), "LastName" + rnd.nextInt());
            user.messages = new ArrayList<>();
            for (int j = 0; j < 1000 + rnd.nextInt(500); j++) {

                Date date = randomDay();

                user.messages.add(new Message(date, "Title" + rnd.nextInt(), "Contents" + rnd.nextInt()));

            }

            user.moviesWatched = rnd.nextInt(500);

            for (int j = 0; j < 90 + rnd.nextInt(100); j++) {
                final FriendRequest friendRequest = friendRequestReposiotry.save(
                        new FriendRequest(UUID.randomUUID().toString(), "Name" + rnd.nextInt(), randomDay()));
                if (user.friendRequests == null) {
                    user.friendRequests = new ArrayList<>();
                }
                user.friendRequests.add(friendRequest);
            }

            userRepository.save(user);
        }
    }

    private Date randomDay() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, rnd.nextInt(36) + 1980);
        cal.set(Calendar.MONTH, rnd.nextInt(12));
        cal.set(Calendar.DAY_OF_MONTH, rnd.nextInt(27) + 1);
        return cal.getTime();
    }

}