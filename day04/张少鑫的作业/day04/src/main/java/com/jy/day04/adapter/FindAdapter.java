package com.jy.day04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.day04.R;
import com.jy.day04.model.bean.FindMoreBean;

import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<FindMoreBean.DataBean> dataBeans;

    public FindAdapter(Context context, ArrayList<FindMoreBean.DataBean> dataBeans) {

        this.context = context;
        this.dataBeans = dataBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from (context).inflate (R.layout.item_find, parent, false);
        return new FindViewHolder (inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FindMoreBean.DataBean dataBean = dataBeans.get (position);
        FindViewHolder findViewHolder = (FindViewHolder) holder;
        findViewHolder.item_title.setText (dataBean.getTitle ());
        findViewHolder.item_time.setText (dataBean.getStartTime ());
        Glide.with (context).load (dataBean.getCover ()).into (findViewHolder.item_image);

    }

    @Override
    public int getItemCount() {
        return dataBeans.size ();
    }

    public static class FindViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView item_image;
        public TextView item_title;
        public TextView item_time;

        public FindViewHolder(View rootView) {
            super (rootView);
            this.rootView = rootView;
            this.item_image = (ImageView) rootView.findViewById (R.id.item_image);
            this.item_title = (TextView) rootView.findViewById (R.id.item_title);
            this.item_time = (TextView) rootView.findViewById (R.id.item_time);
        }

    }
}
