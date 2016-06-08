package com.example.matej.timeandactivityplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// this is menu where u can choose activity
public class ChooseActivity extends AppCompatActivity {

    private Button btnCalendar;
    private Button btnWeather;
    private Button btnProfile;
    private Button btnActivities;
    private Boolean exit = false;

    public ChooseActivity(){

    }
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


        btnActivities = (Button) findViewById(R.id.btnActivities);
        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, WarningAlarmActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(exit){
            System.exit(0);
        }
        else {
            Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
            exit = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run(){
                    exit = false;
                }
            }, 3000);
        }
    }
}

