package com.example.xun_day04.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xun_day04.R;
import com.example.xun_day04.bean.JinYanBean;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<JinYanBean.DataBean.ExpTopBean.ListBean> homeList;

    public RecAdapter(Context context, ArrayList<JinYanBean.DataBean.ExpTopBean.ListBean> homeList) {
        this.context = context;
        this.homeList = homeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.items, null);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        holder1.a.setText(homeList.get(position).getNickName());
        holder1.b.setText(homeList.get(position).getTongQian());
        holder1.c.setText(homeList.get(position).getProvince());
        Glide.with(context).load(homeList.get(position).getHeadUrl()).into(holder1.img);

    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img;
        public TextView a;
        public TextView b;
        public TextView c;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img = (ImageView) rootView.findViewById(R.id.img);
            this.a = (TextView) rootView.findViewById(R.id.a);
            this.b = (TextView) rootView.findViewById(R.id.b);
            this.c = (TextView) rootView.findViewById(R.id.c);
        }

    }
}
