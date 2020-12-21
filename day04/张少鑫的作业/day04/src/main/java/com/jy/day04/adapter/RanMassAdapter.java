package com.jy.day04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jy.day04.R;
import com.jy.day04.model.bean.RanMassBean;

import java.util.ArrayList;

public class RanMassAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final ArrayList<RanMassBean.DataBean.ListBean> listBeans;

    public RanMassAdapter(Context context, ArrayList<RanMassBean.DataBean.ListBean> listBeans) {

        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from (context).inflate (R.layout.item_mass, parent, false);
        return new MassViewHolder (inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RanMassBean.DataBean.ListBean listBean = listBeans.get (position);
        MassViewHolder massViewHolder = (MassViewHolder) holder;
        Glide.with (context).load (listBean.getLogo ()).into (massViewHolder.mass_image);
        massViewHolder.mass_name.setText (listBean.getFullName ());
        massViewHolder.mass_number.setText ("成员:"+listBean.getIntegralnumber ());
        massViewHolder.mass_count.setText ("活动:"+listBean.getCountActivity ());
        massViewHolder.mass_note.setText (listBean.getNote ());

    }

    @Override
    public int getItemCount() {
        return listBeans.size ();
    }

    public static class MassViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView mass_image;
        public TextView mass_name;
        public TextView mass_number;
        public TextView mass_count;
        public TextView mass_note;
        public Button mass_btn;

        public MassViewHolder(View rootView) {
            super (rootView);
            this.rootView = rootView;
            this.mass_image = (ImageView) rootView.findViewById (R.id.mass_image);
            this.mass_name = (TextView) rootView.findViewById (R.id.mass_name);
            this.mass_number = (TextView) rootView.findViewById (R.id.mass_number);
            this.mass_count = (TextView) rootView.findViewById (R.id.mass_count);
            this.mass_note = (TextView) rootView.findViewById (R.id.mass_note);
            this.mass_btn = (Button) rootView.findViewById (R.id.mass_btn);
        }

    }
}
