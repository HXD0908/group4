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
import com.example.jetpack.bean.TextBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextAdapter extends RecyclerView.Adapter {
    private ArrayList<TextBean.DataBeanX.DataBean> dataBeans;
    private Context mContext;

    public TextAdapter(ArrayList<TextBean.DataBeanX.DataBean> dataBeans, Context mContext) {
        this.dataBeans = dataBeans;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv_message.setText(dataBeans.get(position).getAuthor().getName());
        Glide.with(mContext).load(dataBeans.get(position).getAuthor().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder1.iv_title);
        holder1.tv_title.setText(dataBeans.get(position).getAuthor().getQqOpenId());
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_title;
        public TextView tv_title;
        public TextView tv_message;
        public LinearLayout ll;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_title = (ImageView) rootView.findViewById(R.id.iv_title);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_message = (TextView) rootView.findViewById(R.id.tv_message);
            this.ll = (LinearLayout) rootView.findViewById(R.id.ll);
        }

    }
}
