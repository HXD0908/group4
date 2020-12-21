package com.example.tongpao.interfaces;

import com.example.tongpao.bean.FoundTabBean;
import com.example.tongpao.bean.HotActivityBean;
import com.example.tongpao.bean.InfoBean;

public interface IFound {
    interface View extends IBaseView {
        void setHotactivity(HotActivityBean hotactivity);

        void setTab(FoundTabBean foundTabBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHotactivity();

        void getTab();
    }

    interface InfoView extends IBaseView{
        void setInfoReturn(InfoBean infoReturn);
    }
    interface InfoPresenter extends IBasePresenter<InfoView>{
        void getInfoDate(int type);

    }

}
