package com.example.jetpack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.jetpack.R;
import com.example.jetpack.bean.PicBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicAdapter extends RecyclerView.Adapter {
    private ArrayList<PicBean.DataBeanX.DataBean> dataBeans;
    private Context context;

    public PicAdapter(ArrayList<PicBean.DataBeanX.DataBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_pic, parent, false);
        return new ViewHolder1(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        Glide.with(context).load(dataBeans.get(position).getAuthor().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder1.iv_title);
        holder1.tv_title.setText(dataBeans.get(position).getAuthor().getName());
        holder1.tv_message.setText(dataBeans.get(position).getFeeds_text());
        holder1.tv_end.setText(dataBeans.get(position).getActivityText());
        Glide.with(context).load(dataBeans.get(position).getCover()).into(holder1.iv_iv);

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_title;
        public TextView tv_title;
        public TextView tv_message;
        public ImageView iv_iv;
        public TextView tv_end;
        public LinearLayout ll;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_title = (ImageView) rootView.findViewById(R.id.iv_title);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_message = (TextView) rootView.findViewById(R.id.tv_message);
            this.iv_iv = (ImageView) rootView.findViewById(R.id.iv_iv);
            this.tv_end = (TextView) rootView.findViewById(R.id.tv_end);
            this.ll = (LinearLayout) rootView.findViewById(R.id.ll);
        }

    }
}
