package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.CarInfoAdapter;
import com.hxkj.cst.cheshuotong.bean.CarInfoSimple;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.nohttp.CallServer;
import com.nohttp.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarManageActivitty extends Activity {

    /**
     * 返回
     */
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.list_car_info)
    UltimateRecyclerView listCarInfo;
    ArrayList<CarInfoSimple> mCarInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_manage_activitty);
        ButterKnife.bind(this);
        try {

            ButterKnife.bind(this);
            init();
            setListener();
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void init() {
        listCarInfo.enableDefaultSwipeRefresh(true);
        listCarInfo.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!TextUtils.isEmpty(TApplication.app.mUserId)) {
                    getData();
                }
            }
        });
    }

    private void getData() {
        String url = ConstKey.GET_CAR_LIST_ADDRESS + ParamsBuilder.getParams();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        HashMap<String, String> map = new HashMap<>();
        map.put("YHID", TApplication.app.mUserId);
        String content = Base64.encodeToString(ParamsBuilder.hashMapToJson(map).getBytes(), Base64.DEFAULT);
        map.clear();
        MyLog.i(content);
        map.put("content", content);
        request.add(map);
        CallServer.getRequestInstance().add(this, 0, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                MyLog.i(response.get());
                String retContent = ParseReturnUtil.parseRetrun(response.get(), CarManageActivitty.this);
                MyLog.i(retContent);
                if (TextUtils.isEmpty(retContent)){
                    return;
                }
                mCarInfoList= (ArrayList<CarInfoSimple>) GsonTools.parserJsonToArrayBeans(retContent,"CARS",CarInfoSimple.class);
                LinearLayoutManager lm=new LinearLayoutManager(CarManageActivitty.this);
                CarInfoAdapter adpater = new CarInfoAdapter(CarManageActivitty.this, mCarInfoList);
                listCarInfo .setLayoutManager(lm);
                listCarInfo.setAdapter(adpater);
                listCarInfo.setRefreshing(false);
            }

            @Override
            public void onFailed(int what, String url, Object tag, String error, int resCode, long ms) {

            }
        },false,false);
    }

    private void setListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
