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

public class Pomodoro extends Activity{
    private boolean isStudyTime;
    private boolean isTimerRunning;

    Button BtnPomo;
    TextView PomoTXT;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pomodoro);

        isStudyTime = false;
        isTimerRunning = false;

        BtnPomo = findViewById(R.id.BtnPomo);
        PomoTXT = findViewById(R.id.PomoTXT);

        BtnPomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){
                    case R.id.BtnPomo:
                        if(!isTimerRunning) {
                            isStudyTime = true;
                            BtnPomo.setText("뽀모도로 타이머 중지");

                            setCountDownTimerInit(1500000);

                        }else{
                            BtnPomo.setText("뽀모도로 타이머 시작");
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
        PomoTXT.setText("00:00:00");
    }

    private void setCountDownTimerInit(long settingMillis) {
        isTimerRunning = true;
        countDownTimer = new CountDownTimer(settingMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String hour = String.format(Locale.getDefault(), "%02d", (int) millisUntilFinished / 3600000);
                String min = String.format(Locale.getDefault(), "%02d", (int) millisUntilFinished % 3600000 / 60000);
                String sec = String.format(Locale.getDefault(), "%02d", (int) (millisUntilFinished % 60000 / 1000));

                PomoTXT.setText(hour + ":" + min + ":" + sec);
            }

            @Override
            public void onFinish() {
                if (isStudyTime) {
                    isStudyTime = false;
                    setStopCountDownTimer();
                    setCountDownTimerInit(300000);
                    Toast.makeText(Pomodoro.this, "공부 시간이 종료되었습니다. \n 5분의 휴식시간을 가지십시오.", Toast.LENGTH_SHORT).show();
                } else{
                    isStudyTime = true;
                    setStopCountDownTimer();
                    setCountDownTimerInit(1500000);
                    Toast.makeText(Pomodoro.this, "휴식시간이 종료되었습니다. \n 25분동안 공부에 집중하십시오.", Toast.LENGTH_SHORT).show();
                }

            }
        }.start();
    }
}
