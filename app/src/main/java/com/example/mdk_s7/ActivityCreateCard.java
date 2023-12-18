package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityCreateCard extends AppCompatActivity{

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
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.genders,
                R.layout.item_spinner_genders
        );
        spAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown_genders);
        sp.setAdapter(spAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                check_fields();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                check_fields();
            }
        });

        findViewById(R.id.btnSkipCard).setOnClickListener(l -> {
            startActivity(new Intent(this, ActivityMain.class));
        });
        findViewById(R.id.btnCreateCard).setOnClickListener(l -> {
            CardInfo.CardExists = true;
            startActivity(new Intent(this, ActivityMain.class));
        });


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

        btn.setEnabled(false);
        if (!sp.getSelectedItem().toString().equals(getResources().getStringArray(R.array.genders)[0]) &&
                !sp.getSelectedItem().toString().equals(getResources().getStringArray(R.array.genders)[1])) {
            return;
        }

        for (EditText e: ets) {
            if (e.getText().toString().length() <= 1)
                return;
        }

        btn.setEnabled(true);
    }
}