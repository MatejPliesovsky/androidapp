package com.example.matej.timeandactivityplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// this is menu where u can choose activity
public class ChooseActivity extends AppCompatActivity {

    private Button btnCalendar;
    private Button btnWeather;
    private Button btnProfile;
    private Button btnActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);


        btnWeather = (Button) findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(ChooseActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });


        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, Calendar.class);
                startActivity(intent);
            }
        });

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnActivities = (Button) findViewById(R.id.btnActivities);
        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, WarningAlarmActivity.class);
                startActivity(intent);
            }
        });
    }
}

