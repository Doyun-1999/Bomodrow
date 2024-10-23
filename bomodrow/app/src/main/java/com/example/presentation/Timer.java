package com.example.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Timer extends Activity{

    TextView countdownTXT;
    Button StartBtn, PauseBtn, CancelBtn;
    EditText hourTXT, minTXT, secTXT;

    CountDownTimer countDownTimer;

    private boolean timerRunning, firstState;
    private long time = 0;
    private long temptime = 0;

    FrameLayout timerSet, timerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        countdownTXT = findViewById(R.id.countdownTXT);

        StartBtn = findViewById(R.id.startBtn);
        PauseBtn = findViewById(R.id.pauseBtn);
        CancelBtn = findViewById(R.id.cancelBtn);

        hourTXT = findViewById(R.id.hour);
        minTXT = findViewById(R.id.min);
        secTXT = findViewById(R.id.sec);

        timerSet = findViewById(R.id.timerSet);
        timerEnd = findViewById(R.id.timerEnd);

        timerSet.setVisibility(View.VISIBLE);
        timerEnd.setVisibility(View.GONE);

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstState = true;

                timerSet.setVisibility(View.GONE); //설정이 사라짐
                timerEnd.setVisibility(View.VISIBLE);
                startStop();
            }
        });

        PauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerSet.setVisibility(View.VISIBLE);
                timerEnd.setVisibility(View.GONE);
                firstState = true;
                pauseTimer();
            }
        });

        updateTimer();
    }

    private void startStop(){
        if(timerRunning){
            pauseTimer();
        }else{
            startTimer();
        }
    }

    private void startTimer() {

        //처음이면 타이머 값을 설정한다
        if(firstState){
            String sHour = hourTXT.getText().toString();
            String sMin = minTXT.getText().toString();
            String sSec = secTXT.getText().toString();

            if (hourTXT.getText().toString().length() == 0){
                sHour = "0";
            }
            if (minTXT.getText().toString().length() == 0){
                sMin = "0";
            }
            if (secTXT.getText().toString().length() == 0){
                sSec = "0";
            }


            time = (Long.parseLong(sHour)*3600000) + (Long.parseLong(sMin)*60000) + (Long.parseLong(sSec)*1000) + 1000;
        }else{
            time = temptime;
        }

        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                temptime = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Toast.makeText(Timer.this, "타이머가 종료되었습니다", Toast.LENGTH_SHORT).show();

            }
        }.start();

        PauseBtn.setText("일시정지");
        timerRunning = true;
        firstState = false;
    }

    //타이머정지
    private void pauseTimer(){
        countDownTimer.cancel();
        timerRunning=false;
        PauseBtn.setText("계속");
    }

    private void updateTimer(){
        int h = (int) temptime / 3600000;
        int m = (int) temptime % 3600000 / 60000;
        int s = (int) temptime % 3600000 % 60000 / 1000;

        String timeLeftText = "";
        timeLeftText = "" + h + ":";

        if(m < 10) timeLeftText += "0";
        timeLeftText += m + ":";

        if(s < 10) timeLeftText += "0";
        timeLeftText += s;

        countdownTXT.setText(timeLeftText);
    }
}
