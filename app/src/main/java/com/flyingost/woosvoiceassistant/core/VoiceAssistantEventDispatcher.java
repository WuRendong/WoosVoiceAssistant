package com.flyingost.woosvoiceassistant.core;

import android.content.Context;

import com.flyingost.woosvoiceassistant.processor.ProcessorManager;

public class VoiceAssistantEventDispatcher extends Thread {

    private Context mContext;

    private ProcessorManager mProcessorManager;

    public VoiceAssistantEventDispatcher(Context context) {
        mContext = context;
        mProcessorManager = new ProcessorManager();
    }

    @Override
    public void run() {
        super.run();
    }
}
