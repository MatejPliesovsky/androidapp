package com.example.matej.timeandactivityplanner.service;

import com.example.matej.timeandactivityplanner.data.Channel;

/**
 * Created by Asus on 18.06.2016.
 */
public interface WeatherServiceCallback {
    void ConnectionSuccess(Channel channel);
    void ConnectionFailure(Exception ex);
}
