package com.epam.mentoring;

import com.epam.mentoring.data.FriendRequest;
import com.epam.mentoring.data.User;
import com.epam.mentoring.data.aggr.AverageNumberOfMessagesByDay;
import com.epam.mentoring.data.aggr.FriendshipPerMonth;
import com.epam.mentoring.data.aggr.UserWithMinimumMovies;
import com.epam.mentoring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

@SpringBootApplication
public class NosqlApplication {

    private static final Logger LOG = LoggerFactory.getLogger(NosqlApplication.class);

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(NosqlApplication.class, args);
        final UserRepository userRepository = context.getBean(UserRepository.class);

        final MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

        showMessagesByDayOfTheWeek(mongoTemplate);
        showMaxNumberOfFriendshipsFromMonthToMonth(mongoTemplate);
        showUsersWithMoreThan100Friends(mongoTemplate, userRepository);

        context.close();
    }

    private static void showUsersWithMoreThan100Friends(MongoTemplate mongoTemplate, UserRepository userRepository) {
        final TypedAggregation<User> aggregation = Aggregation.newAggregation(User.class,
                project("id", "moviesWatched").and("friendRequests").size().as("friendsCount"),
                match(Criteria.where("friendsCount").gt(100)),
                sort(Sort.Direction.DESC, "moviesWatched"),
                limit(1));

        final AggregationResults<UserWithMinimumMovies> aggregated = mongoTemplate.aggregate(aggregation, UserWithMinimumMovies.class);

        for (UserWithMinimumMovies aggr : aggregated) {
            LOG.info("aggr: {}", aggr);

            final User user = userRepository.findOne(aggr.id);

            LOG.info("User with more that 100 friends and minimum movies: {}", user);
        }
    }

    private static void showMaxNumberOfFriendshipsFromMonthToMonth(MongoTemplate mongoTemplate) {

        final TypedAggregation<FriendRequest> aggregation = Aggregation.newAggregation(FriendRequest.class,
                project("id").andExpression("month(requestDate)").as("month").andExpression("year(requestDate)").as("year"),
                group("year", "month").count().as("requestCount"));

        final AggregationResults<FriendshipPerMonth> aggregated = mongoTemplate.aggregate(aggregation, FriendshipPerMonth.class);

        for (FriendshipPerMonth aggr : aggregated) {
            LOG.info("Friendship per month: {}", aggr);
        }
    }

    private static void showMessagesByDayOfTheWeek(MongoTemplate mongoTemplate) {
        final TypedAggregation<User> aggregation = Aggregation.newAggregation(User.class,
                unwind("messages"),
                project("id").andExpression("dayOfWeek(messages.date)").as("day"),
                group("id", "day").count().as("total"),
                group("day").avg("total").as("avgMsgCount"));

        final AggregationResults<AverageNumberOfMessagesByDay> aggregated = mongoTemplate.aggregate(aggregation, AverageNumberOfMessagesByDay.class);
        for (AverageNumberOfMessagesByDay aggr : aggregated) {
            LOG.info("Day of week {} - average number of messages = {}", aggr.id, aggr.avgMsgCount);
        }
    }
}
