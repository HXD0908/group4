package com.example.day4_1.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day4_1.R;
import com.example.day4_1.bean.PaoZiBean;

import java.util.ArrayList;

public class MyJinYanRecAdapter1 extends RecyclerView.Adapter {

    private Activity context;
    private ArrayList<PaoZiBean.DataBean> list;

    public MyJinYanRecAdapter1(Activity context, ArrayList<PaoZiBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.paozi_view, parent, false);
        return new MyJinYanRecAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyJinYanRecAdapter.ViewHolder viewHolder = (MyJinYanRecAdapter.ViewHolder) holder;
        Glide.with(context).load(list.get(position).getHeadUrl())
                .apply(new RequestOptions().circleCrop()).into(viewHolder.paozi_image);
        viewHolder.paozi_name.setText(list.get(position).getNickName());
        viewHolder.paozi_dizhi.setText(list.get(position).getProvince());
        viewHolder.jinyan.setText("                                                            "+list.get(position).getUserID()+"铜钱");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends MyReMenRecAdapter.ViewHolder {
        public View rootView;
        public ImageView paozi_image;
        public TextView paozi_name;
        public TextView jinyan;
        public TextView paozi_dizhi;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.paozi_image = (ImageView) rootView.findViewById(R.id.paozi_image);
            this.paozi_name = (TextView) rootView.findViewById(R.id.paozi_name);
            this.jinyan = (TextView) rootView.findViewById(R.id.jinyan);
            this.paozi_dizhi = (TextView) rootView.findViewById(R.id.paozi_dizhi);
        }
    }
}
