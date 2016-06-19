package com.example.matej.timeandactivityplanner;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogMethodActivity extends AppCompatActivity {

    private Button Subbut;

    public LogMethodActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_method);
        addListenerOnButton3();
        addFbFragment();

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addListenerOnButton3() {
        Subbut = (Button) findViewById(R.id.subbut);
        Subbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mm = new Intent(LogMethodActivity.this, WithoutFBOptions.class);
                startActivity(mm);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void addFbFragment() {
        Fragment newFragment = new FBLoginFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentis, newFragment);
        transaction.commit();
    }

}