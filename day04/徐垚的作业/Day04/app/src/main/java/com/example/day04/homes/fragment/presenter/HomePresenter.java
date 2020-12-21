package com.example.day04.homes.fragment.presenter;

import com.example.day04.homes.fragment.api.IView;
import com.example.day04.homes.fragment.model.HomeModel;
import com.ylong.mvp.base.BasePersenter;
import com.ylong.mvp.bean.HotActivityBean;
import com.ylong.mvp.myinterface.Callback;

/**
 * Created by XY.
 * Date: 2020/12/18
 */
public class HomePresenter extends BasePersenter<IView.FindView> implements IView.FindPresenter {

    private IView.FindView view;
    private IView.FindModel model;

    public HomePresenter(IView.FindView pView) {
        view = pView;
        model = new HomeModel();
    }

    @Override
    public void getFindData() {
        model.getFindData(new Callback<HotActivityBean>() {
            @Override
            public void fail(String msg) {
                view.getError(msg);
            }

            @Override
            public void success(HotActivityBean pHotActivityBean) {
                if (view!=null){
                    view.getFindData(pHotActivityBean);
                }
            }
        });
    }
}
