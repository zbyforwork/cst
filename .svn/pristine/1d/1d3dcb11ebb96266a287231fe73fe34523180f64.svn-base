package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hxkj.cst.cheshuotong.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends Activity implements View.OnClickListener {


    /**
     * 返回
     */
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_pushSettingone)
    ImageView ivPushSettingone;
    /**
     * 推送设置
     */
    @Bind(R.id.rel_pushSettingone)
    RelativeLayout relPushSettingone;
    @Bind(R.id.iv_suggestionBack)
    ImageView ivSuggestionBack;
    /**
     * 意见反馈
     */
    @Bind(R.id.rel_suggestionBack)
    RelativeLayout relSuggestionBack;
    @Bind(R.id.iv_checkupdate)
    ImageView ivCheckupdate;
    /**
     * 检查更新
     */
    @Bind(R.id.rel_checkupdate)
    RelativeLayout relCheckupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        try {

            ButterKnife.bind(this);
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        ivBack.setOnClickListener(this);
        relCheckupdate.setOnClickListener(this);
        relPushSettingone.setOnClickListener(this);
        relSuggestionBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rel_pushSettingone:
                startActivity(new Intent(getApplicationContext(), PushSettingActivity.class));
                break;
            case R.id.rel_suggestionBack:
                startActivity(new Intent(getApplicationContext(), FeedBackActivity.class));
                break;

            case R.id.rel_checkupdate:
                break;
        }

    }
}
