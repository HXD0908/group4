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
import com.jy.day04.model.bean.RanExperBean;

import java.util.ArrayList;

public class RanExperAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<RanExperBean.DataBean.ExpTopBean.ListBean> listBeans;

    public RanExperAdapter(Context context, ArrayList<RanExperBean.DataBean.ExpTopBean.ListBean> listBeans) {

        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from (context).inflate (R.layout.item_ran, parent, false);
        return new ExperViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RanExperBean.DataBean.ExpTopBean.ListBean listBean = listBeans.get (position);
        ExperViewHolder experViewHolder = (ExperViewHolder) holder;
        Glide.with (context).load (listBean.getHeadUrl ())
                .apply (RequestOptions.bitmapTransform (new CircleCrop ()))
                .into (experViewHolder.ran_image);

        experViewHolder.ran_name.setText (listBean.getNickName ());
        experViewHolder.ran_pro.setText (listBean.getProvince ());
        experViewHolder.ran_city.setText (listBean.getCity ());
        experViewHolder.ran_exp.setText (listBean.getExpScore ()+"经验");


    }

    @Override
    public int getItemCount() {
        return listBeans.size ();
    }

    public static class ExperViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView ran_image;
        public TextView ran_name;
        public TextView ran_pro;
        public TextView ran_city;
        public TextView ran_exp;

        public ExperViewHolder(View rootView) {
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
