package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hxkj.cst.cheshuotong.R;
import com.leaking.slideswitch.SlideSwitch;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PushSettingActivity extends Activity implements View.OnClickListener {

    /**
     * 返回
     */
    @Bind(R.id.iv_back)
    ImageView ivBack;
    /**
     * 新文章提醒
     */
    @Bind(R.id.slide_newsremind)
    SlideSwitch slideNewsremind;
    /**
     * 新闻推送
     */
    @Bind(R.id.slide_newspush)
    SlideSwitch slideNewspush;
    /**
     * 限行推送
     */
    @Bind(R.id.slide_limitpush)
    SlideSwitch slideLimitpush;
    @Bind(R.id.rel_update)
    RelativeLayout relUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_setting);
        try{

            ButterKnife.bind(this);
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() {
        ivBack.setOnClickListener(this);
        slideLimitpush.setOnClickListener(this);
        slideNewspush.setOnClickListener(this);
        slideNewsremind.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.slide_limitpush:
                //限行推送
                break;
            case  R.id.slide_newspush:
                //新闻推送
                break;
            case  R.id.slide_newsremind:
                //新文章提醒
                break;
        }

    }
}
