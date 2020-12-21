package com.jy.day04.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.day04.R;
import com.jy.day04.adapter.FindAdapter;
import com.jy.day04.model.api.FindApiService;
import com.jy.day04.model.bean.FindMoreBean;
import com.jy.day04.model.bean.FindTitleBean;
import com.jy.day04.presenter.ImpFindPresenter;
import com.jy.day04.view.FindView;
import com.jy.day04.view.activity.RanKingActivity;
import com.jy.day04.view.activity.RanMassActivity;

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
public class FindFragment extends Fragment implements FindView {

    private static final String TAG = "FindFragment";
    private TextView mTitleFind;
    private Toolbar mToolbarFind;
    private ImageView mImage1Find;
    private TextView mTitle1Find;
    private ImageView mImage2Find;
    private TextView mTitle2Find;
    private ImageView mImage3Find;
    private TextView mTitle3Find;
    private TextView mActivityFind;
    private TextView mMoreFind;
    private RecyclerView mRecyclerFind;
    private ArrayList<FindMoreBean.DataBean> dataBeans;
    private FindAdapter findAdapter;
    private ImpFindPresenter impFindPresenter;
    private TabLayout mTablayoutFind;
    private ViewPager mViewpagerFind;

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate (R.layout.fragment_find, container, false);
        initView (inflate);
        initData ();
        initListener ();
        initFragment ();
        return inflate;
    }

    private void initListener() {

        mImage3Find.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getActivity (), RanKingActivity.class));
            }
        });

        mTitle3Find.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getActivity (), RanKingActivity.class));
            }
        });

        mImage2Find.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getActivity (), RanMassActivity.class));
            }
        });

        mTitle2Find.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getActivity (), RanMassActivity.class));
            }
        });
    }

    private void initFragment() {
        new Retrofit.Builder ()
                .baseUrl (FindApiService.baseUrl)
                .addCallAdapterFactory (RxJava2CallAdapterFactory.create ())
                .addConverterFactory (GsonConverterFactory.create ())
                .build ()
                .create (FindApiService.class)
                .getTitle ()
                .observeOn (AndroidSchedulers.mainThread ())
                .subscribeOn (Schedulers.io ())
                .subscribe (new Observer<FindTitleBean> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindTitleBean findTitleBean) {
                        List<FindTitleBean.DataBean> data = findTitleBean.getData ();

                        List<Fragment> list = new ArrayList<> ();

                        for (int i = 0; i < data.size (); i++) {
                            int type = data.get (i).getType ();

                            FindContentFragment findContentFragment = new FindContentFragment ();

                            Bundle bundle = new Bundle ();
                            bundle.putInt ("type", type);

                            findContentFragment.setArguments (bundle);

                            list.add (findContentFragment);
                        }
                        mViewpagerFind.setAdapter (new FragmentPagerAdapter (getChildFragmentManager ()) {
                            @NonNull
                            @Override
                            public Fragment getItem(int position) {
                                return list.get (position);
                            }

                            @Override
                            public int getCount() {
                                return list.size ();
                            }
                        });
                        mTablayoutFind.setupWithViewPager (mViewpagerFind);
                        for (int i = 0; i < data.size (); i++) {
                            mTablayoutFind.getTabAt (i).setText (data.get (i).getName ());

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initData() {
        impFindPresenter = new ImpFindPresenter (this);
        impFindPresenter.getMore ();
    }

    private void initView(@NonNull final View itemView) {
        mTitleFind = (TextView) itemView.findViewById (R.id.find_title);
        mToolbarFind = (Toolbar) itemView.findViewById (R.id.find_toolbar);
        mImage1Find = (ImageView) itemView.findViewById (R.id.find_image1);
        mTitle1Find = (TextView) itemView.findViewById (R.id.find_title1);
        mImage2Find = (ImageView) itemView.findViewById (R.id.find_image2);
        mTitle2Find = (TextView) itemView.findViewById (R.id.find_title2);
        mImage3Find = (ImageView) itemView.findViewById (R.id.find_image3);
        mTitle3Find = (TextView) itemView.findViewById (R.id.find_title3);
        mActivityFind = (TextView) itemView.findViewById (R.id.find_activity);
        mMoreFind = (TextView) itemView.findViewById (R.id.find_more);
        mRecyclerFind = (RecyclerView) itemView.findViewById (R.id.find_recycler);
        mTablayoutFind = (TabLayout) itemView.findViewById (R.id.find_tablayout);
        mViewpagerFind = (ViewPager) itemView.findViewById (R.id.find_viewpager);


        LinearLayoutManager linearLayoutManage = new LinearLayoutManager (getActivity ());
        linearLayoutManage.setOrientation (LinearLayoutManager.HORIZONTAL);
        mRecyclerFind.setLayoutManager (linearLayoutManage);

        dataBeans = new ArrayList<> ();
        findAdapter = new FindAdapter (getActivity (), dataBeans);
        mRecyclerFind.setAdapter (findAdapter);
//        mRecyclerFind.addItemDecoration (new DividerItemDecoration (getActivity (), DividerItemDecoration.HORIZONTAL));
//        mRecyclerFind.addItemDecoration (new DividerItemDecoration (getActivity (), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void OnMoreSuccess(FindMoreBean findMoreBean) {
        List<FindMoreBean.DataBean> data = findMoreBean.getData ();
        dataBeans.addAll (data);
        findAdapter.notifyDataSetChanged ();
    }

    @Override
    public void OnFail(String Error) {
        Log.e (TAG, "OnFail: 错误信息:" + Error);
    }
}
