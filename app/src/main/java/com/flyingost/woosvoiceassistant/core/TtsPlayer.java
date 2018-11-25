package com.flyingost.woosvoiceassistant.core;

import android.content.Context;

import com.flyingost.woosvoiceassistant.core.ITtsPlayer;
import com.flyingost.woosvoiceassistant.core.ITtsPlayerListener;

public class TtsPlayer implements ITtsPlayer {

    private Context mContext;

    public TtsPlayer(Context context) {
        mContext = context;
    }

    @Override
    public void play(String text, ITtsPlayerListener listener) {

    }

    @Override
    public void stop() {

    }
}
