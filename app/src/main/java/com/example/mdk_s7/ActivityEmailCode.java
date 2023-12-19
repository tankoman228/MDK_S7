package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityEmailCode extends AppCompatActivity {

    EditText[] et;
    TextView tvSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_code);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));
        resend_wait_thread_start();

        tvSeconds = findViewById(R.id.tvResendTime);

        findViewById(R.id.ibtnBack).setOnClickListener(l -> {
            startActivity(new Intent(this, ActivityLoginOrRegister.class));
        });

        et = new EditText[] {findViewById(R.id.tvEmailCode1), findViewById(R.id.tvEmailCode2), findViewById(R.id.tvEmailCode3), findViewById(R.id.tvEmailCode4)};
        et[0].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    et[1].requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        et[1].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    et[2].requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        et[2].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    et[3].requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        et[3].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {

                    int code = Integer.valueOf(et[0].getText().toString() + et[1].getText().toString() + et[2].getText().toString() + et[3].getText().toString());

                    if (code % 2 == 1) {
                        startActivity(new Intent(ActivityEmailCode.this, ActivityCreatePassword.class));
                    }
                    else {
                        et[0].setEnabled(false); et[0].setText("");
                        et[1].setEnabled(false); et[1].setText("");
                        et[2].setEnabled(false); et[2].setText("");
                        et[3].setEnabled(false); et[3].setText("");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    void resend_wait_thread_start() {
        new Thread(() -> {

            while (true) {
                //TODO: убрать это, когда будет готова кнопка переотправки кода

                for (int seconds = 60; seconds > 0; seconds--) {
                    try {
                        Thread.sleep(1000);

                        int finalSeconds = seconds;
                        ActivityEmailCode.this.runOnUiThread(() -> {
                            String s = " " + finalSeconds + " ";
                            tvSeconds.setText(getString(R.string.RepeatCode) + s + getString(R.string.seconds));
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                ActivityEmailCode.this.runOnUiThread(() -> {
                    et[0].setEnabled(true);
                    et[1].setEnabled(true);
                    et[2].setEnabled(true);
                    et[3].setEnabled(true);
                });
            }
        }).start();
    }
}