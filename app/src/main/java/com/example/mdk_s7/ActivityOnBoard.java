package com.example.mdk_s7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityOnBoard extends AppCompatActivity {

    ImageView ivAnalyse, ivDock, ivTalk;
    TextView tvHeader, tvText;
    ImageView[] bubbles;
    Button btn;

    int stage = 0; //from 0 to 2
    int previous_stage = 0; //from 0 to 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acivity_onboard);

        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.white));

        ivDock = findViewById(R.id.ivDoc);
        ivAnalyse = findViewById(R.id.ivMeth);
        ivTalk = findViewById(R.id.ivTalk);

        tvHeader = findViewById(R.id.tvHeader);
        tvText = findViewById(R.id.tvText);

        btn = findViewById(R.id.btnSkip);

        bubbles = new ImageView[] {findViewById(R.id.ivBubbles1), findViewById(R.id.ivBubbles2),findViewById(R.id.ivBubbles3)};

        btn.setOnClickListener(l -> {
            startActivity(new Intent(this, ActivityLoginOrRegister.class));
        });
        findViewById(R.id.clMain).setOnTouchListener(new OnTouchSwipeListener(this) {
            @Override
            public void onSwipeRight() {
                previous_stage = stage;
                stage--;
                if (stage < 0)
                    stage = 0;
                update_stage();
            }
            @Override
            public void onSwipeLeft() {
                previous_stage = stage;
                stage++;
                if (stage > 2)
                    stage = 2;
                update_stage();
            }
        });
    }

    private void update_stage() {

        if (stage == previous_stage)
            return;

        btn.setText(getString(R.string.skip));

        Animation a = AnimationUtils.loadAnimation(this,R.anim.appear);
        bubbles[stage].startAnimation(a);
        switch (stage) {
            case 0:
                ivAnalyse.startAnimation(a);
                tvText.startAnimation(a);
                tvHeader.startAnimation(a);

                tvHeader.setText(getString(R.string.Analysis));
                tvText.setText(getString(R.string.textAnalysis));
                break;
            case 1:
                ivTalk.startAnimation(a);
                tvText.startAnimation(a);
                tvHeader.startAnimation(a);

                tvHeader.setText(getString(R.string.Notifications));
                tvText.setText(getString(R.string.textNotifications));
                break;
            case 2:
                ivDock.startAnimation(a);
                tvText.startAnimation(a);
                tvHeader.startAnimation(a);

                tvHeader.setText(getString(R.string.Monitoring));
                tvText.setText(getString(R.string.textMonitoring));
                btn.setText(R.string.finish);
                break;
        }

        Animation a2 = AnimationUtils.loadAnimation(this,R.anim.disappear);
        bubbles[previous_stage].startAnimation(a2);
        switch (previous_stage) {
            case 0:
                ivAnalyse.startAnimation(a2);
                break;
            case 1:
                ivTalk.startAnimation(a2);
                break;
            case 2:
                ivDock.startAnimation(a2);
                break;
        }
    }
}