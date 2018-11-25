package com.flyingost.woosvoiceassistant.core;

public interface ITtsPlayer {

    void play(String text, ITtsPlayerListener listener);

    void stop();
}
