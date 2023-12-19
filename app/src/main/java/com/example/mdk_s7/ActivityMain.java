package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.mdk_s7.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//Главная активность с 4 вкладками (фрагментами)
public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new FragmentAnalyse());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener (item -> {

            int index = item.getItemId();

            if (index == R.id.itemAnal)
                replaceFragment(new FragmentAnalyse());
            else if (index == R.id.itemProf)
                replaceFragment(new FragmentProfile());
            else if (index == R.id.itemResu)
                replaceFragment(new FragmentResults());
            else if (index == R.id.itemSupp)
                replaceFragment(new FragmentSupport());

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameMain, fragment);
        ft.commit();
    }
}