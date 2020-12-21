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
import com.example.demo_day5_zuoye.R;
import com.example.demo_day5_zuoye.bean.HotBean;

import java.util.ArrayList;

public class RlvAdapter extends RecyclerView.Adapter {
    private ArrayList<HotBean.DataBean> dataBeans;
    private Context mContext;

    public RlvAdapter(ArrayList<HotBean.DataBean> dataBeans, Context mContext) {
        this.dataBeans = dataBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder1(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        holder1.tv_title.setText(dataBeans.get(position).getTitle());
        holder1.tv_desc.setText(dataBeans.get(position).getLocation());
        holder1.tv_time.setText(dataBeans.get(position).getApplyCutOffTime());
        Glide.with(mContext).load(dataBeans.get(position).getCover()).into(holder1.iv_iv);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_iv;
        public TextView tv_title;
        public TextView tv_desc;
        public TextView tv_time;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_iv = (ImageView) rootView.findViewById(R.id.iv_iv);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }
}
