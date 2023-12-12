package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityLoginOrRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));
    }
}