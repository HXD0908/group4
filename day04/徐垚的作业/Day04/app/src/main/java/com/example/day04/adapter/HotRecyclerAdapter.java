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
import com.ylong.mvp.bean.HotActivityBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by XY.
 * Date: 2020/12/18
 */
public class HotRecyclerAdapter extends RecyclerView.Adapter<HotRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<HotActivityBean.DataBean> mList;

    public HotRecyclerAdapter(Context pContext, List<HotActivityBean.DataBean> pList) {
        context = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotActivityBean.DataBean dataBean = mList.get(position);
        holder.tvTitle.setText(dataBean.getTitle());
        holder.tvLocation.setText(dataBean.getLocation());
        holder.tvStartTime.setText(dataBean.getStartTime());

        Glide.with(context).load(dataBean.getCover())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(holder.hotIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView hotIv;
        public TextView tvTitle;
        public TextView tvLocation;
        public TextView tvStartTime;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.hotIv = (ImageView) rootView.findViewById(R.id.hot_iv);
            this.tvTitle = (TextView) rootView.findViewById(R.id.hot_tv_title);
            this.tvLocation = (TextView) rootView.findViewById(R.id.tv_location);
            this.tvStartTime = (TextView) rootView.findViewById(R.id.tv_startTime);
        }
    }
}
