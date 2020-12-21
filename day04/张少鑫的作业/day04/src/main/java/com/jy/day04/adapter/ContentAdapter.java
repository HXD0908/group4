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
import com.jy.day04.model.bean.FindContentBean;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<FindContentBean.DataBean.ListBean> listBeans;

    public ContentAdapter(Context context, ArrayList<FindContentBean.DataBean.ListBean> listBeans) {

        this.context = context;
        this.listBeans = listBeans;
    }

    public static final int CONTENT_ONE = 0;
    public static final int CONTENT_TWO = 1;

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return CONTENT_ONE;
        } else {
            return CONTENT_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CONTENT_ONE) {
            View inflate = LayoutInflater.from (context).inflate (R.layout.item_content_one, parent, false);
            return new ContentOneViewHolder (inflate);
        } else {
            View inflate = LayoutInflater.from (context).inflate (R.layout.item_content_two, parent, false);
            return new ContentTwoViewHolder (inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType (position);
        switch (itemViewType) {
            case CONTENT_ONE:
                ContentOneViewHolder contentOneViewHolder = (ContentOneViewHolder) holder;
                FindContentBean.DataBean.ListBean listBean = listBeans.get (position);

                contentOneViewHolder.content_title.setText (listBean.getTitle ());

                Glide.with (context).load (listBean.getFilePathList ().get (position).getFilePath ()).into (contentOneViewHolder.content_image1);
                Glide.with (context).load (listBean.getFilePathList ().get (position).getFilePath ()).into (contentOneViewHolder.content_image2);
                Glide.with (context).load (listBean.getFilePathList ().get (position).getFilePath ()).into (contentOneViewHolder.content_image3);
                break;
            case CONTENT_TWO:
                ContentTwoViewHolder contentTwoViewHolder = (ContentTwoViewHolder) holder;
                FindContentBean.DataBean.ListBean listBean1 = listBeans.get (position);
                contentTwoViewHolder.two_title.setText (listBean1.getTitle ());
                contentTwoViewHolder.two_time.setText (listBean1.getCreateTime ());
                Glide.with (context).load (listBean1.getFilePathList ().get (position).getFilePath ()).into (contentTwoViewHolder.two_image);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listBeans.size ();
    }

    public static class ContentOneViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView content_title;
        public ImageView content_image1;
        public ImageView content_image2;
        public ImageView content_image3;

        public ContentOneViewHolder(View rootView) {
            super (rootView);
            this.rootView = rootView;
            this.content_title = (TextView) rootView.findViewById (R.id.content_title);
            this.content_image1 = (ImageView) rootView.findViewById (R.id.content_image1);
            this.content_image2 = (ImageView) rootView.findViewById (R.id.content_image2);
            this.content_image3 = (ImageView) rootView.findViewById (R.id.content_image3);
        }

    }

    public static class ContentTwoViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView two_title;
        public ImageView two_image;
        public TextView two_time;

        public ContentTwoViewHolder(View rootView) {
            super (rootView);
            this.rootView = rootView;
            this.two_title = (TextView) rootView.findViewById (R.id.two_title);
            this.two_image = (ImageView) rootView.findViewById (R.id.two_image);
            this.two_time = (TextView) rootView.findViewById (R.id.two_time);
        }

    }
}
