package com.hxkj.cst.cheshuotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.utils.ExceptionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountRegisterFirstActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 返回
     */
    @BindView(R.id.iv_back)
    ImageView ivBack;
    /**
     * 扫描二维码输入
     */
    @BindView(R.id.rel_erweima)
    RelativeLayout relErweima;
    /**
     * 手动输入
     */
    @BindView(R.id.rel_byhand)
    RelativeLayout relByhand;
    /**
     * 扫描解析申报表二维码进行注册
     */
    @BindView(R.id.registerAnother)
    TextView registerAnother;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register_first);
        ButterKnife.bind(this);
        try {

            ButterKnife.bind(this);
            init();
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    private void init() {
        ivBack.setOnClickListener(this);
        relErweima.setOnClickListener(this);
        relByhand.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rel_erweima:
                //扫描二维码输入
                break;
            case R.id.rel_byhand:
                startActivity(new Intent(getApplicationContext(),AccountRegisterActivity.class));
                break;
            case R.id.registerAnother:
                //扫描解析申报表二维码进行注册
                break;
        }

    }
}
