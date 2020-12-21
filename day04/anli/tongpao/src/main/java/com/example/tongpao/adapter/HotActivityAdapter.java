package com.example.tongpao.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.HotActivityBean;
import com.example.tongpao.utils.GlideUtil;

import java.util.List;

public class HotActivityAdapter extends BaseAdapter {

    public HotActivityAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected void bindData(Object o, ViewHolder viewHolder, int position) {
        ImageView iv_img = (ImageView) viewHolder.getViewById(R.id.iv_imge);
        TextView tv_location = (TextView) viewHolder.getViewById(R.id.tv_location);
        TextView tv_title = (TextView) viewHolder.getViewById(R.id.tv_title);
        TextView tv_time = (TextView) viewHolder.getViewById(R.id.tv_time);


        HotActivityBean.DataBean dataBean= (HotActivityBean.DataBean) o;
        if (!TextUtils.isEmpty(dataBean.getCover())&&iv_img!=null){
            GlideUtil.loadRoundImg(context,dataBean.getCover(),iv_img,15);
        }
        tv_title.setText(dataBean.getTitle());
        tv_time.setText(dataBean.getStartTime());
        tv_location.setText(dataBean.getLocation());

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_hot_activity;
    }
}
