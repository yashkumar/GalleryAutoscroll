package com.example.tedt;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class CustomGallery extends Gallery {

    private Handler handler;

    public CustomGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        handler = new Handler();
        postDelayedScrollNext();
    }

    private void postDelayedScrollNext() {
        handler.postDelayed(new Runnable() {
            public void run() {
                postDelayedScrollNext();
                Log.d("CustomGallery", "dpad RIGHT");
                onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
            }
        }, 1000);
    }

    private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
        return e2.getX() > e1.getX();
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int kEvent;
        if (isScrollingLeft(e1, e2)) {
            Log.d("CustomGallery", "fling LEFT");
            kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        } else {
            Log.d("CustomGallery", "fling LEFT");
            kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(kEvent, null);
        return true;
    }
}
