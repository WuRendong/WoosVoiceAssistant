package com.flyingost.woosvoiceassistant;

import android.app.Application;
import android.content.Intent;

import com.flyingost.woosvoiceassistant.core.CoreService;

public class VoiceAssistantApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        startCoreService();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

        stopCoreService();
    }

    private void startCoreService () {
        Intent intent = new Intent();
        intent.setClass(this.getApplicationContext(), CoreService.class);
        startService(intent);
    }

    private void stopCoreService() {
        Intent intent = new Intent();
        intent.setClass(this.getApplicationContext(), CoreService.class);
        stopService(intent);
    }
}
