package com.epam.mentoring.mvc;

import com.epam.mentoring.data.ItemsContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Endeg on 30.10.2016.
 */
@Controller
@RequestMapping("/")
public class ItemsController {
    @GetMapping
    public ModelAndView items() {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Items");
        model.put("items", ItemsContainer.ITEMS);
        return new ModelAndView("items", model);
    }
}
