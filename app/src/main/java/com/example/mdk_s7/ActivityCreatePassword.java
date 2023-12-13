package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

public class ActivityCreatePassword extends AppCompatActivity {

    RadioButton[] balls;
    int[] digits = new int[] {-1, -1, -1, -1};
    int digits_i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));

        findViewById(R.id.btnSkipPassword).setOnClickListener(l -> {
            startActivity(new Intent(this, ActivityCreateCard.class));
        });


        balls = new RadioButton[] {findViewById(R.id.ball1), findViewById(R.id.ball2), findViewById(R.id.ball3), findViewById(R.id.ball4)};

        findViewById(R.id.btn0).setOnClickListener(l -> input(0));
        findViewById(R.id.btn1).setOnClickListener(l -> input(1));
        findViewById(R.id.btn2).setOnClickListener(l -> input(2));
        findViewById(R.id.btn3).setOnClickListener(l -> input(3));
        findViewById(R.id.btn4).setOnClickListener(l -> input(4));
        findViewById(R.id.btn5).setOnClickListener(l -> input(5));
        findViewById(R.id.btn6).setOnClickListener(l -> input(6));
        findViewById(R.id.btn7).setOnClickListener(l -> input(7));
        findViewById(R.id.btn8).setOnClickListener(l -> input(8));
        findViewById(R.id.btn9).setOnClickListener(l -> input(9));

        findViewById(R.id.btnDelete).setOnClickListener(l -> {
            digits_i--;
            if (digits_i < 0)
                digits_i = 0;
            update_balls();
        });
    }

    void input(int digit) {

        if (digits_i == 4)
            return;

        digits[digits_i] = digit;
        digits_i++;
        update_balls();
    }

    void update_balls() {

        for (int i = 0; i < digits_i; i++) {
            balls[i].setChecked(true);
        }
        for (int i = digits_i; i < 4; i++) {
            balls[i].setChecked(false);
        }
    }
}