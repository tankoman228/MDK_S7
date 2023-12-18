package com.example.mdk_s7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mdk_s7.AnalysisInfo.AnalysisCategory;

public class FragmentAnalyse extends Fragment {

    static AnalysisCategory[] analysisCategories = AnalysisCategory.basicAnalysisCategories;

    public FragmentAnalyse() {
        super(R.layout.fragment_analyse);
    }


    Button[] btns_categories;
    int id_current_type = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button updateButton = view.findViewById(R.id.btnAnalysisPopular);

        updateButton.setOnClickListener(v -> {
            Toast.makeText(this.getContext(), "fefe", Toast.LENGTH_SHORT).show();
        });
        reload_analysis();
    }

    private void type_pick(int id_type) {
        btns_categories[id_current_type].setBackground(getContext().getDrawable(R.drawable.buttons_grey));
        btns_categories[id_current_type].setTextColor(getContext().getColor(R.color.greyText));

        btns_categories[id_type].setBackground(getContext().getDrawable(R.drawable.buttons_blue));
        btns_categories[id_type].setTextColor(getContext().getColor(R.color.white));

        reload_analysis();
    }

    private void reload_analysis() {

    }
}