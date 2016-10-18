package com.hxkj.cst.cheshuotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.TApplication;
import com.hxkj.cst.cheshuotong.adapter.CarInfoAdapter;
import com.hxkj.cst.cheshuotong.adapter.OrderAdapter;
import com.hxkj.cst.cheshuotong.bean.CarInfoSimple;
import com.hxkj.cst.cheshuotong.utils.Base64;
import com.hxkj.cst.cheshuotong.utils.ConstKey;
import com.hxkj.cst.cheshuotong.utils.GsonTools;
import com.hxkj.cst.cheshuotong.utils.MyLog;
import com.hxkj.cst.cheshuotong.utils.MyToast;
import com.hxkj.cst.cheshuotong.utils.ParamsBuilder;
import com.hxkj.cst.cheshuotong.utils.ParseReturnUtil;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CarManageActivitty extends Activity {

    /**
     * 返回
     */
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.list_car_info)
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLog.i(response);
                        String retContent = ParseReturnUtil.parseRetrun(response, CarManageActivitty.this);
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
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("YHID", TApplication.app.mUserId);
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

    private void setListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
