package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.utils.ExceptionUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 2015-10-13 16:14:03
 */
public class PasswordResetActivity extends Activity {


    /**
     * 返回
     */
    @Bind(R.id.iv_Passwordback)
    ImageView ivPasswordback;


    /**
     * 重新输入密码
     */
    @Bind(R.id.et_newpassword)
    EditText etNewpassword;

    @Bind(R.id.rel_password)
    RelativeLayout relPassword;
    @Bind(R.id.relative)
    RelativeLayout relative;
    @Bind(R.id.tv_chongfu)
    TextView tvChongfu;
    /**
     * 再次输入密码
     */
    @Bind(R.id.et_chongfu)
    EditText etChongfu;
    /**
     * 提示密码输入正确与否
     */
    @Bind(R.id.tv_info)
    TextView tvInfo;
    @Bind(R.id.rel_chongfu)
    RelativeLayout relChongfu;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    /**
     * 提交
     */
    @Bind(R.id.bt_login)
    Button btLogin;

    // 输入软键盘管理者
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        try {
            ButterKnife.bind(this);
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }

    }

    public void submit(View v) {
        if (!isCommon()) {
            tvInfo.setVisibility(View.VISIBLE);
        } else {
            tvInfo.setVisibility(View.INVISIBLE);
        }
    }

    public boolean isCommon() {

        String pwd = etNewpassword.getText().toString().trim();
        String nPwd =  etChongfu.getText().toString().trim();

        if ("".equals(pwd)) {
            return false;
        }

        if (nPwd.equals(pwd)) {
            return true;
        } else {
            return false;
        }

    }


}
