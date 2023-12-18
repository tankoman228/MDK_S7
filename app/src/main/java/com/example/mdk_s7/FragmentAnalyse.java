package com.example.mdk_s7;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mdk_s7.AnalysisInfo.*;

import java.util.ArrayList;

public class FragmentAnalyse extends Fragment implements ToActivityConnectable {

    static AnalysisCategory[] analysisCategories = AnalysisCategory.basicAnalysisCategories;

    public FragmentAnalyse() {
        super(R.layout.fragment_analyse);
    }


    Button[] btns_categories;
    Button btn_basket;
    ListView listView;
    int id_current_type = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button updateButton = view.findViewById(R.id.btnAnalysisPopular);

        listView = view.findViewById(R.id.lvAnalysis);
        updateButton.setOnClickListener(v -> {
            Toast.makeText(this.getContext(), "fefe", Toast.LENGTH_SHORT).show();
        });

        btns_categories = new Button[] {
                view.findViewById(R.id.btnAnalysisPopular),
                view.findViewById(R.id.btnAnalysisCovid),
                view.findViewById(R.id.btnAnalysisComplex)
        };
        for (int i = 0; i < btns_categories.length; i++) {
            int ii = i;
            btns_categories[i].setOnClickListener(l -> categoryChosen(ii));
        }

        btn_basket = view.findViewById(R.id.button);
        btn_basket.setOnClickListener(l -> startActivity(new Intent(getContext(), ActivityOnBoard.class)));
        btn_basket.setVisibility(View.INVISIBLE);

        AdapterAnalysis.fragmentAnalyse = this;
        reload_analysis();
    }

    private void categoryChosen(int id_type) {
        btns_categories[id_current_type].setBackground(getContext().getDrawable(R.drawable.buttons_grey));
        btns_categories[id_current_type].setTextColor(getContext().getColor(R.color.greyText));

        btns_categories[id_type].setBackground(getContext().getDrawable(R.drawable.buttons_blue));
        btns_categories[id_type].setTextColor(getContext().getColor(R.color.white));

        reload_analysis();
        id_current_type = id_type;
    }
    private String gs(int id) {
        return getContext().getString(id);
    }

    private void reload_analysis() {

        ArrayList<Analysis> ans = new ArrayList<Analysis>();
        ans.add(new Analysis(gs(R.string.test_pcr_name), gs(R.string.test_pcr_days), gs(R.string.test_pcr_cost)));
        ans.add(new Analysis(gs(R.string.test_blood_name), gs(R.string.test_blood_days), gs(R.string.test_blood_cost)));
        ans.add(new Analysis(gs(R.string.test_bio_name), gs(R.string.test_bio_days), gs(R.string.test_bio_cost)));
        ans.add(new Analysis(gs(R.string.test_soy_name), gs(R.string.test_soy_days), gs(R.string.test_soy_cost)));

        AdapterAnalysis adapter = new AdapterAnalysis(this.getContext(), ans);
        listView.setAdapter(adapter);

        int totalHeight = 266;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            if (listItem != null) {
                listItem.measure(
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            }

            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void sendMeMessage(Object obj) {
        boolean i = (boolean) obj;
        if (AdapterAnalysis.chosen_objects.size() > 0) {
            btn_basket.setVisibility(View.VISIBLE);
        }
        else {
            btn_basket.setVisibility(View.INVISIBLE);
        }
    }
}