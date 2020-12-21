package com.example.day04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.day04.R;
import com.ylong.mvp.bean.MoneyBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by XY.
 * Date: 2020/12/19
 */
public class MoneyAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<MoneyBean.DataBean.TongQianTopBean.ListBean> moneyBeans;

    public MoneyAdapter(Context context, ArrayList<MoneyBean.DataBean.TongQianTopBean.ListBean> moneyBeans) {
        this.context = context;
        this.moneyBeans = moneyBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rank_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        MoneyBean.DataBean.TongQianTopBean.ListBean bean = moneyBeans.get(position);
        holder1.tv.setText((position + 1) + "");
        holder1.tv_name.setText(bean.getNickName());
        String age = "";
        if (bean.getAge() > 0) {
            age = bean.getAge() + "";
        }
        holder1.tv_age.setText(age);
        holder1.tv_location.setText(bean.getCity());
        holder1.tv_right.setText(bean.getTongQian() + "铜钱");
        if (position < 3) {
            holder1.tv_right.setTextColor(context.getResources().getColor(R.color.colorRed));
        }

        Glide.with(context).load(bean.getHeadUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60))).into(holder1.image);
    }

    @Override
    public int getItemCount() {
        return moneyBeans.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;
        public ImageView image;
        public TextView tv_name;
        public TextView tv_age;
        public TextView tv_location;
        public TextView tv_right;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_age = (TextView) rootView.findViewById(R.id.tv_age);
            this.tv_location = (TextView) rootView.findViewById(R.id.tv_location);
            this.tv_right = (TextView) rootView.findViewById(R.id.tv_right);
        }

    }
}
