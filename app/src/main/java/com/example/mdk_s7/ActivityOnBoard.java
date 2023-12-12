package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class ActivityOnBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acivity_onboard);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));
    }
}