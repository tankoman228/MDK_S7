package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mdk_s7.AnalysisInfo.AdapterAnalysis;
import com.example.mdk_s7.AnalysisInfo.AdapterBin;
import com.example.mdk_s7.AnalysisInfo.Analysis;

public class ActivityBin extends AppCompatActivity implements ToActivityConnectable {

    ListView lv;
    TextView tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin);

        lv = findViewById(R.id.lvBin);
        tvPrice = findViewById(R.id.tvBinSum);

        findViewById(R.id.btnBinBack).setOnClickListener(l -> startActivity(new Intent(this, ActivityMain.class)));

        AdapterBin.activityBin = this; //Обмен указателями. НЕ ЗАБЫВАТЬ ПРО ЭТУ СТРОЧКУ!

        sendMeMessage(true);
    }

    //Когда ткнут что-нибудь в одном из элементов списка выбранных услуг, вызовется этот метод
    @Override
    public void sendMeMessage(Object obj) {

        if (AdapterAnalysis.chosen_objects.isEmpty())
            startActivity(new Intent(this, ActivityMain.class));


        //Переопределяем адаптер
        AdapterBin adapter = new AdapterBin(this);
        lv.setAdapter(adapter);

        //Считаем высоту
        int totalHeight = 200;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, lv);
            if (listItem != null) {
                listItem.measure(
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            }

            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = (int)((float)totalHeight / 1.5)
                + (lv.getDividerHeight() * (adapter.getCount() - 1));
        lv.setLayoutParams(params);
        lv.requestLayout();


        //Считаем сумму цен
        int price_sum = 0;
        for (Analysis a : AdapterAnalysis.chosen_objects) {
            price_sum += a.getPrice() * a.countInBasket;
        }
        tvPrice.setText(String.format("%s %S", price_sum, AdapterAnalysis.chosen_objects.get(0).getCurrency()));
    }

    @Override
    public void onClickItem(Object obj) {} //Пусто, но надо определить метод из интерфейса
}