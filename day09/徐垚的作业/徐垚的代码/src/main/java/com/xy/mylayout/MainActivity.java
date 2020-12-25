package com.xy.mylayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler);

        ArrayList<Drawable> list = new ArrayList<>();
        list.add(getResources().getDrawable(R.drawable.ab));
        list.add(getResources().getDrawable(R.drawable.aaa));
        list.add(getResources().getDrawable(R.drawable.niao));
        list.add(getResources().getDrawable(R.drawable.aaa));
        list.add(getResources().getDrawable(R.drawable.aaa));
        list.add(getResources().getDrawable(R.drawable.aaa));
        list.add(getResources().getDrawable(R.drawable.aaa));
        list.add(getResources().getDrawable(R.drawable.ab));


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, list);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setAdapter(recyclerAdapter);
    }
}