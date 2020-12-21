package com.example.demo_day5_zuoye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo_day5_zuoye.R;
import com.example.demo_day5_zuoye.bean.ObBean;

import java.util.ArrayList;

public class RrAdapter extends RecyclerView.Adapter {
    private ArrayList<ObBean.DataBean.ListBean> listBeans;
    private Context mContext;

    public RrAdapter(ArrayList<ObBean.DataBean.ListBean> listBeans, Context mContext) {
        this.listBeans = listBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.rr_layout, parent, false);
        return new ViewHolder4(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder4 holder4  = (ViewHolder4) holder;
        holder4.tv_title.setText(listBeans.get(position).getFullName());
        holder4.people.setText("成员为:"+listBeans.get(position).getIntegralnumber());
        holder4.num.setText("活动:"+listBeans.get(position).getCountUser());
        holder4.tv_desc.setText(listBeans.get(position).getNote());
        Glide.with(mContext).load(listBeans.get(position).getLogo()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder4.iv1);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public static
    class ViewHolder4 extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv1;
        public TextView tv_title;
        public TextView people;
        public TextView num;
        public TextView tv_desc;

        public ViewHolder4(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv1 = (ImageView) rootView.findViewById(R.id.iv1);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.people = (TextView) rootView.findViewById(R.id.people);
            this.num = (TextView) rootView.findViewById(R.id.num);
            this.tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
        }

    }
}
