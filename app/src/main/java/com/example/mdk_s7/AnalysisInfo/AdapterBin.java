package com.example.mdk_s7.AnalysisInfo;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mdk_s7.R;
import com.example.mdk_s7.ToActivityConnectable;

public class AdapterBin extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;

    public static ArrayList<Analysis> objects;
    public static ToActivityConnectable activityBin;


    public AdapterBin(Context context, ArrayList<Analysis> products) {
        ctx = context;
        objects = products;

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

        Analysis thisAnalysis = getAnalysis(position);

        ((TextView) view.findViewById(R.id.tvItemBinName)).setText(thisAnalysis.Title);
        TextView tvCost = view.findViewById(R.id.tvBinCost); tvCost.setText(thisAnalysis.Cost);

        TextView tvPatients = view.findViewById(R.id.tvBinPatients);
        tvPatients.setText("1" + view.getContext().getString(R.string._patient));


        view.findViewById(R.id.btnBinClear).setOnClickListener(l -> {
            thisAnalysis.num--;
            if (thisAnalysis.num < 1)
                objects.remove(thisAnalysis);
            activityBin.sendMeMessage(false);
        });
        view.findViewById(R.id.btnBinAdd).setOnClickListener(l -> {
            thisAnalysis.num++;
            activityBin.sendMeMessage(true);
        });

        return view;
    }

    Analysis getAnalysis(int position) {
        return ((Analysis) getItem(position));
    }
}
