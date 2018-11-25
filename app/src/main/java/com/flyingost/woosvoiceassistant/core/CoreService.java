package com.flyingost.woosvoiceassistant.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.flyingost.woosvoiceassistant.TonePlayer;

public class CoreService extends Service {

    private SpeechRecognizer mSpeechRecognizer;
    private TtsPlayer mTtsPlayer;
    private TonePlayer mTonePlayer;
    private VoiceAssistantEventDispatcher mEventDispather;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
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

    private void init() {
        mSpeechRecognizer = new SpeechRecognizer(this);
        mTonePlayer = new TonePlayer(this);
        mTtsPlayer = new TtsPlayer(this);
        mEventDispather = new VoiceAssistantEventDispatcher(this);
        mEventDispather.start();
    }

}
