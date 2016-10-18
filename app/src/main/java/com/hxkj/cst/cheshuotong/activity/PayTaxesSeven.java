package com.hxkj.cst.cheshuotong.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.bean.JSXX;
import com.hxkj.cst.cheshuotong.bean.PaytaxOrderDetail;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PayTaxesSeven extends AppCompatActivity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_car_image)
    SimpleDraweeView mIvCarImage;
    @Bind(R.id.tv_idcard)
    TextView mTvIdcard;
    @Bind(R.id.tv_tel)
    TextView mTvTel;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.tv_carType)
    TextView mTvCarType;
    @Bind(R.id.tv_carNumber)
    TextView mTvCarNumber;
    @Bind(R.id.tv_chejiahao)
    TextView mTvChejiahao;
    @Bind(R.id.tv_fadongjihao)
    TextView mTvFadongjihao;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.label_address)
    TextView mLabelAddress;
    @Bind(R.id.bt_appointment)
    Button mBtAppointment;
    @Bind(R.id.bt_pay_taxes)
    Button mBtPayTaxes;
    @Bind(R.id.tv_ybtse)
    TextView mTvYbtse;
    @Bind(R.id.tv_min_price)
    TextView mTvMinPrice;
    @Bind(R.id.tv_deadline)
    TextView mTvDeadline;

    private JSXX mJSXX;
    private PaytaxOrderDetail mOrderDetail;
    private String mStatus;  //状态码
    private String mSwjgdm;
    private static final String TN_URL_01 = "http://101.231.204.84:8091/sim/getacptn";
    /**
     * 前一个页面
     * 0：首页订单
     * 1：选择车型
     */
    private int mForwardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_taxes_seven);
        ButterKnife.bind(this);
        TApplication.app.addActivity(this);
        setListener();
        init();
        TApplication.app.addActivity(this);
    }

    private void init() {
        mForwardType = getIntent().getIntExtra("Type", -1);
        if (mForwardType == 1) {
            String imageUrl = getIntent().getStringExtra("image");
            if (imageUrl != null) {
                mIvCarImage.setImageURI(Uri.parse(ConstKey.IMGAE_ROOT + imageUrl));
            }
            getJsxxData();
        }
        if (mForwardType == 0) {
            getOrderDetailData();
        }
    }

    private void setListener() {
        mBtPayTaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (mStatus.equals("1")) {
                    shwoUnderHandle();
                } else {
                    showBilding();
                }*/
                testPay();
            }
        });
        mBtAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStatus.equals("1")) {
                    shwoUnderHandle();
                } else if (mStatus.equals("5") && !TextUtils.isEmpty(mSwjgdm)) {
                    Intent intent = new Intent(PayTaxesSeven.this, ShowAppointmentActivity.class);
                    intent.putExtra("swjgdm", mSwjgdm);
                    startActivity(intent);
                } else if (mStatus.equals("2")) {
                    startActivity(new Intent(PayTaxesSeven.this, AppointmentActivity.class));
                }
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TApplication.app.exitPayTax();
            }
        });
    }

    private void testPay() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, TN_URL_01,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i("tn--->" + response);
                        UPPayAssistEx.startPayByJAR(PayTaxesSeven.this, PayActivity.class, null, null,
                                response, "01");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            msg = "支付成功！";
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
        }
        new MaterialDialog.Builder(this)
                .title("支付结果通知")
                .content(msg)
                .positiveText("确定")
                .titleGravity(GravityEnum.CENTER)
                .contentGravity(GravityEnum.CENTER)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showBilding() {
        new MaterialDialog.Builder(PayTaxesSeven.this)
                .title("你好")
                .content("缴税功能正在开发中，请期待我们的下一个版本")
                .positiveText("返回首页")
                .negativeText("取消")
                .titleGravity(GravityEnum.CENTER)
                .contentGravity(GravityEnum.CENTER)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        retToMain();
                        finish();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


    private void retToMain() {
        TApplication.app.exitPayTax();
    }


    /**
     * 得到车辆信息
     */
    private void getJsxxData() {
        String url = ConstKey.CLJSSB_ADDRESS + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String retContent = ParseReturnUtil.parseRetrun(response, PayTaxesSeven.this);
                        if (TextUtils.isEmpty(retContent)) {
                            return;
                        }
                        MyLog.i(retContent);
                        mJSXX = GsonTools.parserJsonToArrayBean(retContent, JSXX.class);
                        showJSXX();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("CLJSDD", "510100");
                map.put("CLLBID", TApplication.app.mCLLBID);
                map.put("CLJSSBID", TApplication.app.mCLJSSBID);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i(content);
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);

    }

    /**
     * 得到订单详情
     */
    public void getOrderDetailData() {
        final String orderID = getIntent().getStringExtra("orderId");
        MyLog.i("getOrderDetailData -->orderId  " + orderID);
        String url = ConstKey.GRJSDD_DETAIL_ADDRESS + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        MyLog.i("getOrderDetailData response" + response);
                        String retContent = ParseReturnUtil.parseRetrun(response, PayTaxesSeven.this);
                        if (TextUtils.isEmpty(retContent)) {
                            return;
                        }
                            /*JSONObject object=new JSONObject(retContent);
                            MyLog.e(retContent);
                            String orderDeatil=object.getJSONArray("DDLB").get(0).toString();*/
                        mOrderDetail = GsonTools.parserJsonToArrayBean(retContent, PaytaxOrderDetail.class);
                        showOrderDetail();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("DDID", orderID);
                String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
                map.clear();
                MyLog.i(content);
                map.put("content", content);
                return map;
            }
        };
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);
    }

    /**
     * 显示信息
     */
    private void showJSXX() {
        MyLog.i(mJSXX.toString());
        mTvName.setText(mJSXX.getGHDW());
        mTvIdcard.setText(mJSXX.getSFZHM());
        mTvTel.setText(mJSXX.getNSRDH());
        mTvAddress.setText(mJSXX.getNSRDZ());
        mTvCarType.setText(mJSXX.getCX());
        mTvCarNumber.setText(mJSXX.getCPXH());
        mTvChejiahao.setText(mJSXX.getCJHM());
        mTvFadongjihao.setText(mJSXX.getFDJHM());
        mTvPrice.setText(mJSXX.getSE());
        mTvYbtse.setText(mJSXX.getYBTSE());
        mTvMinPrice.setText(mJSXX.getMINPRICE());
        mTvDeadline.setText(mJSXX.getXJRQ());
        mStatus = mJSXX.getSTATUS();
        if (mStatus.equals("1")) {
            shwoUnderHandle();
        }
    }

    /**
     * 显示订单详细信息
     */
    private void showOrderDetail() {
        MyLog.e("mOrderDetail-->" + mOrderDetail);
        mIvCarImage.setImageURI(Uri.parse(ConstKey.IMGAE_ROOT + mOrderDetail.getBIGIMG()));
        mTvName.setText(mOrderDetail.getGHDW());
        mTvIdcard.setText(mOrderDetail.getSFZHM());
        mTvTel.setText(mOrderDetail.getNSRDH());
        mTvAddress.setText(mOrderDetail.getNSRDZ());
        mTvCarType.setText(mOrderDetail.getCX());
        mTvCarNumber.setText(mOrderDetail.getCPXH());
        mTvChejiahao.setText(mOrderDetail.getCJHM());
        mTvFadongjihao.setText(mOrderDetail.getFDJHM());
        mTvPrice.setText(mOrderDetail.getSE());
        mTvYbtse.setText(mOrderDetail.getYBTSE());
        mTvMinPrice.setText(mOrderDetail.getMINPRICE());
        mTvDeadline.setText(mOrderDetail.getXJRQ());
        mStatus = mOrderDetail.getSTATUS();
        TApplication.app.mSelectXZQHDM = mOrderDetail.getCLJSDD();
        TApplication.app.mCLLBID = mOrderDetail.getCLLBID();
        TApplication.app.mCLJSSBID = mOrderDetail.getCLJSSBID();
        if (mStatus.equals("1")) {
            shwoUnderHandle();
        }
        if (mStatus.equals("5")) {
            mBtAppointment.setText("查看预约");
            mSwjgdm = mOrderDetail.getSWJGDM();
        }
    }

    private void shwoUnderHandle() {
        new MaterialDialog.Builder(this)
                .title("你好")
                .content("缴税请求已发往税务局，税务局正在处理中，你可以通过 点击 首页—订单 继续下一步操作")
                .positiveText("返回首页")
                .negativeText("取消")
                .titleGravity(GravityEnum.CENTER)
                .contentGravity(GravityEnum.CENTER)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        retToMain();
                        finish();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            TApplication.app.exitPayTax();
        }
        return false;
    }
}
