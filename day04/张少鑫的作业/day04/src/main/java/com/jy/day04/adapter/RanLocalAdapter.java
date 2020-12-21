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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.jy.day04.R;
import com.jy.day04.model.bean.RanLocalBean;

import java.util.ArrayList;

public class RanLocalAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<RanLocalBean.DataBean.TongQianTopBean.ListBean> listBeans;

    public RanLocalAdapter(Context context, ArrayList<RanLocalBean.DataBean.TongQianTopBean.ListBean> listBeans) {

        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from (context).inflate (R.layout.item_ran, parent, false);

        return new LocalViewHolder (inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RanLocalBean.DataBean.TongQianTopBean.ListBean listBean = listBeans.get (position);
        LocalViewHolder localViewHolder = (LocalViewHolder) holder;

        Glide.with (context).load (listBean.getHeadUrl ())
                .apply (RequestOptions.bitmapTransform (new CircleCrop ()))
                .into (localViewHolder.ran_image);

        localViewHolder.ran_name.setText (listBean.getNickName ());
        localViewHolder.ran_pro.setText (listBean.getProvince ());
        localViewHolder.ran_city.setText (listBean.getCity ());
        localViewHolder.ran_exp.setText (listBean.getExpScore ()+"经验");

    }

    @Override
    public int getItemCount() {
        return listBeans.size ();
    }

    public static class LocalViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView ran_image;
        public TextView ran_name;
        public TextView ran_pro;
        public TextView ran_city;
        public TextView ran_exp;

        public LocalViewHolder(View rootView) {
            super (rootView);
            this.rootView = rootView;
            this.ran_image = (ImageView) rootView.findViewById (R.id.ran_image);
            this.ran_name = (TextView) rootView.findViewById (R.id.ran_name);
            this.ran_pro = (TextView) rootView.findViewById (R.id.ran_pro);
            this.ran_city = (TextView) rootView.findViewById (R.id.ran_city);
            this.ran_exp = (TextView) rootView.findViewById (R.id.ran_exp);
        }

    }
}
