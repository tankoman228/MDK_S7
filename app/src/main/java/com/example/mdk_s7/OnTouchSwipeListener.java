package com.example.mdk_s7;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

//Считывание свайпов вправо и влево
public abstract class OnTouchSwipeListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnTouchSwipeListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public abstract void onSwipeLeft();
    public abstract  void onSwipeRight();

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();

            if (Math.abs(distanceX) > Math.abs(distanceY) &&
                    Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD ) {

                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();

                return true;
            }

            return false;
        }
    }
}
