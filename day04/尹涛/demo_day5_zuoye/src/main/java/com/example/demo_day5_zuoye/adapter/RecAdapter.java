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
import com.example.demo_day5_zuoye.bean.VpBean;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter {
    private ArrayList<VpBean.DataBean.ListBean> listBeans;
    private Context mContext;

    public RecAdapter(ArrayList<VpBean.DataBean.ListBean> listBeans, Context mContext) {
        this.listBeans = listBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.page1_layout, parent, false);
            return new ViewHolder2(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.page2_layout, parent, false);
            return new ViewHolder3(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 0){
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.tv_title.setText(listBeans.get(position).getTitle());
            Glide.with(mContext).load(listBeans.get(position).getFilePathList().get(position).getFilePath()).into(holder2.iv_img1);
            Glide.with(mContext).load(listBeans.get(position).getFilePathList().get(position).getFilePath()).into(holder2.iv_img2);
            Glide.with(mContext).load(listBeans.get(position).getFilePathList().get(position).getFilePath()).into(holder2.iv_img3);
        }else{
            ViewHolder3 holder3 = (ViewHolder3) holder;
            holder3.tv_text.setText(listBeans.get(position).getTitle());
            Glide.with(mContext).load(listBeans.get(position).getFilePathList().get(position).getFilePath()).into(holder3.iv_img4);
        }
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position %2 == 0){
            return 0;
        }else{
            return 1;
        }
    }

    public static
    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public ImageView iv_img1;
        public ImageView iv_img2;
        public ImageView iv_img3;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.iv_img1 = (ImageView) rootView.findViewById(R.id.iv_img1);
            this.iv_img2 = (ImageView) rootView.findViewById(R.id.iv_img2);
            this.iv_img3 = (ImageView) rootView.findViewById(R.id.iv_img3);
        }

    }

    public static
    class ViewHolder3 extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView tv_text;
        public ImageView iv_img4;

        public ViewHolder3(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_text = (TextView) rootView.findViewById(R.id.tv_text);
            this.iv_img4 = (ImageView) rootView.findViewById(R.id.iv_img4);
        }

    }
}
