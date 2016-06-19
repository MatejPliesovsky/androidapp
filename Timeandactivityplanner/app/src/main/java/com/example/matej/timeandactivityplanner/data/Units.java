package com.example.matej.timeandactivityplanner.data;

import org.json.JSONObject;

/**
 * Created by Asus on 18.06.2016.
 */
public class Units implements JSONPopulator {
    private String temperature;
    private double fahrenheit;
    private double celzius;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject object) {
        temperature = object.optString("temperature");
        fahrenheit = Double.parseDouble(temperature);
        celzius = (5.0/9) * (fahrenheit - 32);
        temperature = Double.toString(celzius);
    }
}
