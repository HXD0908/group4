package com.example.anli;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mEtName;
    private EditText mEtYanzhengma;
    private Button mBtnCode;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private int i = 5;
    private int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtYanzhengma = (EditText) findViewById(R.id.et_yanzhengma);
        mBtnCode = (Button) findViewById(R.id.btn_code);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);

        mBtnCode.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_code:
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        i--;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (i>=0){
                                    mBtnCode.setText("倒计时:"+i);
                                }else {
                                    random = (int) ((Math.random()*9+1)*1000);
                                    timer.cancel();
                                    Toast.makeText(MainActivity.this, "获取验证码为："+random, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }, 1000, 1000);
                break;
            case R.id.btn_login:
                String name = mEtName.getText().toString();
                String pwd = mEtYanzhengma.getText().toString();
                String pwd1 = mEtPassword.getText().toString();
                if (name.isEmpty() || pwd.isEmpty() || pwd1.isEmpty()){
                    Toast.makeText(this, "用户名,密码,确认密码不能为空", Toast.LENGTH_SHORT).show();
                }else if (name.matches("[1][0-9]{11}") || pwd.equals(random)){
                    Toast.makeText(this, "用户名输入不正确", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }

                break;
        }
    }
}