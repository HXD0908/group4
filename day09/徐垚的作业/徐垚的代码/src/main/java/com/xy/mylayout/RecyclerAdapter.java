package com.xy.mylayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by XY.
 * <p>
 * Date: 2020/12/21
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<Drawable> mList;

    public RecyclerAdapter(Context pContext, List<Drawable> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60))).into(holder.image_header);
        if (position < 4) {
            holder.iv_right.setImageDrawable(mContext.getResources().getDrawable(R.drawable.moon));
        }else {
            holder.iv_right.setImageDrawable(mContext.getResources().getDrawable(R.drawable.xing));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_header;
        private ImageView iv_right;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_header = itemView.findViewById(R.id.image_header);
            iv_right = itemView.findViewById(R.id.iv_right);
        }
    }
}
