package com.example.presentation;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Calender extends Activity{

     String readDay = null;
     String str = null;
     CalendarView calendarView;
     Button BtnChange, BtnSave, BtnDelete;
     TextView diaryTXT, TView2;
     EditText contextEditTXT;

     @Override
    protected void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.calender);

         calendarView = findViewById(R.id.calenderView);
         diaryTXT = findViewById(R.id.diaryTXT);

         BtnSave = findViewById(R.id.BtnSave);
         BtnChange = findViewById(R.id.BtnChange);
         BtnDelete = findViewById(R.id.BtnDelete);

         TView2 = findViewById(R.id.TView2);
         contextEditTXT = findViewById(R.id.contextEditTXT);

         calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView view, int Year, int Month, int dayOfMonth) {
                 diaryTXT.setVisibility(View.VISIBLE);
                 BtnSave.setVisibility(View.VISIBLE);
                 contextEditTXT.setVisibility(View.VISIBLE);

                 TView2.setVisibility(View.INVISIBLE);
                 BtnChange.setVisibility(View.INVISIBLE);
                 BtnDelete.setVisibility(View.INVISIBLE);

                 diaryTXT.setText(String.format("%d / %d / %d", Year, Month+1, dayOfMonth));
                 checkDay(Year, Month, dayOfMonth);
             }
         });

         BtnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 saveDiary(readDay);
                 str = contextEditTXT.getText().toString();
                 TView2.setText(str);

                 BtnSave.setVisibility(View.INVISIBLE);
                 contextEditTXT.setVisibility(View.INVISIBLE);
                 TView2.setVisibility(View.VISIBLE);
                 BtnChange.setVisibility(View.VISIBLE);
                 BtnDelete.setVisibility(View.VISIBLE);

             }
         });

     }

    private void checkDay(int Year, int Month, int dayOfMonth) {
         readDay = "" + Year + "-" + (Month+1) + "" + "-" + dayOfMonth + ".txt";
        FileInputStream fis;

        try{
            fis = openFileInput(readDay);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);

            contextEditTXT.setVisibility(View.INVISIBLE);
            TView2.setVisibility(View.VISIBLE);
            contextEditTXT.setText(str);

            BtnSave.setVisibility(View.INVISIBLE);
            BtnChange.setVisibility(View.VISIBLE);
            BtnDelete.setVisibility(View.VISIBLE);

            BtnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    contextEditTXT.setVisibility(View.VISIBLE);
                    TView2.setVisibility(View.INVISIBLE);
                    contextEditTXT.setText(str);

                    BtnSave.setVisibility(View.VISIBLE);
                    BtnChange.setVisibility(View.INVISIBLE);
                    BtnDelete.setVisibility(View.INVISIBLE);
                    TView2.setText(contextEditTXT.getText());
                }
            });

            BtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TView2.setVisibility(View.INVISIBLE);
                    contextEditTXT.setText("");
                    contextEditTXT.setVisibility(View.VISIBLE);
                    BtnSave.setVisibility(View.VISIBLE);
                    BtnChange.setVisibility(View.INVISIBLE);
                    BtnDelete.setVisibility(View.INVISIBLE);
                    removeDiary(readDay);
                }
            });
            if(TView2.getText() == null || TView2.getText().length() == 0){
                TView2.setVisibility(View.INVISIBLE);
                diaryTXT.setVisibility(View.VISIBLE);
                BtnSave.setVisibility(View.VISIBLE);
                BtnChange.setVisibility(View.INVISIBLE);
                BtnDelete.setVisibility(View.INVISIBLE);
                contextEditTXT.setVisibility(View.VISIBLE);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    private void removeDiary(String readDay) {

         FileOutputStream fos;
         try{

             fos = openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
             String content="";
             fos.write((content).getBytes());
             fos.close();

         }catch (Exception e){
             e.printStackTrace();
         }
    }

    @SuppressLint("WrongConstant")
    private void saveDiary(String readDay) {
         FileOutputStream fos;
         try{
             fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
             String content = contextEditTXT.getText().toString();
             fos.write((content).getBytes());
             fos.close();

         }catch (Exception e){
             e.printStackTrace();
         }
    }
}
