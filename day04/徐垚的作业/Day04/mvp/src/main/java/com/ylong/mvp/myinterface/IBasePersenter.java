package com.ylong.mvp.myinterface;

public interface IBasePersenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();


}
