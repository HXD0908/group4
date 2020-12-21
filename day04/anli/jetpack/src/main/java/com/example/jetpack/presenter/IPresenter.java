package com.example.jetpack.presenter;

import com.example.jetpack.bean.PicBean;
import com.example.jetpack.callback.ICallBack;
import com.example.jetpack.model.IModel;
import com.example.jetpack.view.IView;

public class IPresenter {
    private final IModel iModel;
    private IView iView;

    public IPresenter(IView iView) {
        iModel = new IModel();
        this.iView = iView;
    }

    public void getDate() {
        iModel.getTu(new ICallBack() {
            @Override
            public void onSuccess(PicBean picBean) {
                iView.onSuccess(picBean);
            }

            @Override
            public void onFaul(String error) {
                iView.onFaul(error);
            }
        });
    }
}
