package com.number7.udemystopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean isRunning = false;
    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        runTime();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
    }

    public void onMainClickStartTimer(View view) {
        isRunning = true;
    }

    public void onMainClickStopTimer(View view) {
        isRunning = false;
    }

    public void onMainClickResetTimer(View view) {
        isRunning = false;
        seconds = 0;
    }

    // run times
    private  void runTime(){
        final Handler handler = new Handler();
        // run code
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // string for textViewTimer
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours,minutes,secs);

                textViewTimer = findViewById(R.id.textViewMainTimer);
                textViewTimer.setText(time);

                if(isRunning){
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

    }
}
