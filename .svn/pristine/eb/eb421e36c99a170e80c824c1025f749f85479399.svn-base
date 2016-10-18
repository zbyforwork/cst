package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.utils.ExceptionUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MaillGetPasswordActivity extends Activity implements View.OnClickListener {

    /**
     * 返回
     */
    @Bind(R.id.iv_Mailback)
    ImageView ivMailback;
    @Bind(R.id.et_mail)
    EditText etMail;
    @Bind(R.id.rel_mail)
    RelativeLayout relMail;
    @Bind(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    @Bind(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @Bind(R.id.bt_getcode)
    Button btGetcode;
    @Bind(R.id.rel_yanzhengma)
    RelativeLayout relYanzhengma;
    /**
     * 下一步到新密码的设置
     */
    @Bind(R.id.bt_login)
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maill_getpassword);
        try{

            ButterKnife.bind(this);
            init();
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    private void init() {
        ivMailback.setOnClickListener(this);
        btLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_Mailback:
                finish();
                break;
            case R.id.bt_login:
                startActivity(new Intent(getApplicationContext(),PasswordResetActivity.class));
                break;



        }

    }
}
