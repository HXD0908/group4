package com.example.tongpao.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<DATA> extends RecyclerView.Adapter {

   protected List<DATA> mData;
   protected Context context;

    public BaseAdapter(Context context, List<DATA> data) {
        this.context=context;
        mData=data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterOnItemClickListener!=null){
                    adapterOnItemClickListener.OnItemClickListener(viewHolder.getLayoutPosition());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DATA data = mData.get(position);
        bindData(data, (ViewHolder) holder,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected abstract void bindData(DATA data, ViewHolder viewHolder,int position);

    protected abstract  int getLayout();

    public class ViewHolder extends RecyclerView.ViewHolder{

       SparseArray views= new SparseArray();
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public View getViewById(int id){
            View view  = (View) views.get(id);
            if (view==null){
                view=itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
    public interface AdapterOnItemClickListener{
        void OnItemClickListener(int position);
    }
    private AdapterOnItemClickListener adapterOnItemClickListener;

    public void setAdapterOnItemClickListener(AdapterOnItemClickListener adapterOnItemClickListener) {
        this.adapterOnItemClickListener = adapterOnItemClickListener;
    }
}
