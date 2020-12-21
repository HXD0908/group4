package com.example.xun_day04.fragment.fragmentb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xun_day04.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCFragment extends Fragment {

    public HomeCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_c, container, false);
        return view;
    }
}
