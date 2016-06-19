package com.example.matej.timeandactivityplanner.data;

import org.json.JSONObject;

/**
 * Created by Asus on 18.06.2016.
 */
public class Condition implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject object) {
        code = object.optInt("code");
        temperature = object.optInt("temp");
        description = object.optString("text");
    }
}
