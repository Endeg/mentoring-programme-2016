package com.epam.mentoring;

import com.epam.mentoring.data.User;
import com.epam.mentoring.data.aggr.Simple;
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

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
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

        context.close();
    }

    private static void showMessagesByDayOfTheWeek(ConfigurableApplicationContext context) {
        final MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
        final TypedAggregation<User> aggregation = Aggregation.newAggregation(User.class,
                unwind("messages"),
                project("id", "messages.date").andExpression("dayOfWeek(messages.date)").as("day"),
                limit(7));

        final AggregationResults<Simple> aggregated = mongoTemplate.aggregate(aggregation, Simple.class);
        for (Simple aggr : aggregated) {
            LOG.info("Average: {}", aggr);
        }

//        final AggregationResults<AverageNumberOfMessagesByDay> aggregated = mongoTemplate.aggregate(aggregation, AverageNumberOfMessagesByDay.class);
//        for (AverageNumberOfMessagesByDay aggr : aggregated) {
//            LOG.info("Average: {}", aggr);
//        }
    }
}
