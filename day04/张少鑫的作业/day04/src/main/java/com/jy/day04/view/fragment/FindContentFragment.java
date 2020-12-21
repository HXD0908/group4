package com.jy.day04.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.day04.R;
import com.jy.day04.adapter.ContentAdapter;
import com.jy.day04.model.api.FindApiService;
import com.jy.day04.model.bean.FindContentBean;

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
public class FindContentFragment extends Fragment {

    private static final String TAG = "FindContentFragment";

    private RecyclerView mRecyclerContent;
    private int type;
    private ArrayList<FindContentBean.DataBean.ListBean> listBeans;
    private ContentAdapter contentAdapter;

    public FindContentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        type = getArguments ().getInt ("type");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate (R.layout.fragment_find_content, container, false);
        initView (inflate);
        initData ();
        return inflate;
    }

    private void initData() {

        new Retrofit.Builder ()
                .baseUrl (FindApiService.baseUrl)
                .addConverterFactory (GsonConverterFactory.create ())
                .addCallAdapterFactory (RxJava2CallAdapterFactory.create ())
                .build ()
                .create (FindApiService.class)
                .getContent (type)
                .subscribeOn (Schedulers.io ())
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new Observer<FindContentBean> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindContentBean findContentBean) {
                        List<FindContentBean.DataBean.ListBean> list = findContentBean.getData ().getList ();
                        listBeans.addAll (list);
                        contentAdapter.notifyDataSetChanged ();
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
        mRecyclerContent = (RecyclerView) itemView.findViewById (R.id.content_recycler);
        mRecyclerContent.setLayoutManager (new LinearLayoutManager (getActivity ()));
        listBeans = new ArrayList<> ();
        contentAdapter = new ContentAdapter (getActivity (), listBeans);
        mRecyclerContent.setAdapter (contentAdapter);
    }
}
