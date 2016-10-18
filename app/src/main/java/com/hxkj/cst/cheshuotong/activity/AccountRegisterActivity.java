package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.bean.User;
import com.hxkj.cst.cheshuotong.bean.XZQH;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PreferenceUtils;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.AssertTrue;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.ValidateUsing;
import com.mobsandgeeks.saripaar.rule.MinRule;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountRegisterActivity extends Activity implements TextWatcher, View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.rel_phone)
    RelativeLayout relPhone;
    @Bind(R.id.tv_password)
    TextView tvPassword;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.rel_password)
    RelativeLayout relPassword;
    @Bind(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    @Bind(R.id.et_yanzhengmaEdit)
    EditText etYanzhengmaEdit;
    @Bind(R.id.bt_getcode)
    Button btGetcode;
    @Bind(R.id.rel_yanzhengma)
    RelativeLayout relYanzhengma;
    @Bind(R.id.tv_mail)
    TextView tvMail;
    @Bind(R.id.et_mail)
    EditText etMail;
    @Bind(R.id.view6)
    View view6;
    @Bind(R.id.rel_mail)
    RelativeLayout relMail;
    @Bind(R.id.relative)
    RelativeLayout relative;
    @Bind(R.id.bt_register)
    Button btRegister;
    Validator validator = new Validator(this);
    private TimeCount timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        etNumber.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        etYanzhengmaEdit.addTextChangedListener(this);
        etMail.addTextChangedListener(this);
        btRegister.setOnClickListener(this);
        btGetcode.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        timer= new TimeCount(60 * 1000, 1000);//构造CountDownTimer对象
    }

    @Override
    protected void onResume() {
        super.onResume();
        btRegister.setBackgroundResource(R.drawable.buttongrey);
        btRegister.setClickable(false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(etNumber.getText()) && !TextUtils.isEmpty(etPassword.getText()) && !TextUtils.isEmpty(etYanzhengmaEdit.getText())) {
            btRegister.setBackgroundResource(R.drawable.buttonblue);
            btRegister.setClickable(true);
        } else {
            btRegister.setBackgroundResource(R.drawable.buttongrey);
            btRegister.setClickable(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                register();
                break;
            case R.id.iv_back:
                timer.cancel();
                finish();
                break;
            case R.id.bt_getcode:
                checkPhone();
                break;
        }

    }

    /**
     * 检查手机号码
     */
    private void checkPhone() {
           final String phone = etNumber.getText().toString().trim();
           new MaterialDialog.Builder(this)
                   .title("确认手机号码")
                   .content("我们将发送验证码至：+86 "+phone)
                   .positiveText("确定")
                   .negativeText("取消")
                   .cancelable(false)
                   .titleGravity(GravityEnum.CENTER)
                   .contentGravity(GravityEnum.CENTER)
                   .callback(new MaterialDialog.ButtonCallback() {
                       @Override
                       public void onPositive(MaterialDialog dialog) {
                           getCode(phone);
                       }
                   })
                   .show();

    }

    /**
     * 获取验证码
     */
    private void getCode(final String phone) {
        timer.start();
        btGetcode.setClickable(false);
        btGetcode.setBackgroundResource(R.drawable.buttongrey);
        String url = ConstKey.GET_CODE + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        final String retContent = ParseReturnUtil.parseRetrun(response, AccountRegisterActivity.this);
                        if (retContent == null) {
                            btGetcode.setClickable(true);
                            btGetcode.setText("获取验证码");
                            btGetcode.setBackgroundResource(R.drawable.buttonblue);
                            timer.cancel();
                            return;
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", phone);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i("content:-->" + content);
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                        AccountRegisterActivity.this.setResult(Activity.RESULT_OK,null);
                        finish();
            }
        }
    };

    /**
     * 注册帐号
     */
    private void register() {
        final String phone = etNumber.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String code = etYanzhengmaEdit.getText().toString().trim();
        String url = ConstKey.REGISTER + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        final String retContent = ParseReturnUtil.parseRetrun(response, AccountRegisterActivity.this);
                        if (retContent == null) {
                            return;
                        }
                        new MaterialDialog.Builder(AccountRegisterActivity.this)
                                .title("你好")
                                .content("正在为你自动登录")
                                .progress(true, 0)
                                .titleGravity(GravityEnum.CENTER)
                                .contentGravity(GravityEnum.CENTER)
                                .progressIndeterminateStyle(false)
                                .show();
                        login(phone, password);
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", phone);
                map.put("password", password);
                map.put("code", code);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i("content:-->" + content);
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);
    }

    /**
     * 登陆验证
     *
     * @param phone    手机号
     * @param password 密码
     */
    private void login(final String phone, final String password) {
        String url = ConstKey.LOGIN + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        final String retContent = ParseReturnUtil.parseRetrun(response, AccountRegisterActivity.this);
                        if (retContent == null) {
                            return;
                        } else {
                            User user = GsonTools.parserJsonToArrayBean(retContent, User.class);
                            PreferenceUtils.setPrefString(TApplication.app, "UserID", user.getID());
                            PreferenceUtils.setPrefString(TApplication.app, "UserPhone", user.getPHONE());
                            PreferenceUtils.setPrefString(TApplication.app, "UserPassword", user.getPASSWORD());
                            TApplication.app.mUserId = user.getID();
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessageDelayed(msg, 2000);
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", phone);
                map.put("mtype", "android");
                map.put("password", password);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i("content:-->" + content);
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            timer.cancel();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        /**
         * 计时完毕时触发
         */
        @Override
        public void onFinish() {
            btGetcode.setText("获取验证码");
            // 又重新启用获取短信验证码按钮
            btGetcode.setEnabled(true);
            btGetcode.setClickable(true);
            btGetcode.setBackgroundResource(R.drawable.buttonblue);
        }
        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            //mGetcode.setClickable(false);
            btGetcode.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
