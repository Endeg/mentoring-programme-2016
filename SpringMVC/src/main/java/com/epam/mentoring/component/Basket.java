package com.epam.mentoring.component;

import com.epam.mentoring.data.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Endeg on 30.10.2016.
 */
@Component
@SessionScope
public class Basket {
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
