package com.example.matej.timeandactivityplanner;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

// this is main activity of project
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ProgressBar progress = (ProgressBar)findViewById(R.id.progress_bar);
        progress.getIndeterminateDrawable().setColorFilter(0xFF9900, PorterDuff.Mode.SRC_IN);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, ChooseActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }
}



