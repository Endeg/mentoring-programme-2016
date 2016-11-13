package com.epam.mentoring;

import com.epam.mentoring.data.FriendRequest;
import com.epam.mentoring.data.User;
import com.epam.mentoring.data.aggr.AverageNumberOfMessagesByDay;
import com.epam.mentoring.data.aggr.FriendshipPerMonth;
import com.epam.mentoring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

@SpringBootApplication
public class NosqlApplication {

    private static final Logger LOG = LoggerFactory.getLogger(NosqlApplication.class);

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(NosqlApplication.class, args);
        final UserRepository userRepository = context.getBean(UserRepository.class);

        final List<User> all = userRepository.findAll();

//        for (User user : all) {
//            LOG.info("user = {}", user);
//        }

        showMessagesByDayOfTheWeek(context);
        showMaxNumberOfFriendshipsFromMonthToMonth(context);

        context.close();
    }

    private static void showMaxNumberOfFriendshipsFromMonthToMonth(ConfigurableApplicationContext context) {
        final MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

        final TypedAggregation<FriendRequest> aggregation = Aggregation.newAggregation(FriendRequest.class,
                project("id").andExpression("month(requestDate)").as("month").andExpression("year(requestDate)").as("year"),
                group("year", "month").count().as("requestCount"));

        final AggregationResults<FriendshipPerMonth> aggregated = mongoTemplate.aggregate(aggregation, FriendshipPerMonth.class);

        for (FriendshipPerMonth aggr : aggregated) {
            LOG.info("Friendship per month: {}", aggr);
        }
    }

    private static void showMessagesByDayOfTheWeek(ConfigurableApplicationContext context) {
        final MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
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
