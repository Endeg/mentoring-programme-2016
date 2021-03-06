package com.epam.mentoring.mvc;

import com.epam.mentoring.component.Basket;
import com.epam.mentoring.data.Item;
import com.epam.mentoring.data.ItemsContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("remove/{itemId}")
    public ModelAndView basket(@PathVariable int itemId) {
        final Item item = ItemsContainer.getItem(itemId);
        basket.getItems().remove(item);
        Map<String, Object> model = new HashMap<>();
        model.put("basketObject", basket);
        return new ModelAndView("basket", model);
    }
}
