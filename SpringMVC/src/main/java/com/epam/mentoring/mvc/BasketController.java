package com.epam.mentoring.mvc;

import com.epam.mentoring.component.Basket;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/basket")
public class BasketController {

    private final Basket basket;

    @Autowired
    public BasketController(Basket basket) {
        this.basket = basket;
    }

    @GetMapping
    public ModelAndView basket() {
        Map<String, Object> model = new HashMap<>();
        model.put("basketObject", basket);
        return new ModelAndView("basket", model);
    }
}
