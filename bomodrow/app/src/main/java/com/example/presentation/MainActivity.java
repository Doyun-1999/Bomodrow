package com.example.presentation;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.Notification;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        tabHost.setup(getLocalActivityManager());
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, Timer.class);
        spec = tabHost.newTabSpec("TIMER").setIndicator("타이머").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Pomodoro.class);
        spec = tabHost.newTabSpec("POMODORO").setIndicator("뽀모도로").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, perHour.class);
        spec = tabHost.newTabSpec("HOUR").setIndicator("1시간").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Calender.class);
        spec = tabHost.newTabSpec("CALENDER").setIndicator("달력").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this,MenuDiction.class);
        spec = tabHost.newTabSpec("DICTIONARY").setIndicator("사전").setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

    }

}