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
import com.example.mdk_s7.ToActivityConnectable;

public class AdapterAnalysis extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Analysis> objects;

    public static  ArrayList<Analysis> chosen_objects;
    public static ToActivityConnectable fragmentAnalyse;


    public AdapterAnalysis(Context context, ArrayList<Analysis> products) {
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

        Analysis thisAnalysis = getAnalysis(position);

        ((TextView) view.findViewById(R.id.tvServiceame)).setText(thisAnalysis.Title);
        ((TextView) view.findViewById(R.id.tvServiceCost)).setText(thisAnalysis.Cost);
        ((TextView) view.findViewById(R.id.tvServiceDays)).setText(thisAnalysis.Time);

        Button btn = view.findViewById(R.id.btnServiceAdd);

        Context context = view.getContext();
        btn.setOnClickListener(v -> {

            if (chosen_objects.contains(thisAnalysis)) {
                chosen_objects.remove(thisAnalysis);

                //Когда нужно убрать из выбранных
                btn.setBackground(context.getDrawable(R.drawable.buttons_blue));
                btn.setTextColor(context.getColor(R.color.white));
                btn.setText(R.string.add);

                fragmentAnalyse.sendMeMessage(false);
            }
            else {
                chosen_objects.add(thisAnalysis);

                //Когда нужно добавить
                btn.setBackground(context.getDrawable(R.drawable.buttons_white_borders_blue));
                btn.setTextColor(context.getColor(R.color.blueButton));
                btn.setText(R.string.remove);

                fragmentAnalyse.sendMeMessage(true);
            }
        });

        return view;
    }

    Analysis getAnalysis(int position) {
        return ((Analysis) getItem(position));
    }
}
