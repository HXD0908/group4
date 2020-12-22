package com.jy.day06.mvp.presenter;

import com.jy.day06.base.BasePresenter;
import com.jy.day06.callback.RxObserverCallBack;
import com.jy.day06.mvp.model.RxOpretorImpl;
import com.jy.day06.mvp.model.constants.ApiConstants;
import com.jy.day06.mvp.view.fragment.HomeFragment;

import okhttp3.ResponseBody;

public class HomeFragPresenter extends BasePresenter {

    private final RxOpretorImpl rxOpretor;
    private HomeFragment homeFragment;

    public HomeFragPresenter(HomeFragment homeFragment) {
        rxOpretor = new RxOpretorImpl ();
        this.homeFragment = homeFragment;
    }

    @Override
    public void start() {
        rxOpretor.rxGetRequest (ApiConstants.PLAY_ANDROID, new RxObserverCallBack<Object> () {
            @Override
            public void OnSuccess(Object o) {
                if (homeFragment != null) {
                    ResponseBody responseBody = (ResponseBody) o;
                    try {
                        String string = responseBody.string ();
                        homeFragment.OnSuccess (string);

                    } catch (Exception e) {
                        e.printStackTrace ();
                    }
                }
            }

            @Override
            public void OnFail(String Error) {
                if (homeFragment != null) {
                    homeFragment.OnFail (Error);
                }
            }
        });
    }
}
