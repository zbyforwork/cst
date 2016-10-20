package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.bean.User;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PreferenceUtils;
import com.mobsandgeeks.saripaar.Validator;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountRegisterActivity extends Activity implements TextWatcher, View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.rel_phone)
    RelativeLayout relPhone;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.rel_password)
    RelativeLayout relPassword;
    @BindView(R.id.tv_yanzhengma)
    TextView tvYanzhengma;
    @BindView(R.id.et_yanzhengmaEdit)
    EditText etYanzhengmaEdit;
    @BindView(R.id.bt_getcode)
    Button btGetcode;
    @BindView(R.id.rel_yanzhengma)
    RelativeLayout relYanzhengma;
    @BindView(R.id.tv_mail)
    TextView tvMail;
    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.rel_mail)
    RelativeLayout relMail;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.bt_register)
    Button btRegister;
    Validator validator = new Validator(this);
    private TimeCount timer;

    private CallServer mCallServer;

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

        mCallServer = CallServer.getRequestInstance();
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
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content", content);
        request.add(map);
        mCallServer.add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), AccountRegisterActivity.this);
                if (retContent == null) {
                    btGetcode.setClickable(true);
                    btGetcode.setText("获取验证码");
                    btGetcode.setBackgroundResource(R.drawable.buttonblue);
                    timer.cancel();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
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
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        map.put("code", code);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content", content);
        mCallServer.add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), AccountRegisterActivity.this);
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

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    /**
     * 登陆验证
     *
     * @param phone    手机号
     * @param password 密码
     */
    private void login(final String phone, final String password) {
        String url = ConstKey.LOGIN + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("mtype", "android");
        map.put("password", password);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i("content:-->" + content);
        map.put("content", content);
        request.add(map);
        mCallServer.add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                final String retContent = ParseReturnUtil.parseRetrun(response.get(), AccountRegisterActivity.this);
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

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
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
