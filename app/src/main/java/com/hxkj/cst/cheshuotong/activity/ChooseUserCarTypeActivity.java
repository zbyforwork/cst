package com.hxkj.cst.cheshuotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hxkj.cst.cheshuotong.MainActivity;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.ChooseUserCarTypeAdapter;
import com.hxkj.cst.cheshuotong.bean.CLXX;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseUserCarTypeActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.recycleview_carType)
    UltimateRecyclerView mRecyclerviewCarType;

    private List<CLXX> mSourceDateList;
    /**
     * 前一个页面
     * 0：首页订单
     * 1：扫描二维码
     */
    private int mForwardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_car_type);
        ButterKnife.bind(this);
        TApplication.app.addActivity(this);
        init();
        TApplication.app.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TApplication.app.mUserId == null) {
            MyToast.showShortMessage(ChooseUserCarTypeActivity.this, "请先登录再进行添加缴税操作");
            showLoginAlert();
            return;
        } else {
            if (mForwardType == 0) {
                getDataByDDID();
            } else if (mForwardType == 1) {
                getData();
            }
        }
    }

    private void showLoginAlert() {
        /*new AlertView("你好", "请先登录再进行操作", null, new String[]{"取消","确定"}, null, ChooseUserCarTypeActivity.this, AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position==0)  {
                    startActivity(new Intent(ChooseUserCarTypeActivity.this,MainActivity.class));
                }
                if (position==1){
                    startActivity(new Intent(ChooseUserCarTypeActivity.this,AccountloginActivity.class));
                }
            }
        }).show();*/
        new MaterialDialog.Builder(this)
                .title("你好")
                .content("请先登录再进行操作")
                .negativeText("取消")
                .positiveText("确定")
                .titleGravity(GravityEnum.CENTER)
                .contentGravity(GravityEnum.CENTER)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        startActivity(new Intent(ChooseUserCarTypeActivity.this, AccountloginActivity.class));
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        startActivity(new Intent(ChooseUserCarTypeActivity.this, MainActivity.class));
                    }
                })
                .show();
    }

    private void init() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mForwardType = getIntent().getIntExtra("Type", -1);
    }

    /**
     * 得到车辆信息
     */
    private void getData() {
        String url = ConstKey.GET_CLLBXX_ADDRESS + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("CLJSDD", TApplication.app.mSelectXZQHDM);
        map.put("HGZMX", TApplication.app.mHGZMX);
        map.put("SBBXX", TApplication.app.mSBBXX);
        map.put("YHID", TApplication.app.mUserId);
        MyLog.i(map.toString());
        MyLog.i(ParamsBuilder.hashMapToJson(map));
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        MyLog.i(content);
        map.clear();
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                String retContent = ParseReturnUtil.parseRetrun(response.get(), ChooseUserCarTypeActivity.this);
                if (null == retContent) {
                    return;
                }
                MyLog.i("解码返回数据：" + retContent);
                try {
                    JSONObject object = new JSONObject(retContent);
                    JSONArray jsonArray = object.getJSONArray("CLLXLB");
                    TApplication.app.mCLJSSBID = object.getString("JSSBID");
                    parseCLXX(jsonArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    /**
     * 得到车辆信息
     */
    private void getDataByDDID() {
        final String orderID = getIntent().getStringExtra("orderId");
        MyLog.i("getOrderDetailData -->orderId  " + orderID);
        String url = ConstKey.GRJSDD_DETAIL_ADDRESS + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url,RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("DDID", orderID);
        MyLog.i(map.toString());
        MyLog.i(ParamsBuilder.hashMapToJson(map));
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        MyLog.i(content);
        map.clear();
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i("AASDASADA-->"+response);
                String retContent = ParseReturnUtil.parseRetrun(response.get(), ChooseUserCarTypeActivity.this);
                if (null == retContent) {
                    return;
                }
                MyLog.i("解码返回数据：" + retContent);
                try {
                    JSONObject object = new JSONObject(retContent);
                    JSONArray jsonArray = object.getJSONArray("CLLXLB");
                    TApplication.app.mSelectXZQHDM = object.getString("CLJSDD");
                    TApplication.app.mCLJSSBID = object.getString("JSSBID");
                    MyLog.i("TApplication.app.mSelectXZQHDM ----" + TApplication.app.mSelectXZQHDM);
                    MyLog.i(" TApplication.app.mCLJSSBID ----" + TApplication.app.mCLJSSBID);
                    parseCLXX(jsonArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    private void parseCLXX(JSONArray jsonArray) throws JSONException {
        mSourceDateList = new ArrayList<>();
        CLXX clxx;
        for (int i = 0; i < jsonArray.length(); i++) {
            clxx = new CLXX();
            JSONObject obj = jsonArray.getJSONObject(i);
            clxx.setXLMC(obj.getString("CLMC"));
            clxx.setCLTPDZ(obj.getString("CLTPDZ"));
            clxx.setID(obj.getString("CLLBID"));
            mSourceDateList.add(clxx);
        }
        MyLog.i(mSourceDateList.toString());
        MyLog.i(mSourceDateList.get(mSourceDateList.size() - 1).toString());
        showCLXX();
    }

    private void showCLXX() {
        LinearLayoutManager lm = new LinearLayoutManager(this);
        ChooseUserCarTypeAdapter adpater = new ChooseUserCarTypeAdapter(this, mSourceDateList);
        mRecyclerviewCarType.setLayoutManager(lm);
        mRecyclerviewCarType.setAdapter(adpater);
    }

}
