package com.example.jetpack.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.adapter.TextAdapter;
import com.example.jetpack.api.ApiService;
import com.example.jetpack.bean.TextBean;

import java.util.ArrayList;
import java.util.List;


public class TextFragment extends Fragment {


    private View view;
    private RecyclerView mRlv;
    private ArrayList<TextBean.DataBeanX.DataBean> dataBeans;
    private TextAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_text, container, false);
        initView();
        initDate();
        return view;
    }

    private void initDate() {
        new Retrofit.Builder()
                .baseUrl(ApiService.getBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getText()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TextBean textBean) {
                        List<TextBean.DataBeanX.DataBean> data = textBean.getData().getData();
                        dataBeans.addAll(data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        dataBeans = new ArrayList<>();
        adapter = new TextAdapter(dataBeans, getActivity());
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRlv.setAdapter(adapter);
    }
}