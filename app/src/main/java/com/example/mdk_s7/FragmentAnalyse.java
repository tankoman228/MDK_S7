package com.example.mdk_s7;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mdk_s7.AnalysisInfo.*;

import java.util.ArrayList;

//Фрагмент с выбором анализов для перехода в корзину
public class FragmentAnalyse extends Fragment implements ToActivityConnectable {

    public FragmentAnalyse() {
        super(R.layout.fragment_analyse);
    }


    Button[] btns_categories;
    Button btn_basket, btn_add_to_basket;
    ListView listView;
    TextView tvPrice, tvDescription, tvPrepare, tvDeadline, tvBioMaterial, tvName;
    ImageView ivMoreBg;
    ConstraintLayout clMoreInfo;

    int current_category_id = 0;
    Analysis selectedAnalysis;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ivMoreBg = view.findViewById(R.id.ivMoreBackground);
        clMoreInfo = view.findViewById(R.id.llAbout);
        listView = view.findViewById(R.id.lvAnalysis);

        //Закрытие доп. инфы об анализе
        view.findViewById(R.id.imageButton).setOnClickListener(l -> {
            ivMoreBg.setVisibility(View.INVISIBLE);
            clMoreInfo.setVisibility(View.INVISIBLE);
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

        //Добавить в корзину или убрать из корзины, когда открыта доп. инфа
        btn_add_to_basket = view.findViewById(R.id.btnAddToBin);
        btn_add_to_basket.setOnClickListener(l -> {

            if (AdapterAnalysis.chosen_objects.contains(selectedAnalysis)) {
                btn_add_to_basket.setBackground(getContext().getDrawable(R.drawable.buttons_blue));
                btn_add_to_basket.setTextColor(getContext().getColor(R.color.white));
                btn_add_to_basket.setText(getString(R.string.add_with_cost) + selectedAnalysis.Cost);

                AdapterAnalysis.chosen_objects.remove(selectedAnalysis);
            }
            else {
                btn_add_to_basket.setBackground(getContext().getDrawable(R.drawable.buttons_white_borders_blue));
                btn_add_to_basket.setTextColor(getContext().getColor(R.color.blueButton));
                btn_add_to_basket.setText(R.string.remove);

                AdapterAnalysis.chosen_objects.add(selectedAnalysis);
            }

            reload_analysis_list();
        });

        //Кнопка в корзину
        btn_basket = view.findViewById(R.id.btnBasket);
        btn_basket.setOnClickListener(l -> startActivity(new Intent(getContext(), ActivityBin.class)));

        //Доп. Инфа об анализе
        tvPrice = view.findViewById(R.id.tvPrice);
        tvPrice.setVisibility(View.INVISIBLE);

        tvDescription = view.findViewById(R.id.tvDescriptionText);
        tvPrepare = view.findViewById(R.id.tvPrepareText);
        tvDeadline = view.findViewById(R.id.tvDeadlineText);
        tvBioMaterial = view.findViewById(R.id.tvBioMaterialText);
        tvName = view.findViewById(R.id.tvAboutTitle);

        AdapterAnalysis.fragmentAnalyse = this;
        reload_analysis_list();
    }

    //Выбор категории, изменение цвета кнопок
    private void categoryChosen(int id_type) {
        btns_categories[current_category_id].setBackground(getContext().getDrawable(R.drawable.buttons_grey));
        btns_categories[current_category_id].setTextColor(getContext().getColor(R.color.greyText));

        btns_categories[id_type].setBackground(getContext().getDrawable(R.drawable.buttons_blue));
        btns_categories[id_type].setTextColor(getContext().getColor(R.color.white));

        current_category_id = id_type;
        reload_analysis_list();
    }

    //сокращение от getContext().getString(id);
    private String gs(int id) {
        return getContext().getString(id);
    }

    //Обновление списка анализов
    private void reload_analysis_list() {

        ArrayList<Analysis> ans = new ArrayList<Analysis>();

        if (AdapterAnalysis.chosen_objects == null || AdapterAnalysis.chosen_objects.isEmpty()) {
            ans.add(new Analysis(gs(R.string.test_pcr_name), gs(R.string.test_pcr_days), gs(R.string.test_pcr_cost), gs(R.string.descr_test), gs(R.string.prepare_test), gs(R.string.vein_blood)));
            ans.add(new Analysis(gs(R.string.test_blood_name), gs(R.string.test_blood_days), gs(R.string.test_blood_cost), gs(R.string.descr_test), gs(R.string.prepare_test), gs(R.string.vein_blood)));
            ans.add(new Analysis(gs(R.string.test_bio_name), gs(R.string.test_bio_days), gs(R.string.test_bio_cost), gs(R.string.descr_test), gs(R.string.prepare_test), gs(R.string.vein_blood)));
            ans.add(new Analysis(gs(R.string.test_soy_name), gs(R.string.test_soy_days), gs(R.string.test_soy_cost), gs(R.string.descr_test), gs(R.string.prepare_test), gs(R.string.vein_blood)));
        }
        else {
            ans = AdapterAnalysis.objects;
        }
        AdapterAnalysis adapter = new AdapterAnalysis(this.getContext(), ans);
        listView.setAdapter(adapter);

        int totalHeight = 300;
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
        params.height = totalHeight / 2
                + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

        check_basket_size();
    }

    //Проверка, сколько товаров в корзине и нужно ли вообще отображать кнопку "в корзину"
    private void check_basket_size() {
        if (AdapterAnalysis.chosen_objects.size() > 0) {
            btn_basket.setVisibility(View.VISIBLE);
            tvPrice.setVisibility(View.VISIBLE);

            int price_sum = 0;
            for (Analysis a : AdapterAnalysis.chosen_objects) {
                price_sum += a.getPrice() * a.countInBasket;
            }
            tvPrice.setText(String.format("%s %S", price_sum, AdapterAnalysis.chosen_objects.get(0).getCurrency()));
        }
        else {
            btn_basket.setVisibility(View.INVISIBLE);
            tvPrice.setVisibility(View.INVISIBLE);
        }
    }

    //Так мы из адаптера просим фрагмент перепроверить сколько товаров в корзине и нужно ли вообще отображать кнопку "в корзину" из
    @Override
    public void sendMeMessage(Object obj) {
        check_basket_size();
    }

    //Кладём в корзину, либо наоборот
    @Override
    public void onClickItem(Object obj) {
        Analysis an = (Analysis) obj;

        tvName.setText(an.Title);
        tvBioMaterial.setText(an.Material);
        tvDeadline.setText(an.Time);
        tvPrepare.setText(an.Preparing);
        tvDescription.setText(an.Description);

        ivMoreBg.setVisibility(View.VISIBLE);
        clMoreInfo.setVisibility(View.VISIBLE);

        selectedAnalysis = an;
        if (AdapterAnalysis.chosen_objects.contains(selectedAnalysis)) {
            //Нажали добавить
            btn_add_to_basket.setBackground(getContext().getDrawable(R.drawable.buttons_white_borders_blue));
            btn_add_to_basket.setTextColor(getContext().getColor(R.color.blueButton));
            btn_add_to_basket.setText(R.string.remove);
        }
        else {
            //Нажали Убрать
            btn_add_to_basket.setBackground(getContext().getDrawable(R.drawable.buttons_blue));
            btn_add_to_basket.setTextColor(getContext().getColor(R.color.white));
            btn_add_to_basket.setText(R.string.add);
            selectedAnalysis.countInBasket = 1;
        }
    }
}