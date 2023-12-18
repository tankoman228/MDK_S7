package com.example.mdk_s7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentAnalyse extends Fragment {

    public FragmentAnalyse() {
        super(R.layout.fragment_analyse);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button updateButton = view.findViewById(R.id.button);

        updateButton.setOnClickListener(v -> {
            Toast.makeText(this.getContext(), "fefe", Toast.LENGTH_SHORT).show();
        });
    }
}