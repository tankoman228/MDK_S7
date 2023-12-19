package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityLoginOrRegister extends AppCompatActivity {

    EditText etEmail;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));

        etEmail = findViewById(R.id.etMail);
        btnEnter = findViewById(R.id.btnMailNext);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //check_email(s.toString());
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check_email(s.toString());
                //btnEnter.setEnabled(true);
            }
            @Override
            public void afterTextChanged(Editable s) {
                check_email(s.toString());
            }
        });

        btnEnter.setOnClickListener(l -> {
            new Thread(() -> {
                ActivityLoginOrRegister.this.runOnUiThread(()-> {
                    btnEnter.setEnabled(false);
                });

                try {
                    Thread.sleep(700);

                    ActivityLoginOrRegister.this.runOnUiThread(()-> {
                        ActivityLoginOrRegister.this.startActivity(new Intent(ActivityLoginOrRegister.this, ActivityEmailCode.class));
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ActivityLoginOrRegister.this.runOnUiThread(()-> {
                    btnEnter.setEnabled(true);
                });
            }).start();
        });
    }

    Pattern email_pattern = Pattern.compile(".+@.+\\.[a-z]{2,4}");
    private void check_email(String s) {

        Matcher mat = email_pattern.matcher(s);

        if(mat.matches())
            btnEnter.setEnabled(true);
        else
            btnEnter.setEnabled(false);
    }
}