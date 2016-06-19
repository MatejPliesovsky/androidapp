package com.example.matej.timeandactivityplanner.data;

import org.json.JSONObject;

/**
 * Created by Asus on 18.06.2016.
 */
public class Channel implements JSONPopulator {
    private Units units;
    private Item item;

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void populate(JSONObject object) {
        units = new Units();
        units.populate(object.optJSONObject("units"));

        item = new Item();
        item.populate(object.optJSONObject("item"));
    }
}
