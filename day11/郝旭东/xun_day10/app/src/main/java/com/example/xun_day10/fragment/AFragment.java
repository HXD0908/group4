package com.example.xun_day10.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xun_day10.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {

    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        return view;
    }
}
