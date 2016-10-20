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

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneGetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * 返回
     */
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.rel_phone)
    RelativeLayout relPhone;
    @BindView(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    /**
     * 输入验证码
     */
    @BindView(R.id.et_yanzhengmaEdit)
    EditText etYanzhengmaEdit;
    /**
     * 获取验证码
     */
    @BindView(R.id.bt_getcode)
    Button btGetcode;
    @BindView(R.id.rel_yanzhengma)
    RelativeLayout relYanzhengma;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    /**
     * 跳转到新密码设置
     */
    @BindView(R.id.bt_next)
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
