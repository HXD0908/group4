package com.example.jetpack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jetpack.R;
import com.example.jetpack.bean.MyDbBean;

import java.util.ArrayList;

public class RecycAdapter extends RecyclerView.Adapter {
    private ArrayList<MyDbBean> list;
    private Context mContext;

    public RecycAdapter(ArrayList<MyDbBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_guan, parent, false);
        return new ViewHolder4(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder4 holder4 = (ViewHolder4) holder;
        holder4.tv_title.setText(list.get(position).getTitile());
        holder4.tv_desc.setText(list.get(position).getDesc());
        Glide.with(mContext).load(list.get(position).getIcon()).into(holder4.iv_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder4 extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_desc;
        public Button bt_insert;

        public ViewHolder4(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_img = (ImageView) rootView.findViewById(R.id.iv_img);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
            this.bt_insert = (Button) rootView.findViewById(R.id.bt_insert);
        }
    }
}
