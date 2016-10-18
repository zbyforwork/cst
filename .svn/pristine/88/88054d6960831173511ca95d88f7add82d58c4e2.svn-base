package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.hxkj.cst.cheshuotong.utils.ExceptionUtil;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.hxkj.cst.cheshuotong.utils.PreferenceUtils;
import com.jp.wheelview.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hugo.android.scanner.CaptureActivity;

public class PayTaxesActivity extends Activity {

    @Bind(R.id.tv_place)
    TextView mTvPlace;
    @Bind(R.id.bt_next)
    Button mBtNext;
    @Bind(R.id.mv_province)
    WheelView mMvProvince;
    @Bind(R.id.mv_city)
    WheelView mMvCity;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    private ArrayList<XZQH> xzqhArrayList;
    private String mXZQHDM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_taxes);
        TApplication.app.addActivity(this);
        ButterKnife.bind(this);
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getData() {
        String url = ConstKey.GET_XZQHLB_ADDRESS + ParamsBuilder.getParams();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        final String retContent = ParseReturnUtil.parseRetrun(response, PayTaxesActivity.this);
                        if (retContent == null) {
                            return;
                        } else {
                            MyLog.i(retContent);
                            xzqhArrayList = (ArrayList<XZQH>) GsonTools.parserJsonToArrayBeans(retContent, "XZQHLB", XZQH.class);
                            init();
                            setListener();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        // 把这个请求加入请求队列
        TApplication.app.addToRequestQueue(stringRequest);

    }

    private void init() {
        mMvProvince.setData(xzqhArrayList);
        MyLog.i(xzqhArrayList.toString());
        mMvProvince.setDefault(0);
        //判断默认第一个是否为直辖市
       /* String proviceName = xzqhArrayList.get(0).getXZQHMC();
        if (proviceName.equals("北京市") || proviceName.equals("重庆市") || proviceName.equals("上海市") || proviceName.equals("天津市")) {
            List<XZQH> temp = new ArrayList<>();
            temp.add(xzqhArrayList.get(0));
            mMvCity.setData(temp);
        } else {
            mMvCity.setData(xzqhArrayList.get(0).getXSXZQH());
        }*/
        mMvCity.setData(xzqhArrayList.get(0).getXSXZQH());
        mMvCity.setDefault(0);

        TApplication.app.mSelectXZQHDM = mMvCity.getSelectedItem().getXZQHDM();
        mTvPlace.setText(mMvProvince.getSelectedText() + mMvCity.getSelectedText());
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    XZQH xzqh = (XZQH) msg.getData().getSerializable("XZQH");
                    //判断是否为直辖市
                  /*  String proviceName = xzqhArrayList.get(0).getXZQHMC();
                    if (proviceName.equals("北京市") || proviceName.equals("重庆市") || proviceName.equals("上海市") || proviceName.equals("天津市")) {
                        List<XZQH> temp = new ArrayList<>();
                        temp.add(xzqh);
                        mMvCity.setData(temp);
                    } else {
                        mMvCity.setData(xzqh.getXSXZQH());
                    }*/
                    mMvCity.setData(xzqh.getXSXZQH());
                    mMvCity.setDefault(0);
                    TApplication.app.mSelectXZQHDM = xzqh.getXSXZQH().get(0).getXZQHDM();
                    mTvPlace.setText(mMvProvince.getSelectedText() + mMvCity.getSelectedText());
                    break;

                case 1:
                    XZQH xzqh1 = (XZQH) msg.getData().getSerializable("XZQH");
                    mTvPlace.setText(mMvProvince.getSelectedText() + mMvCity.getSelectedText());
                    TApplication.app.mSelectXZQHDM = xzqh1.getXZQHDM();
                    break;
            }
        }
    };

    private void setListener() {
        mMvProvince.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, XZQH xzqh) {
                Message msg = new Message();
                msg.what = 0;
                Bundle bundle = new Bundle();
                bundle.putSerializable("XZQH", xzqh);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }

            @Override
            public void selecting(int id, XZQH xzqh) {

            }
        });
        mMvCity.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, XZQH xzqh) {
                Message msg = new Message();
                msg.what = 1;
                Bundle bundle = new Bundle();
                bundle.putSerializable("XZQH", xzqh);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }

            @Override
            public void selecting(int id, XZQH xzqh) {

            }
        });
        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayTaxesActivity.this, CaptureActivity.class));
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
