package com.example.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class perHour extends Activity{
    private boolean isStudyTime;
    private boolean isTimerRunning;

    Button BtnPerHour;
    TextView perHourTXT;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.per_hour);

        isStudyTime = false;
        isTimerRunning = false;

        BtnPerHour = findViewById(R.id.BtnPerHour);
        perHourTXT = findViewById(R.id.perHourTXT);

        BtnPerHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){
                    case R.id.BtnPerHour:
                        if(!isTimerRunning) {
                            isStudyTime = true;
                            BtnPerHour.setText("40+20 타이머 중지");

                            setCountDownTimerInit(2400000);

                            /*
                            for(int i = 0; i <= iLoop; i++){
                                if (isStudyTime) {
                                    isStudyTime = false;
                                    setStopCountDownTimer();
                                    setCountDownTimerInit(3000);
                                    Toast.makeText(Pomodoro.this, "공부 시간이 종료되었습니다. \n 5분의 휴식시간을 가지십시오.", Toast.LENGTH_SHORT).show();
                                } else{
                                    isStudyTime = true;
                                    setStopCountDownTimer();
                                    setCountDownTimerInit(15000);
                                    Toast.makeText(Pomodoro.this, "휴식시간이 종료되었습니다. \n 25분동안 공부에 집중하십시오.", Toast.LENGTH_SHORT).show();
                                }
                                int ILoop = iLoop - i;
                                String TLoop = String.valueOf(ILoop);
                                LoopTXT.setText("반복 : " + TLoop);
                            }
                             */

                        }else{
                            BtnPerHour.setText("40+20 타이머 시작");
                            setStopCountDownTimer();

                        }
                        break;
                }
            }
        });
    }

    private void setStopCountDownTimer() {
        countDownTimer.cancel();
        countDownTimer = null;
        isTimerRunning = false;
        perHourTXT.setText("00:00:00");
    }

    private void setCountDownTimerInit(long settingMillis) {
        isTimerRunning = true;
        countDownTimer = new CountDownTimer(settingMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String hour = String.format(Locale.getDefault(), "%02d", (int) millisUntilFinished / 3600000);
                String min = String.format(Locale.getDefault(), "%02d", (int) millisUntilFinished % 3600000 / 60000);
                String sec = String.format(Locale.getDefault(), "%02d", (int) (millisUntilFinished % 60000 / 1000));

                perHourTXT.setText(hour + ":" + min + ":" + sec);
            }

            @Override
            public void onFinish() {
                if (isStudyTime) {
                    isStudyTime = false;
                    setStopCountDownTimer();
                    setCountDownTimerInit(1200000);
                    Toast.makeText(perHour.this, "공부 시간이 종료되었습니다. \n 20분의 휴식시간을 가지십시오.", Toast.LENGTH_SHORT).show();
                } else{
                    isStudyTime = true;
                    setStopCountDownTimer();
                    setCountDownTimerInit(2400000);
                    Toast.makeText(perHour.this, "휴식시간이 종료되었습니다. \n 40분동안 공부에 집중하십시오.", Toast.LENGTH_SHORT).show();
                }

            }
        }.start();
    }
}