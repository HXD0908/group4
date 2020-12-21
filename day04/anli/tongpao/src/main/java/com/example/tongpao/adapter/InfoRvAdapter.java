package com.example.tongpao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.tongpao.R;
import com.example.tongpao.bean.InfoBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfoRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<InfoBean.DataBean.ListBean> list;
    Context context;

    int VIEW_TYPE_ONE=1;
    int VIEW_TYPE_TWO=2;
    int VIEW_TYPE_THREE=3;

    public InfoRvAdapter(List<InfoBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType==1){
           return new ViewHolderOne(LayoutInflater.from(context).inflate(R.layout.info_one,parent,false));
       }else if (viewType==2){
           return new ViewHolderTwo(LayoutInflater.from(context).inflate(R.layout.info_two,parent,false));

       }else {
           return new ViewHolderThree(LayoutInflater.from(context).inflate(R.layout.info_three,parent,false));
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        InfoBean.DataBean.ListBean listBean = list.get(position);
        List<InfoBean.DataBean.ListBean.FilePathListBean> filePathList = listBean.getFilePathList();
        int itemViewType = getItemViewType(position);
        if (itemViewType==1){
            ViewHolderOne holderOne= (ViewHolderOne) holder;
            holderOne.time.setText(listBean.getCreateTime());
            holderOne.title.setText(listBean.getTitle());
            Glide.with(context).load(filePathList.get(0).getFilePath()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .into(holderOne.iv1);
            Glide.with(context).load(filePathList.get(1).getFilePath()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .into(holderOne.iv2);
            Glide.with(context).load(filePathList.get(2).getFilePath()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .into(holderOne.iv3);
        }else if (itemViewType==2){
            ViewHolderTwo holderTwo= (ViewHolderTwo) holder;
            holderTwo.time.setText(listBean.getCreateTime());
            holderTwo.title.setText(listBean.getTitle());
            Glide.with(context).load(filePathList.get(0).getFilePath()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .into(holderTwo.iv);
        }else {
            ViewHolderThree holderThree= (ViewHolderThree) holder;
            holderThree.time.setText(listBean.getCreateTime());
            holderThree.title.setText(listBean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderOne extends RecyclerView.ViewHolder{

        private ImageView iv1;
        private ImageView iv2;
        private ImageView iv3;
        private TextView title;
        private TextView time;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            iv1=itemView.findViewById(R.id.iv1);
            iv2=itemView.findViewById(R.id.iv2);
            iv3=itemView.findViewById(R.id.iv3);
            title=itemView.findViewById(R.id.tv_title);
            time=itemView.findViewById(R.id.tv_time);

        }
    }
    class ViewHolderTwo extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView title;
        private TextView time;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            title=itemView.findViewById(R.id.tv_title);
            time=itemView.findViewById(R.id.tv_time);
        }
    }
    class ViewHolderThree extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView time;
        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_title);
            time=itemView.findViewById(R.id.tv_time);
        }
    }
    @Override
    public int getItemViewType(int position) {

        List<InfoBean.DataBean.ListBean.FilePathListBean> filePathList = list.get(position).getFilePathList();
        if (filePathList.size()>1){
            return 1;
        }else if (filePathList.size()==1){
            return 2;
        }else {
            return 3;
        }
    }
}
