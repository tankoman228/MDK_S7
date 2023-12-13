package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityCreateCard extends AppCompatActivity {

    EditText[] ets;
    Spinner sp;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));

        btn = findViewById(R.id.btnCreateCard);
        sp = findViewById(R.id.spGender);



        ets = new EditText[] {
                findViewById(R.id.etName),
                findViewById(R.id.etSurname),
                findViewById(R.id.etPatronymic),
                findViewById(R.id.etDate)
        };

        for (EditText e: ets) {
            e.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { check_fields();}
                @Override
                public void afterTextChanged(Editable s) { check_fields(); }
            });
        }
    }

    void check_fields() {

        //if (sp) TODO: сделай выбор гендера и проверку, выбран ли он

        btn.setEnabled(false);
        for (EditText e: ets) {
            if (e.getText().toString().length() <= 1)
                return;
        }

        btn.setEnabled(true);
    }
}