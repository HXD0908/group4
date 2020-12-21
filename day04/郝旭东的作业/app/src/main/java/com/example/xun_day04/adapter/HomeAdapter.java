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
import com.example.xun_day04.bean.HomeBean;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<HomeBean.DataBean.ListBean> homeList;

    public HomeAdapter(Context context, ArrayList<HomeBean.DataBean.ListBean> homeList) {
        this.context = context;
        this.homeList = homeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int type = getItemViewType(viewType);
        if (type % 2 == 0) {
            View view = View.inflate(context, R.layout.qiana, null);
            return new ViewHolder1(view);
        } else {
            View view = View.inflate(context, R.layout.qianb, null);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (position % 2 == 0) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.titlea.setText(homeList.get(position).getTitle());
            holder1.tva.setText(homeList.get(position).getCreateTime());
            Glide.with(context).load(homeList.get(position).getFilePathList().get(position).getFilePath()).into(holder1.imga);
            Glide.with(context).load(homeList.get(position).getFilePathList().get(position).getFilePath()).into(holder1.imgb);
            Glide.with(context).load(homeList.get(position).getFilePathList().get(position).getFilePath()).into(holder1.imgc);
        } else {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.titles.setText(homeList.get(position).getTitle());
            Glide.with(context).load(homeList.get(position).getFilePathList().get(position).getFilePath()).into(holder2.imgA);
        }
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView titlea;
        public ImageView imga;
        public ImageView imgb;
        public ImageView imgc;
        public TextView tva;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.titlea = (TextView) rootView.findViewById(R.id.titlea);
            this.imga = (ImageView) rootView.findViewById(R.id.imga);
            this.imgb = (ImageView) rootView.findViewById(R.id.imgb);
            this.imgc = (ImageView) rootView.findViewById(R.id.imgc);
            this.tva = (TextView) rootView.findViewById(R.id.tva);
        }

    }

    public static
    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView titles;
        public ImageView imgA;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.titles = (TextView) rootView.findViewById(R.id.titles);
            this.imgA = (ImageView) rootView.findViewById(R.id.imgA);
        }

    }
}
