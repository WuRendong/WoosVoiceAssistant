package com.flyingost.woosvoiceassistant.ui;

import android.content.Context;
import android.view.View;

import com.flyingost.woosvoiceassistant.presenter.FloatingViewPresenter;

public class FloatingView extends BaseView<FloatingViewPresenter, FloatingViewPresenter.FloatingViewUi> implements FloatingViewPresenter.FloatingViewUi {
    public FloatingView(Context context) {
        super(context);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new FloatingViewPresenter();
    }

    @Override
    protected FloatingViewPresenter.FloatingViewUi getUi() {
        return this;
    }

}
