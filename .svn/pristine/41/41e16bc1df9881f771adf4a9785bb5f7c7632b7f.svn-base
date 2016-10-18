package com.hxkj.cst.cheshuotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class PhoneGetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * 返回
     */
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.rel_phone)
    RelativeLayout relPhone;
    @Bind(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    /**
     * 输入验证码
     */
    @Bind(R.id.et_yanzhengmaEdit)
    EditText etYanzhengmaEdit;
    /**
     * 获取验证码
     */
    @Bind(R.id.bt_getcode)
    Button btGetcode;
    @Bind(R.id.rel_yanzhengma)
    RelativeLayout relYanzhengma;
    @Bind(R.id.relative)
    RelativeLayout relative;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    /**
     * 跳转到新密码设置
     */
    @Bind(R.id.bt_next)
    Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_get_password);
        try{

            ButterKnife.bind(this);
            init();
        }catch (Exception e){
            ExceptionUtil.handleException(e);
        }
    }

    private void init() {
        ivBack.setOnClickListener(this);
        btNext.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case  R.id.bt_next:
                startActivity(new Intent(getApplicationContext(),PasswordResetActivity.class));
                break;
        }
    }
}
