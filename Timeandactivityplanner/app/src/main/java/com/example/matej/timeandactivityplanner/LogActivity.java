package com.example.matej.timeandactivityplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
// from this method u can login in and login out
public class LogActivity extends AppCompatActivity {

    private Button Subbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_log);
        addListenerOnButton3();


        TextView reg = (TextView) findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent nxt = new Intent(LogActivity.this, RegActivity.class);
                startActivity(nxt);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void addListenerOnButton3() {
        Subbut = (Button) findViewById(R.id.subbut);
        Subbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mm = new Intent(LogActivity.this,ChooseActivity.class);
                startActivity(mm);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
