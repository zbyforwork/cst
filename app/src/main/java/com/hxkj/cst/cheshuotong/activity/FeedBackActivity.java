package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.hxkj.cst.cheshuotong.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends Activity implements View.OnClickListener{

    /**
     * 返回
     */
    @BindView(R.id.iv_back)
    ImageView ivBack;
    /**
     * 用户账号
     */
    @BindView(R.id.et_Number)
    EditText etNumber;
    /**
     * 意见内容
     */
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.sc_content)
    ScrollView scContent;
    /**
     * 提交
     */
    @BindView(R.id.bt_submit)
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ivBack.setOnClickListener(this);
        etNumber.setOnClickListener(this);
        etContent.setOnClickListener(this);
        btSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_Number:
                //用户输入账号
                break;
            case  R.id.et_content:
                //用户输入意见内容
                break;
            case R.id.bt_submit:
                //用户提交信息
                break;
        }

    }
}
