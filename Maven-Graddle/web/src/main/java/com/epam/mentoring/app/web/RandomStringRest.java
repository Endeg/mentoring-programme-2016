package com.epam.mentoring.app.web;

/**
 * Created by Endeg on 15.10.2016.
 */

import com.epam.mentoring.app.business.RandomStringService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RandomStringRest {

    private final RandomStringService service = new RandomStringService();

    @RequestMapping("/strings")
    public List<String> getRandomStrings() {
        return service.getStrings(16);
    }
}
