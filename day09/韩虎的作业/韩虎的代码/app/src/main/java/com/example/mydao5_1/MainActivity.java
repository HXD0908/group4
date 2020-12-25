package com.example.mydao5_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SearchView sv;
    private ImageView iva;
    private ImageView ivb;
    private LinearLayout lla;
    private LinearLayout llb;
    private TextView tva;
    private ImageView ivc;
    private Button buta;
    private TextView tvb;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        sv = (SearchView) findViewById(R.id.sv);
        iva = (ImageView) findViewById(R.id.iva);
        ivb = (ImageView) findViewById(R.id.ivb);
        lla = (LinearLayout) findViewById(R.id.lla);
        llb = (LinearLayout) findViewById(R.id.llb);
        tva = (TextView) findViewById(R.id.tva);
        ivc = (ImageView) findViewById(R.id.ivc);
        buta = (Button) findViewById(R.id.buta);
        tvb = (TextView) findViewById(R.id.tvb);
        lv = (ListView) findViewById(R.id.lv);

        buta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buta:

                break;
        }
    }
}