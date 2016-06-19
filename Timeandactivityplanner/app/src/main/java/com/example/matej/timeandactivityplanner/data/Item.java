package com.example.matej.timeandactivityplanner.data;

import org.json.JSONObject;

/**
 * Created by Asus on 18.06.2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject object) {
        condition = new Condition();
        condition.populate(object.optJSONObject("condition"));
    }
}
