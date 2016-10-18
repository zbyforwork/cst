package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.utils.ExceptionUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WayOfGetpasswordActivity extends Activity implements View.OnClickListener {

    /**
     * 返回
     */
    @Bind(R.id.iv_back)
    ImageView ivBack;
    /**
     * 手机找回
     */
    @Bind(R.id.rel_byphone)
    RelativeLayout relByphone;
    /**
     * 邮箱找回
     */
   /* @Bind(R.id.rel_byMail)
    RelativeLayout relByMail;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_of_getpassword);
        try {

            ButterKnife.bind(this);
            init();

        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    private void init() {
        ivBack.setOnClickListener(this);
        relByphone.setOnClickListener(this);
        /*relByMail.setOnClickListener(this);*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rel_byphone:
                startActivity(new Intent(getApplicationContext(), PhoneGetPasswordActivity.class));

                break;

            /*case R.id.rel_byMail:
                startActivity(new Intent(getApplicationContext(), MaillGetPasswordActivity.class));
                break;*/

        }
    }
}
