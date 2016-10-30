package com.epam.mentoring.data;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Endeg on 30.10.2016.
 */
public class ItemsContainer {

    public static final List<Item> ITEMS = new ArrayList<>();

    static {
        final Random rnd = new Random();

        ITEMS.add(new Item(rnd.nextInt(), "PC"));
        ITEMS.add(new Item(rnd.nextInt(), "Mac"));
        ITEMS.add(new Item(rnd.nextInt(), "X-Box"));
        ITEMS.add(new Item(rnd.nextInt(), "iPhone"));
    }

    public static Item getItem(@PathVariable int itemId) {
        for (Item item : ITEMS) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

}
