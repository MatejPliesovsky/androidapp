package com.example.matej.timeandactivityplanner.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.matej.timeandactivityplanner.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Asus on 18.06.2016.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String myLocation;
    private Exception ex;

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getMyLocation() {
        return myLocation;
    }

    public void refreshWeather(String location){
        this.myLocation = location;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", params[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    ex = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && ex != null){
                    callback.ConnectionFailure(ex);
                    return;
                }

                try {
                    JSONObject object = new JSONObject(s);

                    JSONObject queryResults = object.optJSONObject("query");
                    int count = queryResults.optInt("count");

                    if (count == 0){
                        callback.ConnectionFailure(new LocationWeatherException("No weather information found for " + myLocation));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    callback.ConnectionSuccess(channel);
                } catch (JSONException e) {
                    callback.ConnectionFailure(e);
                }
            }
        }.execute(location);
    }

    public class LocationWeatherException extends Exception {
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}
