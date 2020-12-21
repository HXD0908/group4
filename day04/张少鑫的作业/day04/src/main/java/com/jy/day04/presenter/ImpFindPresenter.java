package com.jy.day04.presenter;

import com.jy.day04.callback.FindCallBack;
import com.jy.day04.model.ImpFindModel;
import com.jy.day04.model.bean.FindMoreBean;
import com.jy.day04.view.FindView;

public class ImpFindPresenter implements FindPresenter, FindCallBack {

    private final ImpFindModel impFindModel;
    public FindView findView;

    public ImpFindPresenter(FindView findView) {
        impFindModel = new ImpFindModel ();
        this.findView = findView;
    }

    @Override
    public void getMore() {
        impFindModel.getMore (this);
    }

    @Override
    public void OnMoreSuccess(FindMoreBean findMoreBean) {
        findView.OnMoreSuccess (findMoreBean);
    }

    @Override
    public void OnFail(String Error) {
        findView.OnFail (Error);
    }
}
