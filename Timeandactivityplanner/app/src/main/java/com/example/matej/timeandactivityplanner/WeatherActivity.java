package com.example.matej.timeandactivityplanner;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matej.timeandactivityplanner.data.Channel;
import com.example.matej.timeandactivityplanner.data.Item;
import com.example.matej.timeandactivityplanner.service.WeatherServiceCallback;
import com.example.matej.timeandactivityplanner.service.YahooWeatherService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Asus on 18.06.2016.
 */
public class WeatherActivity extends ActionBarActivity implements WeatherServiceCallback {
    private ImageView weatherIcon;
    private TextView temperature;
    private TextView condition;
    private TextView location;
    private Button refresh;

    private YahooWeatherService service;
    private ProgressDialog dialog;
    private String myLocation;
    private double latitude, longitude;
    private LocationListener locationListener;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIcon = (ImageView) findViewById(R.id.WeatherIcon);
        temperature = (TextView) findViewById(R.id.Temperature);
        condition = (TextView) findViewById(R.id.Condition);
        location = (TextView) findViewById(R.id.Location);
        refresh = (Button) findViewById(R.id.Refresh);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                try {
                    findCurrentLocation(latitude, longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
            }
            return;
        }
        configureLocation();

        refresh.performClick();

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait!\nI'm finding location...");
        dialog.show();

        service.refreshWeather(myLocation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    configureLocation();
                }
        }
    }

    private boolean configureLocation() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 3600000, 0, locationListener);
            }
        });
        return false;
    }

    private void findCurrentLocation(double latitude, double longitude) throws Exception {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        myLocation = addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryCode();
    }

    @Override
    public void ConnectionSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceID = getResources().getIdentifier("drawable/weather_icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceID);

        weatherIcon.setImageDrawable(weatherIconDrawable);

        temperature.setText(item.getCondition().getTemperature() + " " + channel.getUnits().getTemperature());
        condition.setText(item.getCondition().getDescription());
        location.setText(service.getMyLocation());
    }

    @Override
    public void ConnectionFailure(Exception ex) {
        dialog.hide();
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
    }
}
