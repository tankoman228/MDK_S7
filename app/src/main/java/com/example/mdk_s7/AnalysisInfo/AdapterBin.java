package com.example.mdk_s7.AnalysisInfo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mdk_s7.FragmentAnalyse;
import com.example.mdk_s7.R;
import com.example.mdk_s7.ToActivityConnectable;

public class AdapterBin extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;

    public static ArrayList<Analysis> objects;
    public static ToActivityConnectable activityBin;


    public AdapterBin(Context context) {
        ctx = context;
        objects = AdapterAnalysis.chosen_objects;

        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_bin, parent, false);
        }

        //Получаем анализ, который отображается тут
        Analysis thisAnalysis = getAnalysis(position);

        ((TextView) view.findViewById(R.id.tvItemBinName)).setText(thisAnalysis.Title);

        TextView tvCost = view.findViewById(R.id.tvBinCost);
        tvCost.setText(String.valueOf(thisAnalysis.countInBasket * thisAnalysis.getPrice()) + " " + thisAnalysis.getCurrency());

        TextView tvPatients = view.findViewById(R.id.tvBinPatients);

        //Пациент, пациентов, пациента
        if (thisAnalysis.countInBasket % 10 > 1 && thisAnalysis.countInBasket % 10 < 5 && (thisAnalysis.countInBasket % 100 < 10 || thisAnalysis.countInBasket % 100 >= 20))
            tvPatients.setText(String.valueOf(thisAnalysis.countInBasket) + view.getContext().getString(R.string._patients25));
        else if (thisAnalysis.countInBasket != 11 && thisAnalysis.countInBasket % 10 == 1)
            tvPatients.setText(String.valueOf(thisAnalysis.countInBasket) + view.getContext().getString(R.string._patient));
        else
            tvPatients.setText(String.valueOf(thisAnalysis.countInBasket) + view.getContext().getString(R.string._patients_many));

        //Кнопки
        view.findViewById(R.id.btnBinClear).setOnClickListener(l -> {
            thisAnalysis.countInBasket--;
            if (thisAnalysis.countInBasket < 1)
                thisAnalysis.countInBasket = 1;
            else
                activityBin.sendMeMessage(false);
        });
        view.findViewById(R.id.btnBinAdd).setOnClickListener(l -> {
            thisAnalysis.countInBasket++;
            activityBin.sendMeMessage(true);
        });
        view.findViewById(R.id.btnBinClose).setOnClickListener(l -> {
            thisAnalysis.countInBasket = 1;
            objects.remove(thisAnalysis);
            activityBin.sendMeMessage(false);
        });

        return view;
    }

    Analysis getAnalysis(int position) {
        return ((Analysis) getItem(position));
    }
}
