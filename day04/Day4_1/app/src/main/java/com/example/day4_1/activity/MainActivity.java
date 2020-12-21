package com.example.day4_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day4_1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        logo = (Button) findViewById(R.id.logo);

        logo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logo:
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                break;
        }
    }
}