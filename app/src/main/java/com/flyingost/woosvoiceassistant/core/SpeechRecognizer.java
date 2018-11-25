package com.flyingost.woosvoiceassistant.core;

import android.content.Context;

import com.flyingost.woosvoiceassistant.core.ISpeechRecognizer;

public class SpeechRecognizer implements ISpeechRecognizer {

    private Context mContext;

    public SpeechRecognizer(Context context) {
        mContext = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
