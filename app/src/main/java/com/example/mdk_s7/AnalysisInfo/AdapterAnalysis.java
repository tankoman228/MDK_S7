package com.example.mdk_s7.AnalysisInfo;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mdk_s7.R;

public class AdapterAnalysis extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Analysis> objects, chosen_objects;


    AdapterAnalysis(Context context, ArrayList<Analysis> products) {
        ctx = context;
        objects = products;
        chosen_objects = new ArrayList<>();
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
            view = lInflater.inflate(R.layout.item_tests, parent, false);
        }

        Analysis p = getAnalysis(position);

        ((TextView) view.findViewById(R.id.tvServiceame)).setText(p.Title);
        ((TextView) view.findViewById(R.id.tvServiceCost)).setText(p.Cost);
        ((TextView) view.findViewById(R.id.tvServiceDays)).setText(p.Time);

        Button btn = view.findViewById(R.id.btnServiceAdd);
        btn.setOnClickListener(v -> {
            Toast.makeText(btn.getContext(), "gfgfgg", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    Analysis getAnalysis(int position) {
        return ((Analysis) getItem(position));
    }
}
