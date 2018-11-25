package com.flyingost.woosvoiceassistant.ui;

import android.content.Context;
import android.view.View;

import com.flyingost.woosvoiceassistant.presenter.BasePresenter;

public abstract class BaseView<P extends BasePresenter, U extends Ui> extends View {

    protected P mPresenter;

    public BaseView(Context context) {
        super(context);
        createPresenter();
    }

    protected abstract void createPresenter();

    protected abstract U getUi();

    protected P getPresenter() {
        return mPresenter;
    }

}
