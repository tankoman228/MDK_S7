package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityCreateCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));
    }
}