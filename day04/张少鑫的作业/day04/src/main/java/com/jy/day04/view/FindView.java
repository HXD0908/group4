package com.jy.day04.view;

import com.jy.day04.model.bean.FindMoreBean;

public interface FindView {
    void OnMoreSuccess(FindMoreBean findMoreBean);

    void OnFail(String Error);
}
