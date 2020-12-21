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
import com.example.day4_1.bean.ReMenBean;

import java.util.ArrayList;

public class MyReMenRecAdapter extends RecyclerView.Adapter {

    private Activity context;
    private ArrayList<ReMenBean.DataBean> list;

    public MyReMenRecAdapter(Activity context, ArrayList<ReMenBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View remenhuati_view = LayoutInflater.from(context).inflate(R.layout.remenhuati_view, parent, false);
        return new ViewHolder(remenhuati_view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(context).load(list.get(position).getImageUrl()).into(viewHolder.remenhuati_image);
        viewHolder.remenhuati_zhuti.setText(list.get(position).getName());
        viewHolder.remenhuati_didian.setText(list.get(position).getDepict());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView remenhuati_image;
        public TextView remenhuati_zhuti;
        public TextView remenhuati_didian;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.remenhuati_image = (ImageView) rootView.findViewById(R.id.remenhuati_image);
            this.remenhuati_zhuti = (TextView) rootView.findViewById(R.id.remenhuati_zhuti);
            this.remenhuati_didian = (TextView) rootView.findViewById(R.id.remenhuati_didian);
        }

    }
}
