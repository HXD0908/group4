package com.jy.day04.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.day04.R;
import com.jy.day04.adapter.RanExperAdapter;
import com.jy.day04.adapter.RanLocalAdapter;
import com.jy.day04.model.api.FindApiService;
import com.jy.day04.model.bean.RanLocalBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RanLocalFragment extends Fragment {

    private RecyclerView mRecyclerLocal;
    private ArrayList<RanLocalBean.DataBean.TongQianTopBean.ListBean> listBeans;
    private RanLocalAdapter ranLocalAdapter;

    private static final String TAG = "RanLocalFragment";

    public RanLocalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate (R.layout.fragment_ran_local, container, false);
        initView (inflate);
        initData ();
        return inflate;
    }

    private void initData() {
        new Retrofit.Builder ()
                .baseUrl (FindApiService.baseUrl)
                .addCallAdapterFactory (RxJava2CallAdapterFactory.create ())
                .addConverterFactory (GsonConverterFactory.create ())
                .build ()
                .create (FindApiService.class)
                .getLocal ()
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribeOn (Schedulers.io ())
                .subscribe (new Observer<RanLocalBean> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RanLocalBean ranLocalBean) {
                        List<RanLocalBean.DataBean.TongQianTopBean.ListBean> list = ranLocalBean.getData ().getTongQianTop ().getList ();
                        listBeans.addAll (list);
                        ranLocalAdapter.notifyDataSetChanged ();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e (TAG, "onError: 错误信息:" + e.getMessage ());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(@NonNull final View itemView) {
        mRecyclerLocal = (RecyclerView) itemView.findViewById (R.id.local_recycler);
        mRecyclerLocal.setLayoutManager (new LinearLayoutManager (getActivity ()));
        listBeans = new ArrayList<> ();
        ranLocalAdapter = new RanLocalAdapter (getActivity (), listBeans);
        mRecyclerLocal.setAdapter (ranLocalAdapter);
        mRecyclerLocal.addItemDecoration (new DividerItemDecoration (getActivity (), DividerItemDecoration.VERTICAL));

    }
}
