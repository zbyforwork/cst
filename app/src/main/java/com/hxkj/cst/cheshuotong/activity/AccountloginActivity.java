package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.bean.User;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PreferenceUtils;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountloginActivity extends Activity implements TextWatcher, View.OnClickListener {

    /**
     * 返回
     */
    @BindView(R.id.iv_back)
    ImageView ivBack;

    /**
     * 输入电话号码
     */
    @BindView(R.id.et_number)
    EditText etNumber;

    /**
     * 输入密码
     */
    @BindView(R.id.et_password)
    EditText etPassword;
    /**
     * 登录
     */
    @BindView(R.id.bt_login)
    Button btLogin;
    /**
     * 注册
     */
    @BindView(R.id.tv_register)
    TextView tvRegister;
    /**
     * 找回密码
     */
    @BindView(R.id.tv_getmssingcode)
    TextView tvGetmssingcode;

    // 输入软键盘管理者
    private InputMethodManager imm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btLogin.setBackgroundResource(R.drawable.buttongrey);
        btLogin.setClickable(false);
        etNumber.setText("");
        etPassword.setText("");
    }

    private void initView() {
        etNumber.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        btLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvGetmssingcode.setOnClickListener(this);
    }

    /**
     * 验证下手机号码格式对不对
     */
    public boolean checkPhone() {
        boolean flag = false;
        String phone = etNumber.getText().toString().trim();
        //定义判别用户手机号的正则表达式
        Pattern idNumPattern = Pattern.compile("^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$");
        //通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(phone);
        //判断用户输入是否为手机号
        if (idNumMatcher.matches()) {
            flag = true;
        } else {
            //如果不是，输出信息提示用户
            Toast.makeText(getApplicationContext(), "您输入的并不是手机号码!", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_login:
                if (true) {//checkPhone()) {
                    String phone = etNumber.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    login(phone, password);
                    // 自动隐藏软键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AccountloginActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                break;
            case R.id.tv_register:
                startActivityForResult(new Intent(getApplicationContext(), AccountRegisterActivity.class), 101);
                break;
            case R.id.tv_getmssingcode:
                startActivity(new Intent(getApplicationContext(), WayOfGetpasswordActivity.class));
                break;
        }

    }

    /**
     * 登陆验证
     *
     * @param phone    手机号
     * @param password 密码
     */
    private void login(final String phone, final String password) {
        String url = ConstKey.LOGIN + ParamsBuilder.getParams();
        CallServer callServer = CallServer.getRequestInstance();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("mtype", "android");
        map.put("password", password);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content", content);
        request.add(map);
        callServer.add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), AccountloginActivity.this);
                if (retContent == null) {
                    return;
                } else {
                    User user = GsonTools.parserJsonToArrayBean(retContent, User.class);
                    PreferenceUtils.setPrefString(TApplication.app, "UserID", user.getID());
                    PreferenceUtils.setPrefString(TApplication.app, "UserPhone", user.getPHONE());
                    PreferenceUtils.setPrefString(TApplication.app, "UserPassword", user.getPASSWORD());
                    TApplication.app.mUserId = user.getID();
                    MyToast.show(AccountloginActivity.this, "登录成功");
                    finish();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        }, false, false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(etNumber.getText()) && !TextUtils.isEmpty(etPassword.getText())) {
            btLogin.setBackgroundResource(R.drawable.buttonblue);
            btLogin.setClickable(true);
        } else {
            btLogin.setBackgroundResource(R.drawable.buttongrey);
            btLogin.setClickable(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            finish();
        }
    }
}
