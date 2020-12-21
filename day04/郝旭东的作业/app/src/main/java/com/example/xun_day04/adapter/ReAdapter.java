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
import com.example.xun_day04.bean.ReDainBean;

import java.util.ArrayList;

public class ReAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ReDainBean.DataBean> redianList;

    public ReAdapter(Context context, ArrayList<ReDainBean.DataBean> redianList) {
        this.context = context;
        this.redianList = redianList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.re, null);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        holder1.title.setText(redianList.get(position).getTitle());
        holder1.tv.setText(redianList.get(position).getLocation());
        Glide.with(context).load(redianList.get(position).getCover()).into(holder1.img);
    }

    @Override
    public int getItemCount() {
        return redianList.size();
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img;
        public TextView title;
        public TextView tv;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img = (ImageView) rootView.findViewById(R.id.img);
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
}
