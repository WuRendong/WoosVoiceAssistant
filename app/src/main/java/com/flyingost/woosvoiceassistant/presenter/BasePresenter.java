package com.flyingost.woosvoiceassistant.presenter;

import com.flyingost.woosvoiceassistant.ui.Ui;

public abstract class BasePresenter<U extends Ui> {

    private U mUi;

    public BasePresenter() {
    }

    public U getUi() {
        return mUi;
    }

    public void onUiReady(U ui) {
        mUi = ui;
    }

    public void onUiDestroy(U ui) {
        mUi = null;
    }
}
