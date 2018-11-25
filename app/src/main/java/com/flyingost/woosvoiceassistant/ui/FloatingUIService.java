package com.flyingost.woosvoiceassistant.ui;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;

import com.flyingost.woosvoiceassistant.R;

import static android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

public class FloatingUIService extends Service implements View.OnTouchListener {

    private static final String TAG = FloatingUIService.class.getSimpleName();

    private View mFloatingPanel;
    private WindowManager mWindowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        //Inflate the floating view layout we created
        mFloatingPanel = LayoutInflater.from(this).inflate(R.layout.layout_floating_panel, null);
        ViewStub container = mFloatingPanel.findViewById(R.id.container);
        container.setLayoutResource(R.layout.layout_default);
        container.inflate();

        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                TYPE_APPLICATION_OVERLAY,
                //WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 0;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingPanel, params);


        mFloatingPanel.setOnTouchListener(this/*new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    stopSelf();
                    return true;
                }
                return false;
            }
        }*/);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        if (mFloatingPanel != null) mWindowManager.removeView(mFloatingPanel);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
            stopSelf();
            return true;
        }
        return false;
    }
}
