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
import com.example.demo_day5_zuoye.bean.JingBean;

import java.util.ArrayList;

public class JingRlvAdapter extends RecyclerView.Adapter {
    private ArrayList<JingBean.DataBean.ExpTopBean.ListBean> listBeans;
    private Context mContext;

    public JingRlvAdapter(ArrayList<JingBean.DataBean.ExpTopBean.ListBean> listBeans, Context mContext) {
        this.listBeans = listBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.jing_layout, parent, false);
        return new ViewHolder6(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder6 holder6 = (ViewHolder6) holder;
        holder6.tv_title.setText(listBeans.get(position).getNickName());
        holder6.tv_desc.setText(listBeans.get(position).getProvince());
        holder6.tv_desc1.setText(listBeans.get(position).getCity());
        holder6.text.setText(listBeans.get(position).getTongQian()+"");
        Glide.with(mContext).load(listBeans.get(position).getHeadUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder6.ivm1);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }


    public static
    class ViewHolder6 extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView ivm1;
        public TextView tv_title;
        public TextView tv_desc;
        public TextView tv_desc1;
        public TextView text;

        public ViewHolder6(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.ivm1 = (ImageView) rootView.findViewById(R.id.ivm1);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
            this.tv_desc1 = (TextView) rootView.findViewById(R.id.tv_desc1);
            this.text = (TextView) rootView.findViewById(R.id.text);
        }

    }
}
