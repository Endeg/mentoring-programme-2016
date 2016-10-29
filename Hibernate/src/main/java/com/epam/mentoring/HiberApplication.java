package com.epam.mentoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HiberApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(HiberApplication.class, args);
        context.close();
    }
}
